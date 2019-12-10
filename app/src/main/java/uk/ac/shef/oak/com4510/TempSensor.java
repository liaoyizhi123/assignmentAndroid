package uk.ac.shef.oak.com4510;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class TempSensor {
    private SensorManager mSensorManager;
    private SensorEventListener mTemperEvent = null;
    private Sensor mTempSensor;
    private static List<Float> list = new ArrayList<>();
    private static Integer size = list.size();
    private static String TAG = "TempSensor";

    public TempSensor(Context context) {
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mTempSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        initBarometerListener();
    }

    public void startTempSensor() {
        try {
            mSensorManager.registerListener(mTemperEvent, mTempSensor, (1000 * 1000));

        } catch (Exception e) {
            System.out.println("wrong sensor");
        }
    }

    public void stopTempSensor() {
        mSensorManager.unregisterListener(mTemperEvent);
    }

    private void initBarometerListener() {
        if (!standardPressureSensorAvailable()) {
            Log.d(TAG, "Standard Barometer unavailable");
        } else {
            Log.d(TAG, "Using Barometer");
            mTemperEvent = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
//init

                    float TempValue = event.values[0];
                    //to do the fuck things
                    list.add(TempValue);
                    size++;
//                    System.out.println(list.size());
//                    System.out.println("presure" + list.get(list.size() - 1));
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {
                    if (!standardPressureSensorAvailable()) {
                        Log.d(TAG, "Standard Barometer unavailable");
                    } else {
                        Log.d(TAG, "Using Barometer");
                        mTemperEvent = new SensorEventListener() {
                            @Override
                            public void onSensorChanged(SensorEvent event) {
//init

                                float pressureValue = event.values[0];
                                //to do the fuck things
                                list.add(pressureValue);
                                size++;
//                    System.out.println(list.size());
//                    System.out.println("presure" + list.get(list.size() - 1));
                            }

                            @Override
                            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                            }
                        };
                    }
                }
            };
        }

    }

    private boolean standardPressureSensorAvailable() {
        return (mTempSensor != null);
    }

    public String getLasttemp() {
        float firstTemp = (float) 0.0;
        list.add(firstTemp);
        //System.out.println(size);
        String lastTemper = String.valueOf(list.get(size));
        System.out.println(lastTemper);
        return lastTemper;
    }
}
