package org.firstinspires.ftc.robotcore.external.navigation;

public class Orientation {

    public AxesReference axesReference;

    public AxesOrder axesOrder;

    public AngleUnit angleUnit;

    public float firstAngle;

    public float secondAngle;

    public float thirdAngle;

    public Orientation(AxesReference axesReference, AxesOrder axesOrder, AngleUnit angleUnit){
        this.axesReference = axesReference;
        this.axesOrder = axesOrder;
        this.angleUnit = angleUnit;
    }
}
