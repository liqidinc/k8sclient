// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.bearsnake.klog.Level;
import com.bearsnake.klog.Logger;
import com.bearsnake.klog.StdOutWriter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

public class K8SClient {

    private enum HttpBodyType {
        None,
        Json,
        OctetStream
    }

    private static final String LOGGER_NAME = "K8SClient";
    private static final String DELETE = "DELETE";
    private static final String GET = "GET";
    private static final String PATCH = "PATCH";
    private static final String POST = "POST";
    private static final String PUT = "PUT";

    private final HttpClient _httpClient;
    private final Logger _logger;
    private int _timeoutInSeconds = 60;
    private final String _urlPrefix;

    private static class PodIdentifier {

        public final String _nameSpace;
        public final String _podName;

        public PodIdentifier(
            final String nameSpace,
            final String podName
        ) {
            _nameSpace = nameSpace;
            _podName = podName;
        }
    }

    public K8SClient(
        final String proxyAddress
    ) throws IOException {
        this(proxyAddress, null);
    }

    public K8SClient(
        final String proxyAddress,
        final Logger logger
    ) throws IOException {
        var sb = new StringBuilder();
        sb.append(proxyAddress);
        if (!proxyAddress.endsWith("/")) {
            sb.append("/");
        }
        sb.append("api/v1/");
        _urlPrefix = sb.toString();
        _httpClient = HttpClient.newBuilder()
                                .version(HttpClient.Version.HTTP_1_1)
                                .followRedirects(HttpClient.Redirect.NORMAL)
                                .build();

        if (logger == null) {
            _logger = new Logger(LOGGER_NAME, Level.ERROR);
            _logger.addWriter(new StdOutWriter(Level.ERROR));
        } else {
            _logger = new Logger(LOGGER_NAME, logger);
        }
    }

    public K8SClient setTimeoutInSeconds(final int value) { _timeoutInSeconds = value; return this; }

    private static boolean isSuccessful(
        final int responseCode
    ) {
        return responseCode >= 200 && responseCode <= 299;
    }

    /**
     * Cordons the indicated node, preventing future scheduling of pods there-upon.
     * @param nodeName name of the node to be cordoned
     */
    public void cordonNode(
        final String nodeName
    ) throws K8SRequestError, K8SHTTPError, K8SJSONError {
        var fn = "cordonNode";
        _logger.trace(String.format("Entering %s nodeName=%s", fn, nodeName));
        var mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        var suffix = "nodes/" + nodeName;
        var response = send(GET, suffix, HttpBodyType.None, null, HttpBodyType.Json);
        if (!isSuccessful(response.statusCode())) {
            throw new K8SHTTPError(response.statusCode());
        }

        try {
            var payload = mapper.readValue((String) response.body(), NodePayload.class);
            if ((payload.spec.unschedulable == null) || !payload.spec.unschedulable.equals(true)) {
                // drain the node
                var spec = new NodeSpec().setUnschedulable(true);
                response = send(PATCH, suffix, HttpBodyType.Json, spec, HttpBodyType.Json);
                if (!isSuccessful(response.statusCode())) {
                    throw new K8SHTTPError(response.statusCode());
                }
            }
        } catch (JsonProcessingException ex) {
            throw new K8SJSONError(ex);
        }
    }

    /**
     * Creates a config map for a particular name within a particular namespace.
     * payload.metadata.namespace must be initialized to an existing namespace.
     * If no other namespace applies, then "default" should be used.
     * payload.metadata.name must be initialized to the name for the configmap, which must be formatted like a subdomain.
     */
    public void createConfigMap(
        final ConfigMapPayload payload
    ) throws K8SHTTPError, K8SRequestError {
        var fn = "createConfigMap";
        _logger.trace("Entering %s payload=%s", fn, payload);

        var suffix = "namespaces/" + payload.metadata.namespace + "/configmaps";
        var response = send(POST, suffix, HttpBodyType.Json, payload, HttpBodyType.Json);
        if (!isSuccessful(response.statusCode())) {
            var ex = new K8SHTTPError(response.statusCode());
            _logger.throwing(ex);
            throw ex;
        }

        _logger.trace("Exiting %s", fn);
    }

