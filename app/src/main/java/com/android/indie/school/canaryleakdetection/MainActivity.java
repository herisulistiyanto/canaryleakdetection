package com.android.indie.school.canaryleakdetection;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    @BindView(R.id.tvStatus)
    TextView tvStatus;
    @BindView(R.id.btnAsync)
    Button btnAsync;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;

    SensorManager mSensorManager;
    List<Sensor> sensors;
    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initSensorManager();
    }


    private void moveToSecondActivity() {
        Intent intentMove = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intentMove);
        finish();
    }

    @OnClick(R.id.btnAsync)
    public void doAsync(View view) {

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                SystemClock.sleep(5000);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                showToast("MAIN ASYNC");
            }

        }.execute();

        moveToSecondActivity(); // It should be called when async process is done (onPostExecute),
    }

    private void initSensorManager() {
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor sensor : sensors) {
            mSensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onDestroy() {
//        Should unregister to avoid memLeak specified field, uncomment to solve
//        mSensorManager.unregisterListener(this);
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        Should unregister to avoid memLeak, uncomment to solve
//        mSensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        need to re-register sensor manager after unregister
//        for (Sensor sensor : sensors) {
//            mSensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
//        }
    }

    private void showToast(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
