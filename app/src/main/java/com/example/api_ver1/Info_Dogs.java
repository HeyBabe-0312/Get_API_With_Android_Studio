package com.example.api_ver1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;

public class Info_Dogs extends AppCompatActivity {
    private TextView txtName;
    private TextView txtLife;
    private TextView txtOrigin;
    private TextView txtBreed;
    private ImageView imgAva;
    private int id;
    private String url;
    private boolean tym;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_dogs);

        txtName = findViewById(R.id.txtName);
        txtLife = findViewById(R.id.txtLife);
        txtOrigin = findViewById(R.id.txt_origin);
        txtBreed = findViewById(R.id.txtBreed);
        imgAva = findViewById(R.id.imageView);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        toolbar = (Toolbar) findViewById(R.id.mybutton);

        Intent receive = getIntent();
        if (receive != null){
            id = receive.getIntExtra("id",-1);
            txtName.setText(receive.getStringExtra("name"));
            this.setTitle(" Info of "+receive.getStringExtra("name"));
            txtLife.setText(receive.getStringExtra("life"));
            if(receive.getStringExtra("origin")==null||receive.getStringExtra("origin").isEmpty()) txtOrigin.setText("Không rõ nguồn gốc");
            else txtOrigin.setText(receive.getStringExtra("origin"));
            if(receive.getStringExtra("breed")==null||receive.getStringExtra("breed").isEmpty()) txtBreed.setText("Không rõ chức năng của nó");
            else txtBreed.setText(receive.getStringExtra("breed"));
            tym = receive.getBooleanExtra("tym",false);
            url = receive.getStringExtra("url");
            Glide.with(this).load(url).into(imgAva);
        }
    }
    public void sendData(){
        Intent returnIntent = new Intent();
        returnIntent.putExtra("id",id);
        returnIntent.putExtra("tym",tym);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.info_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.mybutton){
            if(tym==false) {
                Toast.makeText(this,"Đã yêu thích",Toast.LENGTH_SHORT).show();
                tym = true;
            }else {
                Toast.makeText(this,"Bỏ yêu thích",Toast.LENGTH_SHORT).show();
                tym = false;
            }
        }
        else if (i == android.R.id.home){
            sendData();
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}