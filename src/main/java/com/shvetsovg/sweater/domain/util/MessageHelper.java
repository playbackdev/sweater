package com.shvetsovg.sweater.domain.util;

import com.shvetsovg.sweater.domain.User;

public abstract class MessageHelper {
    public static String getAuthorName(User author) {
        return author != null ? author.getUsername() : "<none>";
    }
}
