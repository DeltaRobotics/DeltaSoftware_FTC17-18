package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by User on 10/14/2017.
 */

@Autonomous (name = "CompAuto", group = "")
public class CompAuto extends LinearOpMode
{
    RobotHardware robot = new RobotHardware();
    Drive drive = new Drive();

    String color = "undecided";

    @Override
    public void runOpMode()
    {

        robot.init(hardwareMap);

        robot.motorRF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.motorLF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.motorRB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.motorLB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        DcMotor[] motors = new DcMotor[4];
        motors[0] = robot.motorRF;
        motors[1] = robot.motorRB;
        motors[2] = robot.motorLB;
        motors[3] = robot.motorLF;

        robot.flapper.setPosition(1.0);
        robot.slapper.setPosition(1.0);

        waitForStart();

        drive.timeDrive(290, 0.4, driveStyle.STRAFE_LEFT, motors);
        sleep(2000);
        robot.slapper.setPosition(0.5);
        sleep(1000);
        robot.flapper.setPosition(0.7);
        sleep(1000);
        color = "red";
        if(color == "blue")
        {
            robot.slapper.setPosition(0.25);
        }
        if(color == "red")
        {
            robot.slapper.setPosition(0.75);
        }
        sleep(1000);
        robot.slapper.setPosition(0.5);
        sleep(1000);
        robot.flapper.setPosition(1.0);
        sleep(1000);
        robot.slapper.setPosition(1.0);
        sleep(1000);
        drive.timeDrive(820, 0.5, driveStyle.FORWARD, motors);
        sleep(1000);
        drive.timeDrive(700, 0.5, driveStyle.STRAFE_RIGHT, motors);
    }
    }


