package com.clannad.dao;

import com.clannad.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 配置spring和junit整合,
 * 整合是为了junit启动时加载springIOC容器
 * spring-test,junit
 * Created by F_ck on 2016/11/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    //注入Dao实现类依赖
    @Resource
    private SeckillDao seckillDao;


    @Test
    public void queryById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
        /**
         * 1000元秒杀
         Seckill{seckillId=1000, name='1000元秒杀',
         number=100, startTime=Fri Nov 11 00:00:00 CST 2016,
         endTime=Sat Nov 12 00:00:00 CST 2016,
         createTime=Thu Nov 03 15:20:37 CST 2016}
         */
    }

    @Test
    /**
     * Caused by: org.apache.ibatis.binding.BindingException:
     * Parameter 'offset' not found. Available parameters are [0, 1, param1, param2]
     */
    public void queryAll() throws Exception {
        //java没有保存形参的记录
        //queryAll(int ofset, int limit)  ->  queryAll(arg0,arg1)
        //传递多个参数的时候需要告诉mybatis参数叫什么名字
        List<Seckill> seckillList = seckillDao.queryAll(0,100);
        for(Seckill seckill:seckillList){
            System.out.println(seckill);
        }
    }

    @Test
    public void reduceNumber() throws Exception {

//        Date date = new SimpleDateFormat(
//                "2016-11-11 09:00:00").parse("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        int updateCount = seckillDao.reduceNumber(1000,date);
        System.out.println(updateCount);
    }

}