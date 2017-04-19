/*
 * Copyright 2015-2017 Canoo Engineering AG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.opendolphin.core.client;

import org.opendolphin.core.ModelStore;
import org.opendolphin.core.ModelStoreConfig;
import org.opendolphin.core.client.comm.AttributeChangeListener;

import java.util.Arrays;
import java.util.List;

/**
 * The ClientModelStore is a {@link org.opendolphin.core.ModelStore} with customized behavior appropriate to the client
 * (view) side of a Dolphin connection.  It connects the model store with the {@link ClientDolphin} via
 * an {@link AttributeChangeListener}.  It automatically notifies the server side when presentation models are added
 * or removed.
 */
public class ClientModelStore extends ModelStore<ClientAttribute, ClientPresentationModel> {

    private final ModelSynchronizer modelSynchronizer;

    protected final AttributeChangeListener attributeChangeListener;

    /**
     * Constructs a client model store with default capacities.
     * @see ModelStoreConfig
     */
    public ClientModelStore(final ModelSynchronizer modelSynchronizer) {
        super(new ModelStoreConfig());
        this.modelSynchronizer = modelSynchronizer;
        attributeChangeListener = new AttributeChangeListener(this, modelSynchronizer);
    }


    @Override
    public boolean add(ClientPresentationModel model) {
        boolean success = super.add(model);
        if (success) {
            List<ClientAttribute> attributes = model.getAttributes();
            for (ClientAttribute attribute : attributes) {
                attribute.addPropertyChangeListener(attributeChangeListener);
            }
            if (!model.isClientSideOnly()) {
                modelSynchronizer.onAdded(model);
            }
        }
        return success;
    }

    public void delete(ClientPresentationModel model) {
        delete(model, true);
    }

    public void delete(ClientPresentationModel model, boolean notify) {
        if (model == null) return;
        if (containsPresentationModel(model.getId())) {
            remove(model);
            if (!notify) return;
            if (model.isClientSideOnly()) return;
            modelSynchronizer.onDeleted(model);
        }
    }

    @Override
    public boolean remove(ClientPresentationModel model) {
        boolean success = super.remove(model);
        for (ClientAttribute attribute : model.getAttributes()) {
            attribute.removePropertyChangeListener(attributeChangeListener);
        }
        return success;
    }

    @Override
    @Deprecated
    public void registerAttribute(ClientAttribute attribute) {
        super.registerAttribute(attribute);
        attribute.addPropertyChangeListener(attributeChangeListener);
    }


    public ClientPresentationModel createModel(String id, String presentationModelType, ClientAttribute... attributes) {
        ClientPresentationModel result = new ClientPresentationModel(id, Arrays.asList(attributes));
        result.setPresentationModelType(presentationModelType);
        add(result);
        return result;
    }
}
