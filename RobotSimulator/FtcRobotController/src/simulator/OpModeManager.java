package simulator;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import simulator.util.RobotProgram;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.*;

public class OpModeManager {
    public static Set<Class<?>> classesWithAnnotation_auton(String m_package){
        ArrayList<RobotProgram> programs = new ArrayList<>();
        Reflections reflections = new Reflections(m_package);
        Set<Class<?>> opModes = new HashSet<>();
        opModes.addAll(reflections.getTypesAnnotatedWith(Autonomous.class));

        return opModes;

    }

    public static Set<Class<?>> classesWithAnnotation_tele(String m_package){
        ArrayList<RobotProgram> programs = new ArrayList<>();
        Reflections reflections = new Reflections(m_package);
        Set<Class<?>> opModes = new HashSet<>();
        opModes.addAll(reflections.getTypesAnnotatedWith(TeleOp.class));

        return opModes;

    }


}
