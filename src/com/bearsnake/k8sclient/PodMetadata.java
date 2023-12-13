// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PodMetadata extends NamespacedMetadata {

    public Map<String, String> annotations;
    public String generateName;
    public Map<String, String> labels;
    public List<OwnerReference> ownerReferences;

    public PodMetadata() {}

    public PodMetadata(
        final String namespace,
        final String name,
        final String uid,
        final String resourceVersion,
        final String creationTimestamp,
        final Collection<ManagedField> managedFields,
        final String generateName,
        final Map<String, String> annotations,
        final Map<String, String> labels,
        final Collection<OwnerReference> ownerReferences
    ) {
        super(namespace, name, uid, resourceVersion, creationTimestamp, managedFields);
        this.generateName = generateName;
        this.annotations = new HashMap<>(annotations);
        this.labels = new HashMap<>(labels);
        this.ownerReferences = new LinkedList<>(ownerReferences);
    }

    public PodMetadata setAnnotations(final Map<String, String> map) { annotations = new HashMap<>(map); return this; }
    public PodMetadata setGenerateName(final String value) { generateName = value; return this; }
    public PodMetadata setLabels(final Map<String, String> map) { labels = new HashMap<>(map); return this; }
    public PodMetadata setOwnerReferences(final Collection<OwnerReference> list) { ownerReferences = new LinkedList<>(list); return this; }
    @Override public PodMetadata setName(final String value) { name = value; return this; }
    @Override public PodMetadata setUid(final String value) { uid = value; return this; }
    @Override public PodMetadata setResourceVersion(final String value) { resourceVersion = value; return this; }
    @Override public PodMetadata setCreationTimestamp(final String value) { creationTimestamp = value; return this; }
    @Override public PodMetadata setManagedFields(final Collection<ManagedField> list) { managedFields = new LinkedList<>(list); return this; }

    public void clean() {
        super.clean();
        generateName = null;
        annotations = null;
        labels = null;
        ownerReferences = null;
    }

    @Override
    public String toString() {
        var str = super.toString();
        str = str.substring(0, str.length() - 1);
        str += ", annotations=" + annotations;
        str += ", labels=" + labels;
        str += ", generateName=" + generateName + "\"";
        str += ", ownerReferences=" + ownerReferences + "}";
        return str;
    }
}
