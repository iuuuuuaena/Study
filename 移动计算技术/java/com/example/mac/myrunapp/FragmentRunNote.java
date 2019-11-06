package com.example.mac.myrunapp;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

import static android.content.Context.SENSOR_SERVICE;
import static java.lang.Math.sqrt;

public class FragmentRunNote extends Fragment implements SensorEventListener {



    private TextView tvAccelerometer;
    private TextView tttt;
    private SensorManager mSensorManager;
    private float[] gravity = new float[3];
    RelativeLayout relativeLayout1;
    String message;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        View view=inflater.inflate(R.layout.frag_run_note,container,false);
      //  super.onCreate(savedInstanceState);
        relativeLayout1 =  getActivity().findViewById(R.id.run_relative_run_note);
        tvAccelerometer = view.findViewById(R.id.myrun);
        tttt = view.findViewById(R.id.teee);
        //获取传感器SensorManager对象
        mSensorManager =(SensorManager) getActivity().getSystemService(SENSOR_SERVICE);

        return view;


    }
    /* 传感器精度变化时回调
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
    /**
     * 传感器数据变化时回调
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        //判断传感器类别
        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER: //加速度传感器
                final float alpha = (float) 0.8;
                gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
                gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
                gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];


                String accelerometer = "加速度传感器\n" + "x:"
                        + (event.values[0] - gravity[0]) + "\n" + "y:"
                        + (event.values[1] - gravity[1]) + "\n" + "z:"
                        + (event.values[2] - gravity[2]);

                double highX = event.values[0] - gravity[0];
                double highY = event.values[1] - gravity[1];
                double highZ = event.values[2] - gravity[2];
                double highA = sqrt(highX*highX+highZ*highZ+highY*highY);

                int xxx = 0;
                if(event.values[2] - gravity[2]>2)
                {
                    xxx+=1;
                }



                tttt.setText("步数为"+xxx);
                tvAccelerometer.setText(accelerometer);
                //重力加速度9.81m/s^2，只受到重力作用的情况下，自由下落的加速度
                break;
            case Sensor.TYPE_GRAVITY://重力传感器
                gravity[0] = event.values[0];//单位m/s^2
                gravity[1] = event.values[1];
                gravity[2] = event.values[2];
                break;
            default:
                break;
        }

    }
    /**
     * 界面获取焦点，按钮可以点击时回调
     */
    public void onResume() {
        super.onResume();
        //注册加速度传感器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),//传感器TYPE类型
                SensorManager.SENSOR_DELAY_UI);//采集频率
        //注册重力传感器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY),
                SensorManager.SENSOR_DELAY_FASTEST);
    }
    /**
     * 暂停Activity，界面获取焦点，按钮可以点击时回调
     */
    @Override
    public void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

}


