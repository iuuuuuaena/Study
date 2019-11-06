package com.example.mac.myrunapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    Toolbar mToolbar;
    DrawerLayout drawer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(Color.WHITE);


        //  View headerView = onNavigationItemSelected();
//



//        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
//
//        // getSensorList用于列出设备支持的所有sensor列表
//        List<Sensor> sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
//        Log.i(TAG,"Sensor size:"+sensorList.size());
//        for (Sensor sensor : sensorList) {
//            Log.i(TAG,"Supported Sensor: "+sensor.getName());
//        }
//
//        steps = findViewById(R.id.steps);
//        time = findViewById(R.id.time);
//        // 获取计步器sensor
//        stepCounter = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
//        if(stepCounter != null){
//            // 如果sensor找到，则注册监听器
//            mSensorManager.registerListener(this,stepCounter,1000000);
//        }
//        else{
//            Log.e(TAG,"no step counter sensor found");
//        }
//        new TimeThread().start();




        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.nav_note:
                        mToolbar.setTitle("跑步");
                        FragmentRun fragmentRun = new FragmentRun();
                        getSupportFragmentManager().beginTransaction().replace(R.id.host_layout,fragmentRun).commit();

                        break;
                    case R.id.nav_A:
                        mToolbar.setTitle("加速度");
                        FragmentA fragmentA = new FragmentA();
                        getSupportFragmentManager().beginTransaction().replace(R.id.host_layout,fragmentA).commit();

                        break;
                    case R.id.nav_run_note:
                        mToolbar.setTitle("数据图表");
                        FragmentRunNote fragmentRunNote = new FragmentRunNote();
                        getSupportFragmentManager().beginTransaction().replace(R.id.host_layout,fragmentRunNote).commit();

                        break;
                    case R.id.nav_manage:
                        mToolbar.setTitle("工具");
                        FragmentManage fragmentManage = new FragmentManage();
                        getSupportFragmentManager().beginTransaction().replace(R.id.host_layout,fragmentManage).commit();

                        break;
                }
              //  onNavigationItemSelected(menuItem);
                menuItem.setChecked(true);//点击了把它设为选中状态
                drawer.closeDrawers();//关闭抽屉
                return true;
            }
        });






    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    //Toolbar最左侧的这个按钮就叫作HomeAsUp按钮，它默认的图标是一个返回的箭头，含义是返回上一个活动。
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_note) {
            // Handle the camera action
            mToolbar.setTitle("跑步");
        } else if (id == R.id.nav_A) {
            mToolbar.setTitle("加速度");
        } else if (id == R.id.nav_run_note) {
            mToolbar.setTitle("图表");
        } else if (id == R.id.nav_manage) {
            mToolbar.setTitle("");
        } else if (id == R.id.nav_share) {
            mToolbar.setTitle("发送");
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




    protected void onResume() {
        super.onResume();
    }
}
