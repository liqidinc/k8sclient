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
public class SecretPayload extends TypedEntity implements NamedEntity, NameSpacedEntity {

    public static final String API_VERSION = "v1";
    public static final String KIND = "Secret";

    public Map<String, String> data;
    public NamespacedMetadata metadata;
    public String type;

    public SecretPayload() {
        super(API_VERSION, KIND);
    }

    public SecretPayload(
        final NamespacedMetadata metadata,
        final String type,
        final Map<String, String> data
    ) {
        super(API_VERSION, KIND);
        this.metadata = metadata;
        this.type = type;
        this.data = new HashMap<>(data);
    }

    public void clean() {
        super.clean();
        data = null;
        metadata = null;
        type = null;
    }

    @Override
    public String getName() { return metadata.name; }

    @Override
    public String getNameSpace() { return metadata.namespace; }

    @Override
    public String toString() {
        var str = super.toString();
        str = str.substring(0, str.length() - 1);
        str += ", data=" + data + ", metadata=" + metadata + ", type=\"" + type + "\"}";
        return str;
    }
}
