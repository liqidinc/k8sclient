// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ManagedField {

    public String manager;
    public String operation;
    public String apiVersion;
    public String time;
    public String fieldsType;
    public Map<String, Object> fieldsV1;

    public ManagedField() {}
}
