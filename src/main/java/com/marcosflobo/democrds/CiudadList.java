package com.marcosflobo.democrds;

import io.kubernetes.client.common.KubernetesListObject;
import io.kubernetes.client.openapi.models.V1ListMeta;

import java.util.List;

class CiudadList implements KubernetesListObject {

    private V1ListMeta metadata;
    private List<Ciudad> items;

    @Override
    public V1ListMeta getMetadata() {
        return metadata;
    }

    public void setMetadata(V1ListMeta metadata) {
        this.metadata = metadata;
    }

    public List<Ciudad> getItems() {
        return items;
    }

    public void setItems(List<Ciudad> items) {
        this.items = items;
    }

    @Override
    public String getApiVersion() {
        return "";
    }

    @Override
    public String getKind() {
        return "";
    }
}
