package com.example.instagramfirebasee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class GonderiActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();//xml dosyası olusturdugumuzda kodla bağlayacaksak inflaterlar kullanıyorum.
        menuInflater.inflate(R.menu.insta_option_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }//menuyu baglamak icin

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.add_post){
            Intent intentToUpload = new Intent(GonderiActivity.this,UploadActivity.class);
            startActivity(intentToUpload);

        }else if(item.getItemId()==R.id.sign_out){


                Intent intentToSignUp = new Intent(GonderiActivity.this,KayitActivity.class);
                startActivity(intentToSignUp);
                finish();

            firebaseAuth.signOut();

        }

        return super.onOptionsItemSelected(item);
    }//seçim olursa yapılacaklar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gonderi);
        firebaseAuth=FirebaseAuth.getInstance();
    }
}