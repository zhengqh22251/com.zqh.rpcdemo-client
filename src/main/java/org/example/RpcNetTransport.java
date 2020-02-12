package org.example;

import com.zqh.rpcdemo.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author：zhengqh
 * @date 2020/2/11 23:17
 **/
//发起socket请求
public class RpcNetTransport {
    private String host;
    private int port;

    public RpcNetTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public  Object send(RpcRequest rpcRequest){
        Socket socket = null;
        Object result =null;
        ObjectInputStream objectInputStream= null;
        ObjectOutputStream objectOutputStream = null;

        try {
            //建立连接
            socket = new Socket(host,port);
            //输出写出数据
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();

            objectInputStream = new ObjectInputStream(socket.getInputStream());
            result = objectInputStream.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(objectInputStream!=null){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(objectOutputStream!=null){
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

       return result;
    }

}
