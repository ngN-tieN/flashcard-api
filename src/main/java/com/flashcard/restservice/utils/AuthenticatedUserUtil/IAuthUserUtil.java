package com.flashcard.restservice.utils.AuthenticatedUserUtil;

import com.flashcard.restservice.domain.entities.User;

public interface IAuthUserUtil {
    String getCurrentUsername();
    User getCurrentUser();
}
