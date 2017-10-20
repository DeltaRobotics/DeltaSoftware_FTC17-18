package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;
/**
 * Created by User on 10/19/2017.
 */

@TeleOp(name = "OldRobotTele", group = "")
public class OldRobotTele extends OpMode
{
    DcMotor motorR;
    DcMotor motorRF;
    DcMotor motorL;
    DcMotor motorLF;
    DcMotor launcherWheel;
    DcMotor motorLift;
    DcMotor collector;
    //DcMotor capLift;
    Servo popper;
    //Servo bBP;
    //Servo boot;


    float throttleLeft = 0;
    float throttleRight = 0;
    double throttleScalingLeft = 1.0;
    double throttleScalingRight = 1.0;
    //double bBPvalue = 0.01;
    double popperUp = 0.69;
    double popperDown = 0.94;
    double popperPosition = 0.94;

    //boolean bBpStop = false;
    boolean drive = true;
    boolean spinning = false;
    boolean collecting = false;
    double constant = 0;
    boolean collectback = false;
    int lastE = 0;
    int encoderCount;
    int cps = 0;
    int rev = 0;
    long timeConstant;
    long timeTest;
    long timeTestConstant;
    long loopTime;
    long previousLoop = 0;
    boolean firstTime = true;
    double launcherPower = -0.50;
    //double capLiftPower = 0;
    boolean dPadLeftState = false;
    boolean dPadRightState = false;
    boolean test = true;
    boolean spinStart = false;
    boolean popperTime;
    boolean autoAdjust = true;
    long popperStart;

    //double bootUp = .80;
    //double bootDown = .1;
    //double bootPosition;
    //boolean bootOut = true;
    boolean leftStickButtonState = false;
   // boolean capMode = false;

    int a = 0;
    int p = 5;
    //p-Previous was 20
    int t = 100;
    //t-Previous was 10

    // Declares 2 float variables for the throttle

    @Override
    public void init()
    {
        // Code inside the init method is the code that is run when you press the
        //"Init" button on the driver's station
        motorL = hardwareMap.dcMotor.get("motorL");
        motorR = hardwareMap.dcMotor.get("motorR");
        motorRF = hardwareMap.dcMotor.get("motorRF");
        motorLF = hardwareMap.dcMotor.get("motorLF");
        //bBP = hardwareMap.servo.get("bBP");
        motorLift = hardwareMap.dcMotor.get("motorLift");
        launcherWheel = hardwareMap.dcMotor.get("launcherWheel");
        collector = hardwareMap.dcMotor.get("collector");
        popper = hardwareMap.servo.get("popper");
        //boot = hardwareMap.servo.get("boot");
        //capLift = hardwareMap.dcMotor.get("capLift");
        popper.setPosition(popperDown);
        //boot.setPosition(bootDown);

        //bBP.setPosition(bBPvalue);
        // Adds the components you previously initialized to the config
    }