    /**
     * Creates a secret for a particular name within a particular namespace.
     * payload.metadata.namespace must be initialized to an existing namespace.
     * If no other namespace applies, then "default" should be used.
     * payload.metadata.name must be initialized to the name for the configmap, which must be formatted like a subdomain.
     */
    public void createSecret(
        final SecretPayload payload
    ) throws K8SHTTPError, K8SRequestError {
        var fn = "createSecret";
        _logger.trace("Entering %s payload=%s", fn, payload);

        var suffix = "namespaces/" + payload.metadata.namespace + "/secrets";
        var response = send(POST, suffix, HttpBodyType.Json, payload, HttpBodyType.Json);
        if (!isSuccessful(response.statusCode())) {
            var ex = new K8SHTTPError(response.statusCode());
            _logger.throwing(ex);
            throw ex;
        }

        _logger.trace("Exiting %s", fn);
    }

    /**
     * Deletes a config map for a particular name within a particular namespace.
     */
    public void deleteConfigMap(
        final String namespace,
        final String name
    ) throws K8SHTTPError, K8SRequestError {
        var fn = "deleteConfigMap";
        _logger.trace("Entering %s namespace=%s name=%s", fn, namespace, name);

        var suffix = "namespaces/" + namespace + "/configmaps/" + name;
        var response = send(DELETE, suffix, HttpBodyType.None, null, HttpBodyType.Json);
        if (!isSuccessful(response.statusCode())) {
            var ex = new K8SHTTPError(response.statusCode());
            _logger.throwing(ex);
            throw ex;
        }

        _logger.trace("Exiting %s", fn);
    }

    /**
     * Deletes a secret for a particular name within a particular namespace.
     */
    public void deleteSecret(
        final String namespace,
        final String name
    ) throws K8SHTTPError, K8SRequestError {
        var fn = "deleteSecret";
        _logger.trace("Entering %s namespace=%s name=%s", fn, namespace, name);

        var suffix = "namespaces/" + namespace + "/secrets/" + name;
        var response = send(DELETE, suffix, HttpBodyType.None, null, HttpBodyType.Json);
        if (!isSuccessful(response.statusCode())) {
            var ex = new K8SHTTPError(response.statusCode());
            _logger.throwing(ex);
            throw ex;
        }

        _logger.trace("Exiting %s", fn);
    }

    /**
     * Evicts all pods which are not within a known system namespace...
     * @param nodeName name of the node which we are evicting pods from
     * @param allPods if true, we evict all pods, including those in system namespaces.
     */
    public void evictPodsForNode(
        final String nodeName,
        final boolean allPods
    ) throws K8SHTTPError, K8SJSONError, K8SRequestError {
        var fn = "evictPodsForNode";
        _logger.trace("Entering %s nodeName=%s allPods=%s", fn, nodeName, allPods);

        var mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        var suffix = "pods?fieldSelector=spec.nodeName%3D" + nodeName + "&limit=500";
        var response = send(GET, suffix, HttpBodyType.None, null, HttpBodyType.Json);
        if (!isSuccessful(response.statusCode())) {
            throw new K8SHTTPError(response.statusCode());
        }

        // evict pods
        var deletedPods = new LinkedList<PodIdentifier>();
        try {
            var payload = mapper.readValue((String) response.body(), PodListPayload.class);
            for (var item : payload.items) {
                var metaData = item.metadata;
                var nameSpace = metaData.namespace;
                if (nameSpace.equalsIgnoreCase("kube-system")) {
                    continue;
                } else if (!allPods && nameSpace.equalsIgnoreCase("calico-system")) {
                    continue;
                }

                var podName = metaData.name;
                System.out.printf("Evicting ns=%s pod=%s", nameSpace, podName);
                suffix = String.format("namespaces/%s/pods/%s", nameSpace, podName);
                response = send(DELETE, suffix, HttpBodyType.None, null, HttpBodyType.None);
                if (!isSuccessful(response.statusCode())) {
                    throw new K8SHTTPError(response.statusCode());
                }

                deletedPods.add(new PodIdentifier(nameSpace, podName));
            }
        } catch (JsonProcessingException ex) {
            _logger.catching(ex);
            throw new K8SJSONError(ex);
        }

        // wait for the pods to die off
        var done = false;
        while (!done) {
            try {
                Thread.sleep(1000);
                done = true;
                for (var pi : deletedPods) {
                    suffix = String.format("namespaces/%s/pods/%s", pi._nameSpace, pi._podName);
                    response = send(GET, suffix, HttpBodyType.None, null, HttpBodyType.Json);
                    if (isSuccessful(response.statusCode())) {
                        done = false;
                        break;
                    }
                }
            } catch (InterruptedException ex) {
                //  nothing to do
            }
        }

        _logger.trace("Exiting %s", fn);
    }

