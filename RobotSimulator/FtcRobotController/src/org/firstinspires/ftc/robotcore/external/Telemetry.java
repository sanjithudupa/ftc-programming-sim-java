package org.firstinspires.ftc.robotcore.external;

import simulator.UdpMessageManager;

import java.io.IOException;

public class Telemetry {

    public void addData(String caption, Object value){
        try {
            UdpMessageManager.send("<tel>" + caption + "<v>" + value);
            System.out.println(caption + " -> " + value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(){
        try {
            UdpMessageManager.send("TEL_UP");
            System.out.println("TEL_UP");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
