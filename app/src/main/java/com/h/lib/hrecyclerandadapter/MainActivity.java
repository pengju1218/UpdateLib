package com.h.lib.hrecyclerandadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.h.lib.hrecycandadapter.recyclerview.HRecyclerView;

public class MainActivity extends AppCompatActivity {

    HRecyclerView mHRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
