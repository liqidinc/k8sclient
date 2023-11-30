// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PodStatus {

    public String phase;
    public List<PodStatusCondition> conditions;
    public String hostIP;
    public String podIP;
    public List<Map<String, String>> podIPs;

    public PodStatus() {
    }
}
