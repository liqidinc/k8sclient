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
public class PodMetadata extends NamespacedMetadata {

    public Map<String, String> annotations = new HashMap<>();
    public String generateName;
    public Map<String, String> labels = new HashMap<>();
    public List<OwnerReference> ownerReferences = new LinkedList<>();

    @Override
    public String toString() {
        var str = super.toString();
        str = str.substring(0, str.length() - 1);
        str += ", annotations=" + annotations;
        str += ", labels=" + labels;
        str += ", generateName=" + generateName + "\"";
        str += ", ownerReferences=" + ownerReferences + "}";
        return str;
    }
}
