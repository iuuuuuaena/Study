package com.example.mac.myrunapp;

import android.content.Context;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gcssloop.widget.ArcSeekBar;

import java.util.List;


public  class FragmentRun extends Fragment implements SensorEventListener {
    Button runButton;
    Button paintButton;
    RelativeLayout relativeLayout;
    TextView runText;
    private TextView StepView,StepD;
    static int progrem1;
    static int i =2;
    private MySensorEventListener mListener;
    private final String TAG = "tiny";
    SensorManager mSensorManager;
    Sensor stepCounter;
    float mSteps = 0;
    TextView tv1;
    TextView tv2;
    TextView ttt;
    private int mStepDetector = 0;  //
    private int mStepCounter = 0;   // 自
    // TextView text;
    @Override
    @Nullable
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.frag_run,container,false);
        ArcSeekBar mArcSeekBar;

        /*调取记步传感器*/
        mSensorManager = (SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);

        // getSensorList用于列出设备支持的所有sensor列表
        List<Sensor> sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        Log.i(TAG,"Sensor size:"+sensorList.size());
        for (Sensor sensor : sensorList) {
            Log.i(TAG,"Supported Sensor: "+sensor.getName());
        }

        ttt =view.findViewById(R.id.tttv);
        tv1 = view.findViewById(R.id.tv_step1);
        tv2 = view.findViewById(R.id.tv_step2);
        // 获取计步器sensor

        stepCounter = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(stepCounter != null){
            // 如果sensor找到，则注册监听器
            mSensorManager.registerListener(this,stepCounter,1000000);
        }
        else{
            Log.e(TAG,"no step counter sensor found");
        }


        mListener = new MySensorEventListener();


        runText = view.findViewById(R.id.run_text);
        relativeLayout=getActivity().findViewById(R.id.run_relative); //通过getActivity()获取activity_main.xml文件中id号为line1的LinearLayout布局
        StepView = view.findViewById(R.id.stepView);
        StepD = view.findViewById(R.id.stepD);
        runButton=view.findViewById(R.id.run_button);
        StepD.getPaint().setFlags(Paint.FAKE_BOLD_TEXT_FLAG);
        StepD.getPaint().setAntiAlias(true);//抗锯齿
        runButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 System.out.println( "progress的值为" +progrem1);
                 StepView.setText("你将要跑");
                 StepD.setText(progrem1+"");
                 System.out.println("text值为"+runText.getText());
                 if( i % 2 == 1){
                     System.out.println("i值为"+i);
                     runText.setText("点击结束");
                     runButton.setText("点击结束");
                     i+=1;
                  //  runButton.setText("点击结束");
                } else {
                    runText.setText("点击开始");
                    runButton.setText("点击开始");
                    i+=1;
                }
            }
        });
        paintButton=view.findViewById(R.id.go_paint);
        paintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                text.append("sadkjdas");
            }
        });


        mArcSeekBar = view.findViewById(R.id.arc_seek_bar);
        mArcSeekBar.setOnProgressChangeListener(new ArcSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(ArcSeekBar arcSeekBar, int progress, boolean isUser) {
//                Log.e("xiaobing", "变化" + progress);
                System.out.println( "progress的值为" +progress);
                progrem1 = progress;
                StepView.setText("设置步数：");
                StepD.setText(progrem1+"");
                   // mArcSeekBar.setProgress(progress);
              //  isUser = true;


            }

            @Override
            public void onStartTrackingTouch(ArcSeekBar seekBar) {
//                Log.e("xiaobing", "正在跑步" );
//                StepView.setText("设置步数为" );
//                StepD.setText("20000");


            }

            @Override
            public void onStopTrackingTouch(ArcSeekBar seekBar) {
//                Log.e("xiaobing", "变化" );
//                StepView.setText("设置步数为：" );
//                StepD.setText("0");

            }
        });
        mArcSeekBar.setArcColors(R.array.arc_colors_custom);

       // int[] colors = {0xFFFF0000, 0xFF00FF00, 0xFF0000FF};
       // mArcSeekBar.setArcColors(colors);
        mArcSeekBar.setMaxValue(20000);
        mArcSeekBar.setMinValue(0);



        return view;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        maxScreen();
    }

    //设置线性布局为满屏
    public void maxScreen(){
//        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(getActivity().getWindowManager().getDefaultDisplay().getWidth(),
//                getActivity().getWindowManager().getDefaultDisplay().getHeight()));
    }


    /*设置步数*/
    public void  Step(int step){
        StepView.setText("步数为"+step);
    }

    // 实现SensorEventListener回调接口，在sensor改变时，会回调该接口
    // 并将结果通过event回传给app处理


    class MySensorEventListener implements SensorEventListener {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
                if (event.values[0] == 1.0f) {
                    mStepDetector++;
                }
            } else if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
                mStepCounter = (int) event.values[0];
            }
            String desc1 = String.format("已走%d步", mStepDetector);
            String desc2 = String.format("共计%d步", mStepCounter);
            tv1.setText(desc1);
            //tv2.setText(desc2);

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        mSteps = event.values[0];
        Log.i(TAG, "Detected step changes:" + event.values[0]);
       // ttt.setText("开机到现在走了" + String.valueOf((int) mSteps) + "步");
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Log.i(TAG,"onAccuracyChanged");
    }

    public void onPause() {
        // if unregister this hardware will not detected the step changes
        // mSensorManager.unregisterListener(this);

        mSensorManager.unregisterListener(mListener);
        super.onPause();
    }

    public void onResume() {
        mSensorManager.registerListener(mListener, mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR),
                SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(mListener, mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER),
                SensorManager.SENSOR_DELAY_NORMAL);


        super.onResume();
    }


}
