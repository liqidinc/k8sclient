// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PodMetadata {

    public String name;
    public String generateName;
    public String namespace;
    public String uid;
    public String resourceVersion;
    public String creationTimestamp;
    public Map<String, String> labels;
    public Map<String, String> annotations;
    public List<OwnerReference> ownerReferences;
    public List<ManagedField> managedFields;

    public PodMetadata() {}
}
