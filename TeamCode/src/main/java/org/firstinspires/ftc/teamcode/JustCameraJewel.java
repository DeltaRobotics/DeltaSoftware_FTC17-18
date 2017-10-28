package org.firstinspires.ftc.teamcode;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import for_camera_opmodes.LinearOpModeCamera;

/**
 * Created by User on 10/28/2017.
 */
@Autonomous(name="Jewel Only", group ="Autonomous Testing")
public class JustCameraJewel extends LinearOpModeCamera
{
    int jewelColorInt;

    public void runOpMode()
    {
        if (isCameraAvailable())
        {
        //Resolution of image, currently set to 1 (higher number means less resolution but faster speed)
        setCameraDownsampling(1);
        //Takes some time, is initializing all of the camera's internal workings
        startCamera();
        //Stays Initialized, waits for the Driver's Station Button to be pressed
        waitForStart();


        while(opModeIsActive())
        {

            telemetry.addData("Active", "OpMode");

                if (imageReady())
                {

                    int redValueLeft = -76800;
                    int blueValueLeft = -76800;
                    int greenValueLeft = -76800;

                    Bitmap rgbImage;
                    //The last value must correspond to the downsampling value from above
                    rgbImage = convertYuvImageToRgb(yuvImage, width, height, 1);

                    telemetry.addData("Width", rgbImage.getWidth());
                    telemetry.addData("Height", rgbImage.getHeight());
                    telemetry.update();


                    for (int x = 480; x < 960; x++)
                    {
                        for (int y = 850; y < 1280; y++)
                        {
                            if (x == 0 && y >= 850)
                            {
                                rgbImage.setPixel(x, y, Color.rgb(0, 255, 0));
                            }
                            if (x >= 0 && y == 850)
                            {
                                rgbImage.setPixel(x, y, Color.rgb(0, 255, 0));
                            }
                            if (x == 480 && y >= 850)
                            {
                                rgbImage.setPixel(x, y, Color.rgb(0, 255, 0));
                            }
                            if (x >= 480 && y == 1279)
                            {
                                rgbImage.setPixel(x, y, Color.rgb(0, 255, 0));
                            }
                        }
                    }

                    SaveImage(rgbImage);

                    //Analyzing Jewel Color
                    for (int x = 480; x < 960; x++)
                    {
                        for (int y = 850; y < 1280; y++)
                        {
                            int pixel = rgbImage.getPixel(x, y);
                            redValueLeft += red(pixel);
                            blueValueLeft += blue(pixel);
                            greenValueLeft += green(pixel);
                        }
                    }
                    redValueLeft = normalizePixels(redValueLeft);
                    blueValueLeft = normalizePixels(blueValueLeft);
                    greenValueLeft = normalizePixels(greenValueLeft);
                    telemetry.addData("redValueLeft", redValueLeft);
                    telemetry.addData("blueValueLeft", blueValueLeft);
                    telemetry.addData("greenValueLeft", greenValueLeft);


                    jewelColorInt = highestColor(redValueLeft, blueValueLeft, greenValueLeft);

                    telemetry.addData("Jewel Color", jewelColorInt);
                    if (jewelColorInt == 0)
                    {
                        telemetry.addData("Jewel Color", "0 : Red");
                    } else if (jewelColorInt == 1)
                    {
                        telemetry.addData("Jewel Color", "1 : Blue");
                    } else if (jewelColorInt == 2)
                    {
                        telemetry.addData("Jewel Color", "Green? What Did You Do? Green Shouldn't Even Be An Option!");
                    } else
                    {
                        telemetry.addData("Jewel Color", "Something's Wrong");
                    }
                    telemetry.update();
                }


            }
            stopCamera();
            telemetry.addData("Not available?", "??");

        }
    }
}

