package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by User on 10/14/2017.
 */

@Autonomous (name = "AutoBlueLeft", group = "Auto")
public class AutoBlueLeft extends LinearOpMode
{
    RobotHardware robot = new RobotHardware();
    Drive drive = new Drive();
    ServoMove servoMove = new ServoMove();

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

        Servo[] servos = new Servo[2];
        servos[0] = robot.flapper;
        servos[1] = robot.slapper;

        robot.slapper.setPosition(0.8);
        robot.flapper.setPosition(1.0);

        waitForStart();

        drive.timeDrive(395, 0.4, driveStyle.STRAFE_LEFT, motors);
        /*sleep(2000);
        robot.slapper.setPosition(0.3);
        sleep(1000);
        robot.flapper.setPosition(0.68);
        sleep(1000);
        color = "blue";
        if(color == "blue")
        {
            robot.slapper.setPosition(0.10);
        }
        if(color == "red")
        {
            robot.slapper.setPosition(0.50);
        }
        sleep(1000);
        robot.slapper.setPosition(0.3);
        sleep(1000);
        robot.flapper.setPosition(1.0);
        sleep(1000);
        robot.slapper.setPosition(0.8);
        sleep(1000);
        */
        servoMove.knockOffJewl(servos);
        drive.timeDrive(750, 0.5, driveStyle.FORWARD, motors);
        sleep(1000);
        drive.timeDrive(700, 0.5, driveStyle.STRAFE_RIGHT, motors);
    }
    }


