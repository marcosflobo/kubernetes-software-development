package com.marcosflobo.democrds;

import io.kubernetes.client.informer.ResourceEventHandler;
import io.kubernetes.client.informer.SharedInformerFactory;
import io.kubernetes.client.util.Config;
import io.kubernetes.client.util.generic.GenericKubernetesApi;

public class CiudadWatcher {

    public static void main(String[] args) {
        try {
            var client = Config.defaultClient();
            String group = "asturias.com";
            String version = "v1";
            String plural = "ciudades";
            String namespace = "default";

            // Crear SharedInformerFactory
            SharedInformerFactory informerFactory = new SharedInformerFactory();

            // Crear GenericKubernetesApi usando los tipos personalizados
            GenericKubernetesApi<Ciudad, CiudadList> ciudadApi = new GenericKubernetesApi<>(
                    Ciudad.class,
                    CiudadList.class,
                    group,
                    version,
                    plural,
                    client
            );

            // Configurar el Shared Informer para escuchar los eventos
            informerFactory.sharedIndexInformerFor(ciudadApi, Ciudad.class, 0)
                    .addEventHandler(new ResourceEventHandler<>() {
                        @Override
                        public void onAdd(Ciudad ciudad) {
                            handleEvent("ADDED", ciudad);
                        }

                        @Override
                        public void onUpdate(Ciudad oldCiudad, Ciudad newCiudad) {
                            handleEvent("UPDATED", newCiudad);
                        }

                        @Override
                        public void onDelete(Ciudad ciudad, boolean deletedFinalStateUnknown) {
                            handleEvent("DELETED", ciudad);
                        }

                        private void handleEvent(String action, Ciudad ciudad) {
                            System.out.printf("\nEvento detectado: %s\n", action);
                            System.out.printf("Nombre: %s\n", ciudad.getSpec().getNombre());
                            System.out.printf("Población: %d\n", ciudad.getSpec().getPoblacion());
                            System.out.printf("Ye ciudad: %s\n", ciudad.getSpec().getYeciudad());
                            System.out.printf("URL Logo: %s\n", ciudad.getSpec().getUrlLogo());
                        }
                    });

            // Iniciar el SharedInformerFactory
            informerFactory.startAllRegisteredInformers();

            System.out.println("Watcher iniciado. Escuchando eventos...");
            Thread.currentThread().join(); // Mantener el programa corriendo
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

