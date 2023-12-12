// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PodStatusCondition {

    public String type;
    public String status;
    public String lastTransitionTime;

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{type=\"").append(type)
          .append("\", status=\"").append(status)
          .append("\", lastTransitionTime=\"").append(lastTransitionTime)
          .append("\"}");
        return sb.toString();
    }
}
