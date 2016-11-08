package com.clannad.dto;

import com.clannad.entity.SuccessKilled;
import com.clannad.enums.SecKillStateEnum;

/**
 * 封装秒杀执行后结果
 * Created by F_ck on 2016/11/8.
 */
public class SecKillExecution {

    private long seckillId;

    //执行秒杀结果
    private int state;

    //状态的标示
    private String stateInfo;

    //秒杀成功对象
    private SuccessKilled successKilled;

    public SecKillExecution(long seckillId, SecKillStateEnum stateEnum) {
        this.seckillId = seckillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    public SecKillExecution(long seckillId, SecKillStateEnum stateEnum,
                            SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.successKilled = successKilled;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }

    @Override
    public String toString() {
        return "SecKillExecution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successKilled=" + successKilled +
                '}';
    }
}
