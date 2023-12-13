// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PodSpecVolume {

    public String name;

    public PodSpecVolume() {}

    public PodSpecVolume(
        final String name
    ) {
        this.name = name;
    }

    public void clean() {
        name = null;
    }

    @Override
    public String toString() {
        return "{name=\"" + name + "\"}";
    }
}
