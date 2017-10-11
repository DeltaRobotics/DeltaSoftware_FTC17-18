package org.firstinspires.ftc.teamcode;
import org.firstinspires.ftc.robotcore.internal.ftdi.eeprom.FT_EEPROM_232H;
import org.firstinspires.ftc.teamcode.RobotHardware;
import java.util.ArrayList;

/**
 * Created by User on 10/7/2017.
 */


enum driveStyle
{
    FORWARD, BACKWARD, STRAFE_LEFT, STRAFE_RIGHT, FORWARD_RIGHT, FORWARD_LEFT, BACKWARD_RIGHT, BACKWARD_LEFT, PIVOT_RIGHT, PIVOT_LEFT
}
public class Drive
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

    /*public void timeDrive(double time, double motorPower, Enum drive)
    {
        switch(drive)
        {
            case driveStyle.FORWARD:
                {

                }
        }
    }
    */
}
