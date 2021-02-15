package com.example.instagramfirebasee;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GonderiRecyclerAdapter extends RecyclerView.Adapter<GonderiRecyclerAdapter.PostlarHolder> {

    private ArrayList<String> kullaniciemailList;
    private ArrayList<String> kullaniciyorumList;
    private ArrayList<String> kullaniciresimList;

    public GonderiRecyclerAdapter(ArrayList<String> kullaniciemailList, ArrayList<String> kullaniciyorumList, ArrayList<String> kullaniciresimList) {
        this.kullaniciemailList = kullaniciemailList;
        this.kullaniciyorumList = kullaniciyorumList;
        this.kullaniciresimList = kullaniciresimList;
    }

    @NonNull
    @Override
    public PostlarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//olusturdugumıuz row ile baglama islemi
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_row,parent,false);
        return new PostlarHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostlarHolder holder, int position) {
        holder.kullaniciyorumtext.setText(kullaniciyorumList.get(position));
        holder.kullanicimailtext.setText(kullaniciemailList.get(position));
        Picasso.get().load(kullaniciresimList.get(position)).into(holder.imageView);

    }

    @Override
    public int getItemCount() {//kac tane row varsa
        return kullaniciemailList.size();
    }

    class PostlarHolder extends RecyclerView.ViewHolder{

        //layout daki görünümleri tuttuk
        ImageView imageView;
        TextView kullanicimailtext;
        TextView kullaniciyorumtext;

        public PostlarHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.recyclerView_gonderi_imageview);
            kullanicimailtext=itemView.findViewById(R.id.recyclerView_kullaniciemaili_text);
            kullaniciyorumtext=itemView.findViewById(R.id.recyclerView_yorumlar_text);
        }
    }
}
