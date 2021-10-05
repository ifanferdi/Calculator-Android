package com.ifanferdi.projectcalculator;

import androidx.appcompat.app.AppCompatActivity;

import org.mariuszgromada.math.mxparser.*;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.textInput);
        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.display).equals(display.getText().toString())){
                    display.setText("");
                }
            }
        });
    }

    private void updateText(String  strToAdd){
        String oldStr = display.getText().toString();
        int cursorPosition = display.getSelectionStart();
        String leftStr  = oldStr.substring(0, cursorPosition);
        String rightStr = oldStr.substring(cursorPosition);

        if (getString(R.string.display).equals(display.getText().toString())){
            display.setText(strToAdd);
            display.setSelection(cursorPosition + 1);
        } else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            display.setSelection(cursorPosition + 1);
        }
    }

    public void btnNol(View view){
        updateText("0");
    }

    public void btnSatu(View view){
        updateText("1");
    }

    public void btnDua(View view){
        updateText("2");
    }

    public void btnTiga(View view){
        updateText("3");
    }

    public void btnEmpat(View view){
        updateText("4");
    }

    public void btnLima(View view){
        updateText("5");
    }

    public void btnEnam(View view){
        updateText("6");
    }

    public void btnTujuh(View view){
        updateText("7");
    }

    public void btnDelapan(View view){
        updateText("8");
    }

    public void btnSembilan(View view){
        updateText("9");
    }

    public void btnClear(View view){
        display.setText("");
    }

    public void btnKurung(View view){
        int cursorPosition = display.getSelectionStart();
        int kurungBuka = 0;
        int kurungTutup = 0;
        int textLen = display.getText().length();

        for(int i = 0; i < cursorPosition; i++){
            if (display.getText().toString().substring(i, i+1).equals("(")){
                kurungBuka += 1;
            }
            if (display.getText().toString().substring(i, i+1).equals(")")){
                kurungTutup += 1;
            }
        }

        if (kurungBuka == kurungTutup || display.getText().toString().substring(textLen-1, textLen).equals("(")){
            updateText("(");
        } else if (kurungTutup < kurungBuka && !display.getText().toString().substring(textLen-1, textLen).equals("(")){
            updateText(")");
        }
        display.setSelection(cursorPosition + 1);
    }

    public void btnPangkat(View view){
        updateText("^");
    }

    public void btnBagi(View view){
        updateText("÷");
    }

    public void btnKali(View view){
        updateText("×");
    }

    public void btnKurang(View view){
        updateText("-");
    }

    public void btnTambah(View view){
        updateText("+");
    }

    public void btnHasil(View view){
        String userExp = display.getText().toString();
        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");

        Expression expression = new Expression(userExp);

        String hasil = String.valueOf(expression.calculate());

        display.setText(hasil);
        display.setSelection(hasil.length());
    }

    public void btnKoma(View view){
        updateText(".");
    }

    public void btnPlusMinus(View view){
        updateText("-");
    }

    public void btnBackspace(View view){
        int cursorPosition = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPosition != 0 && textLen != 0){
            SpannableStringBuilder  selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPosition - 1, cursorPosition, "");
            display.setText(selection);
            display.setSelection(cursorPosition - 1);
        }
    }
}