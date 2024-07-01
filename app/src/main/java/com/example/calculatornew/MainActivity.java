
package com.example.calculatornew;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv,solutionTv;
    MaterialButton buttonC,buttonBrackOpen,buttonBrackClose;
    MaterialButton buttonDivide,buttonMultiply,buttonPlus,buttonMinus,buttonEquals;
    MaterialButton button0,button1,button2,button3,button4,button5, button6,button7,button8,button9;
    MaterialButton buttonAC, buttonDot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);
        assignId(buttonC,R.id.button_c);
        assignId(button0,R.id.button_zero);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);
        assignId(buttonAC,R.id.button_ac);
        assignId(buttonDivide,R.id.button_divide);
        assignId(buttonMultiply,R.id.button_multiply);
        assignId(buttonPlus,R.id.button_add);
        assignId(buttonMinus,R.id.button_minus);
        assignId(buttonDot,R.id.button_dot);
        assignId(buttonBrackClose,R.id.button_close_bracket);
        assignId(buttonBrackOpen,R.id.button_open_bracket);
        assignId(buttonEquals,R.id.button_equals);

    }
    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view){
        MaterialButton button=(MaterialButton) view;
        String buttonText=button.getText().toString();
        String dataToCalculate=solutionTv.getText().toString();
        if(buttonText.equals("AC")){
            solutionTv.setText(" ");
            resultTv.setText("0");
            return;
        }
        if (buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }

        if(buttonText.equals("C")){
            dataToCalculate=dataToCalculate.substring(0,dataToCalculate.length()-1);

        }
        else
        {
            dataToCalculate=dataToCalculate+buttonText;
        }

        solutionTv.setText(dataToCalculate);
        String finalResult=getResult(dataToCalculate);
        if(!finalResult.equals("err")){
            resultTv.setText(finalResult);
        }
    }
    String getResult(String data){
         try{
             Context context=Context.enter();
             context.setOptimizationLevel(-1);
             Scriptable scriptable=context.initStandardObjects();
             String finalResult=context.evaluateString(scriptable,data,"javascript",1,null).toString();
             return finalResult;

         }
         catch (Exception e)
         {
             return "err";
         }
    }


}



