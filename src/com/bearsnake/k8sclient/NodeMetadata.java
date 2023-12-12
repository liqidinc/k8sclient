// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NodeMetadata {

    public String name;
    public String uid;
    public String resourceVersion;
    public String creationTimestamp;
    public Map<String, String> labels = new HashMap<>();
    public Map<String, String> annotations = new HashMap<>();
    public List<ManagedField> managedFields = new LinkedList<>();

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{name=\"").append(name).append("\"")
          .append(", uid=\"").append(uid).append("\"")
          .append(", resourceVersion=\"").append(resourceVersion).append("\"")
          .append(", creationTimestamp=\"").append(creationTimestamp).append("\"")
          .append(", labels=").append(labels)
          .append(", annotations=").append(annotations)
          .append(", managedFields=").append(managedFields).append("}");
        return sb.toString();
    }
}
