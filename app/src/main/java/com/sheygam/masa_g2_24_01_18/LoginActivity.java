package com.sheygam.masa_g2_24_01_18;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity implements MainFragment.MainFragmentListener,NameFragment.NameFragmentListener, EmailFragment.EmailFragmentListener {
    private MainFragment mainFragment;
    private String currentName = "", currentEmail = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mainFragment = new MainFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container,mainFragment)
                .commit();
    }

    @Override
    public void onLoginClicked() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,new NameFragment())
                .addToBackStack("REPLACE_TO_NAME")
                .commit();
    }

    @Override
    public void onNameNext(String name) {
        currentName = name;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,new EmailFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onEmailNext(String email) {
        currentEmail = email;
        mainFragment.updateFragment(currentName, currentEmail);
        getSupportFragmentManager()
                .popBackStack("REPLACE_TO_NAME", FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
