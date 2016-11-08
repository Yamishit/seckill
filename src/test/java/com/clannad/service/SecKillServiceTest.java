package com.clannad.service;

import com.clannad.dto.Exposer;
import com.clannad.dto.SecKillExecution;
import com.clannad.entity.Seckill;
import com.clannad.exception.RepeatKillException;
import com.clannad.exception.SecKillCloseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by F_ck on 2016/11/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SecKillServiceTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SecKillService secKillService;

    @Test
    public void getSecKillList() throws Exception {
        List<Seckill> seckillList = secKillService.getSecKillList();
        logger.info("list={}",seckillList);
    }

    @Test
    public void getById() throws Exception {
        long id = 1000;
        Seckill seckill = secKillService.getById(id);
        logger.info("seckill={}",seckill);
    }

    @Test
    public void exportSecKillUrl() throws Exception {
        long id = 1000;
        Exposer exposer = secKillService.exportSecKillUrl(id);
        logger.info("exposer={}",exposer);
        //exposer=Exposer{
        // exposed=true,
        // md5='49983d1ccb04482a375eb9b746611f83',
        // seckillId=1000, now=0, start=0, end=0}
    }

    @Test
    public void executeSecKill() throws Exception {
        long id = 1000;
        long phone = 18072749271L;
        String md5 = "49983d1ccb04482a375eb9b746611f83";
        try{
            SecKillExecution secKillExecution = secKillService
                    .executeSecKill(id,phone,md5);
            logger.info("result:{}",secKillExecution);
        }catch (RepeatKillException e){
            logger.error(e.getMessage());
        }catch (SecKillCloseException e){
            logger.error(e.getMessage());
        }
    }

    //测试代码完整逻辑,注意可重复执行
    @Test
    public void testLogic() throws Exception{
        long id = 1001;
        long phone = 18072749271L;
        Exposer exposer = secKillService.exportSecKillUrl(id);
        if(exposer.isExposed()){
            logger.info("exposer={}",exposer);
            try{
                SecKillExecution secKillExecution = secKillService
                        .executeSecKill(id,phone,exposer.getMd5());
                logger.info("result:{}",secKillExecution);
            }catch (RepeatKillException e){
                logger.error(e.getMessage());
            }catch (SecKillCloseException e){
                logger.error(e.getMessage());
            }
        }else{
            //秒杀未开启
            logger.warn("exposer={}",exposer);
        }
    }

}