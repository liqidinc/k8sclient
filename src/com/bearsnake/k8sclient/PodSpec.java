// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PodSpec {

    public List<PodSpecContainer> containers;
    public List<PodSpecVolume> volumes;
    public String restartPolicy;
    public Integer terminationGracePeriodSeconds;
    public String dnsPolicy;
    public String serviceAccountName;
    public String serviceAccount;
    public String nodeName;

    public PodSpec() {
    }
}
