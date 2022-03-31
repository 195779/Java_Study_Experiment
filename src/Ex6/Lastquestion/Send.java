package Ex6.Lastquestion;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author 15328
 */
public class Send implements Runnable{
    static Socket socket = null;
    static Scanner input = new Scanner(System.in);

    public Send(Socket socket){
        Send.socket = socket;
    }

    @Override
    public void run(){
        while(true){
            String message = input.nextLine();
            try {
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                out.println(message);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
