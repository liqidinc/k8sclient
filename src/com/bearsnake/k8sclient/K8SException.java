// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

public class K8SException extends Exception {
    public K8SException(
        final String message
    ) {
        super(message);
    }
}
