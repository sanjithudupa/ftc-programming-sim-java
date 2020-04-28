package com.qualcomm.robotcore.eventloop.opmode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import sun.reflect.Reflection;

/**
 * LinearOpMode class used for autonomouses and some TeleOps
 * 
 */
public abstract class LinearOpMode extends OpMode{
    private boolean hasRun = false;


    abstract public void run();

    @Override
    public void loop() {
        if(!hasRun){
            run();
        }
        hasRun = true;
        isStopped = true;
    }
}