    /**
     * Returns a config map given its namespace and name.
     */
    public ConfigMapPayload getConfigMap(
        final String namespace,
        final String name
    ) throws K8SRequestError, K8SHTTPError, K8SJSONError {
        var fn = "getConfigMap";
        _logger.trace("Entering %s namespace=%s name=%s", fn, namespace, name);

        var mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        var suffix = "namespaces/" + namespace + "/configmaps/" + name;
        var response = send(GET, suffix, HttpBodyType.None, null, HttpBodyType.Json);
        if (!isSuccessful(response.statusCode())) {
            throw new K8SHTTPError(response.statusCode());
        }

        try {
            var configMap = mapper.readValue((String) response.body(), ConfigMapPayload.class);
            _logger.trace("Exiting %s with %s", fn, configMap);
            return configMap;
        } catch (JsonProcessingException ex) {
            _logger.catching(ex);
            throw new K8SJSONError(ex);
        }
    }

    /**
     * Retrieves a Node object for a specific node, by name
     */
    public Node getNode(
        final String nodeName
    ) throws K8SRequestError, K8SHTTPError, K8SJSONError {
        var fn = "getNode";
        _logger.trace("Entering %s nodeName=%s", fn, nodeName);

        var mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        var suffix = "nodes/" + nodeName;
        var response = send(GET, suffix, HttpBodyType.None, null, HttpBodyType.Json);
        if (!isSuccessful(response.statusCode())) {
            throw new K8SHTTPError(response.statusCode());
        }

        try {
            var node = mapper.readValue((String) response.body(), Node.class);
            _logger.trace("Exiting %s with %s", fn, node);
            return node;
        } catch (JsonProcessingException ex) {
            _logger.catching(ex);
            throw new K8SJSONError(ex);
        }
    }

    /**
     * Returns a collection of NodeEntity objects representing the nodes known to K8S
     */
    public Collection<Node> getNodes() throws K8SRequestError, K8SHTTPError, K8SJSONError {
        var fn = "getNodes";
        _logger.trace("Entering %s", fn);

        var mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        var suffix = "nodes";
        var response = send(GET, suffix, HttpBodyType.None, null, HttpBodyType.Json);
        if (!isSuccessful(response.statusCode())) {
            throw new K8SHTTPError(response.statusCode());
        }

        try {
            var nodeList = mapper.readValue((String) response.body(), NodeListPayload.class);
            _logger.trace("Exiting %s with %s", fn, nodeList);
            return nodeList.items;
        } catch (JsonProcessingException ex) {
            _logger.catching(ex);
            throw new K8SJSONError(ex);
        }
    }

    /**
     * Retrieves a Pod object for a specific pod, by name
     */
    public Pod getPod(
        final String podName
    ) throws K8SRequestError, K8SHTTPError, K8SJSONError {
        var fn = "getPod";
        _logger.trace("Entering %s podName=%s", fn, podName);

        var mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        var suffix = "pods/" + podName;
        var response = send(GET, suffix, HttpBodyType.None, null, HttpBodyType.Json);
        if (!isSuccessful(response.statusCode())) {
            throw new K8SHTTPError(response.statusCode());
        }

        try {
            var pod = mapper.readValue((String) response.body(), Pod.class);
            _logger.trace("Exiting %s with %s", fn, pod);
            return pod;
        } catch (JsonProcessingException ex) {
            _logger.catching(ex);
            throw new K8SJSONError(ex);
        }
    }

