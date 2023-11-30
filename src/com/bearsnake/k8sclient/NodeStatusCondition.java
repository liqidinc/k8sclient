// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NodeStatusCondition {

    public String type;
    public String status;
    public String lastHeartbeatTime;
    public String lastTransitionTime;
    public String reason;
    public String message;

    public NodeStatusCondition() {
    }
}
