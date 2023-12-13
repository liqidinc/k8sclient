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
public class NodeStatus {

    public Map<String, String> capacity = new HashMap<>();
    public Map<String, String> allocatable = new HashMap<>();
    public List<NodeStatusCondition> conditions = new LinkedList<>();
    public List<NodeStatusAddress> addresses = new LinkedList<>();
    public NodeStatusNodeInfo nodeInfo = new NodeStatusNodeInfo();

    public NodeStatus() {}

    public NodeStatus(
        final NodeStatusNodeInfo nodeInfo,
        final Map<String, String> capacity,
        final Map<String, String> allocatable,
        final Collection<NodeStatusCondition> conditions,
        final Collection<NodeStatusAddress> addresses
    ) {
        this.nodeInfo = nodeInfo;
        this.capacity = new HashMap<>(capacity);
        this.allocatable = new HashMap<>(allocatable);
        this.conditions = new LinkedList<>(conditions);
        this.addresses = new LinkedList<>(addresses);
    }

    public void clean() {
        nodeInfo = null;
        capacity = null;
        allocatable = null;
        conditions = null;
        addresses = null;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{capacity=").append(capacity)
          .append(", allocatable=").append(allocatable)
          .append(", conditions").append(conditions)
          .append(", addresses=").append(addresses)
          .append(", nodeInfo=").append(nodeInfo)
          .append("}");
        return sb.toString();
    }
}
