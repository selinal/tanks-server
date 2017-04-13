package com.sbt.codeit.core.server;

import com.sbt.codeit.common.WorldMap;
import com.sbt.codeit.core.util.WorldMapManager;

import java.io.*;

/**
 * Created by sbt-selin-an on 13.04.2017.
 */
public class ClientThread implements Runnable {
    InputStream in;
    OutputStream out;
    WorldMapManager mapManager;

    public ClientThread(WorldMapManager mapManager, InputStream in, OutputStream out) {
        this.in = in;
        this.out = out;
        this.mapManager = mapManager;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            PrintWriter writer = new PrintWriter(out, true);

            for (;;) {

                StringBuffer sb = new StringBuffer();
                String line;
                while ((line = reader.readLine()) != null) {
                    //create map from input stream
                    sb.append(line);
                }
                mapManager.createMap(sb.toString());
                if(WorldMapManager.validate(map, serverMap)){
                    WorldMapManager.mergeMaps(map, serverMap)
                }
                writer.println(map);
            }

            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
