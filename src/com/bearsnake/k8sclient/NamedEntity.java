// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

/**
 * Any class which has a name - anything with metadata will implement this
 */
public interface NamedEntity {

    String getName();
}
