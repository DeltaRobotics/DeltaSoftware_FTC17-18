package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by User on 9/26/2017.
 */

public class RobotHardware
{
    public DcMotor motorRF = null;
    public DcMotor motorLF = null;
    public DcMotor motorRB = null;
    public DcMotor motorLB = null;

    public Servo armBase = null;
    public Servo arm = null;

    boolean useEncoder = false;

    HardwareMap hwMap = null;
    private ElapsedTime elapsedTime = new ElapsedTime();

    public RobotHardware()
    {

    }

    public void init(HardwareMap ahwMap)
    {
        motorRF = ahwMap.dcMotor.get("motorRF");
        motorLF = ahwMap.dcMotor.get("motorLF");
        motorRB = ahwMap.dcMotor.get("motorRB");
        motorLB = ahwMap.dcMotor.get("motorLB");

        armBase = ahwMap.servo.get("armBase");
        arm = ahwMap.servo.get("arm");

        motorRF.setPower(0);
        motorLF.setPower(0);
        motorRB.setPower(0);
        motorLB.setPower(0);

    }
}
