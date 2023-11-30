// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.core.JsonProcessingException;

public class K8SJSONError extends K8SException {
    public K8SJSONError(
        final JsonProcessingException ex
    ) {
        super(String.format("JSON Error:%s", ex.toString()));
    }
}
