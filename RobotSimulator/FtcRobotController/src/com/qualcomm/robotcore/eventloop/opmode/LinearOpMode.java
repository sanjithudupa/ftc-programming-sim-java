package com.qualcomm.robotcore.eventloop.opmode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import simulator.Main;
import sun.reflect.Reflection;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * LinearOpMode class used for autonomouses and some TeleOps
 * 
 */
public abstract class LinearOpMode extends OpMode{
    private boolean hasRun = false;


    @Override
    public boolean regularOpMode() {
        return false;
    }

    abstract public void runOpMode();

    @Override public void init(){
        runOpMode();
    }

    public void waitForStart(){
        while(!Main.programStarted){
            //wait
        }
    }

    @Override
    public void loop() {
//        if(!hasRun){
//            runOpMode();
//        }
        hasRun = true;
        isStopped = true;
    }

}

