// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PodSpec {

    public String restartPolicy;
    public Integer terminationGracePeriodSeconds;
    public String dnsPolicy;
    public String serviceAccountName;
    public String serviceAccount;
    public String nodeName;
    public List<PodSpecContainer> containers;
    public List<PodSpecVolume> volumes;

    public PodSpec() {}

    public PodSpec(
        final String restartPolicy,
        final Integer terminationGracePeriodSeconds,
        final String dnsPolicy,
        final String serviceAccountName,
        final String serviceAccount,
        final String nodeName,
        final Collection<PodSpecContainer> containers,
        final Collection<PodSpecVolume> volumes
    ) {
        this.restartPolicy = restartPolicy;
        this.terminationGracePeriodSeconds = terminationGracePeriodSeconds;
        this.dnsPolicy = dnsPolicy;
        this.serviceAccountName = serviceAccountName;
        this.serviceAccount = serviceAccount;
        this.nodeName = nodeName;
        this.containers = new LinkedList<>(containers);
        this.volumes = new LinkedList<>(volumes);
    }

    public void clean() {
        containers = null;
        volumes = null;
        restartPolicy = null;
        terminationGracePeriodSeconds = null;
        dnsPolicy = null;
        serviceAccountName = null;
        serviceAccount = null;
        nodeName = null;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{containers=").append(containers)
          .append(", volumes=").append(volumes)
          .append(", restartPolicy=\"").append(restartPolicy)
          .append("\", terminationGracePeriodSeconds=").append(terminationGracePeriodSeconds)
          .append(", dnsPolicy=\"").append(dnsPolicy)
          .append("\", serviceAccountName=\"").append(serviceAccountName)
          .append("\", serviceAccount=\"").append(serviceAccount)
          .append("\", nodeName=\"").append(nodeName)
          .append("\"}");
        return sb.toString();
    }
}
