package com.clannad.web;

import com.clannad.bean.UserBean;
import com.clannad.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by F_ck on 2016/11/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-web.xml",
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class UserControllerTest {
    @Autowired
    UserService userService;
    @Test
    public void register() throws Exception {
        userService.createUser(new UserBean());
    }

}