package hu.petrik.weather;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) {
        ExecutorService exe = Executors.newCachedThreadPool();
        System.out.println("Server is up and running");

        try {
            ServerSocket socket = new ServerSocket(8080);

            while (true) {
                Socket sock = socket.accept();
                InetAddress client = sock.getInetAddress();
                System.out.printf("Client: %-16s %-16s", client.getHostName(), client.getHostAddress());

                Worker w = new Worker(sock);
                exe.submit(w);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
