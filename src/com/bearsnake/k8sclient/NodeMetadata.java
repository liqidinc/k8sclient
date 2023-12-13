// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NodeMetadata extends GenericMetadata {

    public Map<String, String> annotations;
    public Map<String, String> labels;

    public NodeMetadata() {}

    public NodeMetadata(
        String name,
        String uid,
        String resourceVersion,
        String creationTimestamp,
        Collection<ManagedField> managedFields,
        Map<String, String> annotations,
        Map<String, String> labels
    ) {
        super(name, uid, resourceVersion, creationTimestamp, managedFields);
        this.annotations = annotations;
        this.labels = labels;
    }

    public NodeMetadata setAnnotations(final Map<String, String> map) { annotations = new HashMap<>(map); return this; }
    public NodeMetadata setLabels(final Map<String, String> map) { labels = new HashMap<>(map); return this; }
    @Override public NodeMetadata setName(final String value) { name = value; return this; }
    @Override public NodeMetadata setUid(final String value) { uid = value; return this; }
    @Override public NodeMetadata setResourceVersion(final String value) { resourceVersion = value; return this; }
    @Override public NodeMetadata setCreationTimestamp(final String value) { creationTimestamp = value; return this; }
    @Override public NodeMetadata setManagedFields(final Collection<ManagedField> list) { managedFields = new LinkedList<>(list); return this; }

    public void clean() {
        super.clean();
        annotations = null;
        labels = null;
    }

    @Override
    public String toString() {
        var str = super.toString();
        str = str.substring(0, str.length() - 1);
        str += ", annotations=" + annotations;
        str += ", labels=" + labels;
        str += "}";
        return str;
    }
}
