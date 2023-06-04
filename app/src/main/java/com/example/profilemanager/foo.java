package com.example.profilemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class foo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foo);
        ViewGroup grid = findViewById(R.id.gridLayout);

        for(int i =0; i<6; i++){ // gets all children of layout
            ImageView subView = (ImageView) grid.getChildAt(i);
            subView.setOnClickListener(new View.OnClickListener() { // set the onclick listeners for every button
                @Override
                public void onClick(View v) {
                    SetTeamIcon(v);
                }
            });
        }



    }

    public void SetTeamIcon(View view){
        Intent intent= new Intent();
        String idString = view.getResources().getResourceEntryName(view.getId()); // this gets the resource name directly
        intent.putExtra("resourceID", idString);
        setResult(RESULT_OK, intent);
        finish();
    }
}