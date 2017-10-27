package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by User on 10/17/2017.
 */

@TeleOp(name = "DRTeleOp", group = "")
public class DRTeleOp extends LinearOpMode
{
    RobotHardware curiosity = new RobotHardware();


    double slapperInit = 0.8;
    double slapperPosition = 0.0;
    double flapperInit = 1.0;
    double flapperPosition = 0.0;
    double wristInit = 0.795;
    double knockInit = 0.928;
    double clawInit = 0.646;
    double wristMaxChange = 0.005;
    double knockMaxChange = 0.005;
    double clawMaxChange = 0.005;
    double wristPos = wristInit;
    double knockPos = knockInit;
    double clawPos = clawInit;

    double armServoAdjustment = 0.2;
    double joint1MaxSpeed = 0.70;

    double joint2Position = 0.0;
    double joint3Position = 0.0;

    long initTime;
    long changeTime = 1200;
    int last = 99999;
    int joint1Midpoint = -111;
    double speed = 1.0;

    boolean dPadUpState = false;
    boolean dPadDownState = false;


    public void runOpMode()
    {
        curiosity.init(hardwareMap);

        //Sets the end behavior of the motors - brake implies the robot will physically attempt to stop instead of just coasting
        curiosity.motorRF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        curiosity.motorLF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        curiosity.motorLB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        curiosity.motorRB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        curiosity.joint1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        curiosity.slapper.setPosition(slapperInit);
        curiosity.flapper.setPosition(flapperInit);
        curiosity.wrist.setPosition(wristInit);
        curiosity.knock.setPosition(knockInit);
        curiosity.claw.setPosition(clawInit);

        initTime = System.currentTimeMillis();
        while((System.currentTimeMillis() - initTime) < changeTime)
        {
            curiosity.joint2.setPosition(0.35);
        }
        curiosity.joint2.setPosition(0.5); //stops servo from turning

        waitForStart();

        while (opModeIsActive())
        {
            //Clipping the ranges of servos so they don't go out of bounds
            slapperPosition = Range.clip(slapperPosition, 0.05, 0.95);
            flapperPosition = Range.clip(flapperPosition, 0.05, 0.95);
            armServoAdjustment = Range.clip(armServoAdjustment, 0.2, 0.7);
            knockPos = Range.clip(knockPos, 0.01, 0.928);
            wristPos = Range.clip(wristPos, 0.01, 0.99);


            if(gamepad1.a)
            {
                speed = 1.0;
            }
            else if(gamepad1.b)
            {
                speed = 0.5;
            }

            //Setting drive motors for mecanum - gamepad 1 - driver
            curiosity.motorRF.setPower(speed*((-gamepad1.left_stick_y - gamepad1.left_stick_x) - gamepad1.right_stick_x));
            curiosity.motorLB.setPower(speed*(-(-gamepad1.left_stick_y - gamepad1.left_stick_x) - gamepad1.right_stick_x));
            curiosity.motorRB.setPower(speed*(-(-gamepad1.left_stick_x + gamepad1.left_stick_y) - gamepad1.right_stick_x));
            curiosity.motorLF.setPower(speed*(-gamepad1.left_stick_x + gamepad1.left_stick_y) - gamepad1.right_stick_x);

            //Driver - Flapper and Slapper manual controls
            if(gamepad1.right_trigger > 0.5)
            {
                slapperPosition =+ 0.05;
            }
            else if(gamepad1.right_bumper)
            {
                slapperPosition  =- 0.05;
            }

            if(gamepad1.y)
            {
                flapperPosition =+ 0.05;
            }
            else if(gamepad1.x)
            {
                flapperPosition =- 0.05;
            }

            //Manipulator - Arm controls, Joints 1 and 2

            //Setting Arm Adjustment Speed
            if(gamepad2.dpad_up && !dPadUpState)
            {
                dPadUpState = true;
                armServoAdjustment += 0.02;
            }
            else if(!gamepad2.dpad_up)
            {
                dPadUpState = false;
            }

            if(gamepad2.dpad_down && !dPadDownState)
            {
                dPadDownState  = true;
                armServoAdjustment -= 0.02;
            }
            else if(!gamepad2.dpad_down)
            {
                dPadDownState = false;
            }

            //Setting Joint 1

            curiosity.joint1.setPower(-(gamepad2.left_stick_y * joint1MaxSpeed));
            if((curiosity.joint1.getCurrentPosition() < last) && (last != 99999) && (last < joint1Midpoint))
            {
                curiosity.joint1.setPower(-(gamepad2.left_stick_y * 0.1));
            }

            if((curiosity.joint1.getCurrentPosition() > last) && (last != 99999) && (last > joint1Midpoint))
            {
                curiosity.joint1.setPower(-(gamepad2.left_stick_y * 0.1));
            }

            last = curiosity.joint1.getCurrentPosition();

            //Setting Joint 2
            if(gamepad2.left_bumper)
            {
                joint2Position = (0.5 - armServoAdjustment);
            }
            else if(gamepad2.left_trigger > 0.4)
            {
                joint2Position = (0.5 + armServoAdjustment);
            }
            else
            {
                joint2Position = 0.5;
            }

            if(gamepad2.right_stick_x > 0.1 || gamepad2.right_stick_x < -0.1)
            {
                wristPos += (gamepad2.right_stick_x * wristMaxChange);

                curiosity.wrist.setPosition(wristPos);

            }

            if(gamepad2.right_stick_y > 0.1 || gamepad2.right_stick_y < -0.1)
            {
                knockPos += gamepad2.right_stick_y * knockMaxChange;
                curiosity.knock.setPosition(knockPos);

            }

            if(gamepad2.right_bumper || gamepad2.right_trigger > 0.1)
            {
                if(gamepad2.right_bumper)
                {
                    clawPos += clawMaxChange;
                }
                if(gamepad2.right_trigger > 0.1)
                {
                    clawPos -= gamepad2.right_trigger * clawMaxChange;
                }
                if(clawPos > 1.0)
                {
                    clawPos = 1.0;
                }

                if(clawPos < 0.0)
                {
                    clawPos = 0.0;
                }

                curiosity.claw.setPosition(clawPos);

            }

            //Sending telemetry for arm data
            telemetry.addData("armServoAdjustment", armServoAdjustment);
            telemetry.addData("Joint 1", curiosity.joint1.getPower());
            telemetry.addData("Joint 2", curiosity.joint2.getPosition());
            telemetry.addData("Wrist Pos", curiosity.wrist.getPosition());
            telemetry.addData("Knock Pos", curiosity.knock.getPosition());
            telemetry.addData("Claw Pos", curiosity.claw.getPosition());

            telemetry.update();

            //Actually setting the positions of the servos
            curiosity.slapper.setPosition(slapperPosition);
            curiosity.flapper.setPosition(flapperPosition);
            curiosity.joint2.setPosition(joint2Position);
        }
    }
}
