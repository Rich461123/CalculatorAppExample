package com.example.calculatorappexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {

    TextView workingsTV;
    TextView resultTV;

    String workings = "";
    String formula;
    String tempFormula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextView();

    }

    private void initTextView() {
        workingsTV = (TextView) findViewById(R.id.workingsTextview);
        resultTV = (TextView) findViewById(R.id.resultTextView);
    }

    private void setWorkings(String givenValue){
        workings = workings + givenValue;
        workingsTV.setText(workings);
//        if(resultTV.getText().toString() == "" && givenValue == "+" && givenValue == "/" && givenValue == "*"){
//                resultTV.setText("");
//                workingsTV.setText("");
//                workings = "";
//                workingsTV.setText(workings);
//        }else {
//            if(resultTV.getText().toString() == ""){
//                workings = workings + givenValue;
//                workingsTV.setText(workings);
//            }else if(resultTV.getText().toString() != ""){
//                resultTV.setText("");
//                workingsTV.setText("");
//                workings = "";
//                workings = workings + givenValue;
//                workingsTV.setText(workings);
//            }
//            }
//        }
//        resultTV.setText("");
//        if(resultTV.getText().toString() == ""){
//            workings = workings + givenValue;
//            workingsTV.setText(workings);
//        }else if(resultTV.getText().toString() != ""){
//                resultTV.setText("");
//                workingsTV.setText("");
//                workings = "";
//                workings = workings + givenValue;
//                workingsTV.setText(workings);
//        }
    }


    public void equalsOnClick(View view) {
        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        checkForPowerOf();

        try {
            result = (double) engine.eval(formula);
        } catch (ScriptException e) {
            Toast.makeText(this, "Invaild Input", Toast.LENGTH_SHORT).show();
        }

        if(result != null)
            resultTV.setText(String.valueOf(result.doubleValue()));
    }

    private void checkForPowerOf() {
        ArrayList<Integer> indexOfPowers = new ArrayList<>();
        for(int i = 0; i < workings.length(); i++){
            if(workings.charAt(i) == '^')
                indexOfPowers.add(i);
        }

        formula = workings;
        tempFormula = workings;
        for (Integer index : indexOfPowers){
            changeFormula(index);
        }

        formula = tempFormula;
    }

    private void changeFormula(Integer index) {

        String numberLeft = "";
        String numberRight = "";

        for(int i = index + 1; i < workings.length(); i++){
            if(isNumeric(workings.charAt(i)))
                numberRight = numberRight + workings.charAt(i);
            else break;
        }

        for(int i = index - 1; i >= 0; i--){
            if(isNumeric(workings.charAt(i)))
                numberLeft = numberLeft + workings.charAt(i);
            else break;
        }

        String original = numberLeft + "^" + numberRight;
        String changed = "Math.pow(" + numberLeft + "," + numberRight + ")";
        tempFormula = tempFormula.replace(original, changed);
    }

    private boolean isNumeric(char c){
        if((c <= '9' && c >= '0') || c == '.')
            return true;
        return false;
    }

    public void clearOnClick(View view) {
        setWorkings("");
        workings = "";
        resultTV.setText("");
        leftBracket = true;
    }

    boolean leftBracket = true;
    public void bracketsOnClick(View view) {
        if(leftBracket){
            setWorkings("(");
            leftBracket = false;
        }else {
            setWorkings(")");
            leftBracket = true;
        }
    }

    public void powerOfOnClick(View view) {
        setWorkings("^");
    }

    public void divisionOnClick(View view) {
            setWorkings("/");
    }

    public void decimaOnClick(View view) {
        setWorkings(".");
    }

    public void minusOnClick(View view) {
        setWorkings("-");
    }

    public void plusOnClick(View view) {
        setWorkings("+");
    }

    public void timesOnClick(View view) {
        setWorkings("*");
    }

    public void sevenOnClick(View view) {
            setWorkings("7");
    }

    public void eightOnClick(View view) {
            setWorkings("8");
    }

    public void nineOnClick(View view) {
            setWorkings("9");
    }

    public void fourOnClick(View view) {
            setWorkings("4");
    }

    public void fiveOnClick(View view) {
            setWorkings("5");
    }

    public void sixOnClick(View view) {
            setWorkings("6");
    }

    public void oneOnClick(View view) {
            setWorkings("1");
    }

    public void twoOnClick(View view) {
            setWorkings("2");
    }

    public void threeOnClick(View view) {
            setWorkings("3");
    }

    public void zeroOnClick(View view) {
            setWorkings("0");
    }
}