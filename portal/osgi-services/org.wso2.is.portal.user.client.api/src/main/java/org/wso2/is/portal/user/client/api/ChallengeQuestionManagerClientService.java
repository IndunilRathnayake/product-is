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

package org.wso2.is.portal.user.client.api;

import org.wso2.carbon.identity.mgt.exception.IdentityStoreException;
import org.wso2.carbon.identity.mgt.exception.UserNotFoundException;
import org.wso2.carbon.identity.recovery.IdentityRecoveryException;
import org.wso2.carbon.identity.recovery.model.ChallengeQuestion;
import org.wso2.carbon.identity.recovery.model.UserChallengeAnswer;

import java.util.List;

/**
 * Service with operations related to challenge question management.
 */
public interface ChallengeQuestionManagerClientService {

    List<ChallengeQuestion> getChallengeQuestionList() throws IdentityRecoveryException;

    List<ChallengeQuestion> getAllChallengeQuestionsForUser(String userUniqueId)
            throws IdentityStoreException, UserNotFoundException, IdentityRecoveryException;

    void setChallengeQuestionForUser(String userUniqueId, String questionId, String questionSetId, String answer)
            throws IdentityStoreException, UserNotFoundException, IdentityRecoveryException;

    void deleteChallengeQuestionForUser(String userUniqueId, String questionId)
            throws IdentityRecoveryException, IdentityStoreException, UserNotFoundException;

    UserChallengeAnswer[] getChallengeAnswersOfUser(String userUniqueId) throws IdentityRecoveryException,
            IdentityStoreException, UserNotFoundException;
}
