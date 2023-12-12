// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PodStatus {

    public String phase;
    public List<PodStatusCondition> conditions = new LinkedList<>();
    public String hostIP;
    public String podIP;
    public List<Map<String, String>> podIPs = new LinkedList<>();

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
