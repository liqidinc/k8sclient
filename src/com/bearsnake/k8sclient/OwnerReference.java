// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OwnerReference {

    public String apiVersion;
    public String kind;
    public String name;
    public String uid;
    public String controller;
    public boolean blockOwnerDeletion;

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{apiVersion=\"").append(apiVersion)
          .append("\n, kind=\"").append(kind)
          .append("\", name=\"").append(name)
          .append("\", uid=\"").append(uid)
          .append("\", controller=\"").append(controller)
          .append("\", blockOwnerDeletion=").append(blockOwnerDeletion)
          .append("}");
        return sb.toString();
    }
}
