// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConfigMapPayload extends Payload {

    public ConfigMapMetadata metadata = new ConfigMapMetadata();
    public Map<String, String> data = new HashMap<>();

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{kind=\"").append(kind)
          .append("\", apiVersion=\"").append(apiVersion).append("\"");
        sb.append(", metadata=").append(metadata.toString())
          .append(", data=").append(data.toString())
          .append("}");
        return sb.toString();
    }
}
