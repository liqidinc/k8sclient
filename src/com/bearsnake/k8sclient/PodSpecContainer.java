// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PodSpecContainer {

    public String name;
    public String image;

    public PodSpecContainer() {}

    public PodSpecContainer(
        final String name,
        final String image
    ) {
        this.name = name;
        this.image = image;
    }

    public void clean() {
        name = null;
        image = null;
    }

    @Override
    public String toString() {
        return "{name=\"" + name + "\", image=\"" + image + "\"}";
    }
}
