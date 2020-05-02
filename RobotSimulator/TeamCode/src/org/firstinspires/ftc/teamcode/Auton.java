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
        telemetry.update();

        leftFront = hardwareMap.dcMotor.get("lf");
        rightFront = hardwareMap.dcMotor.get("rf");
        leftRear = hardwareMap.dcMotor.get("lr");
        rightRear = hardwareMap.dcMotor.get("rr");

        waitForStart();

        leftFront.setPower(1);
        leftRear.setPower(1);
        rightFront.setPower(1);
        rightRear.setPower(1);

        sleep(1000);

        telemetry.addData("lf pos",leftFront.getCurrentPosition());
        telemetry.addData("rf pos",rightFront.getCurrentPosition());
        telemetry.addData("lr pos",leftRear.getCurrentPosition());
        telemetry.addData("rr pos",rightRear.getCurrentPosition());

    }

}
