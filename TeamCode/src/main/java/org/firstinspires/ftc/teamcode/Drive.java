package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


/**
 * Created by User on 10/7/2017.
 */


enum driveStyle
{
    FORWARD, BACKWARD, STRAFE_LEFT, STRAFE_RIGHT, FORWARD_RIGHT, FORWARD_LEFT, BACKWARD_RIGHT, BACKWARD_LEFT, PIVOT_RIGHT, PIVOT_LEFT
}
public class Drive extends LinearOpMode
{


    public double[] setPower(double dirX, double dirY, double pivot)
    {
        double[] motorPowers = new double[4];
        motorPowers[0] = (-dirY - dirX) - pivot;
        motorPowers[1] = -(-dirX + dirY) - pivot;
        motorPowers[2] = -(-dirY - dirX) - pivot;
        motorPowers[3] = (-dirX + dirY) - pivot;

        //motorPowers[0] = motorRF
        //motorPowers[1] = motorRB
        //motorPowers[2] = motorLB
        //motorPowers[3] = motorLF

        return motorPowers;
    }

    public void timeDrive(long time, double motorPower, driveStyle drive, DcMotor[] motors)
    {
        switch(drive)
        {
            case FORWARD:
                {
                    motors[0].setPower(setPower(0, -motorPower, 0)[0]);
                    motors[1].setPower(setPower(0, -motorPower, 0)[1]);
                    motors[2].setPower(setPower(0, -motorPower, 0)[2]);
                    motors[3].setPower(setPower(0, -motorPower, 0)[3]);

                    sleep(time);

                    motors[0].setPower(setPower(0, 0, 0)[0]);
                    motors[1].setPower(setPower(0, 0, 0)[1]);
                    motors[2].setPower(setPower(0, 0, 0)[2]);
                    motors[3].setPower(setPower(0, 0, 0)[3]);

                    break;


                }

            case BACKWARD:
            {
                motors[0].setPower(setPower(0, motorPower, 0)[0]);
                motors[1].setPower(setPower(0, motorPower, 0)[1]);
                motors[2].setPower(setPower(0, motorPower, 0)[2]);
                motors[3].setPower(setPower(0, motorPower, 0)[3]);

                sleep(time);

                motors[0].setPower(setPower(0, 0, 0)[0]);
                motors[1].setPower(setPower(0, 0, 0)[1]);
                motors[2].setPower(setPower(0, 0, 0)[2]);
                motors[3].setPower(setPower(0, 0, 0)[3]);

                break;

            }

            case STRAFE_LEFT:
            {

            }

            case STRAFE_RIGHT:
            {

            }

            case FORWARD_LEFT:
            {

            }

            case FORWARD_RIGHT:
            {

            }

            case BACKWARD_LEFT:
            {

            }

            case BACKWARD_RIGHT:
            {

            }

            case PIVOT_LEFT:
            {

            }

            case PIVOT_RIGHT:
            {

            }
        }

    }

    @Override
    public void runOpMode()
    {}


}
