// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NamespacedMetadata extends GenericMetadata {

    public String namespace;

    @Override
    public String toString() {
        var str = super.toString();
        str = str.substring(0, str.length() - 1);
        str += ", namespace=\"" + namespace + "\"" + "}";
        return str;
    }
}