    /**
     * Returns a collection of NodeEntity objects representing the nodes known to K8S
     */
    public Collection<Pod> getPods() throws K8SRequestError, K8SHTTPError, K8SJSONError {
        var fn = "getPods";
        _logger.trace("Entering %s", fn);

        var mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        var suffix = "pods";
        var response = send(GET, suffix, HttpBodyType.None, null, HttpBodyType.Json);
        if (!isSuccessful(response.statusCode())) {
            throw new K8SHTTPError(response.statusCode());
        }

        try {
            var podList = mapper.readValue((String) response.body(), PodListPayload.class);
            _logger.trace("Exiting %s with %s", fn, podList);
            return podList.items;
        } catch (JsonProcessingException ex) {
            _logger.catching(ex);
            throw new K8SJSONError(ex);
        }
    }

    /**
     * Returns a collection of NodeEntity objects representing the nodes known to K8S,
     * which are local to a particular node.
     */
    public Collection<Pod> getPodsForNode(
        final String nodeName
    ) throws K8SRequestError, K8SHTTPError, K8SJSONError {
        var fn = "getPodsForNode";
        _logger.trace("Entering %s with nodeName=%s", fn, nodeName);

        var pods = getPods();
        pods.removeIf(pod -> pod.spec.nodeName.equals(nodeName));

        _logger.trace("Exiting %s with %s", fn, pods);
        return pods;
    }

    /**
     * Returns a secret given its namespace and name.
     */
    public SecretPayload getSecret(
        final String namespace,
        final String name
    ) throws K8SRequestError, K8SHTTPError, K8SJSONError {
        var fn = "getSecret";
        _logger.trace("Entering %s namespace=%s name=%s", fn, namespace, name);

        var mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        var suffix = "namespaces/" + namespace + "/secrets/" + name;
        var response = send(GET, suffix, HttpBodyType.None, null, HttpBodyType.Json);
        if (!isSuccessful(response.statusCode())) {
            throw new K8SHTTPError(response.statusCode());
        }

        try {
            var secret = mapper.readValue((String) response.body(), SecretPayload.class);
            _logger.trace("Exiting %s", fn);
            return secret;
        } catch (JsonProcessingException ex) {
            _logger.catching(ex);
            throw new K8SJSONError(ex);
        }
    }

    /**
     * Uncordons the indicated node, preventing future scheduling of pods there-upon.
     * @param nodeName name of the node we are un-cordoning
     */
    public void uncordonNode(
        final String nodeName
    ) throws K8SRequestError, K8SHTTPError {
        var fn = "uncordonNode";
        _logger.trace("Entering %s nodeName=%s", fn, nodeName);

        var suffix = "nodes/" + nodeName;
        var spec = new NodeSpec().setUnschedulable(null);
        var response = send(PATCH, suffix, HttpBodyType.Json, spec, HttpBodyType.Json);
        if (!isSuccessful(response.statusCode())) {
            var ex = new K8SHTTPError(response.statusCode());
            _logger.throwing(ex);
            throw ex;
        }

        _logger.trace("Exiting %s", fn);
    }

    /**
     * Rewrites the annotation map for a particular node
     */
    public void updateAnnotationsForNode(
        final String nodeName,
        final Map<String, String> annotations
    ) throws K8SHTTPError, K8SRequestError {
        var fn = "updateAnnotationsForNode";
        _logger.trace("Entering %s nodeName=%s annotations=%s", fn, nodeName, annotations);

        Node node = new Node();
        node.metadata = new NodeMetadata();
        node.metadata.name = nodeName;
        node.metadata.annotations = annotations;

        var suffix = "nodes/" + nodeName;
        var response = send(PATCH, suffix, HttpBodyType.Json, node, HttpBodyType.Json);
        if (!isSuccessful(response.statusCode())) {
            throw new K8SHTTPError(response.statusCode());
        }

        _logger.trace("Exiting %s", fn);
    }

    /**
     * Updates a config map for a particular name within a particular namespace.
     * payload.metadata.namespace must be initialized to an existing namespace.
     * If no other namespace applies, then "default" should be used.
     * payload.metadata.name must be initialized to the name for the configmap, which must be formatted like a subdomain.
     */
    public void updateConfigMap(
        final ConfigMapPayload payload
    ) throws K8SHTTPError, K8SRequestError {
        var fn = "updateConfigMap";
        _logger.trace("Entering %s payload=%s", fn, payload);

        payload.kind = "ConfigMap";
        payload.apiVersion = "v1";
        var suffix = "namespaces/" + payload.metadata.namespace + "/configmaps/" + payload.metadata.name;
        var response = send(PUT, suffix, HttpBodyType.Json, payload, HttpBodyType.Json);
        if (!isSuccessful(response.statusCode())) {
            var ex = new K8SHTTPError(response.statusCode());
            _logger.throwing(ex);
            throw ex;
        }

        _logger.trace("Exiting %s", fn);
    }

