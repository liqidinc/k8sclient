// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NodePayload extends TypedEntity {

    public static String API_VERSION = "v1";
    public static String KIND = "Node";

    public NodeMetadata metadata;
    public NodeSpec spec;
    public NodeStatus status;

    public NodePayload() {
        super(API_VERSION, KIND);
    }

    public NodePayload(
        final NodeMetadata metadata,
        final NodeSpec spec,
        final NodeStatus status
    ) {
        super(API_VERSION, KIND);
        this.metadata = metadata;
        this.spec = spec;
        this.status = status;
    }

    public void clean() {
        super.clean();
        metadata = null;
        spec = null;
        status = null;
    }

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
