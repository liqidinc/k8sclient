// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class TypedEntity {

    public String apiVersion;
    public String kind;

    public TypedEntity() {}

    public TypedEntity(
        final String apiVersion,
        final String kind
    ) {
        this.apiVersion = apiVersion;
        this.kind = kind;
    }

    @Override
    public String toString() {
        return "{apiVersion=\"" + apiVersion + "\", kind=\"" + kind + "\"}";
    }
}
