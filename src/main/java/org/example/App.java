package org.example;

import com.zqh.rpcdemo.IHelloService;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ){

      RpcProxyClient rpcProxyClient =new RpcProxyClient();
      IHelloService iHelloService = rpcProxyClient.clientProxy(IHelloService.class,
              "localhost",8090);
       Object result = iHelloService.sayHello("郑求华");//会调用代理方法
       System.out.println(result);
    }
}
