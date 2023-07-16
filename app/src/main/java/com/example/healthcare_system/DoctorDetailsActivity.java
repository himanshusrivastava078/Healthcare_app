package com.example.healthcare_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {

    //create hardcoded array
    private String[][] doctor_details1 =
            {
                    {"Doctor Name : Ajit Srivastava", "Hospital Address : Rawatpur", "Exp : 5yrs", "Mobile No: 9889898345", " 600"},
                    {"Doctor Name : Prasad Pawar", "Hospital Address : Pune", "Exp : 15yrs", "Mobile No: 7464898345", " 1600"},
                    {"Doctor Name : Swapnil Tomar ", "Hospital Address : Kanpur", "Exp : 8yrs", "Mobile No: 7625898345", " 1000"},
                    {"Doctor Name : Deepak Tawade", "Hospital Address : Bada Phatak", "Exp : 25yrs", "Mobile No: 7009898345", " 2800"},
                    {"Doctor Name : Ashok Panda", "Hospital Address : Katraj ", "Exp : 35yrs", "Mobile No: 7389898345", " 4000"}
            };
    private String[][] doctor_details2 =
            {
                    {"Doctor Name : Seema Shukla ", "Hospital Address : Prayagraj", "Exp : 5yrs", "Mobile No: 9089898345", " 900"},
                    {"Doctor Name : Ramesh Koswani", "Hospital Address : Mirzapur", "Exp : 10yrs", "Mobile No: 7964898345", " 1500"},
                    {"Doctor Name : Swati Pal ", "Hospital Address : Barelli", "Exp : 18yrs", "Mobile No: 9025898345", " 1900"},
                    {"Doctor Name : Ravi Tawade", "Hospital Address : Morodabad", "Exp : 25yrs", "Mobile No: 7809898345", " 2800"},
                    {"Doctor Name : Neha Pal ", "Hospital Address : Kanpur ", "Exp : 35yrs", "Mobile No: 7389898345", " 4000"}
            };
    private String[][] doctor_details3 =
            {
                    {"Doctor Name : Nitesh Srivastava", "Hospital Address : Mumbai ", "Exp : 5yrs", "Mobile No: 8089898345", " 400"},
                    {"Doctor Name : Ashu Pal", "Hospital Address : Kanpur", "Exp : 15yrs", "Mobile No: 7464898345", " 1600"},
                    {"Doctor Name : Swapnil Tomar ", "Hospital Address : Kanpur", "Exp : 8yrs", "Mobile No: 7625898345", " 1000"},
                    {"Doctor Name : Deepak Tawade", "Hospital Address : Bada Phatak", "Exp : 25yrs", "Mobile No: 7009898345", " 2800"},
                    {"Doctor Name : Ashok Panda", "Hospital Address : Katraj ", "Exp : 35yrs", "Mobile No: 7389898345", " 4000"}
            };
    private String[][] doctor_details4 =
            {
                    {"Doctor Name : Ravi  Srivastava", "Hospital Address : Lucknow", "Exp : 5yrs", "Mobile No: 9889898345", " 600"},
                    {"Doctor Name : Prakash Pawar", "Hospital Address : Pune", "Exp : 15yrs", "Mobile No: 7464898345", " 1600"},
                    {"Doctor Name : Swadeepti Tomar ", "Hospital Address : Kanpur", "Exp : 8yrs", "Mobile No: 7625898345", " 1000"},
                    {"Doctor Name : Deepak Chahar", "Hospital Address : Bada Phatak", "Exp : 25yrs", "Mobile No: 7009898345", " 2800"},
                    {"Doctor Name : Ashoka Panda", "Hospital Address : Katraj ", "Exp : 35yrs", "Mobile No: 7389898345", " 4000"}
            };
    private String[][] doctor_details5 =
            {
                    {"Doctor Name : Nilesh Srivastava", "Hospital Address : Lucknow", "Exp : 5yrs", "Mobile No: 9889898345", " 600"},
                    {"Doctor Name : Swami Pawar", "Hospital Address : Pune", "Exp : 15yrs", "Mobile No: 7464898345", " 1600"},
                    {"Doctor Name : anil Tomar ", "Hospital Address : Kanpur", "Exp : 8yrs", "Mobile No: 7625898345", " 1000"},
                    {"Doctor Name : Deepika Tawade", "Hospital Address : Bada Phatak", "Exp : 25yrs", "Mobile No: 7009898345", " 2800"},
                    {"Doctor Name : Antima   Panda", "Hospital Address : Katraj ", "Exp : 35yrs", "Mobile No: 7389898345", " 4000"}
            };

    TextView tv;
    Button btn;
    String[][] doctor_details = {};

    HashMap<String,String> item;                     // initialize hash map
    ArrayList list;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        btn = findViewById(R.id.buttonBMDBack);
        tv = findViewById(R.id.textViewDDTitle);
       // ListView lst = findViewById(R.id.listViewDD);

        Intent it = getIntent();                          //create object of intent
        String title = it.getStringExtra("title");      // intent k jariye titile me value get ki
        tv.setText(title);                                  // title ko textView me pass kra diya as aargumrnt

        if (title.compareTo("Family Physicisn") == 0) {
            doctor_details = doctor_details1;
        } else if (title.compareTo("Dietician") == 0) {
            doctor_details = doctor_details2;
        } else if (title.compareTo("Dentist") == 0) {
            doctor_details = doctor_details3;
        } else if (title.compareTo("Surgeon") == 0) {
            doctor_details = doctor_details4;
        } else
            doctor_details = doctor_details5;


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });

        list = new ArrayList();
        for (int i = 0; i < doctor_details.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", doctor_details[i][0]);
            item.put("line2", doctor_details[i][1]);
            item.put("line3", doctor_details[i][2]);
            item.put("line4", doctor_details[i][3]);
            item.put("line5", "cons Fees:" + doctor_details[i][4] + "/-");
            list.add(item);
        }
        // create simple adapter object
        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                //its mapping
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
        );
        ListView lst = findViewById(R.id.listViewBM);
        lst.setAdapter(sa);


        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);

                //all the data parse to other activity
                it.putExtra("text1", title);
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);

            }
        });

   }
}