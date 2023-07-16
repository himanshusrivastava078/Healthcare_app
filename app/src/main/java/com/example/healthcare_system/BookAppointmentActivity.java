package com.example.healthcare_system;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {
    //ActivityUserBinding binding;
    TextView tv;
    EditText ed1,ed2,ed3,ed4;

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
     private Button btnBook , btnBack, dateButton,timeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);


        tv=findViewById(R.id.textViewLTBTitle);
        ed1=findViewById(R.id.editTextLTBFullname);
        ed2=findViewById(R.id.editTextLTBAddress);
        ed3=findViewById(R.id.editTextLTBPinCode);
        ed4=findViewById(R.id.editTextLTBContactNumber);
        dateButton =findViewById(R.id.buttonCartDatePicker);
        timeButton =findViewById(R.id.buttonCartTimePicker);
        btnBook= findViewById(R.id.buttonLTBBooking);
        btnBack= findViewById(R.id.buttonLTBBack);

        //By this edit text is not editable
        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        // we need to fetch the data with the help of intent
        Intent it = this.getIntent();
        String title = it.getStringExtra("text1");
        String fullname = it.getStringExtra("text2");
        String address = it.getStringExtra("text3");
        String contact = it.getStringExtra("text4");
        String fees= it.getStringExtra("text5");

        //display all the variable data to the text
        tv.setText(title);
        ed1.setText(fullname);
        ed2.setText(address);
        ed3.setText(contact );
        ed4.setText("Cons Fees "+ fees+ "/-");

        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        initTimePicker();
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookAppointmentActivity.this,DoctorDetailsActivity.class));
            }
        });

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(BookAppointmentActivity.this, "Appointment Book successfully", Toast.LENGTH_SHORT).show();
           Database db= new Database(getApplicationContext(),"healthcare",null,1);
           SharedPreferences sharedPreferences= getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
            String username = sharedPreferences.getString("username","").toString();
            if(db.checkAppointmentExists(username,title+"=>" + fullname,address,contact,dateButton.getText().toString(),timeButton.getText().toString())==1){
                Toast.makeText(BookAppointmentActivity.this, "Appointment already booked", Toast.LENGTH_SHORT).show();

            }else{
                db.addOrder(username,title+" =>"+ fullname , address, contact,0, dateButton.getText().toString(),timeButton.getText().toString(),Float.parseFloat(fees),"appointment");
                Toast.makeText(BookAppointmentActivity.this, "Your Appointment is done Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(BookAppointmentActivity.this,HomeActivity.class));
            }
            }
        });
    }
    //Float.parseFloat(fees
    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener= new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                year = year + 1;
                dateButton.setText(dayOfMonth+"/"+month+"/"+year);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog= new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+ 86400000);
    }
    private void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timeButton.setText(hourOfDay+":"+minute);
            }
        };
        Calendar cal = Calendar.getInstance();
        int hrs = cal.get(Calendar.HOUR);
        int mins = cal.get(Calendar.MINUTE);

        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog= new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);

    }

}