package com.mathheals.tibbi_odev;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class head_info extends AppCompatActivity implements View.OnClickListener{

    private TextView date_take;
    private Button kaydet;
    private EditText sure;
    private String derece;

    private ListView list_view;
    List<head> Bas_list;

    //firebase için
    private FirebaseAuth firebaseAuth;
    DatabaseReference dataref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_info);

        //id yi aldık burada bas ağrısı bilgilerini ekle ve çek
        final Bundle useralınan = getIntent().getExtras();
        final String UserId_gelen = useralınan.getString("userıd");
        Toast.makeText(this, UserId_gelen, Toast.LENGTH_SHORT).show();

        //şimdiki zaman alındı.
        Date simdikiZaman = new Date();
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");

        date_take = (TextView) findViewById(R.id.date);
        date_take.setText(df.format(simdikiZaman));
        kaydet = (Button) findViewById(R.id.kaydet);
        sure = (EditText) findViewById(R.id.sure);

        list_view = (ListView) findViewById(R.id.bas_list);
        Bas_list = new ArrayList<>();

        firebaseAuth = FirebaseAuth.getInstance();
        dataref = FirebaseDatabase.getInstance().getReference("bas agrisi").child(UserId_gelen);


        //spinerı al xmlden
        final Spinner dropdown = (Spinner) findViewById(R.id.spinner);
        //spinera item gir
        String[] items = new String[]{"yüksek", "orta", "düşük"};
        //bu itemları dropdowna geçir adapter vasıtası ile
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set ettik dropdowna
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {


                switch (position) {
                    case 0:
                        derece = "yüksek";
                        break;
                    case 1:
                        derece = "orta";
                        break;
                    case 2:
                        derece = "düşük";
                        break;

                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        kaydet.setOnClickListener(this);
    }



    private void Info() {

        String tarih=date_take.getText().toString().trim();
        int sure_al=Integer.parseInt(sure.getText().toString().trim());
        String derece_al=derece.toString().trim();

        String id=dataref.push().getKey();
        head bas=new head(id,tarih,sure_al,derece_al);
        dataref.child(id).setValue(bas);
        Toast.makeText(head_info.this, "bas agrisi kaydedildi", Toast.LENGTH_SHORT).show();

    }






    protected void onStart() {
        super.onStart();

        dataref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Bas_list.clear();
                for(DataSnapshot basSnapshort:dataSnapshot.getChildren()){


                    head bas=basSnapshort.getValue(head.class);

                    Bas_list.add(bas);



                }
                head_adapter adapter=new head_adapter(head_info.this, Bas_list);
                list_view.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });







    }@Override
    public void onClick(View v) {
        if(v==kaydet){Info();}}}