package com.sample.helper;

import org.springframework.util.DigestUtils;

import com.sample.entity.User;

public class UserUtility {

    public String gravatarFor(User user) {
        int size = 80;
        String gravatarId = DigestUtils.md5DigestAsHex(user.getEmail().getBytes());
        String url = "https://secure.gravatar.com/avatar/" + gravatarId + "?s=" + size;
        return url;
    }

}
