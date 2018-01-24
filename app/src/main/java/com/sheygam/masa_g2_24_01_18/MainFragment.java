package com.sheygam.masa_g2_24_01_18;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by gregorysheygam on 24/01/2018.
 */

public class MainFragment extends Fragment{
    private String name = "", email = "";
    private TextView nameTxt, emailTxt;
    private Button loginBtn;
    private MainFragmentListener listener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof MainFragmentListener){
            listener = (MainFragmentListener) activity;
        }else{
            //Throw exception
        }
    }

    public void updateFragment(String name, String email){
        this.name = name;
        this.email = email;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container,false);
        nameTxt = view.findViewById(R.id.name_txt);
        emailTxt = view.findViewById(R.id.email_txt);
        loginBtn = view.findViewById(R.id.login_btn);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nameTxt.setText(name);
        emailTxt.setText(email);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!= null){
                    listener.onLoginClicked();
                }
            }
        });
    }

    public interface MainFragmentListener{
        void onLoginClicked();
    }
}
