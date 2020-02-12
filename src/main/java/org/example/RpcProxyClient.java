package org.example;

import java.lang.reflect.Proxy;

/**
 * @Author：zhengqh
 * @date 2020/2/11 22:57
 **/
public class RpcProxyClient {
    public <T> T clientProxy(final Class<T> interfaceCls,final String host,final int port ){
     return (T)Proxy.newProxyInstance(interfaceCls.getClassLoader(),
             new Class<?>[]{interfaceCls},
             new ZqhInvocationHandler(host,port));
    }
}
