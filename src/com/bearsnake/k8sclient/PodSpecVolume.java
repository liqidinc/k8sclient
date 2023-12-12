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

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{name=\"").append(name).append("\"}");
        return sb.toString();
    }
}
