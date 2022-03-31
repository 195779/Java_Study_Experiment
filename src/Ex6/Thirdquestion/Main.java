package Ex6.Thirdquestion;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author 15328
 */
public class Main {

}


/**客户端类*/


/**
 * Socket客户端<br>
 * 功能说明：信息共享<br/>
 * 模拟聊天室，实现多人聊天
 *
 * @author 大智若愚的小懂
 * @Date 2016年8月31日
 * @version 1.0
 */
 class ChatroomClient extends Socket {

    private static final String SERVER_IP = "127.0.0.1"; // 服务端IP
    private static final int SERVER_PORT = 8899; // 服务端端口
    private static final String END_MARK = "quit"; // 退出聊天室标识

    private Socket client;

    private Writer writer;

    // 发送消息输入流
    private BufferedReader in;

    /**
     * 构造函数<br/>
     * 与服务器建立连接
     * @throws Exception
     */
    public ChatroomClient() throws Exception {
        super(SERVER_IP, SERVER_PORT);
        this.client = this;
        System.out.println("Cliect[port:" + client.getLocalPort() + "] 您已进入聊天室");
    }

    /**
     * 启动监听收取消息，循环可以不停的输入消息，将消息发送出去
     * @throws Exception
     */
    public void load() throws Exception {
        this.writer = new OutputStreamWriter(this.getOutputStream(), "UTF-8");
        new Thread(new ReceiveMsgTask()).start(); // 启动监听

        while(true) {
            in = new BufferedReader(new InputStreamReader(System.in));
            String inputMsg = in.readLine();
            writer.write(inputMsg);
            writer.write("\n");
            writer.flush(); // 写完后要记得flush
        }
    }

    /**
     * 内部类：监听服务器发来的消息线程类
     */
    class ReceiveMsgTask implements Runnable {

        private BufferedReader buff;

        @Override
        public void run() {
            try {
                this.buff = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
                while (true) {
                    String result = buff.readLine();
                    if (END_MARK.equals(result)) { // 遇到退出标识时表示服务端返回确认退出
                        System.out.println("Cliect[port:" + client.getLocalPort() + "] 您已退出聊天室");
                        break;
                    } else { // 输出服务端回复的消息
                        System.out.println(result);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    buff.close();
                    // 关闭连接
                    writer.close();
                    client.close();
                    in.close();
                } catch (Exception e) {

                }
            }
        }
    }

    /**
     * 入口
     * @param args
     */
    public static void main(String[] args) {
        try {
            ChatroomClient client = new ChatroomClient(); // 启动客户端
            client.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class ChatServer extends ServerSocket {

    private static final int SERVER_PORT = 8899; // 服务端端口
    private static final String END_MARK = "quit"; // 退出聊天室标识
    private static final String VIEW_USER = "viewuser"; // 查看在线成员列表

    private static List<String> userList = new CopyOnWriteArrayList<String>();
    private static List<Task> threadList = new ArrayList<Task>(); // 服务器已启用线程集合
    private static BlockingQueue<String> msgQueue = new ArrayBlockingQueue<String>(
            20); // 存放消息的队列

    public ChatServer() throws Exception {
        super(SERVER_PORT);
    }

    /**
     * 启动向客户端发送消息的线程，使用线程处理每个客户端发来的消息
     *
     * @throws Exception
     */
    public void load() throws Exception {
        new Thread(new PushMsgTask()).start(); // 开启向客户端发送消息的线程

        while (true) {
            // server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的
            Socket socket = this.accept();
            /**
             * 我们的服务端处理客户端的连接请求是同步进行的， 每次接收到来自客户端的连接请求后，
             * 都要先跟当前的客户端通信完之后才能再处理下一个连接请求。 这在并发比较多的情况下会严重影响程序的性能，
             * 为此，我们可以把它改为如下这种异步处理与客户端通信的方式
             */
            // 每接收到一个Socket就建立一个新的线程来处理它
            new Thread(new Task(socket)).start();
        }
    }

    /**
     * 内部类：从消息队列中取消息，再发送给聊天室所有成员
     */
    class PushMsgTask implements Runnable {

        @Override
        public void run() {
            while (true) {
                String msg = null;
                try {
                    msg = msgQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (msg != null) {
                    for (Task thread : threadList) {
                        thread.sendMsg(msg);
                    }
                }
            }
        }

    }

    /**
     * 内部类：处理客户端发来的消息线程类
     */
    class Task implements Runnable {

        private Socket socket;

        private BufferedReader buff;

        private Writer writer;

        private String userName; // 成员名称

        /**
         * 构造函数<br>
         * 处理客户端的消息，加入到在线成员列表中
         *
         * @throws Exception
         */
        public Task(Socket socket) {
            this.socket = socket;
            this.userName = String.valueOf(socket.getPort());
            try {
                this.buff = new BufferedReader(new InputStreamReader(
                        socket.getInputStream(), "UTF-8"));
                this.writer = new OutputStreamWriter(socket.getOutputStream(),
                        "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
            userList.add(this.userName);
            threadList.add(this);
            pushMsg("【" + this.userName + "进入了聊天室】");
            System.out.println("Form Cliect[port:" + socket.getPort() + "] "
                    + this.userName + "进入了聊天室");
        }

        @Override
        public void run() {
            try {
                while (true) {
                    String msg = buff.readLine();

                    if (VIEW_USER.equals(msg)) { // 查看聊天室在线成员
                        sendMsg(onlineUsers());
                    } else if (END_MARK.equals(msg)) { // 遇到退出标识时就结束让客户端退出
                        sendMsg(END_MARK);
                        break;
                    } else {
                        pushMsg(String.format("%1$s说：%2$s", userName, msg));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally { // 关闭资源，聊天室移除成员
                try {
                    writer.close();
                    buff.close();
                    socket.close();
                } catch (Exception e) {

                }
                userList.remove(userName);
                threadList.remove(this);
                pushMsg("【" + userName + "退出了聊天室】");
                System.out.println("Form Cliect[port:" + socket.getPort() + "] "
                        + userName + "退出了聊天室");
            }
        }

        /**
         * 准备发送的消息存入队列
         *
         * @param msg
         */
        private void pushMsg(String msg) {
            try {
                msgQueue.put(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /**
         * 发送消息
         *
         * @param msg
         */
        private void sendMsg(String msg) {
            try {
                writer.write(msg);
                writer.write("\015\012");
                writer.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 聊天室在线成员列表
         *
         * @return
         */
        private String onlineUsers() {
            StringBuffer sbf = new StringBuffer();
            sbf.append("======== 在线成员列表(").append(userList.size())
                    .append(") ========\015\012");
            for (int i = 0; i < userList.size(); i++) {
                sbf.append("[" + userList.get(i) + "]\015\012");
            }
            sbf.append("===============================");
            return sbf.toString();
        }

    }

    /**
     * 入口
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            ChatServer server = new ChatServer(); // 启动服务端
            server.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
