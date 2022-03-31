package Ex6.Firstquestion;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author 15328
 */
public class Main {
    public static void main(String[] args) {

    }
}
class Server{
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(8189);
        Socket socket = serverSocket.accept();

        //获取输入输出流
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter out = new PrintWriter(outputStream,true);
        //输出流，文本格式输出
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        //输入流，处理文本输入


        Scanner input = new Scanner(System.in);
        //确定连接的通信
        out.println("Server is alive !");
        String x = in.readLine();
        String y;
        boolean symbol = false;
        if(Objects.equals(x,  "SocketOfClient is alive !")){
            System.out.println(x);
            symbol = true;
            //能够互相发送消息
        }

        while(symbol) {


            System.out.print("Me: ");


            y=input.nextLine();
            out.println(y);



            if(Objects.equals(x, "bye")){
                socket.close();
            }
            if(socket.isClosed()){
                System.out.println("客户端要求关闭，已经获得服务端许可，客户端已经关闭，服务端也即将关闭");
                break;
            }


            if(Objects.equals(x, "OK")){
                System.out.println("服务端要求关闭，已经获得客户端许可，服务端和客户端都将关闭");
                break;
            }



            x = in.readLine();
            if(Objects.equals(x, "bye")) {
                System.out.println("has received: " + x);
                out.println("OK");
                //告诉客户端允许关闭
            }
            System.out.print("Client: *");
            System.out.println(x + "*");
        }
        in.close();
        out.close();
        socket.close();
        serverSocket.close();
    }
}
class SocketOf{
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 8189);

        //获取输入输出流
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter out = new PrintWriter(outputStream, true);
        //输出流，文本格式输出
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        //输入流，处理文本输入


        String x = in.readLine();
        String y = "";
        boolean symbol = false;
        Scanner input = new Scanner(System.in);


        if(Objects.equals(x, "Server is alive !")) {
            out.println("SocketOfClient is alive !");
            System.out.println(x);
            symbol = true;
        }


        while(symbol) {

            System.out.print("Me: ");

            y=input.nextLine();
            out.println(y);


            if(Objects.equals(x, "bye")) {
                break;
            }

            x = in.readLine();
            if(Objects.equals(x, "bye")) {
                System.out.println("has received: " + x);
                out.println("OK");
            }
            System.out.print("Server: *");
            System.out.println(x + "*");
            if(Objects.equals(x, "OK")) {
                break;
            }
        }
        in.close();
        out.close();
        socket.close();
    }
}
