package com.aazsoft.mvc.security.service.impl;

import com.aazsoft.mvc.security.model.CurrentUser;

public interface CurrentUserService {

    boolean canAccessUser(CurrentUser currentUser, Long userId);

}
