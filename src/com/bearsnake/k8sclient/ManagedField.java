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
public class ManagedField {

    public String manager;
    public String operation;
    public String apiVersion;
    public String time;
    public String fieldsType;
    public Map<String, Object> fieldsV1 = new HashMap<>();

    public ManagedField() {}

    public ManagedField(
        final String manager,
        final String operation,
        final String apiVersion,
        final String time,
        final String fieldsType,
        final Map<String, Object> fieldsV1
    ) {
        this.manager = manager;
        this.operation = operation;
        this.apiVersion = apiVersion;
        this.time = time;
        this.fieldsType = fieldsType;
        this.fieldsV1 = new HashMap<>(fieldsV1);
    }

    public void clean() {
        manager = null;
        operation = null;
        apiVersion = null;
        time = null;
        fieldsType = null;
        fieldsV1 = null;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("manager=\"").append(manager).append("\"");
        sb.append(", operation=\"").append(operation).append("\"");
        sb.append(", apiVersion=\"").append(apiVersion).append("\"");
        sb.append(", time=\"").append(time).append("\"");
        sb.append(", fieldsType=\"").append(fieldsType).append("\"");
        sb.append(", fieldsV1=").append(fieldsV1);
        sb.append("}");
        return sb.toString();
    }
}
