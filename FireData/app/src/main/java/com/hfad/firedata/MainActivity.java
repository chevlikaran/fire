package com.hfad.firedata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    EditText editText1;
    Button submit;
    Button submit1;
    String[] value2 = new String[2];
    DatabaseReference rootRef, demoRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.name);
        editText1 = (EditText) findViewById(R.id.password);
        submit = (Button) findViewById(R.id.sign_up);
        submit1 = (Button) findViewById(R.id.sign_in);
        //database reference pointing to root of database
        rootRef = FirebaseDatabase.getInstance().getReference();
        //database reference pointing to demo node
        demoRef = rootRef.child("demo3");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = editText.getText().toString();
                String value1 = editText1.getText().toString();
                if(value1.length() == 0 ){
                    editText1.setError("Required");
                }
                else if(value.length()==0) {
                    editText.setError("Required");
                }
                    else{
                    value2[0] = value;
                    value2[1] = value1;
                    List nameList = new ArrayList<String>(Arrays.asList(value2));


                    //push creates a unique id in database
                    demoRef.push().setValue(nameList);
                }
            }
        });
    }

    public void SignInM(View view){
        Intent intent = new Intent(this,SignInActivity.class);
        startActivity(intent);
    }
}
