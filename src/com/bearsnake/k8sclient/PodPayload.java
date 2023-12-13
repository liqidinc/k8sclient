// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PodPayload extends TypedEntity {

    public static final String API_VERSION = "v1";
    public static final String KIND = "PodPayload";

    public PodMetadata metadata;
    public PodSpec spec;
    public PodStatus status;

    public PodPayload() {
        super(API_VERSION, KIND);
    }

    public PodPayload(
        final PodMetadata metadata,
        final PodSpec spec,
        final PodStatus status
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
        str += ", status=" + status + "}";
        return str;
    }
}
