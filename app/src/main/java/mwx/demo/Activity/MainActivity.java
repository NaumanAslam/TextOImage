package mwx.demo.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;


import mwx.demo.Adapter.MyRecyclerViewAdapter;
import mwx.demo.Models.DataModel;

import mwx.demo.R;
import mwx.demo.Utils.JsonParser;
import mwx.textoimage.TextoImage;
import mwx.textoimage.AsyncResponse;
import mwx.textoimage.Type;

public class MainActivity extends AppCompatActivity implements AsyncResponse,  MyRecyclerViewAdapter.ItemClickListener {
     MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         findViewById(R.id.aa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextoImage textoImage = new TextoImage(getApplicationContext(), Type.JSON, "http://pastebin.com/raw/wgkJgazE");
                textoImage.delegate = MainActivity.this;
                textoImage.execute();
            }
        });


        // set up the RecyclerView

    }
    @Override
    public void onItemClick(View view, int position) {
     }

    @Override
    public void dataLoaded(String output) {
        JsonParser jsonParser = new JsonParser();
        ArrayList<DataModel> _data = jsonParser.parseJsonData(output);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvAnimals);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, _data);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }


}
