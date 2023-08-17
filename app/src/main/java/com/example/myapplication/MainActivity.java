package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.app.ActivityOptions;


public class MainActivity extends AppCompatActivity implements RecyclerAdapter.RecyclerListener {

    public static final int UNRESIZABLE = 0;
    public static final int ADJACENT = 1;
    public static final int MINIMUM = 2;
    public static final int BOUNDS = 3;
    public static final int BASIC = 4;

    public static final String INTENT = "intent.string.extra";

    public static String[] msgs = {
            "Unresizable activity\nThis activity cannot be opened in splitscreen or freeform mode. This is achieved by setting the resizableActivity attribute to false in the manifest.",
            "Adjacent activity\nThis activity will be launched adjacent to the current one by setting the corresponding flag in the intent. Only works in splitscreen mode.",
            "Minimum size activity\n This activity will open up with the minimum size defined in the manifest. The on screen placement is defined through the gravity attribute in manifest as well. Only works in freeform mode.",
            "Activity with set bounds\nThis activity should launch into a custom rect. Only works in freeform mode.",
            "Basic activity\nThis activity is just a default one with no special features.",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(new RecyclerAdapter(msgs, this));
    }

    @Override
    public void onClick(View v, int pos) {
        Intent intent = null;

        switch (pos) {
            case UNRESIZABLE:
                intent = new Intent(this, UnresizableActivity.class);
                intent.putExtra(INTENT, "This is an unresizable activity. It cannot be opened in splitscreen or freeform mode. This is achieved by setting the resizableActivity attribute to false in the manifest.");
                // This flag must be set in order to open the activity in a new window
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                break;

            case ADJACENT:
                intent = new Intent(this, AdjacentActivity.class);
                intent.putExtra(INTENT, "This activity has been launched adjacent to the current one by setting the corresponding flag in the intent. Only works in splitscreen mode.");
                intent.addFlags(Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT | Intent.FLAG_ACTIVITY_NEW_TASK);
                break;

            case MINIMUM:
                intent = new Intent(this, MinimumSizeActivity.class);
                intent.putExtra(INTENT, "This is an activity with the minimum size defined in the manifest. The on screen placement is defined through the gravity attribute in manifest as well. Only works in freeform mode.");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                break;

            case BOUNDS:
                Intent intent2 = new Intent(this, BoundsActivity.class);
                intent2.putExtra(INTENT, "This activity should launch into a custom rect. Only works in freeform mode.");
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                // Define the bounds in which the Activity will be launched into
                Rect bounds = new Rect(500, 300, 100, 0);

                // Set the bounds as an activity option
                ActivityOptions options = ActivityOptions.makeBasic();
                options.setLaunchBounds(bounds);

                startActivity(intent2, options.toBundle());
                break;

            case BASIC:
                intent = new Intent(this, BasicActivity.class);
                intent.putExtra(INTENT, "This is just a basic activity with no features");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}
/*

public class MainActivity extends AppCompatActivity {
Button newscreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newscreen=(Button) findViewById(R.id.newscreen);
        newscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity1.class);
                startActivity(intent);
            }
        });
    }
}*/
