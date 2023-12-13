// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NodePayload extends TypedEntity {

    public NodeMetadata metadata = new NodeMetadata();
    public NodeSpec spec = new NodeSpec();
    public NodeStatus status = new NodeStatus();

    public NodePayload() {}

    @Override
    public String toString() {
        var str = super.toString();
        str = str.substring(0, str.length() - 1);
        str += ", metadata=" + metadata;
        str += ", spec=" + spec;
        str += ", status=" + status;
        str += "}";
        return str;
    }
}
