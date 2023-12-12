// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.LinkedList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PodSpec {

    public List<PodSpecContainer> containers = new LinkedList<>();
    public List<PodSpecVolume> volumes = new LinkedList<>();
    public String restartPolicy;
    public Integer terminationGracePeriodSeconds;
    public String dnsPolicy;
    public String serviceAccountName;
    public String serviceAccount;
    public String nodeName;

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{containers=").append(containers)
          .append(", volumes=").append(volumes)
          .append(", restartPolicy=\"").append(restartPolicy)
          .append("\", terminationGracePeriodSeconds=").append(terminationGracePeriodSeconds)
          .append(", dnsPolicy=\"").append(dnsPolicy)
          .append("\", serviceAccountName=\"").append(serviceAccountName)
          .append("\", serviceAccount=\"").append(serviceAccount)
          .append("\", nodeName=\"").append(nodeName)
          .append("\"}");
        return sb.toString();
    }
}
