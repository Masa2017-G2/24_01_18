package com.sheygam.masa_g2_24_01_18;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MyFragment.Callback {
    public static float SCALE = 1.0F;
    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        MyFragment fragment = new MyFragment();
//        fragment.onAttach((Context) this);
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.add(R.id.fragment_container, fragment);
//        transaction.commit();
        findViewById(R.id.add_btn)
                .setOnClickListener(this);

        findViewById(R.id.replace_btn)
                .setOnClickListener(this);

        findViewById(R.id.remove_btn)
                .setOnClickListener(this);

        findViewById(R.id.attach_btn)
                .setOnClickListener(this);

        findViewById(R.id.detach_btn)
                .setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.add_btn){
            Random rnd = new Random();
            int color = Color.rgb(rnd.nextInt(256), rnd.nextInt(256),rnd.nextInt(256));
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .add(R.id.fragment_container,MyFragment.newInstance(color,SCALE),"FRAG" + count++)
                    .commit();
            SCALE -= 0.1;
        }else if(v.getId() == R.id.replace_btn){
            int color = Color.rgb(0,0,0);
            MyFragment fragment = new MyFragment();
            Bundle args = new Bundle();
            args.putString("TITLE","New Title");
            args.putInt("COLOR", color);
            args.putFloat("SCALE", 0.5F);
            fragment.setArguments(args);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,fragment)
                    .commit();
        }else if(v.getId() == R.id.remove_btn){
            Fragment fragment = getSupportFragmentManager().findFragmentByTag("FRAG3");
            if (fragment != null){
                getSupportFragmentManager()
                        .beginTransaction()
                        .remove(fragment)
                        .commit();
            }
        }else if(v.getId() == R.id.attach_btn){
            Fragment  fragment = getSupportFragmentManager().findFragmentByTag("FRAG2");
            if (fragment != null){
                getSupportFragmentManager()
                        .beginTransaction()
                        .attach(fragment)
                        .commit();
            }
        }else if (v.getId() == R.id.detach_btn){
            Fragment fragment = getSupportFragmentManager().findFragmentByTag("FRAG2");
            if (fragment != null){
                getSupportFragmentManager()
                        .beginTransaction()
                        .detach(fragment)
                        .commit();
            }
        }
    }

    @Override
    public void onBtnClick(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
