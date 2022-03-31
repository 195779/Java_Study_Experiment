package Ex6.Lastquestion;

import java.io.IOException;
import java.net.Socket;


/**
 * @author 15328
 */
public class Client {
    static Socket socket = null;

    public static void main(String[] args) throws IOException {
        System.out.println("******客户端******");
        socket = new Socket("localhost",8189);
        System.out.println("连接成功，you are: " + socket.getLocalPort());
        new Thread(new Read(socket)).start();
        new Thread(new Send(socket)).start();
    }
}
