package com.android.indie.school.canaryleakdetection;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by herisulistiyanto on 12/21/16.
 */

public class SecondActivity extends AppCompatActivity {

    @BindView(R.id.tvStatus)
    TextView tvStatus;
    @BindView(R.id.activity_second)
    RelativeLayout activitySecond;

    private final String TAG = SecondActivity.class.getSimpleName();

    private final Handler mLeakyHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        anotherAsyncProcess();
        mLeakyHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showToast("SECOND HANDLER");
            }
        }, 3000);

        //it should be accessed when all async process is done, not like this way
        Intent backIntent = new Intent(SecondActivity.this, MainActivity.class);
        startActivity(backIntent);
        finish();
    }

    private void anotherAsyncProcess() {

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                SystemClock.sleep(5000);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                showToast("SECOND ASYNC");
            }
        }.execute();
    }

    private void showToast(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
//        it should remove callback n message from your handler
//        mLeakyHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
