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
public class GenericMetadata {

    public String creationTimestamp;
    public List<ManagedField> managedFields = new LinkedList<>();
    public String name;
    public String resourceVersion;
    public String uid;

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
