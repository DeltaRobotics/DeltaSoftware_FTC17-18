package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by User on 9/16/2017.
 */

@TeleOp (name = "Mecanum Drive", group = "")
public class MecanumDrive extends LinearOpMode
{
    RobotHardware robot = new RobotHardware();

    public void runOpMode()
    {
        robot.init(hardwareMap);

        robot.motorRF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.motorLF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.motorLB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.motorRB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        waitForStart();

        while (opModeIsActive())
        {

            /*
            if (gamepad1.right_stick_x == 0)
            {
                motorRF.setPower((-gamepad1.left_stick_y - gamepad1.left_stick_x));
                motorLB.setPower(-(-gamepad1.left_stick_y - gamepad1.left_stick_x));
                motorRB.setPower(-(-gamepad1.left_stick_x + gamepad1.left_stick_y));
                motorLF.setPower((-gamepad1.left_stick_x + gamepad1.left_stick_y));
            }


            if (gamepad1.right_stick_x != 0)
            {
                motorLF.setPower(-gamepad1.right_stick_x);
                motorLB.setPower(-gamepad1.right_stick_x);
                motorRF.setPower(-gamepad1.right_stick_x);
                motorRB.setPower(-gamepad1.right_stick_x);
            }
            */

            robot.motorRF.setPower((-gamepad1.left_stick_y - gamepad1.left_stick_x) - gamepad1.right_stick_x);
            robot.motorLB.setPower(-(-gamepad1.left_stick_y - gamepad1.left_stick_x) - gamepad1.right_stick_x);
            robot.motorRB.setPower(-(-gamepad1.left_stick_x + gamepad1.left_stick_y) - gamepad1.right_stick_x);
            robot.motorLF.setPower((-gamepad1.left_stick_x + gamepad1.left_stick_y) - gamepad1.right_stick_x);

            telemetry.addData("Left Joystick X", gamepad1.left_stick_x);
            telemetry.addData("Left Joystick Y", gamepad1.left_stick_y);
            telemetry.addData("Right Joystick X", gamepad1.right_stick_x);
            telemetry.addData("motorRF Power", robot.motorRF.getPower());
            telemetry.addData("motorLB Power", robot.motorLB.getPower());
            telemetry.addData("motorRB Power", robot.motorRB.getPower());
            telemetry.addData("motorLF Power", robot.motorLF.getPower());

            telemetry.update();

            //Comment

        }
    }
}
