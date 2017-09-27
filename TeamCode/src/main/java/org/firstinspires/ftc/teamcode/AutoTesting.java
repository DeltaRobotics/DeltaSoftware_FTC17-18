package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by User on 9/26/2017.
 */

@Autonomous (name = "AutoTesting", group = "")
public class AutoTesting extends LinearOpMode
{
    RobotHardware robot = new RobotHardware();

    @Override
    public void runOpMode()
    {
        robot.init(hardwareMap);

        robot.motorRB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorLB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.motorRB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.motorLB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

            robot.motorRF.setTargetPosition(-20000);
            robot.motorRB.setTargetPosition(-20000);
            robot.motorLF.setTargetPosition(20000);
            robot.motorLB.setTargetPosition(20000);

            robot.motorRB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorLB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.motorRF.setPower(1);
            robot.motorLF.setPower(1);
            robot.motorRB.setPower(1);
            robot.motorLB.setPower(1);

            while(opModeIsActive() && robot.motorRB.isBusy() && robot.motorLB.isBusy())
            {

            }

            robot.motorRB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.motorLB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            sleep(3000);


            robot.motorRB.setTargetPosition(20000);
            robot.motorLB.setTargetPosition(-20000);

            robot.motorRB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorLB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.motorRF.setPower(1);
            robot.motorLF.setPower(1);
            robot.motorRB.setPower(1);
            robot.motorLB.setPower(1);

        while(opModeIsActive() && robot.motorRB.isBusy() && robot.motorLB.isBusy())
        {

        }


    }
}
