// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OwnerReference {

    public String apiVersion;
    public String kind;
    public String name;
    public String uid;
    public String controller;
    public boolean blockOwnerDeletion;

    public OwnerReference() {}
}
