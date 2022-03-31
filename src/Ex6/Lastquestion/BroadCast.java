package Ex6.Lastquestion;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author 15328
 * 服务端发送消息给所有客户端
 */
public class BroadCast implements Runnable {
    static ArrayList<Socket> socketArrayList = new ArrayList<>();
    Scanner input = new Scanner(System.in);

    public static void add(Socket socket){
        socketArrayList.add(socket);
    }

    @Override
    public void run(){
        while(true){
            String message = input.nextLine();
            StringBuffer msg = new StringBuffer(50).append("服务器：").append(message);
            for(Socket socket :socketArrayList){
                try {
                    PrintWriter out = new PrintWriter(socket.getOutputStream());
                    out.println(new String(msg));
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
