// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NodeStatusAddress {

    public String type;
    public String address;

    public NodeStatusAddress() {}

    public NodeStatusAddress(
        final String type,
        final String address
    ) {
        this.type = type;
        this.address = address;
    }

    public void clean() {
        type = null;
        address = null;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{type=\"").append(type)
          .append("\", address=\"").append(address)
          .append("\"}");
        return sb.toString();
    }
}
