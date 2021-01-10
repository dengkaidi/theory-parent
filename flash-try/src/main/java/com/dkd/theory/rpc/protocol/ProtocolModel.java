package com.dkd.theory.rpc.protocol;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 客户端和服务端之间传输数据的协议，通俗来讲就是客户端和服务端之间传输数据的格式
 * @author dkd
 */
@ToString
@Data
@Accessors(chain = true)
public class ProtocolModel implements Serializable {

    private String clazz;

    private String method;

    private Class<?>[] argTypes;

    private Object[] args;
}
