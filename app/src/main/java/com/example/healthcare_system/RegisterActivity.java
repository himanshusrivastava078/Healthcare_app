 package com.example.healthcare_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

 public class RegisterActivity extends AppCompatActivity {

    EditText edUsername, edEmail,edPassword,edConfirmPassword;
    Button btn;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUsername=findViewById(R.id.editTextLTBFullname);
        edEmail=findViewById(R.id.editTextLTBAddress);
        edPassword=findViewById(R.id.editTextLTBPinCode);
        edConfirmPassword=findViewById(R.id.editTextLTBContactNumber);
        btn = findViewById(R.id.buttonRegin);
        tv=findViewById(R.id.textViewExistingUser);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username= edUsername.getText().toString();
                String email= edEmail.getText().toString();
                String password= edPassword.getText().toString();
                String ConfirmPassword= edConfirmPassword.getText().toString();
                //object of data base
                Database db = new Database(getApplicationContext(),"healthcare",null,1 );

                if(username.length()==0 || email.length()==0 || password.length()==0 || ConfirmPassword.length()==0){
                    Toast.makeText(RegisterActivity.this, "Please fill All details", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(password.compareTo(ConfirmPassword)==0){
                        if(isValid(password)){
                            //create database
                            db.register(username,email,password);
                            Toast.makeText(RegisterActivity.this, "Record Inserted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        }
                        else{
                            Toast.makeText(RegisterActivity.this, "Password must contain atleast 8 character, having letter, digit and special symbol", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegisterActivity.this, "Password and confirm password did'n match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
    }


    //function for password checking
    public static boolean isValid(String passwordhere){
        int f1=0,f2=0,f3=0;
        if(passwordhere.length()<8){
            return false;
        }else{
            for(int p=0; p<passwordhere.length();p++){
                if(Character.isLetter(passwordhere.charAt(p))){
                    f1=1;
                }
            }
            for(int r=0; r<passwordhere.length();r++){
                if(Character.isDigit(passwordhere.charAt(r))){
                    f2=1;
                }
            }
            for(int s=0; s<passwordhere.length();s++){
                char c= passwordhere.charAt(s);
                if(c>=33 && c<=46||c==64){
                    f3=1;
                }
            }
            if(f1==1 && f2==1 && f3==1){
                return true;
            }
            return false;
        }
    }
}