package hu.petrik.weather;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket sock = new Socket("localhost", 8080);

            DataInputStream dataIn = new DataInputStream(sock.getInputStream());
            DataOutputStream dataOut = new DataOutputStream(sock.getOutputStream());

            Scanner sc = new Scanner(System.in);
            int menu;
            do {
                System.out.println("Valasszon egy opciot");
                System.out.println("1: listazas");
                System.out.println("2: elorejelzesek szama");
                System.out.println("-1: kilepes");
                menu = sc.nextInt();

                dataOut.writeInt(menu);
                dataOut.flush();

                System.out.println(dataIn.readUTF());

                System.out.println(dataIn.readUTF());
            } while (menu != -1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
