package com.clannad.web;

import com.clannad.dto.UserInfo;
import com.clannad.entity.TestEntity;
import com.clannad.entity.UserEntity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.BeanUtils;
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

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public UserInfo register(@RequestBody String jsonObject){
        Gson gson = new Gson();
        UserEntity testEntity = gson.fromJson(jsonObject,UserEntity.class);
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(testEntity,userEntity);
        return null;
    }

}
