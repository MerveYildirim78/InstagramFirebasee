package com.example.instagramfirebasee;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaCas;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class GonderiActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    ArrayList<String> kullaniciemailfromFB;
    ArrayList<String> kullaniciyorumfromFB;
    ArrayList<String> kullaniciresimfromFB;
    GonderiRecyclerAdapter gonderiRecyclerAdapter;

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
        firebaseFirestore=FirebaseFirestore.getInstance();

        kullaniciemailfromFB=new ArrayList<>();
        kullaniciyorumfromFB=new ArrayList<>();
        kullaniciresimfromFB=new ArrayList<>();

        getDataFromFireStore();

        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        gonderiRecyclerAdapter=new GonderiRecyclerAdapter(kullaniciemailfromFB,kullaniciyorumfromFB,kullaniciresimfromFB);
        recyclerView.setAdapter(gonderiRecyclerAdapter);




    }
    public void getDataFromFireStore(){//veriler snapshot ın içinde yüklüve güncelleniyor
       firebaseFirestore.collection("Postlar").orderBy("date", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
           @Override
           public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
               if( e != null){
                   Toast.makeText(GonderiActivity.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
               }
               if(queryDocumentSnapshots !=null){

                   for(DocumentSnapshot snapshot:queryDocumentSnapshots.getDocuments()){
                       Map <String,Object> data =snapshot.getData();
                       String yorum=(String) data.get("yorum");//casting
                       String kullaniciemail=(String) data.get("kullaniciemail");//casting
                       String indirmeurl=(String) data.get("indirmeurl");//casting

                       kullaniciemailfromFB.add(kullaniciemail);
                       kullaniciyorumfromFB.add(yorum);
                       kullaniciresimfromFB.add(indirmeurl);

                       gonderiRecyclerAdapter.notifyDataSetChanged();

                   }

               }


           }
       });



    }
}