package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "teleop")
public class TeleOpMode extends OpMode {

    DcMotor motor = new DcMotor();

    @Override
    public void init() {
        motor = hardwareMap.dcMotor.get("DcMotor");
    }

    @Override
    public void loop() {
        motor.setPower(0.8);
    }
}
