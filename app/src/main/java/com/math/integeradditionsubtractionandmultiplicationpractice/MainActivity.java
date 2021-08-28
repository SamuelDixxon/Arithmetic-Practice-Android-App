package com.math.integeradditionsubtractionandmultiplicationpractice;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View w) {
                openMainProgram();
            }
        });
    }

    public void openMainProgram() {
        Intent intent = new Intent(this, MainActivityMath.class);
        startActivity(intent);
    }
}