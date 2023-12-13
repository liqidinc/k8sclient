// Kubernetes Client Library project
// Copyright © 2023 by Kurt Duncan, BearSnake LLC
// All Rights Reserved

package com.bearsnake.k8sclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Collection;
import java.util.LinkedList;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NamespacedMetadata extends GenericMetadata {

    public String namespace;

    public NamespacedMetadata() {}

    public NamespacedMetadata(
        final String namespace,
        final String name,
        final String uid,
        final String resourceVersion,
        final String creationTimestamp,
        final Collection<ManagedField> managedFields
    ) {
        super(name, uid, resourceVersion, creationTimestamp, managedFields);
        this.namespace = namespace;
    }

    public NamespacedMetadata setNamespace(final String value) { namespace = value; return this; }
    @Override public NamespacedMetadata setName(final String value) { name = value; return this; }
    @Override public NamespacedMetadata setUid(final String value) { uid = value; return this; }
    @Override public NamespacedMetadata setResourceVersion(final String value) { resourceVersion = value; return this; }
    @Override public NamespacedMetadata setCreationTimestamp(final String value) { creationTimestamp = value; return this; }
    @Override public NamespacedMetadata setManagedFields(final Collection<ManagedField> list) { managedFields = new LinkedList<>(list); return this; }

    public void clean() {
        super.clean();
        namespace = null;
    }

    @Override
    public String toString() {
        var str = super.toString();
        str = str.substring(0, str.length() - 1);
        str += ", namespace=\"" + namespace + "\"" + "}";
        return str;
    }
}
