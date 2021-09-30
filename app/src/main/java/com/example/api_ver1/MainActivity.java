package com.example.api_ver1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.api_ver1.model.DogBreed;
import com.example.api_ver1.view.DogsAdapter;
import com.example.api_ver1.viewmodel.DogsApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 1;
    private DogsApiService apiService;
    private DogsAdapter dogAdapter;
    private ArrayList<DogBreed> dogBreeds ;
    private GridView dogGridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dogGridView = findViewById(R.id.gridView);
        dogBreeds = new ArrayList<DogBreed>();
        dogAdapter = new DogsAdapter(this,dogBreeds);
        dogGridView.setAdapter(dogAdapter);
        dogAdapter.notifyDataSetChanged();
        dogAdapter = new DogsAdapter(this,dogBreeds);
        dogGridView.setAdapter(dogAdapter);
        dogAdapter.notifyDataSetChanged();
        apiService = new DogsApiService();

        Call<ArrayList<DogBreed>> dogsCall = apiService.getDogs();
        dogsCall.enqueue(new Callback<ArrayList<DogBreed>>() {
            @Override
            public void onResponse(Call<ArrayList<DogBreed>> call, Response<ArrayList<DogBreed>> response) {
                ArrayList<DogBreed> dog = response.body();
                System.out.println(dog);
                for (DogBreed d : dog){
                    dogBreeds.add(d);
                    dogAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<DogBreed>> call, Throwable t) {
                dogsCall.cancel();
            }
        });
        dogGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent i = new Intent(MainActivity.this, Info_Dogs.class);

                String name = dogBreeds.get(position).getName();
                String life = dogBreeds.get(position).getLifeSpan();
                String origin = dogBreeds.get(position).getOrigin();
                String breed = dogBreeds.get(position).getBredFor();
                boolean tym = dogBreeds.get(position).getTym();
                String url = dogBreeds.get(position).getUrl();

                i.putExtra("id",position);
                i.putExtra("name",name);
                i.putExtra("life",life);
                i.putExtra("origin",origin);
                i.putExtra("breed",breed);
                i.putExtra("tym",tym);
                i.putExtra("url",url);

                startActivityForResult(i, SECOND_ACTIVITY_REQUEST_CODE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check that it is the SecondActivity with an OK result
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                int id = data.getIntExtra("id",-1);
                boolean tym = data.getBooleanExtra("tym",false);
                dogBreeds.get(id).setTym(tym);
                dogAdapter.notifyDataSetChanged();
            }
        }
    }
}