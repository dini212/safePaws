package com.FinalProject.SafePaws.service;

import com.FinalProject.SafePaws.entity.User;
import com.FinalProject.SafePaws.utils.dto.user.request.UpdateUserDTO;

public interface UserService {

//    User existsByEmail(String email);
    User getByJWT();

    User updateUser(UpdateUserDTO request);
}
