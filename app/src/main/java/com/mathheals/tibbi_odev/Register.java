package com.mathheals.tibbi_odev;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by user on 14.10.2017.
 */

public class Register extends AppCompatActivity implements View.OnClickListener{
    private EditText email;
    private EditText sifre;
    private EditText isim,doktor,hastane;
    private Button kayit_ol;
    private TextView kayit;
    private ProgressDialog progress;

    private ImageView resim;


    //resim için buton, storage ve url
    private Button mSelectImage;
    private StorageReference mStorage;
    private Uri uri;
    final String idm=UUID.randomUUID().toString();
    private static final int PICK_IMAGE_REQUEST = 71;

    DatabaseReference dataref;
    FirebaseAuth firebaseauth;

    //resim için
    FirebaseStorage fire_storage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);


        firebaseauth=FirebaseAuth.getInstance();
        dataref= FirebaseDatabase.getInstance().getReference();


        //resim için

        fire_storage =FirebaseStorage.getInstance();
        storageReference=fire_storage.getReference();

        if(firebaseauth.getCurrentUser()!=null){

            finish();
            startActivity(new Intent(getApplicationContext(),profile.class));

        }
        progress=new ProgressDialog(this);

        email=(EditText)findViewById(R.id.input_email);
        sifre=(EditText)findViewById(R.id.input_password);
        isim=(EditText)findViewById(R.id.input_name);
        doktor=(EditText)findViewById(R.id.doktor);
        hastane=(EditText)findViewById(R.id.hastane);

        kayit_ol=(Button)findViewById(R.id.btn_signup);
        kayit=(TextView)findViewById(R.id.link_login);




        kayit_ol.setOnClickListener(this);
        kayit.setOnClickListener(this);

        resim=(ImageView)findViewById(R.id.image);
        mStorage = FirebaseStorage.getInstance().getReference();
        mSelectImage = (Button) findViewById(R.id.res_sec);

        mSelectImage.setOnClickListener(new View.OnClickListener() {
                                            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
                                            @Override
                                            public void onClick(View view) {
                                                Intent intent = new Intent();
                                                intent.setType("image/*");
                                                intent.setAction(Intent.ACTION_GET_CONTENT);

                                                //resim seçmek için galeriyi açıyoruz secresim ise galeriyi açınca üstünde yazan isim
                                                startActivityForResult(Intent.createChooser(intent,"SEC RESİM"),PICK_IMAGE_REQUEST);
                                            }
                                        }

        );



    }


    private void Register_ol() {
        String isim_reg = isim.getText().toString().trim();
        String email_reg = email.getText().toString().trim();
        String password_reg = sifre.getText().toString().trim();
        String doktor_reg=doktor.getText().toString().trim();
        String hastane_reg=hastane.getText().toString().trim();

        if (TextUtils.isEmpty(email_reg)) {
            Toast.makeText(this, "sifrenizi giriniz", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password_reg)) {
            Toast.makeText(this, "pasaportunuzu giriniz", Toast.LENGTH_SHORT).show();
            return;
        }


        if (TextUtils.isEmpty(isim_reg)) {
            Toast.makeText(this, "isiminizi giriniz", Toast.LENGTH_SHORT).show();
            return;
        }


        progress.setMessage("registered... (kayıt olunuyor)");
        progress.show();

        firebaseauth.createUserWithEmailAndPassword(email_reg,password_reg).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){


                    Intent intent=new Intent(getApplicationContext(),profile.class);
                    Bundle bund=new Bundle();//veriyi diğer activityye yollamak için kullanıyoruz
                    bund.putString("photoid",idm);
                    intent.putExtras(bund);

                    saveuser(idm);

                    Toast.makeText(Register.this, "registered success", Toast.LENGTH_SHORT).show();


                    String userID =firebaseauth.getInstance().getCurrentUser().getUid();

                    bund.putString("userıd",userID);
                    intent.putExtras(bund);
                    startActivity(intent);
                    finish();



                }
                else
                {
                    Toast.makeText(Register.this, "couldnt registered", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void saveuser(String id_photo){
        String isim_reg = isim.getText().toString().trim();
        String email_reg = email.getText().toString().trim();
        String password_reg = sifre.getText().toString().trim();
        String doktor_reg=doktor.getText().toString().trim();
        String hastane_reg=hastane.getText().toString().trim();


        String id=dataref.child("User").push().getKey();
        User user=new User(id,isim_reg,email_reg,password_reg,doktor_reg,hastane_reg);
        user.setResim(idm);
        System.out.println("user udm"+user.getResim());
        FirebaseUser user_fire=firebaseauth.getCurrentUser();
        dataref.child("User").child(id).setValue(user);
       // upload_image(id_photo);



        //dataref.child(user_fire.getUid()).setValue(user);

        Toast.makeText(this, "kaydedildi", Toast.LENGTH_SHORT).show();



    }
   /* private void upload_image(String id_photo){
        if(uri!=null){
            progress.setTitle("resim yükleniyor");
            progress.show();



            StorageReference ref=storageReference.child("images/"+id_photo );
            ref.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progress.dismiss();
                            Toast.makeText(Register.this,"yüklendi",Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progress.dismiss();
                    Toast.makeText(Register.this, "yüklenemedi", Toast.LENGTH_SHORT).show();
                }
            });
               /* .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double Progress=(100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                        progress.setMessage("yüklendi"+(int)Progress+"%");
                    }
                });}
    }*/

    @Override
    public void onClick(View v) {
        if(v==kayit_ol){Register_ol();}
        if(v==kayit){
            finish();

            startActivity(new Intent(this,MainActivity.class));



        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode== RESULT_OK&&data!=null&& data.getData()!=null){
            Uri uri = data.getData();
            try{
                //seçilen resmi bitmap olarak resim view ine aktarırız.url i kullanıyoruz bunun için

                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                resim.setImageBitmap(bitmap);


                StorageReference filepath = mStorage.child("Photos").child(idm);

                filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(Register.this, "Upload Done", Toast.LENGTH_LONG).show();
                    }
                });


            }
            catch (IOException e){

                e.printStackTrace();

            }

        }


    }}

