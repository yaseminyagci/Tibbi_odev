package com.mathheals.tibbi_odev;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class profile extends AppCompatActivity implements View.OnClickListener{
    private Button cikis;
    private TextView mail;
    private ImageView profil_photo;
    private FirebaseAuth firebaseAuth;
    private ImageView image1,image2,image3;
    private Uri filePath;

    ProgressDialog progress;
    FirebaseStorage fire_storage;
    StorageReference storageReference;
    DatabaseReference dataref;

    private static final int PICK_IMAGE_REQUEST = 71;
    final File localfile= File.createTempFile("images","jpg");

    public profile()throws IOException{}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
/*

        //gelen ıd kısmı
        Bundle useralınan=getIntent().getExtras();
        final  String UserId_gelen=useralınan.getString("userıd");
      //  final String  photoid=useralınan.getString("photoid");

        Toast.makeText(this, UserId_gelen, Toast.LENGTH_SHORT).show();

        System.out.print(UserId_gelen);
//        System.out.print("photo iddd"+photoid);

*/
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null){
            startActivity(new Intent(this,MainActivity.class));

        }
        final FirebaseUser user=firebaseAuth.getCurrentUser();

        fire_storage = FirebaseStorage.getInstance();
        storageReference=fire_storage.getReference();

        profil_photo=(ImageView)findViewById(R.id.profile_image);
        mail=(TextView)findViewById(R.id.mail);
        mail.setText("Merhaba\n");
        cikis=(Button)findViewById(R.id.input_exit);

        cikis.setOnClickListener(this);

       /* dataref= FirebaseDatabase.getInstance().getReference("User").child(user.getUid());

        dataref.addValueEventListener(new ValueEventListener() {
            String resim_id;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                resim_id = dataSnapshot.child("resim").getValue().toString();
                Toast.makeText(profile.this, "rresim id" +resim_id, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
*/


   /*     StorageReference indir = storageReference.child("Photos/").child(String.valueOf(user.getPhotoUrl()));
    indir.getFile(localfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
        @Override
        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
            Bitmap bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
            profil_photo.setImageBitmap(bitmap);
        }
    });
*/



        image1 = (ImageView) findViewById(R.id.book);
        image1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view1){
                Intent myIntent = new Intent(profile.this, head_info.class);
           //gönderilen id basagrisina
                Bundle bund=new Bundle();//veriyi diğer activityye yollamak için kullanıyoruz
                bund.putString("userıd",user.getUid());
                myIntent.putExtras(bund);


                startActivity(myIntent);
            }
        });



        //grafik istatistik olan kısma yönlendirme yapılır.
        image2 = (ImageView) findViewById(R.id.chart);
        image2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view1){
                Intent myIntent = new Intent(profile.this, chart_info.class);
                //gönderilen id basagrisina

                Bundle bund=new Bundle();//veriyi diğer activityye yollamak için kullanıyoruz
                bund.putString("userıd",user.getUid());
                myIntent.putExtras(bund);


                startActivity(myIntent);
            }
        });







    }





    @Override
    public void onClick(View v) {
        if(v==cikis){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(this,MainActivity.class));
        }else
        {}
       image1.setOnClickListener(this);


    }

}
