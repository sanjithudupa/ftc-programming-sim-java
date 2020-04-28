package com.qualcomm.robotcore.eventloop.opmode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class OpMode {

    public volatile boolean isStarted = false;
    public volatile boolean isStopped = false;

    protected final HardwareMap hardwareMap;
    protected final Telemetry telemetry;

//    protected final GamePad gamepad1;
//    protected final GamePad gamepad2;

    public abstract void init();

    public abstract void loop();

    public OpMode(){
        hardwareMap = new HardwareMap();
        telemetry = new Telemetry();
    }

}
