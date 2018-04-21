package com.abt.gson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.orhanobut.logger.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    private TextView mTips = null;
    private StringBuilder mSB = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        refreshUI();
    }

    private void initView() {
        mTips = findViewById(R.id.tips);
        mSB = new StringBuilder();
    }

    private void refreshUI() {
        getData();

        mTips.post(new Runnable() {
            @Override
            public void run() {
                mTips.setText(mSB.toString());
            }
        });
    }

    private String getJsonForAssets() {
        StringBuilder builder = new StringBuilder();
        try {
            InputStreamReader isr = new InputStreamReader(getAssets().open("data.json"), "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
            br.close();
            isr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    private void getData() {
        //String data = "{a:1, b:'Hello,world!', c:{d:'5', e:'gson'}}";
        String data = getJsonForAssets();
        Gson gson = new Gson();
        Bean bean = gson.fromJson(data, Bean.class);
        mSB.append("key: c, value: ").append(bean.getC().toString()).append("\n");
        Logger.e(bean.getC().toString());
        try {
            LinkedTreeMap tm = (LinkedTreeMap)bean.getC();
            tm.put("f", "json");
            Iterator it = tm.keySet().iterator();
            while (it.hasNext()) {
                String key = (String)it.next();
                String value = (String)tm.get(key);
                mSB.append("key: " + key + ", value: " + value).append("\n");
                Logger.e("key: " + key + ", value: " + value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