    @Override
    public void loop() {
        timeTest = System.currentTimeMillis();
        if(firstTime)
        {
            timeTestConstant = System.currentTimeMillis();
            firstTime = false;
            //bootPosition = bootDown;
        }
        loopTime = timeTest - previousLoop;
        previousLoop = timeTest;
        telemetry.addData("Loop Time", loopTime);

        if(spinStart)
        {
            a = launcherWheel.getCurrentPosition();
            spinStart = false;
            timeConstant = System.currentTimeMillis();
        }
        telemetry.addData("cps", cps);
        telemetry.addData("a", a);
        telemetry.addData("AutoAdjust", autoAdjust);
        telemetry.addData("Encoder Count", launcherWheel.getCurrentPosition());
        telemetry.addData("Encoder Change", encoderCount);

        if(System.currentTimeMillis() - timeConstant > t) //Time-check for cps
        {
            if (spinning) {
                timeConstant = System.currentTimeMillis(); //Reset time-check for cps
                encoderCount = launcherWheel.getCurrentPosition() - lastE; //Change in encoder counts
                lastE = launcherWheel.getCurrentPosition(); //Resetting the encoder position for calculating difference
                //****For Not Averaging****//
                cps = encoderCount * (1000/t);
                //****For Averaging****//
                a = ((a * (p - 1) + cps) / p);
                if (a < -500 && autoAdjust) //Was -1800 on old flywheel
                {
                    if (a > -2200) {
                        launcherPower -= .008;
                    }
                    if (a < -2300) {
                        launcherPower += .008;
                    }
                }
            }
        }

        if(gamepad1.right_bumper)
        {
            collectback = true;
        }

        if(gamepad1.left_bumper)
        {
            collectback = false;
        }

        if (gamepad1.dpad_down)
        {
            drive = false;
        }

        if (gamepad1.dpad_up)
        {
            drive = true;
        }

        if (gamepad2.right_bumper)
        {
            spinning = true;
        }

        if (gamepad2.left_bumper)
        {
            spinning = false;
        }

        if (gamepad1.right_trigger > 0.75)
        {
            collecting = true;
        }

        if (gamepad1.left_trigger > 0.75)
        {
            collecting = false;
        }
/*
        if(gamepad1.dpad_right)
        {
            bootOut = true;
        }

        if(gamepad1.dpad_left)
        {
            bootOut = false;
        }
*/

        //Adjusting the Launcher Power - incrementing by 5%
        if (gamepad2.dpad_left && !dPadLeftState)
        {
            dPadLeftState = true;
            launcherPower -= 0.005;
        }
        else if (!gamepad2.dpad_left)
        {
            dPadLeftState = false;
        }

        if (gamepad2.dpad_right && !dPadRightState)
        {
            dPadRightState = true;
            launcherPower += 0.005;
        }
        else if (!gamepad2.dpad_right)
        {
            dPadRightState = false;
        }


        //Beacon Button Presser - incrementing by .005
        /*if (gamepad2.b && !bBpStop)
        {
            bBPvalue += .005;
        }
        if (gamepad2.x && !bBpStop)
        {
            bBPvalue -= .005;
        }
        if (gamepad2.a)
        {
            bBpStop = true;
            bBPvalue = 0.01;
            bBP.setPosition(bBPvalue);
            sleep(500);
        }
        if (!gamepad2.a)
        {
            bBpStop = false;
        }
        if (gamepad2.y)
        {
            bBpStop = true;
            bBPvalue = 0.64;
            bBP.setPosition(bBPvalue);
            sleep(500);
        }
        if (!gamepad2.a) {
            bBpStop = false;
        }
        */

        //Setting the Launcher Power
        if (spinning)
        {
            launcherWheel.setPower(-launcherPower);
        }

        if (!spinning)
        {
            launcherWheel.setPower(0);
        }
/*
        if(bootOut)
        {
            bootPosition = bootDown;
        }
        if(!bootOut)
        {
            bootPosition = bootUp;
        }
        */
        //Setting the Popper Position
        if ((gamepad2.right_trigger > 0.8 && a < -2200 && a > -2300 && cps < -2200))// || (gamepad2.right_trigger > 0.8 && !autoAdjust))
        {

            popperTime = true;
            popperPosition = popperUp;
            popperStart = System.currentTimeMillis();
        }
        if (gamepad2.left_trigger > 0.8 )
        {
            popperPosition = popperDown;
        }

        if(popperTime)
        {
            if(System.currentTimeMillis() - popperStart > 200)
            {
                popperPosition = popperDown;
                popperTime = false;
            }
        }


        if(gamepad2.dpad_up)
        {
            autoAdjust = true;
        }

        if(gamepad2.dpad_down)
        {
            autoAdjust = false;
        }
        popper.setPosition(popperPosition);


        //Setting the Collector and the Lift Motor
        if(collecting)
        {
            collector.setPower(-0.8);
            motorLift.setPower(-1.0);
        }
        else if(collectback)
        {
            collector.setPower(.8);
            motorLift.setPower(1.0);
        }
        else
        {
            collector.setPower(0.0);
            motorLift.setPower(0.0);
        }

            /*if (gamepad2.back && backState == false)
            {
                backState = true;
                collectorPower -= 0.05;
            }
            else if (!gamepad2.back)
            {
                backState = false;
            }
            /*if (gamepad2.start && startState == false)
            {
                startState = true;
                collectorPower += 0.05;
            }
            else if (!gamepad2.start)
            {
                startState = false;
            }*/
            /*
        if (capMode == true)
        {
            if (gamepad2.right_stick_y > 0)
            {
                capLiftPower = 1.0;
            }
            else if (gamepad2.right_stick_y < 0)
            {
                capLiftPower = -1.0;
            }
            else
            {
                capLiftPower = 0;
            }
        }
        else
        {
            capLiftPower = 0;
        }
        */

        throttleLeft = Range.clip(throttleLeft, -1, 1);
        throttleRight = Range.clip(throttleRight, -1, 1);
        //bBPvalue = Range.clip(bBPvalue, .01, .99);
        // Makes it so the variables can't go below -1 or above 1

        //Scales the motors exponentially
        if (gamepad1.right_stick_y > 0)
        {
            throttleRight = gamepad1.right_stick_y  *  gamepad1.right_stick_y;
        }
        else if (gamepad1.right_stick_y < 0)
        {
            throttleRight = gamepad1.right_stick_y;// * gamepad1.right_stick_y * -1;
        }
        else
        {
            throttleRight = 0;
        }

        if (gamepad1.left_stick_y > 0)
        {
            throttleLeft = gamepad1.left_stick_y;// * gamepad1.left_stick_y;
        }
        else if (gamepad1.left_stick_y < 0)
        {
            throttleLeft = gamepad1.left_stick_y;// * gamepad1.left_stick_y * -1;
        }
        else
        {
            throttleLeft = 0;
        }

        // Scales the  variable throttleLeft exponentially

        if (gamepad1.b) {
            throttleScalingLeft = 0.5;
            throttleScalingRight = 0.5;
        }
        if (gamepad1.a) {
            throttleScalingLeft = 1;
            throttleScalingRight = 1;
        }
        throttleLeft = throttleLeft * (float) throttleScalingLeft;
        throttleRight = throttleRight * (float) throttleScalingRight;

        if (drive) {
            motorL.setPower(throttleLeft);
            motorLF.setPower(throttleLeft);
            motorR.setPower(-throttleRight);
            motorRF.setPower(-throttleRight);
        } else {
            motorL.setPower(-throttleRight);
            motorLF.setPower(-throttleRight);
            motorR.setPower(throttleLeft);
            motorRF.setPower(throttleLeft);
        }

        if (gamepad2.left_stick_button && leftStickButtonState == false)
        {
            leftStickButtonState = true;
            //bBPvalue = 0.99;
            //capMode = true;

        }
        else if (!gamepad2.left_stick_button)
        {
            leftStickButtonState = false;
        }

        //capLift.setPower(capLiftPower);
        //bBP.setPosition(bBPvalue);
        //boot.setPosition(bootPosition);

        //telemetry.addData("bBP Position", bBPvalue);
        telemetry.addData("Popper Position", popperPosition);
        //telemetry.addData("Collector Power", collector.getPower());
        telemetry.addData("Launcher Wheel (the motor)", launcherWheel.getPower());
        telemetry.addData("Launcher Power (the variable)", launcherPower);
        telemetry.addData("Drive", drive);

        //telemetry.addData("Boot Position", bootPosition);
        //telemetry.addData("Lift Power", motorLift.getPower());

        // Sets the appropriate motors to the appropriate variables

    }

    public static void sleep(int amt) // In milliseconds
    {
        double a = System.currentTimeMillis();
        double b = System.currentTimeMillis();
        while ((b - a) <= amt) {
            b = System.currentTimeMillis();
        }
    }
}
