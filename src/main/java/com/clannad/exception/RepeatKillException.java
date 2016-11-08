package com.clannad.exception;

/**
 * 重复秒杀异常(运行期异常)
 * Created by F_ck on 2016/11/8.
 */
public class RepeatKillException extends SecKillException {
    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
