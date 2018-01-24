package com.sheygam.masa_g2_24_01_18;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by gregorysheygam on 24/01/2018.
 */

public class NameFragment extends Fragment {
    private EditText inputName;
    private Button nextBtn;
    private NameFragmentListener listener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof NameFragmentListener){
            listener = (NameFragmentListener) activity;
        }else{
            //throw exception
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.name_fragment, container,false);
        inputName = view.findViewById(R.id.input_name);
        nextBtn = view.findViewById(R.id.next_btn);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onNameNext(inputName.getText().toString());
                }
            }
        });
    }

    public interface NameFragmentListener{
        void onNameNext(String name);
    }
}
