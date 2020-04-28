package org.firstinspires.ftc.robotcore.external;

import simulator.UdpMessageManager;

import java.io.IOException;

public class Telemetry {

    static String readout = "";

    public void addData(String caption, Object value){
        try {
            UdpMessageManager.send("tel_" + caption + "_" + value);
            System.out.println(caption + " > " + value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
