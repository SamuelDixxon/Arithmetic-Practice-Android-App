package com.math.integeradditionsubtractionandmultiplicationpractice;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import android.os.Bundle;
public class MainActivityMath extends AppCompatActivity {
    EditText one; EditText two; EditText three; EditText four;
    int num1_lower; int num2_lower; int num1_upper; int num2_upper;
    String operation;
    Button begin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_math);
        Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(MainActivityMath.this, R.layout.my_spinner_style, getResources().getStringArray(R.array.names) );
        myAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        mySpinner.setAdapter(myAdapter);
        one = (EditText) findViewById(R.id.editTextNumber);
        two = (EditText) findViewById(R.id.editTextNumber2);
        three = (EditText) findViewById(R.id.editTextNumber3);
        four = (EditText) findViewById(R.id.editTextNumber4);
        begin = (Button) findViewById(R.id.toGo);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    num1_lower = Integer.parseInt(one.getText().toString());
                    num1_upper =  Integer.parseInt(two.getText().toString());
                    num2_lower =  Integer.parseInt(three.getText().toString());
                    num2_upper =  Integer.parseInt(four.getText().toString());
                    if (num1_lower > num1_upper || num2_lower > num2_upper) {
                        Toast.makeText(MainActivityMath.this, "The upper bound number in the range should be greater than the lower bound!", Toast.LENGTH_LONG).show();
                    } else {
                        operation = mySpinner.getSelectedItem().toString();
                        openMainProgram();
                    }
                } catch ( NumberFormatException e ) {
                    Toast.makeText(MainActivityMath.this, "This field requires a number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void openMainProgram() {
        Intent intent = new Intent(this, DoingArithmetic.class);
        intent.putExtra("n1l", num1_lower);
        intent.putExtra("n1u", num1_upper);
        intent.putExtra("n2l", num2_lower);
        intent.putExtra("n2u", num2_upper);
        intent.putExtra("op", operation);
        startActivity(intent);
    }
}