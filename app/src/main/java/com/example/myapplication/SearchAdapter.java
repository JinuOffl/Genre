package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    Context context;

    ArrayList<String> titleList;
    ArrayList<String> thumbList;
    ArrayList<String>castList;
    FeatureModel model;



    public SearchAdapter(Context context, ArrayList<String> titleList,ArrayList<String> castList, ArrayList<String> thumbList ) {
        this.context = context;
        this.titleList = titleList;
        this.thumbList = thumbList;
        this.castList = castList;
    }


    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_card_layout, parent, false);
        return new SearchAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        holder.textViewSearch.setText(titleList.get(position));
        Glide.with(holder.imageViewSearch.getContext()).load(thumbList.get(position)).into(holder.imageViewSearch);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper, new DetailFragment(model.getTitle(), model.getCover(), model.getThumb(), model.getDesc(), model.getCast(), model.getMovie(), model.getGenre())).addToBackStack(null).commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return titleList.size();
    }
    public static class SearchViewHolder extends RecyclerView.ViewHolder{

    ImageView imageViewSearch;
    TextView textViewSearch, textViewSearchCast;

    public SearchViewHolder(View itemView) {
        super(itemView);
        textViewSearch = itemView.findViewById(R.id.tv_movie_title);
        imageViewSearch = itemView.findViewById(R.id.iv_movie_cover);
        // searchMovICast =(TextView) itemView.findViewById(R.id.searchMovCast);
    }
  }
}
