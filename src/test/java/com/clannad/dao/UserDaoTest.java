package com.clannad.dao;

import com.clannad.dto.UserInfo;
import com.clannad.utils.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by F_ck on 2016/11/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class UserDaoTest {

//    @Autowired
//    UserDao userDao;
//
//    @Test
//    public void creteUser() throws Exception {
//        UserInfo userInfo = new UserInfo(new ObjectId().toString(), new Date(), new Date(), 1, new Date(), "df", "ewt", "awdsffsd", "asdg");
//        userDao.creteUser(userInfo);
//    }


}