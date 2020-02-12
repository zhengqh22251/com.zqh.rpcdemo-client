package org.example;


import com.zqh.rpcdemo.RpcRequest;
import jdk.internal.org.objectweb.asm.Handle;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author：zhengqh
 * @date 2020/2/11 23:01
 **/
public class ZqhInvocationHandler implements InvocationHandler {
    private String host;
    private int port;

    public ZqhInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
         // 构建将要调用的目标服务 所需要的信息
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameters(args);
        //发起socket请求
        RpcNetTransport rpcNetTransport =new RpcNetTransport(host,port);
        Object result =  rpcNetTransport.send(rpcRequest);
        return result;
    }
}
