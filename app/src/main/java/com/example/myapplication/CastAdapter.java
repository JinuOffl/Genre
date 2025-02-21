package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

public class CastAdapter extends FirebaseRecyclerAdapter<CastModel,CastAdapter.MyViewHolder> {
    List<CastModel> castmodels;
    public CastAdapter(@NonNull FirebaseRecyclerOptions<CastModel> options) {
        super(options);
    }

    @NonNull
    @Override
    public CastAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cast_card_layout,parent,false);
        return new CastAdapter.MyViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull CastModel model) {
        holder.textView.setText(model.getCast());
        Glide.with(holder.imageView.getContext()).load(model.getCastImg()).into(holder.imageView);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.iv_cast);
            textView=itemView.findViewById(R.id.tv_cast);
        }
    }
}
