package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

public class MovieGenreAdapter extends FirebaseRecyclerAdapter<FeatureModel, MovieGenreAdapter.MyViewHolder>{
    List<FeatureModel> featureModelList;
    String[] AllGenres;
    int rv_position;

    public MovieGenreAdapter(@NonNull FirebaseRecyclerOptions<FeatureModel> options, String[] AllGenres, int rv_position) {
        super(options);
        this.AllGenres= AllGenres;
        this.rv_position=rv_position;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card_layout,parent,false);
        return new MyViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull FeatureModel model) {
        holder.textView.setText(model.getTitle());
        Glide.with(holder.imageView.getContext()).load(model.getCover()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new DetailFragment(model.getTitle(),model.getCover(),model.getThumb(),model.getDesc(),model.getCast(),model.getMovie(),model.getGenre())).addToBackStack(null).commit();
            }
        });
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.iv_movie_cover);
            textView=itemView.findViewById(R.id.tv_movie_title);
        }
    }
}

