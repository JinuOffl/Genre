package com.example.myapplication;

import android.app.DownloadManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class DetailFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    String title, cover, thumb, desc, cast_movie, movieUrl, genre;
    List<CastModel> castModelList;
    CastAdapter castAdapter;
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    private ImageButton downloadButton;
    private ProgressBar progressBar;
    private NotificationManager notificationManager;
    private long downloadID;
    private static final String CHANNEL_ID = "download_channel";
    private ProgressDialog progressDialog;

    public DetailFragment() {
        // Required empty public constructor
    }

    public DetailFragment(String title, String cover, String thumb, String desc, String cast_movie, String movieUrl, String genre) {
        this.title = title;
        this.cover = cover;
        this.thumb = thumb;
        this.desc = desc;
        this.cast_movie = cast_movie;
        this.movieUrl = movieUrl;
        this.genre = genre;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_detail, container, false);
        //Code for Getting Data from previous Fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        downloadButton = view.findViewById(R.id.download_btn);

        progressDialog = new ProgressDialog(this.getContext());
        progressDialog.setTitle("Downloading Video");
        progressDialog.setMessage("Please wait...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(true);


        ImageView coverholder = view.findViewById(R.id.iv_detail_cover);
        ImageView thumbholder = view.findViewById(R.id.iv_detail_thumb);
        TextView titleholder = view.findViewById(R.id.tv_movie_title2);
        TextView descholder = view.findViewById(R.id.tv_desc);
        TextView genreholder = view.findViewById(R.id.tv_genre);
        floatingActionButton = view.findViewById(R.id.playButton);

        titleholder.setText(title);
        descholder.setText(desc);
        genreholder.setText(StringUtils.capitalize(genre));
        Glide.with(getContext()).load(cover).into(coverholder);
        Glide.with(getContext()).load(thumb).into(thumbholder);

        //Code for Cast

        LinearLayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(requireContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_cast);
        recyclerView.setLayoutManager(layoutManager);

        FirebaseRecyclerOptions<CastModel> options =
                new FirebaseRecyclerOptions.Builder<CastModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Cast").child(cast_movie), CastModel.class)
                        .build();

        castAdapter = new CastAdapter(options);
        recyclerView.setAdapter(castAdapter);

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDownload(movieUrl);
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlayerActivity.class);
                intent.putExtra("video", movieUrl);
                startActivity(intent);

            }
        });

        return view;
    }

    private void startDownload(String url) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI |
                DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle("Video Download");
        request.setDescription("Downloading video...");
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
                "downloaded_video.mp4");
        DownloadManager manager = (DownloadManager)
                getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
        downloadID = manager.enqueue(request);
// Register receiver for when the download is complete
        getActivity().registerReceiver(onDownloadComplete, new
                IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }
    private BroadcastReceiver onDownloadComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
// Fetching the download id received with the broadcast
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
// Checking if the received broadcast is for our enqueued download by matchi download id
            if (downloadID == id) {
                Toast.makeText(context, "Download Completed", Toast.LENGTH_SHORT).show();
            }
        }
    };
    @Override
    public void onStart() {
        super.onStart();
        castAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        castAdapter.stopListening();
    }

    public void onBackPressed() {
        AppCompatActivity activity = (AppCompatActivity) getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper, new HomeFragment()).addToBackStack(null).commit();

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(onDownloadComplete);
    }
    
};