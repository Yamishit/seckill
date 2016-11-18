package com.clannad.service.impl;

import com.clannad.bean.UserBean;
import com.clannad.dao.UserDao;
import com.clannad.dto.UserInfo;
import com.clannad.service.UserService;
import com.clannad.utils.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by F_ck on 2016/11/18.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public UserInfo createUser(UserBean userBean) {
        userBean.setUserId(new ObjectId().toString());
        String uesrId = userDao.creteUser(userBean);
        return null;
    }
}
