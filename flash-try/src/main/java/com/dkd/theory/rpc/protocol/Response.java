package com.dkd.theory.rpc.protocol;

import lombok.Data;

import java.io.Serializable;

/**
 * @author dkd on 2021/1/6
 */
@Data
public class Response implements Serializable {
    private Object data;
    private int reqId;
    private int status;

    public static final int OK = 0;
    public static final int TIMEOUT = 1;
    public static final int EXCEPTION = 2;
}
