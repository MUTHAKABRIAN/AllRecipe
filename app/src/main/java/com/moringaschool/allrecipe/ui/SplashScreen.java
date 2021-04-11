package com.moringaschool.allrecipe.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.moringaschool.allrecipe.R;
import com.moringaschool.allrecipe.ui.LoginActivity;
import com.moringaschool.allrecipe.ui.MainActivity;

public class SplashScreen extends Activity {
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashfile);


        TextView view =findViewById(R.id.text);
        view.setText("Afri-Recipe");
        Animation anim_text = AnimationUtils.loadAnimation(this,R.anim.slide_in_bottom);
        view.startAnimation(anim_text);

        ImageView imageView =findViewById(R.id.logo_id);
        imageView.setImageResource(R.drawable.logo_spoon_clipart_5285972);
        Animation anim_image =AnimationUtils.loadAnimation(this,R.anim.slide_in_top);
        imageView.startAnimation(anim_image);

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}