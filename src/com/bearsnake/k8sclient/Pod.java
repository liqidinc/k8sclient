// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pod implements NamedEntity {

    public PodMetadata metadata = new PodMetadata();
    public PodSpec spec = new PodSpec();
    public PodStatus status = new PodStatus();

    @Override
    public String getName() { return metadata.name; }

    @Override
    public String toString() {
        var str = "{metadata=" + metadata;
        str += ", spec=" + spec;
        str += "status=" + status;
        str += "}";
        return str;
    }
}
