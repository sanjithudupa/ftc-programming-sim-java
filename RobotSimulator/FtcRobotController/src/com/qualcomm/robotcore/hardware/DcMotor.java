package com.qualcomm.robotcore.hardware;

import simulator.UdpMessageManager;

import java.io.IOException;

/**
 * DcMotor class
 * @author sanjithudupa
 */
public class DcMotor {
    //the name of the motor(used in configuration)

    public String motorName = null;

    /**
     * Function to get the position(in encoder counts) of the motor
     * @return The position received from the simulation
     */
    public int getCurrentPosition() {
        try {
            return Integer.parseInt(UdpMessageManager.get("<mpos>" + motorName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * A function that will set power to a motor
     * @param power the power that the motor will be set to
     */
    public void setPower(double power){
        String temp = "";
        try {
            temp = UdpMessageManager.get("<mp>" + motorName + "<v>" + power);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(temp);
    }

    /**
     * A function that will return a new DcMotor with name set. Used for hardware map.
     * @param name config name of motor
     * @return
     */
    public DcMotor get(String name){
        DcMotor motor = new DcMotor();
        motor.motorName = name;
        return motor;
    }

}
