package com.junie.debug;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.junie.debuglog.DebugLog;
import com.junie.debuglog.setting.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DebugLog.getConfig().setLogLevel(Constant.All);


        DebugLog.i("纯message测试");
        DebugLog.i("拼接测试：我叫%s,今年%d岁了","小明",12);
        //长字符串
//        DebugLog.i(getString(R.string.string_long));
        //打印对象
        User user = new User();
        user.setName("小明");
        user.setAge(12);
        Apple apple = new Apple();
        apple.setName("大苹果");
        apple.setSize(9);
        user.setApple(apple);
        DebugLog.i(user); // FIXME: 2017/3/1

        Child child = new Child();
        child.setName("哇哈哈");
        child.setId(111);
        DebugLog.d(child);


//        DebugLog.i(new JSONObject());
        //打印Array　FIXME: 2017/3/1
        String[] string = {"小明","小红","小白","小绿"};
        DebugLog.i(string);
        double[][] doubles = {{1.2, 1.6, 1.7, 30, 33},
                {1.2, 1.6, 1.7, 30, 33},
                {1.2, 1.6, 1.7, 30, 33},
                {1.2, 1.6, 1.7, 30, 33}};
        //打印双重Array FIXME: 2017/3/1
        DebugLog.i(doubles);



        //打印JSONObject
        try {
            JSONObject obj = new JSONObject();
            obj.put("name","小明");
            obj.put("age",12);
            JSONObject obj1 = new JSONObject();
            obj1.put("school","小学");
            obj1.put("obj",obj);
            DebugLog.i(obj1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //打印JSONArray
        try {
            JSONArray array = new JSONArray();
            JSONObject obj1 = new JSONObject();
            obj1.put("name","小明");
            obj1.put("age",12);
            array.put(obj1);
            array.put(obj1);
            array.put(obj1);
            DebugLog.i(array);
            JSONObject obj = new JSONObject();
            obj.put("hhh","什么鬼");
            obj.put("array",array);
            DebugLog.i(obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //// FIXME: 2017/3/1  XML

        //打印Map  //// FIXME: 2017/3/1 逗号换行
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");
        map.put("key2", "value2");
        DebugLog.i(map);

        //打印List
        List<String> list = Arrays.asList("foo", "bar");
        DebugLog.i(list);


        //list测试
        List<Integer> arraylist = new ArrayList<Integer>(6);
        arraylist.add(new Integer(1));
        arraylist.add(new Integer(2));
        arraylist.add(new Integer(3));
        arraylist.add(new Integer(4));
        arraylist.add(new Integer(5));
        arraylist.add(new Integer(6));
        DebugLog.d(arraylist);


        Intent intent = new Intent();
        intent.setAction("I am Action");
        intent.putExtra("aaa","bbbbb");
        intent.putExtra("ccc",123);
        intent.putExtra("user",user);
        DebugLog.d(intent);
    }

    private Handler smsHandler = new Handler();


}


