// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConfigMapPayload extends TypedEntity implements NamedEntity, NameSpacedEntity {

    public Map<String, String> data = new HashMap<>();
    public NamespacedMetadata metadata = new NamespacedMetadata();

    public ConfigMapPayload() {
        super("v1", "ConfigMap");
    }

    @Override
    public String getName() { return metadata.name; }

    @Override
    public String getNameSpace() { return metadata.namespace; }

    @Override
    public String toString() {
        var str = super.toString();
        str = str.substring(0, str.length() - 1);
        str += ", data=" + data + ", metadata=" + metadata + "}";
        return str;
    }
}
