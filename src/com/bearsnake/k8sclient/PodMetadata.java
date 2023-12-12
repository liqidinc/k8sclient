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
public class PodMetadata {

    public String name;
    public String generateName;
    public String namespace;
    public String uid;
    public String resourceVersion;
    public String creationTimestamp;
    public Map<String, String> labels = new HashMap<>();
    public Map<String, String> annotations = new HashMap<>();
    public List<OwnerReference> ownerReferences = new LinkedList<>();
    public List<ManagedField> managedFields = new LinkedList<>();

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{name=\"").append(name)
          .append("\", generateName=\"").append(generateName)
          .append("\", namespace=\"").append(namespace)
          .append("\", uid=\"").append(uid)
          .append("\", resourceVersion=\"").append(resourceVersion)
          .append("\", creationTimestamp=\"").append(creationTimestamp)
          .append("\", labels=").append(labels)
          .append(", annotations=").append(annotations)
          .append(", ownerReferences=").append(ownerReferences)
          .append(", managedFields=").append(managedFields)
          .append("}");
        return sb.toString();
    }
}
