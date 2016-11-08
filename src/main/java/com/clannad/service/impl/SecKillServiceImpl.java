package com.clannad.service.impl;

import com.clannad.dao.SeckillDao;
import com.clannad.dao.SuccessKilledDao;
import com.clannad.dto.Exposer;
import com.clannad.dto.SecKillExecution;
import com.clannad.entity.Seckill;
import com.clannad.entity.SuccessKilled;
import com.clannad.enums.SecKillStateEnum;
import com.clannad.exception.RepeatKillException;
import com.clannad.exception.SecKillCloseException;
import com.clannad.exception.SecKillException;
import com.clannad.service.SecKillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by F_ck on 2016/11/8.
 */
//@Component  spring所有的组件都可以用  @Service @Dao @Controller
@Service
public class SecKillServiceImpl implements SecKillService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //注入service依赖
    @Autowired  //@Resource,@Inject
    private SeckillDao seckillDao;

    @Autowired
    private SuccessKilledDao successKilledDao;

    //md5盐值字符串,用于混淆MD5
    private final String slat = "dsafhasjkfhkka&**^^*34hkjh";

    public List<Seckill> getSecKillList() {
        return seckillDao.queryAll(0, 4);
    }

    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    public Exposer exportSecKillUrl(long seckillId) {
        Seckill seckill = seckillDao.queryById(seckillId);
        if (seckill == null)
            return new Exposer(false, seckillId);
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();
        if (nowTime.getTime() < startTime.getTime()
                || nowTime.getTime() > endTime.getTime()) {
            //还没开始或者已结束
            return new Exposer(false, seckillId, nowTime.getTime(),
                    startTime.getTime(), endTime.getTime());
        }
        //转化特定字符串的过程,不可逆
        String md5 = getMD5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    private String getMD5(long seckillId) {
        String base = seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    @Transactional
    /**
     * 事务方法
     * 使用注解控制事务方法的优点
     * 1:开发团队达成一致约定,明确标注事务方法的编程风格
     * 2:保证事务方法的执行时间尽可能短,不要穿插其他网络操作,RPC/HTTP请求或者剥离到事务方法外部
     * 3:不是所有的方法都需要事务,如只有一条修改操作,只读操作不需要事务控制
     */
    public SecKillExecution executeSecKill(long seckillId, long userPhone, String md5) throws SecKillException, RepeatKillException, SecKillCloseException {
        if (md5 == null || !md5.equals(getMD5(seckillId)))
            throw new SecKillException("seckill date rewrite");
        //执行秒杀逻辑:减库存+记录购买行为
        Date nowTime = new Date();
        try {
            //减库存
            int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
            if (updateCount <= 0) {
                //没有更新到记录
                throw new SecKillCloseException("seckill is closed");
            } else {
                //记录购买行为
                int insertCount = successKilledDao
                        .insertSuccessKilled(seckillId, userPhone);
                //唯一验证:seckillId,userPhone
                if (insertCount <= 0) {
                    //重复秒杀
                    throw new RepeatKillException("seckill repeated");
                } else {
                    //秒杀成功
                    SuccessKilled successKilled = successKilledDao
                            .queryByIdWithSeckill(seckillId, userPhone);
                    return new SecKillExecution(seckillId, SecKillStateEnum.SUCCESS, successKilled);
                }
            }
        } catch (SecKillCloseException e1) {
            throw e1;
        } catch (RepeatKillException e2) {
            throw e2;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            //所有编译期异常,转化为运行期异常   spring检测到运行时异常  会回滚
            throw new SecKillException("seckill inner error:" + e.getMessage());
        }
    }
}