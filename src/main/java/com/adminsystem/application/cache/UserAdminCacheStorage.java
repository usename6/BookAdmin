package com.adminsystem.application.cache;

import com.adminsystem.application.component.po.BookAdminPO;

public interface UserAdminCacheStorage {
    BookAdminPO get(Integer id);
    boolean delete(Integer id);
    boolean set(Integer id, BookAdminPO bookAdminPO);
}
