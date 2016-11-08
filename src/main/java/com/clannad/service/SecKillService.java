package com.clannad.service;

import com.clannad.dto.Exposer;
import com.clannad.dto.SecKillExecution;
import com.clannad.entity.Seckill;
import com.clannad.exception.RepeatKillException;
import com.clannad.exception.SecKillCloseException;
import com.clannad.exception.SecKillException;

import java.util.List;

/**
 * 业务接口,站在"使用者"的角度设计接口
 * 三个方面:方法定义粒度,参数,返回类型(return 类型要友好/异常)
 * Created by F_ck on 2016/11/8.
 */
public interface SecKillService {

    /**
     * 查询所有秒杀记录
     *
     * @return
     */
    List<Seckill> getSecKillList();

    /**
     * 查询一个秒杀记录
     */
    Seckill getById(long seckillId);

    /**
     * 秒杀"开启"时输出秒杀接口的地址,否则输出系统时间和秒杀时间
     * 两种情况  一种未开始秒杀  一种秒杀结束
     *
     * @param seckillId
     */
    Exposer exportSecKillUrl(long seckillId);

    /**
     * 执行秒杀操作
     *
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SecKillExecution executeSecKill(long seckillId, long userPhone, String md5)
        throws SecKillException,RepeatKillException,SecKillCloseException;


}
