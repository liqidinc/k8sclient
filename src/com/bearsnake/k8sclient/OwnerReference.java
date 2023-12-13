// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OwnerReference extends TypedEntity {

    public String name;
    public String uid;
    public String controller;
    public Boolean blockOwnerDeletion;

    public OwnerReference() {}

    public OwnerReference(
        final String name,
        final String uid,
        final String controller,
        final boolean blockOwnerDeletion
    ) {
        this.name = name;
        this.uid = uid;
        this.controller = controller;
        this.blockOwnerDeletion = blockOwnerDeletion;
    }

    public void clean() {
        name = null;
        uid = null;
        controller = null;
        blockOwnerDeletion = null;
    }

    @Override
    public String toString() {
        var str = super.toString();
        str += ", name=\"" + name + "\"";
        str += ", uid=\"" + uid + "\"";
        str += ", controller=\"" + controller + "\"";
        str += ", blockOwnerDeletion=" + blockOwnerDeletion;
        str += "}";
        return str;
    }
}
