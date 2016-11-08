package com.clannad.exception;

import com.clannad.dto.SecKillExecution;

/**
 * 秒杀关闭异常
 * Created by F_ck on 2016/11/8.
 */
public class SecKillCloseException extends SecKillException{

    public SecKillCloseException(String message) {
        super(message);
    }

    public SecKillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
