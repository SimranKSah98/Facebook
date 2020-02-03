package com.example.facebook.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.facebook.R;
import com.example.facebook.pojo.Post;

public class TagsActivity extends AppCompatActivity {

    Post post;
    CheckBox fiction,holywood,chinise,badminton,androidos,clothing,footware,poetry;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tags);
        inti();

    }

    private void inti() {
       fiction=findViewById(R.id.fictioncheckbox);
       holywood=findViewById(R.id.Hollywoodcheckbox);
       chinise=findViewById(R.id.Chineseheckbox);
       badminton=findViewById(R.id.Badmintoncheckbox);
       androidos=findViewById(R.id.Androidcheckbox);
       clothing=findViewById(R.id.Clothingcheckbox);
       footware=findViewById(R.id.Footwearcheckbox);
       poetry=findViewById(R.id.Poetrycheckbox);
       submit=findViewById(R.id.tagssubmit);

       if(fiction.isChecked())
       {

       }
        if(holywood.isChecked())
        {

        }
        if(chinise.isChecked())
        {

        }
        if(badminton.isChecked())
        {

        }
        if(androidos.isChecked())
        {

        }
        if(footware.isChecked())
        {

        }
        if(poetry.isChecked())
        {

        }
        if(clothing.isChecked())
        {

        }



    }
}
