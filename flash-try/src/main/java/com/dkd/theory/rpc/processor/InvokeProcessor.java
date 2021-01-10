package com.dkd.theory.rpc.processor;

import com.dkd.theory.rpc.protocol.ProtocolModel;

/**
 *
 * @author dkd
 */
public interface InvokeProcessor {

    /**
     * 调用定义
     * @param protocolModel
     * @return
     */
    Object invoke(ProtocolModel protocolModel);
}
