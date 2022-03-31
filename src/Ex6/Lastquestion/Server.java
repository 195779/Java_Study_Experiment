package Ex6.Lastquestion;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 15328
 */
public class Server implements Runnable{
    static Socket socket = null;
    //确定唯一一个服务端端口：8189
    static ServerSocket serverSocket = null;
    static {
        try {
            serverSocket = new ServerSocket(8189);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        /**不断接受新客户端，将其添加到广播类的static类型的list，启动读取客户端并向所有客户端转发的线程*/
        new Thread(new Server()).start();
        /**对所有客户端广播服务器自己的信息*/
        new Thread(new BroadCast()).start();
    }

    @Override
    public void run() {
        System.out.println("启动8189端口，等待客户端连接：");
        int clientCount = 0;
        while(true) {
            try {
                socket = serverSocket.accept();
                System.out.println("第" + (++clientCount) + "客户: " +
                        socket.getPort() + "连接到服务器");
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(socket != null) {
                BroadCast.add(socket);

                new Thread(new ServerRead(socket)).start();
            }
        }
    }
}
