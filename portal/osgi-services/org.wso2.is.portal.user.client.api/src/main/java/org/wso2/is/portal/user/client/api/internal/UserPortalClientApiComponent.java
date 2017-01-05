/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.is.portal.user.client.api.internal;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.identity.meta.claim.mgt.service.ProfileMgtService;
import org.wso2.carbon.identity.mgt.RealmService;
import org.wso2.is.portal.user.client.api.IdentityStoreClientService;
import org.wso2.is.portal.user.client.api.IdentityStoreClientServiceImpl;
import org.wso2.is.portal.user.client.api.ProfileMgtClientService;
import org.wso2.is.portal.user.client.api.ProfileMgtClientServiceImpl;

@Component(
        name = "org.wso2.is.portal.user.client.api.internal.UserPortalClientApiComponent",
        immediate = true,
        property = {
                "componentName=wso2-identity-user-portal-api"
        }
)
public class UserPortalClientApiComponent {

    private static final Logger log = LoggerFactory.getLogger(UserPortalClientApiComponent.class);

    @Activate
    public void registerUserPortalClientApi(BundleContext bundleContext) {

        initializeClientServices(bundleContext);
        if (log.isDebugEnabled()) {
            log.debug("User portal UUF support Bundle Started.");
        }
    }

    @Deactivate
    public void unregisterUserPortalClientApi(BundleContext bundleContext) {

        if (log.isDebugEnabled()) {
            log.debug("User portal UUF support Bundle Started.");
        }
    }

    @Reference(
            name = "realmService",
            service = RealmService.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "unsetRealmService")
    protected void setRealmService(RealmService realmService) {

        UserPortalClientApiDataHolder.getInstance().setRealmService(realmService);
    }

    protected void unsetRealmService(RealmService realmService) {

        UserPortalClientApiDataHolder.getInstance().setRealmService(null);
    }

    @Reference(
            name = "profileMgtService",
            service = ProfileMgtService.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "unsetProfileMgtService")
    protected void setProfileMgtService(ProfileMgtService profileMgtService) {
        UserPortalClientApiDataHolder.getInstance().setProfileMgtService(profileMgtService);
    }

    protected void unsetProfileMgtService(ProfileMgtService profileMgtService) {
        UserPortalClientApiDataHolder.getInstance().setProfileMgtService(null);
    }

    private void initializeClientServices(BundleContext bundleContext) {

        IdentityStoreClientService identityStoreClientService = new IdentityStoreClientServiceImpl();
        bundleContext.registerService(IdentityStoreClientService.class, identityStoreClientService, null);

        ProfileMgtClientService profileMgtClientService = new ProfileMgtClientServiceImpl();
        bundleContext.registerService(ProfileMgtClientService.class, profileMgtClientService, null);
    }
}
