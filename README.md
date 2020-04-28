# 3D FTC Java Robotics Simulator
**Currently still being built**, this project is meant to help (beginner and experienced) FTC programmers test their autonomous programs in the simplest way possible.

## How it works
This simulator has two parts, the Java side and the 3D simulator side. The simulator is an application built in [Unity3D](https://unity.com/) with C# that is able to communicate with a Java project in [IntelliJ IDEA](https://www.jetbrains.com/idea). This means that the end user (you!) wont have to deal with all the simulation code and will only have to work on your own program! To use the project you would have to copy your teamcode folder from Android Studio over into IntelliJ, run the simulation application, run the program and you're program will be able to be run!

## Other Information
For the Java project, I use [JavaFX](https://openjfx.io/) to simulate a Driver Station-type display. The 3D simulation application hosts a UDP server that the Java client sends Datagram Packets to in order to communicate. The communication works both ways which means functions like ```myMotor.getCurrentPosition()``` will work without the Java end having to recalculate anything. 
