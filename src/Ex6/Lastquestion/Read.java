package Ex6.Lastquestion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Read implements Runnable{
    static Socket socket = null;
    public Read(Socket socket){
        Read.socket = socket;
    }
    @Override
    public void run(){
        while(true) {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println(in.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
