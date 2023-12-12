// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.LinkedList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PodListPayload extends Payload {

    public List<PodEntity> items = new LinkedList<>();

    public PodListPayload() {}

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{kind=\"").append(kind)
          .append("\", apiVersion=\"").append(apiVersion).append("\"");

        sb.append(", items=[");
        var first = true;
        for (var item : items) {
            if (first) { sb.append(", "); }
            sb.append(item.toString());
            first = false;
        }
        sb.append("]}");
        return sb.toString();
    }
}
