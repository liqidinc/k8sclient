// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NodeEntity {

    public NodeMetadata metadata = new NodeMetadata();
    public NodeSpec spec = new NodeSpec();
    public NodeStatus status = new NodeStatus();

    public NodeEntity() {}

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("metadata=").append(metadata);
        sb.append(", spec=").append(spec);
        sb.append(", status=").append(status);
        sb.append("}");
        return sb.toString();
    }
}
