package com.clannad.service;

import com.clannad.bean.UserBean;
import com.clannad.dao.UserDao;
import com.clannad.dto.UserInfo;

/**
 * Created by F_ck on 2016/11/18.
 */
public interface UserService {

    /**
     * 创建用户
     */
    UserInfo createUser(UserBean userBean);

}
