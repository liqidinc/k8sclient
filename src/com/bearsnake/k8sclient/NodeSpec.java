// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NodeSpec {

    public String podCIDR;
    public List<String> podCIDRs;
    public Boolean unschedulable;
    public List<Map<String, String>> taints;

    public NodeSpec() {
    }

    public NodeSpec setUnschedulable(final Boolean value) { unschedulable = value; return this; }
}
