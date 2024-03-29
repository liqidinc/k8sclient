// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

public class K8SHTTPError extends K8SException {

    private final int _responseCode;

    public K8SHTTPError(
        final int responseCode
    ) {
        super(String.format("HTTP Status Code:%d", responseCode));
        _responseCode = responseCode;
    }

    public int getResponseCode() { return _responseCode; }
}
