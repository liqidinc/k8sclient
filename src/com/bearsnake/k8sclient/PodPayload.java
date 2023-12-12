// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PodPayload extends Payload {

    public PodMetadata metadata = new PodMetadata();
    public PodSpec spec = new PodSpec();
    public PodStatus status = new PodStatus();

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{kind=\"").append(kind)
          .append("\", apiVersion=\"").append(apiVersion).append("\"");
        sb.append(", metadata=").append(metadata)
          .append(", spec=").append(spec)
          .append(", status=").append(status)
          .append("}");
        return sb.toString();
    }
}
