package Ex6.Secondquestion;

import java.io.*;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class SocketOf{
    public static void main(String[] args) throws IOException {


        Socket socket = new Socket("localhost",8189);
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        PrintWriter out = new PrintWriter(outputStream,true);


        String x = in.readLine();
        Scanner input = new Scanner(System.in);
        if(Objects.equals(x, "Verifying Server!")) {
            System.out.println("has received the first message : " + x);
            while(true) {

                //输入口令
                System.out.println("Password:");
                x = input.nextLine();
                out.println(x);

                //接收反馈
                x = in.readLine();
                if(Objects.equals(x, "Password Wrong!")){
                    System.out.println("has received ：Password Wrong!");
                }
                else{
                    if(Objects.equals(x, "Password Right!  AND  Registration Successful!")){
                        System.out.println("has received : Password Right!  AND  Registration Successful!");
                        break;
                    }
                    if(Objects.equals(x, "Illegal User!")){
                        System.out.println("has received : Illegal User!");
                        break;
                    }
                }
            }
        }
        else{
            System.out.println("Server Wrong!");
        }
        in.close();
        out.close();
        socket.close();
    }
}
