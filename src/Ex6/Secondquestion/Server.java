package Ex6.Secondquestion;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author 15328
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8189);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        PrintWriter out = new PrintWriter(outputStream,true);


        out.println("Verifying Server!");

        System.out.println("ready to receieve the password from the Client : ");
        int count = 0;
        while(true) {
            count++;
            String x = in.readLine();
            if(count > 3){
                out.println("Illegal User!");
                System.out.println("count > 3 : Illegal User!");
                break;
            }
            if(Objects.equals(x, "195779")){
                out.println("Password Right!  AND  Registration Successful!");
                System.out.println("Password Right!  AND  Registration Successful!");
                break;
            }
            else{
                out.println("Password Wrong!");
                System.out.println("Password Wrong!");
            }
        }
        in.close();
        out.close();
        socket.close();
        serverSocket.close();
    }
}

