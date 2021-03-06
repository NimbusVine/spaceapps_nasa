package io.github.weichen.spaceappgame;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MenuActivity extends Activity implements View.OnClickListener {
    public static final String EXTRA_MESSAGE = "Iteration";

    Button learn;
    Button test;
    Button name;
    MediaPlayer mp;
    AssetFileDescriptor asd;
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        learn = (Button) findViewById(R.id.learn_button);
        learn.setOnClickListener(this);

        test = (Button) findViewById(R.id.test_button);
        test.setOnClickListener(this);

        name = (Button) findViewById(R.id.name);
        name.setOnClickListener(this);
        try {
            asd = getAssets().openFd("fant.mp3");
            mp = new MediaPlayer();
            mp.setDataSource(asd.getFileDescriptor(), asd.getStartOffset(),asd.getLength());
            mp.setLooping(true);
            mp.prepare();
            mp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void browser1(View v){
        if(v==name) {
            count++;

            Intent br = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Zeph0528/spaceapps_nasa"));
            startActivity(br);
        }
    }
    @Override
    public void onClick(View view) {
        if(view == name){
            Intent br = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Zeph0528/spaceapps_nasa"));
            startActivity(br);
        }
        if (view == learn){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(EXTRA_MESSAGE,0);
            startActivity(intent);
        }
        else if (view == test){
            Intent intent = new Intent(this,TestingActivity.class);
            startActivity(intent);
        }
    }
    public void onPause()
    {
        super.onPause();
        mp.pause();
    }
}
