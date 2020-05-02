package com.qualcomm.robotcore.hardware;

import com.qualcomm.hardware.bosch.BNO055IMU;

/**
 * Very basic class to create proper instances of hardware devices.
 * @author sanjithudupa
 */
public class HardwareMap {

     public DcMotor dcMotor = new DcMotor();

     public BNO055IMU get(Class<BNO055IMU> imu, String name){
          return new BNO055IMU();
     }

}
