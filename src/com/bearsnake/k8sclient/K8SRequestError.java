// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

public class K8SRequestError extends K8SException {
    public K8SRequestError(
        final String message
    ) {
        super(String.format("K8S Request Error:%s", message));
    }
}
