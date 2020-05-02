package simulator;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.*;

/**
 * Main class. This is what shows driver station window when run
 *
 */
public class Main extends Application {

    public int width = 288;
    public int height = 480;

    public int button_size = width/2;

//    public static boolean programStarted = false;
    boolean autonSelected = true;

    OpMode program = null;

    public static ThreadHelper threadHelper;

    class ThreadHelper{
        public ThreadHelper(){

        }
    }

    /**
     * UI Element Declarations
     */
    ComboBox select_autonomous = new ComboBox();
    ComboBox select_teleop = new ComboBox();
    RadioButton auto_radio = new RadioButton("a");
    RadioButton tele_radio = new RadioButton("t");
    Button reset_button = new Button("reset robot");


    public static Button main_button = new Button("init");
    public static Text   error_text  = new Text("");

    Thread programThread;

    public static final int[] buttonPressCount = {0};

    @Override
    public void start(Stage primaryStage){
        Scene scene = new Scene(generateUI(), width, height);
        primaryStage.setTitle("FTC Driver Station");
        primaryStage.setScene(scene);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    //generate the ui
    Pane generateUI(){

        Pane root = new Pane();

        Set<Class<?>> autos = OpModeManager.classesWithAnnotation_auton("org.firstinspires.ftc.teamcode");
        ObservableList<Class<?>> autons = FXCollections.observableArrayList();
        for(Class<?> p : autos){
            //do disabled checking here
            autons.add(p);
        }
        select_autonomous.setPromptText("Select Autonomous");

        select_autonomous.setItems(autons);

        select_autonomous.setCellFactory(new Callback<ListView<Class<?>>, ListCell<Class<?>>>() {

            @Override
            public ListCell<Class<?>> call(ListView<Class<?>> param) {
                final ListCell<Class<?>> cell = new ListCell<Class<?>>() {
                    @Override
                    protected void updateItem(Class<?> celll, boolean bln) {
                        super.updateItem(celll, bln);
                        if (celll == null) {
                            setText(null);
                            return;
                        }

                        Annotation annotation = celll.getAnnotation(Autonomous.class);
                        setText(((Autonomous) annotation).name());


                    }
                };
                return cell;
            }
        });

        select_autonomous.setMinWidth(width/1.5);
        select_autonomous.setMaxWidth(width/1.5);
        select_autonomous.setLayoutX((width/2) - (width/3));
        select_autonomous.setLayoutY(width/16);
        select_autonomous.valueProperty().addListener(new ChangeListener<Class<?>>(){
            @Override
            public void changed(ObservableValue<? extends Class<?>> observable, Class<?> oldValue, Class<?> newValue) {
                if(!select_autonomous.getSelectionModel().isEmpty()){
                    select_teleop.getSelectionModel().clearSelection();
                }
            }
        });

        Set<Class<?>> teles = OpModeManager.classesWithAnnotation_tele("org.firstinspires.ftc.teamcode");
        ObservableList<Class<?>> teleops = FXCollections.observableArrayList();
        for(Class<?> p : teles){
            //do disabled checking here
            teleops.add(p);
        }
        select_teleop.setPromptText("Select TeleOp");

        select_teleop.setItems(teleops);

        select_teleop.setCellFactory(new Callback<ListView<Class<?>>, ListCell<Class<?>>>() {

            @Override
            public ListCell<Class<?>> call(ListView<Class<?>> param) {
                final ListCell<Class<?>> cell = new ListCell<Class<?>>() {
                    @Override
                    protected void updateItem(Class<?> celll, boolean bln) {
                        super.updateItem(celll, bln);
                        if (celll == null) {
                            setText(null);
                            return;
                        }

                        Annotation annotation = celll.getAnnotation(TeleOp.class);

                        setText(((TeleOp) annotation).name());


                    }
                };
                return cell;
            }
        });

        select_teleop.setMinWidth(width/1.5);
        select_teleop.setMaxWidth(width/1.5);
        select_teleop.setLayoutX((width/2) - (width/3));
        select_teleop.setLayoutY((height) - ((width/16) * 2.5));
        select_teleop.valueProperty().addListener(new ChangeListener<Class<?>>(){
            @Override
            public void changed(ObservableValue<? extends Class<?>> observable, Class<?> oldValue, Class<?> newValue) {
                if(!select_teleop.getSelectionModel().isEmpty()){
                    select_autonomous.getSelectionModel().clearSelection();
                }
            }
        });

        ToggleGroup radio_group = new ToggleGroup();

        auto_radio.setToggleGroup(radio_group);
        tele_radio.setToggleGroup(radio_group);

        auto_radio.setLayoutX((width/2) - 15);
        auto_radio.setLayoutY((width/16) + (width/8));
        auto_radio.setTextFill(Color.WHITE);
        auto_radio.setSelected(true);

        tele_radio.setLayoutX((width/2) - 15);
        tele_radio.setLayoutY((height) - ((width/16) * 2.5) - (width/12) - 5);
        tele_radio.setTextFill(Color.WHITE);

        radio_group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                // Has selection.
                if (radio_group.getSelectedToggle() != null) {
                    RadioButton button = (RadioButton) radio_group.getSelectedToggle();
                    if(button.getText() == "a"){
                        autonSelected = true;
                    }else{
                        autonSelected = false;
                    }
                }
            }
        });

        main_button.setMinWidth(button_size);
        main_button.setMinHeight(button_size);
        main_button.setLayoutX((width/2) - (button_size/2));
        main_button.setLayoutY((height/2) - (button_size/2));

        main_button.setStyle("-fx-background-radius: 5em;");

        main_button.setOnAction(value ->  {
            if(buttonPressCount[0] == 0){
                getProgram();
                initProgram();
                main_button.setText("start");

            }else if(buttonPressCount[0] == 1){
                if(program.regularOpMode()){
                    programThread = new Thread(this::runProgram);
                    programThread.start();
                }

                main_button.setText("stop");
            }else if(buttonPressCount[0] == 2){
                program.isStopped = true;
                programThread.interrupt();
                programThread = null;
                stopProgram();
                main_button.setText("init");
                buttonPressCount[0] = -1;
            }

            buttonPressCount[0]++;

        });

        reset_button.setMinWidth(width/3);
        reset_button.setLayoutX((width/2) - (width/6));
        reset_button.setLayoutY((height/2) + (button_size/2) + 10);
        reset_button.setStyle("-fx-background-radius: 3em;");

        reset_button.setOnAction(value -> {
            try {
                UdpMessageManager.send("RESET");
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        root.getChildren().addAll(main_button, reset_button, select_autonomous, select_teleop, auto_radio, tele_radio);

        return root;
    }

    //function that initializes the selected auton or teleop
    void getProgram(){
        try {
            Class opMode;
            if(autonSelected){
                opMode = (Class) select_autonomous.getValue();
            }else{
                opMode = (Class) select_teleop.getValue();
            }
            program = (OpMode) opMode.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    void initProgram(){
        if(program.regularOpMode()){
            //it is a regular opmode
            program.init();
        }else{
            //its a linear opmode
            programThread = new Thread(this::runProgram);
            programThread.start();

        }

    }

    //function that will run the auton or teleop that @see initOpMode() selected
    void runProgram(){
        while(!program.isStopped){
            program.loop();

        }
        stopProgram();
    }

    void stopProgram(){
        try {
            UdpMessageManager.send("STOP");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }


    public static void stopp(){
        buttonPressCount[0] = 2;
    }



}
