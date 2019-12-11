package uk.ac.shef.oak.com4510;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class PressureSensor {
    private SensorManager mSensorManager;
    private SensorEventListener mPressureEvent = null;
    private Sensor mPressureSensor;
    private static List<Float> list = new ArrayList<>();
    private static Integer size = list.size();
    private static String TAG = "TempSensor";

    public PressureSensor(Context context) {
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mPressureSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        initBarometerListener();
    }

    private void initBarometerListener() {
        if (!standardPressureSensorAvailable()) {
            Log.d(TAG, "Standard Barometer unavailable");
        } else {
            Log.d(TAG, "Using Barometer");
            mPressureEvent = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    float pressureValue = event.values[0];
                    //System.out.println("pressure"+pressureValue);
                    list.add(pressureValue);
                    size++;
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {
                }
            };
        }
    }

    private boolean standardPressureSensorAvailable() {
        return (mPressureSensor != null);
    }

    public void startTempSensor() {
        try {
            mSensorManager.registerListener(mPressureEvent, mPressureSensor, (1000 * 1000));
        } catch (Exception e) {
            System.out.println("wrong sensor");
        }
    }

    public void stopTempSensor() {
        mSensorManager.unregisterListener(mPressureEvent);
    }

    public String getLasttemp() {
        float firstTemp = (float) 1013.25;
        list.add(firstTemp);
        // System.out.println(size);
        String lastTemper = String.valueOf(list.get(size));
        System.out.println(lastTemper);
        return lastTemper;
    }

}
