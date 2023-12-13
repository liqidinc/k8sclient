// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NodeStatusCondition {

    public String type;
    public String status;
    public String lastHeartbeatTime;
    public String lastTransitionTime;
    public String reason;
    public String message;

    public NodeStatusCondition() {}

    public NodeStatusCondition(
        final String type,
        final String status,
        final String lastHeartbeatTime,
        final String lastTransitionTime,
        final String reason,
        final String message
    ) {
        this.type = type;
        this.status = status;
        this.lastHeartbeatTime = lastHeartbeatTime;
        this.lastTransitionTime = lastTransitionTime;
        this.reason = reason;
        this.message = message;
    }

    public void clean() {
        type = null;
        status = null;
        lastHeartbeatTime = null;
        lastTransitionTime = null;
        reason = null;
        message = null;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{type=\"").append(type)
          .append("\", status=\"").append(status)
          .append("\", lastHeartbeatTime=\"").append(lastHeartbeatTime)
          .append("\", lastTransitionTime=\"").append(lastTransitionTime)
          .append("\", reason=\"").append(reason)
          .append("\", message=\"").append(message)
          .append("\"}");
        return sb.toString();
    }
}
