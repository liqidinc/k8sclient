// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Node implements NamedEntity {

    public NodeMetadata metadata = new NodeMetadata();
    public NodeSpec spec = new NodeSpec();
    public NodeStatus status = new NodeStatus();

    public Node() {}

    public Node(
        final NodeMetadata metadata,
        final NodeSpec spec,
        final NodeStatus status
    ) {
        this.metadata = metadata;
        this.spec = spec;
        this.status = status;
    }

    public void clean() {
        metadata = null;
        spec = null;
        status = null;
    }

    @Override
    public String getName() { return metadata.name; }

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
