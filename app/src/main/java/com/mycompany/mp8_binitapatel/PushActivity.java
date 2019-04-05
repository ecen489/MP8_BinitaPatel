package com.mycompany.mp8_binitapatel;

/**
 * Created by Binita on 4/3/2019.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PushActivity extends AppCompatActivity {

    FirebaseDatabase fbdb;
    DatabaseReference dbrf;
    EditText className;
    EditText classID;
    EditText classGrade;
    EditText passwd;
    TextView myTxt;
    int stuID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push);


        fbdb = FirebaseDatabase.getInstance();
        dbrf = fbdb.getReference();

        classID = findViewById(R.id.classID);
        className = findViewById(R.id.className);
        classGrade = findViewById(R.id.classGrade);



    }

    public void onRadioButtonClicked_Bart(View view) {
        stuID = 123;
    }

    public void onRadioButtonClicked_Ralph(View view) {
        stuID = 404;
    }

    public void onRadioButtonClicked_Milhouse(View view) {
        stuID = 456;
    }

    public void onRadioButtonClicked_Lisa(View view) {
        stuID = 888;
    }

    public void pushClick(View view) {
        int stuclassID = Integer.valueOf(classID.getText().toString());
        String stuclassName = className.getText().toString();
        String stuclassGrade = classGrade.getText().toString();




        Grade grade = new Grade(stuclassID,stuclassName,stuclassGrade,stuID);

        DatabaseReference insLoc = dbrf.child("simpsons/grades/");
        DatabaseReference ranKey = insLoc.push();
        ranKey.setValue(grade);
        Intent backIntent = new Intent();
        setResult(RESULT_OK, backIntent);
        finish();
    }
}