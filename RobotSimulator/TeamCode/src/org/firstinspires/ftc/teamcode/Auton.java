package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name = "auton")
public class Auton extends LinearOpMode {

    DcMotor leftFront = null;
    DcMotor rightFront = null;
    DcMotor leftRear = null;
    DcMotor rightRear = null;

    @Override
    public void runOpMode() {

        leftFront = hardwareMap.dcMotor.get("lf");
        rightFront = hardwareMap.dcMotor.get("rf");
        leftRear = hardwareMap.dcMotor.get("lr");
        rightRear = hardwareMap.dcMotor.get("rr");

        waitForStart();


        telemetry.addData("Caption", "Value");

        leftFront.setPower(1);
        rightFront.setPower(1);
        leftRear.setPower(1);
        rightRear.setPower(1);


//        telemetry.addData("",leftFront.getCurrentPosition());


        sleep(1000);

        telemetry.update();

    }

}
