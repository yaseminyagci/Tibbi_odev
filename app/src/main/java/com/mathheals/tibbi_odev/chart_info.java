package com.mathheals.tibbi_odev;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class chart_info extends AppCompatActivity {

    private List<head> list;
    private int[] sure_al;
    private String derece;
    private String[] tarih;
    PieChart chart;
    PieChart barchart;



    private FirebaseAuth firedat;
    private DatabaseReference datref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_info);


        final Bundle useralınan=getIntent().getExtras();
        final  String UserId_gelen=useralınan.getString("userıd");
        Toast.makeText(this, UserId_gelen, Toast.LENGTH_SHORT).show();

        firedat=FirebaseAuth.getInstance();
        datref= FirebaseDatabase.getInstance().getReference("bas agrisi").child(UserId_gelen);

        chart=(PieChart)findViewById(R.id.pasta);


        bilgilerial();
    }
   /* private  void bilgilerial(){
        datref.addValueEventListener(new ValueEventListener() {

            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                List<PieEntry>pieentities=new ArrayList<PieEntry>();

                int[]dataa;
                int toplam=0,toplamdüs=0,toplamort=0;


                for(DataSnapshot basSnapshort:dataSnapshot.getChildren()) {

                    head kitap = basSnapshort.getValue(head.class);

                    if (kitap.getDerece() == "yüksek")
                        toplam += kitap.getSure();

                    else if (kitap.getDerece() == "düşük")
                        toplamdüs += kitap.getSure();
                    else if(kitap.getDerece() == "orta")
                        toplamort += kitap.getSure();

                }
                dataa= new int[]{toplam, toplamdüs, toplamort};

                String[] derecem=new String[]{"yüksek","düşük","orta"};
                for(int i=0;i<3;i++){

                    pieentities.add(new PieEntry(dataa[i],derecem[i]));
                }
                PieDataSet dataset=new PieDataSet(pieentities,"");

                dataset.setColors(ColorTemplate.COLORFUL_COLORS);
                PieData data=new PieData(dataset);

                chart.setData(data);
                chart.invalidate();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
*/

    private  void bilgilerial(){
        datref.addListenerForSingleValueEvent(new ValueEventListener() {

            int toplam=0,toplamdüs=0,toplamort=0;

            List<PieEntry>pieentities=new ArrayList<PieEntry>();
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {

                int[]dataa;


                for(DataSnapshot basSnapshort:dataSnapshot.getChildren()) {

                    head kitap = basSnapshort.getValue(head.class);

                    if (kitap.getDerece() == "yüksek")
                        toplam += kitap.getSure();
                    else if (kitap.getDerece() == "düşük")
                        toplamdüs += kitap.getSure();
                    else if(kitap.getDerece() == "orta")
                        toplamort += kitap.getSure();

                }
                dataa= new int[]{toplam, toplamdüs, toplamort};

                String[] derecem=new String[]{"yüksek","düşük","orta"};
                for(int i=0;i<3;i++){

                    pieentities.add(new PieEntry(dataa[i],derecem[i]));
                }
                PieDataSet dataset=new PieDataSet(pieentities,"");

                dataset.setColors(ColorTemplate.COLORFUL_COLORS);
                PieData data=new PieData(dataset);

                chart.setData(data);
                chart.invalidate();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });





    }



    }



