package com.qualcomm.hardware.bosch;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import simulator.UdpMessageManager;

import java.io.IOException;

public class BNO055IMU {

    /**
     * Does nothing, just for similarity to sdk
     * @param parameters
     */
    public void initialize(Parameters parameters){}

    /**
     * @TODO add all other parameters(acceleration)
     */
    public static class Parameters
    {
        public AngleUnit angleUnit = AngleUnit.DEGREES;
    }

    public Orientation getAngularOrientation(AxesReference reference, AxesOrder order, AngleUnit angleUnit){
        Orientation orientation = new Orientation(reference, order, angleUnit);

        float first = 0;
        float second = 0;
        float third = 0;

        float x = 0;
        float y = 0;
        float z = 0;

        //y and z axes are switched in sim
        try {
            x = Float.parseFloat(UdpMessageManager.get("<imu>x").substring(2));
            y = Float.parseFloat(UdpMessageManager.get("<imu>z").substring(2));
            z = Float.parseFloat(UdpMessageManager.get("<imu>y").substring(2));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(order == AxesOrder.ZYX){
            //order is zyx
            first = z;
            second = y;
            third = x;
        }else if(order == AxesOrder.XYZ){
            //order is xyz
            first = x;
            second = y;
            third = z;
        }else if(order == AxesOrder.YXZ){
            //order is yxz
            first = y;
            second = x;
            third = z;
        }else if(order == AxesOrder.YZX){
            //order is yzx
            first = y;
            second = z;
            third = x;
        }

        orientation.firstAngle = (angleUnit == AngleUnit.DEGREES) ? first : toRadians(first);
        orientation.secondAngle = (angleUnit == AngleUnit.DEGREES) ? second : toRadians(second);
        orientation.thirdAngle = (angleUnit == AngleUnit.DEGREES) ? third : toRadians(third);

        return orientation;

    }

    float toRadians(float deg){
        return (float)(deg * Math.PI)/180;
    }

//    enum AngleUnit {
//        RADIANS,
//        DEGREES;
//
//    }
//
//    enum AxesOrder {
//        ZYX/*("zyx")*/,
//        XYZ/*("xyz")*/,
//        YXZ/*("yxz")*/,
//        YZX/*("yzx")*/;
//
//    }
//
//    enum AxesReference {
//        EXTRINSIC, INTRINSIC
//    }

}
