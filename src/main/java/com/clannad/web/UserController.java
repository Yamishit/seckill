package com.clannad.web;

import com.clannad.bean.UserBean;
import com.clannad.dto.UserInfo;
import com.clannad.entity.TestEntity;
import com.clannad.entity.UserEntity;
import com.clannad.service.UserService;
import com.clannad.service.impl.UserServiceImpl;
import com.clannad.utils.RequestFormat;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by F_ck on 2016/11/16.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public UserInfo register(@RequestBody String jsonObject){
        UserBean userBean = (UserBean) RequestFormat.getInstance()
                .getRequestObj(jsonObject,new UserBean());
//        UserInfo userInfo = userService.createUser(userBean);
        return userService.createUser(userBean);
    }

}
