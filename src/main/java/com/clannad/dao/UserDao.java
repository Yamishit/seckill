package com.clannad.dao;

import com.clannad.bean.UserBean;
import com.clannad.dto.UserInfo;

/**
 * Created by F_ck on 2016/11/16.
 */
public interface UserDao {

    /**
     * 创建用户
     */
    String creteUser(UserBean userBean);

}
