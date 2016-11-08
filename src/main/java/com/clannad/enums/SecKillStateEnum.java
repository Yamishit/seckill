package com.clannad.enums;

/**
 * 使用枚举表述常量数据字典
 * Created by F_ck on 2016/11/8.
 */
public enum SecKillStateEnum {
    SUCCESS(1,"秒杀成功"),
    END(0,"秒杀结束"),
    REPEAT_KILL(-1,"重复秒杀"),
    INNER_ERROR(-2,"系统异常"),
    DATA_REWRITE(-3,"数据篡改");
    private int state;
    private String stateInfo;

    SecKillStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static SecKillStateEnum stateOf(int index){
        for(SecKillStateEnum state : values()){
            if(state.getState() == index){
                return state;
            }
        }
        return null;
    }



}
