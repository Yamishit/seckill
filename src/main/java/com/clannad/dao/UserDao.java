package com.clannad.dao;

import com.clannad.dto.UserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * Created by F_ck on 2016/11/16.
 */
public interface UserDao {

    /**
     * 创建用户
     */
    void creteUser(UserInfo userInfo);

}
