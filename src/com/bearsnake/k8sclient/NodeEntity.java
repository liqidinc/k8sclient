// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NodeEntity {

    public NodeMetadata metadata;
    public NodeSpec spec;
    public NodeStatus status;

    public NodeEntity() {}
}
