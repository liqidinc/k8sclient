// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NodeStatus {

    public Map<String, String> capacity;
    public Map<String, String> allocatable;
    public List<NodeStatusCondition> conditions;
    public List<NodeStatusAddress> addresses;
    public NodeStatusNodeInfo nodeInfo;

    public NodeStatus() {
    }
}
