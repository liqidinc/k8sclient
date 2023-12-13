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
public class PodStatus {

    public String phase;
    public List<PodStatusCondition> conditions;
    public String hostIP;
    public String podIP;
    public List<Map<String, String>> podIPs;

    public PodStatus() {}

    public PodStatus(
        final String hostIP,
        final String podIP,
        final Collection<Map<String, String>> podIPs,
        final Collection<PodStatusCondition> conditions,
        final String phase
    ) {
        this.hostIP = hostIP;
        this.podIP = podIP;
        this.podIPs = new LinkedList<>();
        podIPs.forEach(map -> this.podIPs.add(new HashMap<>(map)));
        this.phase = phase;
    }

    public void clean() {
        phase = null;
        conditions = null;
        hostIP = null;
        podIP = null;
        podIPs = null;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{phase=\"").append(phase)
          .append("\", conditions=").append(conditions)
          .append(", hostIP=\"").append(hostIP)
          .append("\", podIP=\"").append(podIP)
          .append("\", podIPs=").append(podIPs)
          .append("}");
        return sb.toString();
    }
}
