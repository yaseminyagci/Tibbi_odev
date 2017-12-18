package com.mathheals.tibbi_odev.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mathheals.tibbi_odev.MainActivity;
import com.mathheals.tibbi_odev.R;
import com.mathheals.tibbi_odev.Register;

/**
 * Created by user on 2.9.2017.
 */
public class Fragment_Button extends Fragment {
    Button buttonYeniSayfa;
    private static final String TAG = "MainActivity";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // bu fragment'in layout'unu hazır hale getirelim
        View view =inflater.inflate(R.layout.fragment_button, container, false);

        Button button = (Button)view.findViewById(R.id.buton1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Register.class);
                startActivity(intent);
                getActivity().finish();

            }
        });

        Button button2 = (Button)view.findViewById(R.id.buton2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();


            }
        });
return view;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

}
