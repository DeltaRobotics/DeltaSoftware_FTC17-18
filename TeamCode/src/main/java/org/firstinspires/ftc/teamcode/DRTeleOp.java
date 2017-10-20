package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by User on 10/17/2017.
 */

public class DRTeleOp extends LinearOpMode
{
    RobotHardware curiosity = new RobotHardware();


    double slapperInit = 0.0;
    double slapperPosition = 0.0;
    double flapperInit = 0.0;
    double flapperPosition = 0.0;


    public void runOpMode()
    {
        curiosity.init(hardwareMap);


        //Sets the end behavior of the motors - brake implies the robot will physically attempt to stop instead of just coasting
        curiosity.motorRF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        curiosity.motorLF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        curiosity.motorLB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        curiosity.motorRB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        curiosity.slapper.setPosition(slapperInit);
        curiosity.flapper.setPosition(flapperInit);

        waitForStart();

        while (opModeIsActive())
        {
            //Clipping the ranges of servos so they don't go out of bounds
            slapperPosition = Range.clip(slapperPosition, 0.05, 0.95);
            flapperPosition = Range.clip(flapperPosition, 0.05, 0.95);

            //Setting drive motors for mecanum - gamepad 1 - driver
            curiosity.motorRF.setPower((-gamepad1.left_stick_y - gamepad1.left_stick_x) - gamepad1.right_stick_x);
            curiosity.motorLB.setPower(-(-gamepad1.left_stick_y - gamepad1.left_stick_x) - gamepad1.right_stick_x);
            curiosity.motorRB.setPower(-(-gamepad1.left_stick_x + gamepad1.left_stick_y) - gamepad1.right_stick_x);
            curiosity.motorLF.setPower((-gamepad1.left_stick_x + gamepad1.left_stick_y) - gamepad1.right_stick_x);

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
            else if(gamepad1.b)
            {
                flapperPosition =- 0.05;
            }

            //Actually setting the positions of the servos
            curiosity.slapper.setPosition(slapperPosition);
            curiosity.flapper.setPosition(flapperPosition);

        }

    }
}
