package com.abt.gsondemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

/*    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String data = "{a:1, b:'Hello,world!', c:{d:5, e:'gson'}}";
        Gson gson = new Gson();
        Bean bean = gson.fromJson(data, Bean.class);
        Log.e(TAG, bean.getC().toString());
        try {
            JSONObject c = new JSONObject(bean.getC().toString());
            c.put("f", "json");
            Log.e(TAG, c.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String data = "{a:1, b:'Hello,world!', c:{d:'5', e:'gson'}}";
        Gson gson = new Gson();
        Bean bean = gson.fromJson(data, Bean.class);
        Log.e(TAG, bean.getC().toString());
        try {
            LinkedTreeMap tm = (LinkedTreeMap)bean.getC();
            tm.put("f", "json");
            Iterator it = tm.keySet().iterator();
            while (it.hasNext()) {
                String key = (String)it.next();
                String value = (String)tm.get(key);
                Log.e(TAG, "key: " + key + ", value: " + value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
