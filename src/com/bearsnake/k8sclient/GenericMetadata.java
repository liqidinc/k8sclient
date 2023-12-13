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
public abstract class GenericMetadata {

    public String creationTimestamp;
    public List<ManagedField> managedFields;
    public String name;
    public String resourceVersion;
    public String uid;

    protected GenericMetadata() {}

    protected GenericMetadata(
        final String name,
        final String uid,
        final String resourceVersion,
        final String creationTimestamp,
        final Collection<ManagedField> managedFields
    ) {
        this.name = name;
        this.uid = uid;
        this.resourceVersion = resourceVersion;
        this.creationTimestamp = creationTimestamp;
        this.managedFields = new LinkedList<>(managedFields);
    }

    public void clean() {
        creationTimestamp = null;
        managedFields = null;
        name = null;
        resourceVersion = null;
        uid = null;
    }

    @Override
    public String toString() {
        var str = "{creationTimeStamp=\"" + creationTimestamp + "\"";
        str += ", managedFields=" + managedFields;
        str += ", name=\"" + name + "\"";
        str += ", resourceVersion=\"" + resourceVersion + "\"";
        str += ", uid=\"" + uid + "\"";
        str += "}";
        return str;
    }
}
