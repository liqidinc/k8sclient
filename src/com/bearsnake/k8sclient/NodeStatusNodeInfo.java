// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{machineId=\"").append(machineID)
          .append("\", systemUUID=\"").append(systemUUID)
          .append("\", bootID=\"").append(bootID)
          .append("\", kernelVersion=\"").append(kernelVersion)
          .append("\", osImage=\"").append(osImage)
          .append("\", containerRuntimeVersion-\"").append(containerRuntimeVersion)
          .append("\", kubeletVersion=\"").append(kubeletVersion)
          .append("\", kubeProxyVersion=\"").append(kubeProxyVersion)
          .append("\", operatingSystem=\"").append(operatingSystem)
          .append("\", architecture=\"").append(architecture)
          .append("\"}");
        return sb.toString();
    }
}
