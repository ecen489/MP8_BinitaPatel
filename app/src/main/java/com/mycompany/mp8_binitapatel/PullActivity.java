package com.mycompany.mp8_binitapatel;

/**
 * Created by Binita on 4/3/2019.
 */

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Pull extends AppCompatActivity {

    Button query1, query2, logout, push;
    EditText studentID;
    FirebaseAuth mAuth;
    FirebaseUser user;
    FirebaseDatabase fbdb;
    DatabaseReference dbrf;

    //vars for recycler
    private ArrayList<String> mStudents = new ArrayList<>();
    private ArrayList<String> mClass = new ArrayList<>();
    private ArrayList<String> mGrades = new ArrayList<>();
    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mStudents, mClass, mGrades, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull);

        query1 = (Button) findViewById(R.id.Query1);
        query2 = (Button) findViewById(R.id.Query2);
        logout = (Button) findViewById(R.id.signOut);
        push = (Button) findViewById(R.id.push);
        studentID = (EditText) findViewById(R.id.editText);

        fbdb = FirebaseDatabase.getInstance();
        dbrf = fbdb.getReference();

    }

    public void query1(View view){
        int ID = Integer.parseInt(studentID.getText().toString());
        DatabaseReference passID = dbrf.child("simpsons/students/"+ID);

        passID.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot data) {

                Grade grad = data.getValue(Grade.class);
                String course = grad.course_name;
                String course_grade = grad.grade;
                mClass.add(course);
                mGrades.add(course_grade);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Grade grades = new Grade();
    }


    public void logoutAccount(View view){
        mAuth.signOut();
        user =null;
        Toast.makeText(getApplicationContext(),"Logged Out", Toast.LENGTH_SHORT).show();
        //Take back to Main activity
    }

    public void toPush(View view){
        final Intent forwardIntent = new Intent(Pull.this, Push.class);
        startActivity(forwardIntent);
    }

}