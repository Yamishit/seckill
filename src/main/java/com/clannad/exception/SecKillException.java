package com.clannad.exception;

/**
 * 秒杀相关业务异常
 * Created by F_ck on 2016/11/8.
 */
public class SecKillException extends RuntimeException{

    public SecKillException(String message) {
        super(message);
    }

    public SecKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
