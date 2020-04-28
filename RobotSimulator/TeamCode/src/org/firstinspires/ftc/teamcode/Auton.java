package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name = "auton")
public class Auton extends LinearOpMode {
    DcMotor motor = new DcMotor();

    @Override
    public void init() {
        motor = hardwareMap.dcMotor.get("DcMotor");
    }

    @Override
    public void run() {
        motor.setPower(0.5);
        telemetry.addData("Caption","hello");
    }
}
