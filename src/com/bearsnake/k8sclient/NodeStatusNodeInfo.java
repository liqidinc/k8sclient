// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NodeStatusNodeInfo {

    public String machineID;
    public String systemUUID;
    public String bootID;
    public String kernelVersion;
    public String osImage;
    public String containerRuntimeVersion;
    public String kubeletVersion;
    public String kubeProxyVersion;
    public String operatingSystem;
    public String architecture;

    public NodeStatusNodeInfo() {
    }
}
