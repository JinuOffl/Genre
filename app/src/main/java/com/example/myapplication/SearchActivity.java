package com.example.myapplication;

import static androidx.core.content.ContentProviderCompat.requireContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    EditText searchText;
    RecyclerView RecyclerViewSearch;
    DatabaseReference serchRef;
    //private List<FilmDataModel> filmDataModels;
    //private FilmsAdapter filmsAdapter;

    ArrayList<String> titleList;
    ArrayList<String> thumbList;
    ArrayList<String>castList;
    SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchText =(EditText) findViewById(R.id.tv_search);
        RecyclerViewSearch = (RecyclerView) findViewById(R.id.rv_search);

        serchRef = FirebaseDatabase.getInstance().getReference();

        //RecyclerViewSearch.setHasFixedSize(true);
        LinearLayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        RecyclerViewSearch.setLayoutManager(layoutManager);
        RecyclerViewSearch.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        titleList = new ArrayList<>();
        thumbList = new ArrayList<>();
        castList = new ArrayList<>();

//        filmDataModels = new ArrayList<>();
//        filmsAdapter = new FilmsAdapter(filmDataModels);
//        RecyclerViewSearch.setAdapter(filmsAdapter);

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!s.toString().isEmpty()) {
                    setAdapter(s.toString());
                }else {
                    titleList.clear();
                    castList.clear();
                    thumbList.clear();
                    RecyclerViewSearch.removeAllViews();

                }

            }

            private void setAdapter(final String searchedstring) {

                serchRef.child("movies").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {

                        //clear for new
                        titleList.clear();
                        castList.clear();
                        thumbList.clear();
                        RecyclerViewSearch.removeAllViews();

                        int counter =  0; //for results

                        for (DataSnapshot snapshot1 : snapshot.getChildren()){
                            String fid = snapshot1.getKey();
                            String title = snapshot1.child("title").getValue(String.class);
                            String thumb = snapshot1.child("cover").getValue(String.class);
                            String cast = snapshot1.child("cast").getValue(String.class);

                            /*try{

                            }catch(NullPointerException e){
                                Toast.makeText(this,e,Toast.LENGTH_SHORT).show();
                            }
                            */


                            if(title!=null){
                                if (title.toLowerCase().contains(searchedstring.toLowerCase())){
                                    titleList.add(title);
                                    castList.add(cast);
                                    thumbList.add(thumb);
                                    counter++;
                                }
                            }else if(cast!=null) {
                                if (cast.toLowerCase().contains(searchedstring.toLowerCase())) {
                                    titleList.add(title);
                                    castList.add(cast);
                                    thumbList.add(thumb);
                                    counter++;
                                }
                            }
                            if (counter == 3) //results
                                break;
                        }
                        searchAdapter = new SearchAdapter(SearchActivity.this, titleList,castList,thumbList);
                        RecyclerViewSearch.setAdapter(searchAdapter);

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                });
            }
        });
    }
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), HomeFragment.class));
        //overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }
}
