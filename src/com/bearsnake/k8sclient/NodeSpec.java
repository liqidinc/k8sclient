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
public class NodeSpec {

    public String podCIDR;
    public List<String> podCIDRs = new LinkedList<>();
    public Boolean unschedulable;
    public List<Map<String, String>> taints = new LinkedList<>();

    public NodeSpec setUnschedulable(final Boolean value) { unschedulable = value; return this; }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{podCIDR=").append(podCIDR)
          .append(", podCIDRs").append(podCIDRs)
          .append(", unschedulable=").append(unschedulable)
          .append(", taints=").append(taints)
          .append("}");
        return sb.toString();
    }
}
