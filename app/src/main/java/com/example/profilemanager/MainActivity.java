package com.example.profilemanager;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.net.URI;

public class MainActivity extends AppCompatActivity {
    ImageView pfp;
    EditText zip;
    ActivityResultLauncher<Intent> launcher;
    Button gmapsbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pfp = findViewById(R.id.pfp);
        zip = findViewById(R.id.TeamAddress);
        gmapsbtn = findViewById(R.id.GMapsButton);


        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent data = result.getData();
                        assert data != null;
                        String drawableName = data.getStringExtra("resourceID");
                        int resID = getResources().getIdentifier(drawableName, "drawable", getPackageName());
                        pfp.setImageResource(resID);
                    }
                }
        );

        pfp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), foo.class);
                launcher.launch(intent);
            }
        });
        
        gmapsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGMaps(v);
            }
        });
    }

    public void openGMaps(View view){
        EditText TAddress = findViewById(R.id.TeamAddress);

        Uri IntentURI = Uri.parse("https://maps.google.co.in/maps?q="+TAddress.getText().toString());

        Intent mapIntent = new Intent(Intent.ACTION_VIEW, IntentURI);
        mapIntent.setPackage("com.google.android.apps.maps");

        // check if google maps is installed, good practice for other apps too
        if(mapIntent.resolveActivity(getPackageManager()) != null){
            startActivity(mapIntent);
        }
    }


}