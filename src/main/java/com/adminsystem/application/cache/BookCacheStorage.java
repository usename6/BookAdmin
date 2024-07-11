package com.adminsystem.application.cache;

import com.adminsystem.application.component.dto.BookInfoDTO;
import com.adminsystem.application.component.po.BookInfoPO;

public interface BookCacheStorage {
    BookInfoPO get(Integer id);
    boolean delete(Integer id);
    boolean set(Integer id, BookInfoPO bookInfoPO);
}
