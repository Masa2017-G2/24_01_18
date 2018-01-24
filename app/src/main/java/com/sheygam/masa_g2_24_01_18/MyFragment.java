package com.sheygam.masa_g2_24_01_18;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by gregorysheygam on 24/01/2018.
 */

public class MyFragment extends Fragment {
    private Callback callback;
    private float scale;
    private int color;
    private String title = "My Fragment";


    public static MyFragment newInstance(int color, float scale){
        MyFragment fragment = new MyFragment();
        fragment.color = color;
        fragment.scale = scale;
        return fragment;
    }
    @Override
    public void onAttach(Activity activity) {
        Log.d("MY_TAG", "onAttach: ");
        super.onAttach(activity);
        if(activity instanceof Callback){
            callback = (Callback) activity;
        }else{
            throw new RuntimeException(activity.getClass().getSimpleName() +
            "Must implements interface MyFragment.Callback!");
        }
    }

    public void setFragmentListener(Callback callback){
        this.callback = callback;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if(args != null){
            color = args.getInt("COLOR");
            scale = args.getFloat("SCALE");
            title = args.getString("TITLE");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("MY_TAG", "onCreateView: ");
        View view = inflater.inflate(R.layout.my_fragment,container,false);
//        Random rnd = new Random();
//        int color = Color.rgb(rnd.nextInt(256),rnd.nextInt(256),rnd.nextInt(256));
        view.setBackgroundColor(color);
        view.setScaleX(scale);
        view.setScaleY(scale);
        TextView titleTxt = view.findViewById(R.id.title_txt);
        titleTxt.setText(title);
//        MainActivity.SCALE -= 0.1;
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.click_btn)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(callback != null){
                            callback.onBtnClick("Was clicked btn");
                        }
                    }
                });
    }

    @Override
    public void onDestroyView() {
        Log.d("MY_TAG", "onDestroyView: ");
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        Log.d("MY_TAG", "onDetach: ");
        super.onDetach();
    }

    public interface Callback{
        void onBtnClick(String msg);
    }
}
