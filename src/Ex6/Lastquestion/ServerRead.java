package Ex6.Lastquestion;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author 15328
 * 接收客户端消息，发送给服务端和所有客户端
 */
public class ServerRead implements Runnable{
    private Socket socket = null;
    static ArrayList<Socket> socketArrayList = new ArrayList<>();

    public ServerRead(Socket socket) {
        this.socket = socket;
        socketArrayList.add(this.socket);
    }

    @Override
    public void run() {
        while(true) {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message = socket.getPort() + ": " + in.readLine();
                /*发给服务端**/
                System.out.println(message);

                for(Socket socket : socketArrayList) {
                    /*发给每个客户端**/
                    PrintWriter out = new PrintWriter(socket.getOutputStream());
                    out.println(message);
                    out.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