    /**
     * Updates a secret for a particular name within a particular namespace.
     * payload.metadata.namespace must be initialized to an existing namespace.
     * If no other namespace applies, then "default" should be used.
     * payload.metadata.name must be initialized to the name for the configmap, which must be formatted like a subdomain.
     */
    public void updateSecret(
        final SecretPayload payload
    ) throws K8SHTTPError, K8SRequestError {
        var fn = "updateSecret";
        _logger.trace("Entering %s payload=%s", fn, payload);

        payload.kind = "Secret";
        payload.apiVersion = "v1";
        var suffix = "namespaces/" + payload.metadata.namespace + "/secrets/" + payload.metadata.name;
        var response = send(PUT, suffix, HttpBodyType.Json, payload, HttpBodyType.Json);
        if (!isSuccessful(response.statusCode())) {
            var ex = new K8SHTTPError(response.statusCode());
            _logger.throwing(ex);
            throw ex;
        }

        _logger.trace("Exiting %s", fn);
    }

    private HttpResponse<?> send(
        final String method,
        final String pathSuffix,
        final HttpBodyType requestHttpBodyType,
        final Object body,
        final HttpBodyType responseHttpBodyType
    ) throws K8SRequestError {
        var fullPath = _urlPrefix + pathSuffix;
        var builder = HttpRequest.newBuilder()
                                 .uri(URI.create(fullPath))
                                 .timeout(Duration.ofSeconds(_timeoutInSeconds));

        if (method.equalsIgnoreCase("DELETE")) {
            builder.DELETE();
        } else if (method.equalsIgnoreCase("GET")) {
            builder.GET();
        } else {
            try {
                HttpRequest.BodyPublisher publisher = null;
                switch (requestHttpBodyType) {
                    case None -> {
                        publisher = HttpRequest.BodyPublishers.noBody();
                        builder.header("Content-type", "text/plain");
                    }
                    case OctetStream -> {
                        publisher = HttpRequest.BodyPublishers.ofByteArray((byte[]) body);
                        builder.header("Content-type", "application/octet-stream");
                    }
                    case Json -> {
                        var objMapper = new ObjectMapper();
                        var bodyStr = objMapper.writeValueAsString(body);
                        publisher = HttpRequest.BodyPublishers.ofString(bodyStr);
                        if (method.equalsIgnoreCase("PATCH")) {
                            builder.header("Content-type", "application/strategic-merge-patch+json");
                        } else {
                            builder.header("Content-type", "application/json");
                        }
                    }
                }

                if (method.equalsIgnoreCase("POST")) {
                    builder.POST(publisher);
                } else if (method.equalsIgnoreCase("PUT")) {
                    builder.PUT(publisher);
                } else if (method.equalsIgnoreCase("PATCH")) {
                    builder.method("PATCH", publisher);
                }
            } catch (JsonProcessingException ex) {
                throw new K8SRequestError("JSON exception" + ex.getMessage());
            }
        }

        var httpRequest = builder.build();
        _logger.trace("===>%s:%s", method, fullPath);
        if (body != null) {
            _logger.trace("===>%s", body);
        }

        var responseHandler =
            switch (responseHttpBodyType) {
                case None -> HttpResponse.BodyHandlers.discarding();
                case OctetStream -> HttpResponse.BodyHandlers.ofByteArray();
                case Json -> HttpResponse.BodyHandlers.ofString();
            };

        try {
            var response = _httpClient.send(httpRequest, responseHandler);
            _logger.trace("<===%s", response.toString());
            if (response.body() != null) {
                _logger.trace("<===%s", response.body().toString());
            }
            return response;
        } catch (InterruptedException | IOException ex) {
            throw new K8SRequestError(ex.toString());
        }
    }
}
