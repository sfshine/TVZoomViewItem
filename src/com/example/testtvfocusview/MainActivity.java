package com.example.testtvfocusview;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity
{
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        setContentView(R.layout.activity_tvui4);
        //        findViewById(R.id.view_4).setNextFocusRightId(R.id.view_5); //需要programly才可以实现nextFocus, 否则不好使
        //        findViewById(R.id.view_8).setNextFocusRightId(R.id.view_1);
        setContentView(R.layout.activity_main);
    }
    
}
