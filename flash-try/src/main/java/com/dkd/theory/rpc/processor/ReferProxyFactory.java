package com.dkd.theory.rpc.processor;

import com.dkd.theory.rpc.protocol.ProtocolModel;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author dkd
 */
public class ReferProxyFactory {
    private InvokeProcessor invokeProcessor;

    public ReferProxyFactory(InvokeProcessor invokeProcessor) {
        this.invokeProcessor = invokeProcessor;
    }

    private ConcurrentHashMap<String, Object> refers = new ConcurrentHashMap<>();

    public <T> void refer(Class<T> clazz) {
        T proxy = (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{clazz}, new ReferInvocationHandler(clazz));
        refers.put(clazz.getName(), proxy);
    }

    public class ReferInvocationHandler implements InvocationHandler {

        private Class clazz;

        public ReferInvocationHandler(Class clazz) {
            this.clazz = clazz;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            ProtocolModel model = new ProtocolModel();
            model.setClazz(clazz.getName())
                    .setArgs(args)
                    .setArgTypes(model.getArgTypes())
                    .setMethod(method.getName());
            return invokeProcessor.invoke(model);
        }
    }
}
