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
public class NodeSpec {

    public String podCIDR;
    public List<String> podCIDRs;
    public Boolean unschedulable;
    public List<Map<String, String>> taints;

    public NodeSpec() {}

    public NodeSpec(
        final String podCIDR,
        final Collection<String> podCIDRs,
        final boolean unschedulable,
        final Collection<Map<String, String>> taints
    ) {
        this.podCIDR = podCIDR;
        this.podCIDRs = new LinkedList<>(podCIDRs);
        this.unschedulable = unschedulable;
        this.taints = new LinkedList<>();
        taints.forEach(map -> this.taints.add(new HashMap<>(map)));
    }

    public void clean() {
        podCIDR = null;
        podCIDRs = null;
        unschedulable = null;
        taints = null;
    }

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
