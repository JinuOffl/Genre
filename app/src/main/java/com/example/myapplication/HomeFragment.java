package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.apache.commons.lang3.StringUtils;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView[] textViews;


    String[] AllGenres ={"action","horror","romance","comedy","scifi","animation","crime"};
    RecyclerView[] recyclerViews;
    int rv_position;


    private String mParam1;
    private String mParam2;

    View view;
    ImageButton accButton,imageButton;
    RecyclerView recview;
    FeatureAdapter featureAdapter;
    private List<MovieGenreAdapter> movieGenreAdapters;
    //MovieGenreAdapter movieGenreAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        //ActionBar actionbar=ActionBar.findViewById(R.id.custom_actionBar);
        Toolbar toolbar =(Toolbar) view.findViewById(R.id.custom_actionBar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        LinearLayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(requireContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        imageButton=(ImageButton)view.findViewById(R.id.search_icon);
        accButton=(ImageButton)view.findViewById(R.id.account_icon);
        //button = (Button) view.findViewById(R.id.button);

        RecyclerViewArraying();
        movieGenreAdapters = new ArrayList<>(7);


        recview = (RecyclerView) view.findViewById(R.id.rv_1);
        recview.setLayoutManager(layoutManager);

        FirebaseRecyclerOptions<FeatureModel> options =
                new FirebaseRecyclerOptions.Builder<FeatureModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Featured"), FeatureModel.class)
                        .build();

        featureAdapter = new FeatureAdapter(options);
        recview.setAdapter(featureAdapter);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
        accButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
            }
        });



        for(int i = 0; i< AllGenres.length; i++) {
            rv_position = i;

            LinearLayoutManager layout;
            layout = new LinearLayoutManager(requireContext());
            layout.setOrientation(RecyclerView.HORIZONTAL);
            layout.setReverseLayout(true);
            layout.setStackFromEnd(true);

            recyclerViews[i].setLayoutManager(layout);

            FirebaseRecyclerOptions<FeatureModel> optionsGenre =
                    new FirebaseRecyclerOptions.Builder<FeatureModel>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("movies").orderByChild("genre").equalTo(AllGenres[i]), FeatureModel.class)
                            .build();

            MovieGenreAdapter movieGenreAdapter = new MovieGenreAdapter(optionsGenre, AllGenres, rv_position);
            recyclerViews[i].setAdapter(movieGenreAdapter);
            movieGenreAdapters.add(movieGenreAdapter);
        }
        loadAllGenreTitle();

        return view;
    }

    private void loadAllGenreTitle() {
        for(int i=0;i< AllGenres.length;i++){
            String title=AllGenres[i];
            textViews[i].setText(StringUtils.capitalize(title)+" Movies");
        }
    }

    private void RecyclerViewArraying() {

            recyclerViews = new RecyclerView[7];
            recyclerViews[0] = (RecyclerView) view.findViewById(R.id.rv_Action);
            recyclerViews[1] = (RecyclerView) view.findViewById(R.id.rv_Horror);
            recyclerViews[2] = (RecyclerView) view.findViewById(R.id.rv_Romance);
            recyclerViews[3] = (RecyclerView) view.findViewById(R.id.rv_Comedy);
            recyclerViews[4] = (RecyclerView) view.findViewById(R.id.rv_SciFi);
            recyclerViews[5] = (RecyclerView) view.findViewById(R.id.rv_Animation);
            recyclerViews[6] = (RecyclerView) view.findViewById(R.id.rv_Crime);

            textViews = new TextView[7];
            textViews[0] = view.findViewById(R.id.tv_Action);
            textViews[1] = view.findViewById(R.id.tv_Horror);
            textViews[2] = view.findViewById(R.id.tv_Romance);
            textViews[3] = view.findViewById(R.id.tv_Comedy);
            textViews[4] = view.findViewById(R.id.tv_SciFi);
            textViews[5] = view.findViewById(R.id.tv_Animation);
            textViews[6] = view.findViewById(R.id.tv_Crime);
    }

    @Override
    public void onStart() {
        super.onStart();
        featureAdapter.startListening();
        for (MovieGenreAdapter movieGenreAdapter : movieGenreAdapters) {
            movieGenreAdapter.startListening();
        }
    }
    @Override
    public void onStop() {
        super.onStop();
        featureAdapter.stopListening();
        for (MovieGenreAdapter movieGenreAdapter : movieGenreAdapters) {
            movieGenreAdapter.stopListening();
        }
    }

}