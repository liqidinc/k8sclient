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
public class ConfigMapMetadata {

    public String name;
    public String namespace;
    public String uid;
    public String resourceVersion;
    public String creationTimestamp;
    public List<ManagedField> managedFields = new LinkedList<>();

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{name=\"").append(name).append("\"")
          .append(", namespace=\"").append(namespace).append("\"")
          .append(", uid=\"").append(uid).append("\"")
          .append(", resourceVersion=\"").append(resourceVersion).append("\"")
          .append(", creationTimeStamp=\"").append(creationTimestamp).append("\"")
          .append(", managedFields=").append(managedFields.toString())
          .append("}");
        return sb.toString();
    }
}
