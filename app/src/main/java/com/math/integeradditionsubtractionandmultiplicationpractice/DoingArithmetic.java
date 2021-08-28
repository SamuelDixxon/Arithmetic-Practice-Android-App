package com.math.integeradditionsubtractionandmultiplicationpractice;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.Math;

import androidx.appcompat.app.AppCompatActivity;

public class DoingArithmetic extends AppCompatActivity  {
    int n1l, n1u, n2l, n2u, rand1, rand2, answer; String operation, outputop;
    TextView getExpression, outputinfo, counter;
    EditText getInput;
    Button Submit;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
        setContentView(R.layout.activity_do_math);
        getExpression = findViewById(R.id.OutputExpression);
        outputinfo = findViewById(R.id.expression);
        counter = findViewById(R.id.Counter);
        getInput = findViewById(R.id.giveInput);
        getInput.setFocusableInTouchMode(true);
        Submit = findViewById(R.id.btnSubmit);
        Bundle bn = getIntent().getExtras();
        operation = bn.getString("op");
        getExpression.setText("User Selection\nOperation: " + operation + "\n" + "Range one " + bn.getInt("n1l") + " - " + bn.getInt( "n1u" ) + "\n" +  "Range two " + bn.getInt("n2l") + " - " + bn.getInt( "n2u" ));
        n1l = bn.getInt("n1l");
        n1u = bn.getInt("n1u");
        n2l = bn.getInt("n2l");
        n2u = bn.getInt("n2u");
        DoGame();
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainProgram();
            }
        });

        getInput.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                //If the keyevent is a key-down event on the "enter" button
                if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    try {
                        Integer.parseInt(getInput.getText().toString());
                        if ( (Integer.parseInt(getInput.getText().toString())) ==  answer ) {
                            getInput.setText("");
                            getInput.setHint("Got it right!");
                            DoGame();
                            UpdateCounter();
                        } else {
                            ResetCounter();
                            getInput.setText("");
                            getInput.setHint("Got it wrong!");
                        }
                    } catch (NumberFormatException e) {
                        Toast.makeText(DoingArithmetic.this, "This field requires a number", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                return false;
            }
        });

        getInput.setOnClickListener(new View.OnClickListener() { @Override  public void onClick(View view) { } });}


    void DoGame() {
        getInput.setSelection(0);
        rand1 = (int)Math.floor(Math.random()*(n1u-n1l+1)+n1l);
        rand2 = (int)Math.floor(Math.random()*(n2u-n2l+1)+n2l);
        switch(operation) {
            case "Add":
                outputop = " + ";
                answer = rand1+rand2;
                break;
            case "Subtract":
                outputop = " - ";
                answer = rand1-rand2;
                break;
            case "Multiply":
                outputop = " * ";
                answer = rand1*rand2;
                break;
        }
        outputinfo.setText(rand1 + outputop + rand2 + " = ?");
    }

    @Override
    protected void onResume() {
        super.onResume();
        getInput.setFocusableInTouchMode(true);
        getInput.requestFocus();
    }

    public void openMainProgram() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    void UpdateCounter() {
        int Get = Integer.parseInt(counter.getText().toString().replaceAll("\\D+",""));
        ++Get;
        counter.setText("Count: " + Get);
    }

    void ResetCounter() {
        counter.setText("Count: 0");
    }

}
