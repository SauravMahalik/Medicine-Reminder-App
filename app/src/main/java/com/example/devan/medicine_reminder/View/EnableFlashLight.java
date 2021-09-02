package com.example.devan.medicine_reminder.View;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.ContextWrapper;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.support.annotation.RequiresApi;

public class EnableFlashLight extends ContextWrapper {

    public EnableFlashLight(Context base) {
        super(base);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)

    public void enableFlash()
    {
        try {

            final CameraManager cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
            String id = cameraManager.getCameraIdList()[0];

            Thread turnOnOffThread = new Thread();

            int[] frequencyValues = new int[]{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0};
            turnOnOffThread.start();

            cameraManager.setTorchMode(id, true);

            for (int i = 0; i < frequencyValues.length; i++) {
                if (frequencyValues[i] == 0) {

                    turnOnOffThread.sleep(200);
                    cameraManager.setTorchMode(id, false);
                }
                if (frequencyValues[i] == 1) {

                    turnOnOffThread.sleep(200);
                    cameraManager.setTorchMode(id, true);
                }
            }

        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            System.out.print("Finally for the FlashLight Notification Class");
        }
    }
}
