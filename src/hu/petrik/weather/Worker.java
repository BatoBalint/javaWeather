package hu.petrik.weather;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.net.Socket;
import java.util.HashMap;

public class Worker implements Runnable {
    private Socket sock;
    private HashMap<String, Weather> weatherHashMap;

    public Worker(Socket s) {
        this.sock = s;
    }

    @Override
    public void run() {
        weatherHashMap = new HashMap<>();
        readData();

        try {
            DataInputStream dataIn = new DataInputStream(sock.getInputStream());
            DataOutputStream dataOut = new DataOutputStream(sock.getOutputStream());

            int menu;
            do {
                menu = dataIn.readInt();
                switch (menu) {
                    case 1: dataOut.writeUTF(listazas());
                        break;
                    case 2: dataOut.writeUTF(hasMapMeret());
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                }

            } while (menu != -1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String listazas() {
        StringBuilder s = new StringBuilder();
        for (Weather w : weatherHashMap.values()) {
            s.append(w + "\n");
        }
        return s.toString();
    }

    private String hasMapMeret() {
        return weatherHashMap.size() + "";
    }

    private void readData() {
        try {
            FileReader fr = new FileReader("weather.txt");
            BufferedReader br = new BufferedReader(fr);

            br.readLine();
            String line = br.readLine();

            while (line != null) {
                Weather w = new Weather(line);
                weatherHashMap.put(w.getCountry(), w);
                line = br.readLine();
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
