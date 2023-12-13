// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NodeListPayload extends TypedEntity {

    public static String API_VERSION = "v1";
    public static String KIND = "Node";

    public List<Node> items = new LinkedList<>();

    public NodeListPayload() {
        super(API_VERSION, KIND);
    }

    public NodeListPayload(
        final Collection<Node> items
    ) {
        super(API_VERSION, KIND);
        this.items = new LinkedList<>(items);
    }

    public void clean() {
        super.clean();
        items = null;
    }

    @Override
    public String toString() {
        var str = super.toString();
        str = str.substring(0, str.length() - 1);
        str += ", items=" + items + "}";
        return str;
    }
}
