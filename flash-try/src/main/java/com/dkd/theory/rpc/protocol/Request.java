package com.dkd.theory.rpc.protocol;

import lombok.Data;

import java.io.Serializable;

/**
 * @author dkd on 2021/1/6
 */
@Data
public class Request implements Serializable {
    private int reqId;

    private ProtocolModel data;
}
