package com.example.healthcare_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicineActivity extends AppCompatActivity {

    private String[][] packages =
    {
            {" Uprise-D3 1000IU Capsule", " ","","", "50"},
            {" HealthVit Chromium Picolinate 200mcg Capsule", " ","","", "350"},
            {" Vitamin B Complex Capsule", " ","","", "448"},
            {" Inlife Vitamin E Wheat Germ Oil Capsule", " ","","", "539"},
            {" Dolo 650 Tablet", " ","","", "30"},
           {" Crocin 650 Advance Tablet", " ","","", "50"},
           {" Strepsils Medicated Lozenges for Sore Throat", " ","","", "40"},
           {" Tata 1mg Calcium + Vitamin D3 ", " ","","", "30"},
           {" Feronia-XT Tablet", " ","","", "130"}
    };


    private String[] package_details ={
           "Building and keeping the bones & teeth strong\n" +
                    "Reducing Fatigue/stress and muscular pains\n" +
                    " Boosting immunity and increasing resistance  against  infection",

            "Chromium is a essential trace mineral tht plays an important role in helping insulin regulate blood glucose"+
                   "Provide relief from Vitamin B deficiencies\n"+
                    "Helps in formation of red blood cells\n"+
                    "Maintains healthy nervous System ",

            " It promotes health as well as skin benefit.\n"+
                    "It helps reduce skin blemish and pigmentation\n +" +
                  "It Act as safe guard the skin from the Harsh UVA and UVB sun rays.",

            "Dolo 650 Tablet helps relive pain and fever by blocking the release of certain chemical messengers responsible for fever and pain",

            "Helps relive fever and bring down a high temperature\n" +
            "Suitable for people with a heart condition or high blood pressure",

            "Relieves the symptoms of a bacterial throat infection and soothes the recovery process\n" +
                   "Provides a warm and comforting feeling during sore throat",
                    " Reduce the risk of calcium deficiency, Rickets, and Osteoparosis \n" +
                        " Promotes mobility and flxibility of joints",
            "Helps to reduce the iron deficiency due to chronic blood loss or low intake of iron ",
                   "Helps to reduce the iron deficiency due to chronic blood loss or low intake of iron "


    };

    HashMap<String ,String> item;
    ArrayList list;

    ListView listView;
    SimpleAdapter sa;
    Button btnBack,btnGoToCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        listView=findViewById(R.id.listViewBM);
        btnBack=findViewById(R.id.buttonBMDBack);
        btnGoToCart=findViewById(R.id.buttonBMGoToCart);

        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this, CartBuyMedicineActivity.class));
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this, HomeActivity.class));
            }
        });

//        list = new ArrayList();
//        for (int i = 0; i < packages.length; i++) {
//            item = new HashMap<String, String>();
//            item.put("line1", packages[i][0]);
//            item.put("line2", packages[i][1]);
//            item.put("line3", packages[i][2]);
//            item.put("line4", packages[i][3]);
//            item.put("line5"," Total Cost : " + packages[i][4]);
//            list.add(item);
//        }
//        sa = new SimpleAdapter(this, list,
//                R.layout.multi_lines,
//                //its mapping
//                new String[]{"line1", "line2", "line3", "line4", "line5"},
//                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
//        );
//        listView.setAdapter(sa);
//
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
//                Intent it = new Intent (BuyMedicineActivity.this,BuyMedicineDetailsActivity.class);
//                it.putExtra("text1", packages[i][0]);
//                it.putExtra("text2", package_details[i]);
//                it.putExtra("text3", packages[i][4]);
//                startActivity(it);
//            }
//        });
        list = new ArrayList();
        for(int i=0; i<packages.length; i++){
            item = new HashMap<String, String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5","Total Cost : " +  packages[i][4]+ "/-");
            list.add(item);
        }

        sa= new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1", "line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});

        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it = new Intent (BuyMedicineActivity.this, BuyMedicineDetailsActivity.class);
                it.putExtra("text1", packages[i][0]);
               it.putExtra("text2", package_details[i]);
                it.putExtra("text3", packages[i][4]);
                startActivity(it);
            }
        });
    }
}