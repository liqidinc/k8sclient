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

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{name=\"").append(name)
          .append("\", image=\"").append(image)
          .append("\"}");
        return sb.toString();
    }
}
