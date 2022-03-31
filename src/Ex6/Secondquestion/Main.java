package Ex6.Secondquestion;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author 15328
 */
public class Main {
    public static void main(String[] args) throws IOException {
        try(ServerSocket s = new ServerSocket(8189)){

            try(Socket incoming = s.accept()){
                InputStream inputStream = incoming.getInputStream();
                OutputStream outputStream = incoming.getOutputStream();

                Scanner in = new Scanner(inputStream,"UTF-8");
                PrintWriter out = new PrintWriter(outputStream,true);

                System.out.println("incoming.Localport:  "+incoming.getLocalPort() + " incoming.Address: " + incoming.getInetAddress());
                out.println("s.port:  "+s.getLocalPort() + " s.Address: " + s.getInetAddress());
                String str = "Verifying Server!";
                out.println("Consume: " + str);
                System.out.println("Server： " + str);
                int count = 0;
                while(count < 3) {
                    out.println("Input Password:");
                    String line = in.nextLine();
                    System.out.println("the test_password :" + line);
                    if(Objects.equals(line,"195779")){
                        String s3 = "Registration Successful!";
                        System.out.println("Server： " + s3);
                        out.println("Consumer:  "+ s3);
                        break;
                    }
                    else{
                        String s2 = "PassWord Wrong!";
                        System.out.println("Server： "+ s2);
                        out.println("Consumer:  " + s2 );
                    }
                    count++;
                }
                if(count >= 3){
                    String s1  = "Illegal User!";
                    System.out.println("Server： " + s1);
                    out.println("Consumer:  "+ s1);
                    incoming.close();
                    s.close();
                }


            }
        }
    }
}
