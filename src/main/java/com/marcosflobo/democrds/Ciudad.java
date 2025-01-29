package com.marcosflobo.democrds;

import io.kubernetes.client.common.KubernetesObject;
import io.kubernetes.client.openapi.models.V1ObjectMeta;

public class Ciudad implements KubernetesObject {

    private V1ObjectMeta metadata;
    private CiudadSpec spec;

    @Override
    public V1ObjectMeta getMetadata() {
        return metadata;
    }

    public CiudadSpec getSpec() {
        return spec;
    }

    @Override
    public String getApiVersion() {
        return "asturias.com/v1";
    }

    @Override
    public String getKind() {
        return "Ciudad";
    }
}
