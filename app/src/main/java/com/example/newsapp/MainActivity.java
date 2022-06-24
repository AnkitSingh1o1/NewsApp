package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements androidx,fr {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            Bundle bundle = new Bundle();
            bundle.putString("some_string", "trending");

            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container, NewsListFragment.class, bundle)
                    .commit();
        }

        //------------------Set OnClick Listeners---------------------------------------------------
        String clickedTextView = "";
        TextView comics = (TextView) findViewById(R.id.trending);
        comics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String clickedTextView = "trending";
                Bundle bundle = new Bundle();
                bundle.putString("some_string", clickedTextView);
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .add(R.id.fragment_container, NewsListFragment.class, null)
                        .commit();
            }
        });
        //------------------------------------------------------------------------------------------

    }
}