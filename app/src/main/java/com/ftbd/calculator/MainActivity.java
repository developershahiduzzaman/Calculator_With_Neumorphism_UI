package com.ftbd.calculator;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import soup.neumorphism.NeumorphButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv,SolutionTv;
    NeumorphButton ac,c, percent, divider,seven,eight,nine,into,four,five,six,minus;
    NeumorphButton one,two,three,plus, doubleZero,zero,point,equal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTv = findViewById(R.id.textV2);
        SolutionTv = findViewById(R.id.textV1);

        assignId(ac ,R.id.ac);
        assignId(c ,R.id.c);
        assignId(percent ,R.id.percent);
        assignId(divider ,R.id.divider);
        assignId(seven ,R.id.seven);
        assignId(eight ,R.id.eight);
        assignId(nine ,R.id.nine);
        assignId(into ,R.id.into);
        assignId(four ,R.id.four);
        assignId(five ,R.id.five);
        assignId(six ,R.id.six);
        assignId(minus ,R.id.minus);
        assignId(one ,R.id.one);
        assignId(two ,R.id.two);
        assignId(three ,R.id.three);
        assignId(plus ,R.id.plus);
        assignId(doubleZero ,R.id.doubleZero);
        assignId(zero ,R.id.zero);
        assignId(point ,R.id.point);
        assignId(equal ,R.id.equal);



    }

    void assignId (NeumorphButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        NeumorphButton neumorphButton = (NeumorphButton) view;
        String buttonText = neumorphButton.getText().toString();
        String dataCalculate = SolutionTv.getText().toString();



        if (buttonText.equals("AC")){
            SolutionTv.setText("");
            resultTv.setText("0");
            return;
        }

        if (buttonText.equals("=")){
            SolutionTv.setText(resultTv.getText());
            return;
        }

        if (buttonText.equals("C")){
            dataCalculate = dataCalculate.substring(0,dataCalculate.length()-1);

        }else {
            dataCalculate = dataCalculate+buttonText;

        }
        SolutionTv.setText(dataCalculate);
        String final_result = result(dataCalculate);

        if (!final_result.equals("Error")){
            resultTv.setText(final_result);
        }

    }

    String result(String data){
        //return "Calculate";

        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
           String final_result = context.evaluateString(scriptable,data,
                    "Javascript",1,null)
                    .toString();

           if (final_result.endsWith(".0")){
               final_result = final_result.replace(".0","");
           }
           return final_result;
        }catch (Exception e){
            return "Error";
        }
    }
}