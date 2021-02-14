package com.example.instagramfirebasee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class KayitActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    EditText emailText,sifreText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance(); //deger atadım
        emailText = findViewById(R.id.emailText);
        sifreText = findViewById(R.id.sifreText);

        //kullanıcı varsa boş gelmiyorsa oturumu açık tutma
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if(firebaseUser!=null){

            Intent intent =new Intent(KayitActivity.this,GonderiActivity.class);
            startActivity(intent);
            finish();
        }

    }

    public void girisClicked (View view){
        String email = emailText.getText().toString();
        String sifre = sifreText.getText().toString();

        firebaseAuth.signInWithEmailAndPassword(email,sifre).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Intent intent = new Intent(KayitActivity.this,GonderiActivity.class);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(KayitActivity.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();

            }
        });

    }
    public void kayitClicked (View view){

        String email= emailText.getText().toString();
        String sifre = sifreText.getText().toString();

        if(email.matches(" ")){

        }
        firebaseAuth.createUserWithEmailAndPassword(email,sifre).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(KayitActivity.this,"Kullanıcı Oluşturuldu",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(KayitActivity.this,GonderiActivity.class);
                startActivity(intent);//kullanıcı oluşunca gönderiye götürücek
                finish();



            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(KayitActivity.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();//hatayı kullanıcının anladıgı biçimde uzun göster.

            }
        }); // basarılı ve basarısız olursa cagırılan metodlar

    }
}