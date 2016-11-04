package com.clannad.dao;

import com.clannad.entity.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by F_ck on 2016/11/3.
 */
public interface SeckillDao {
    /**
     * 减库存
     * @param seckillId
     * @param killTime
     * @return 如果影响行数>1,表示更新的记录行数
     */
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    /**
     * 根据Id查询秒杀对象
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);

    /**
     * 根据偏移量查询秒杀商品列表
     * @param ofset
     * @param limit
     * @return
     */
    //java没有保存形参的记录
    //queryAll(int ofset, int limit)  ->  queryAll(arg0,arg1)
    //传递多个参数的时候需要告诉mybatis参数叫什么名字
    //@param("形参名称")给形参设置名字
    List<Seckill> queryAll(@Param("offset") int ofset, @Param("limit") int limit);



}
