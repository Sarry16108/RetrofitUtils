package com.example.yanghj.retrofitutils;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.RequestServer;
import retrofit.RequestUrls;
import retrofit.RetrofitUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.request)
    Button  mRequest;

    @BindView(R.id.request2)
    Button  mRequest2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ButterKnife.bind(this);


        mRequest.setOnClickListener(this);
        mRequest2.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.request:
                RequestServer.getInstance().getFreeMissions(this, RequestUrls.FREE_MISSION, "queryType", "1",
                        "version", "2.7", "deviceType", "5");
                break;
            case R.id.request2:
                RequestServer.getInstance().getFreeMissions2(this, RequestUrls.FREE_MISSION, "1", "2.7", "5");
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        RetrofitUtil.getInstance().cancelAllCalls(true);
    }
}
