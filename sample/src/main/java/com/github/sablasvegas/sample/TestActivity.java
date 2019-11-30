package com.github.sablasvegas.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.sablasvegas.shadout.Shadout;

public class TestActivity extends AppCompatActivity {

    Button button;
    Shadout shadout;
    int i = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        shadout = findViewById(R.id.shad);
        button = findViewById(R.id.butt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                if (i == 0)
                    shadout.setMShadowColor(getResources().getColor(R.color.color_blue_shadow));
                if (i == 1)
                    shadout.setMShadowColor(getResources().getColor(R.color.colorPrimary));
                if (i == 2) {
                    shadout.setMShadowColor(TestActivity.this.getResources().getColor(R.color.color_pink_shadow));
                    i = -1;
                }

            }
        });
    }
}
