package com.sds.tigher.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sds.tigher.MutiProgressView;
import com.sds.tigher.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final List<String> datas = new ArrayList<>();
        datas.add("门窗开启 14:00:03");
        datas.add("门窗开启 14:00:03");
        datas.add("门窗开启 14:00:03");
        datas.add("门窗开启 14:00:03");
        datas.add("门窗开启 14:00:03");
        datas.add("门窗开启 14:00:03");
        datas.add("门窗开启 14:00:03");
        datas.add("门窗开启 14:00:03");
        datas.add("门窗开启 14:00:03");
        datas.add("门窗开启 14:00:03");
        MutiProgressView mutiProgressView = (MutiProgressView) findViewById(R.id.progressView);
        mutiProgressView.setMutiProgressAdapter(new MutiProgressView.MutiProgressAdapter() {

            @Override
            public int getCount() {
                return datas.size();
            }

            @Override
            public List<String> getData() {
                return datas;
            }
        });
    }


}
