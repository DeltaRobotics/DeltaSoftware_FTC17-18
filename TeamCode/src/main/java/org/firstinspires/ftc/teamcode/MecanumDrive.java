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
    DcMotor motorRF;
    DcMotor motorLB;
    DcMotor motorRB;
    DcMotor motorLF;

    public void runOpMode()
    {
        motorRF = hardwareMap.dcMotor.get("motorRF");
        motorLB = hardwareMap.dcMotor.get("motorLB");
        motorRB = hardwareMap.dcMotor.get("motorRB");
        motorLF = hardwareMap.dcMotor.get("motorLF");
        waitForStart();

        while (opModeIsActive())
        {

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

            telemetry.addData("Left Joystick X", gamepad1.left_stick_x);
            telemetry.addData("Left Joystick Y", gamepad1.left_stick_y);
            telemetry.addData("Right Joystick X", gamepad1.right_stick_x);
            telemetry.addData("motorRF Power", motorRF.getPower());
            telemetry.addData("motorLB Power", motorLB.getPower());
            telemetry.addData("motorRB Power", motorRB.getPower());
            telemetry.addData("motorLF Power", motorLF.getPower());

            telemetry.update();

        }
    }
}
