package com.chaos.chaoslux;


import android.bluetooth.BluetoothSocket;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MetronomeFragment extends Fragment {

    BluetoothSocket btSocket = ConnectFragment.getSocket();
    private Button metroOn;
    private Button metroOff;

    public MetronomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.metronome_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Metronome");
        metroOn = getView().findViewById(R.id.metronomeOn);
        metroOff = getView().findViewById(R.id.metronomeOff);

        metroOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (btSocket != null) {
                    try {
                        btSocket.getOutputStream().write(6);
                        //btSocket.getOutputStream().write(6);
                    } catch (IOException e) {
                        Toast.makeText(getActivity().getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Not Connected", Toast.LENGTH_SHORT).show();
                }
            }
        });

        metroOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (btSocket != null) {
                    try {
                        btSocket.getOutputStream().write(7);
                        //btSocket.getOutputStream().write(7);
                    } catch (IOException e) {
                        Toast.makeText(getActivity().getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Not Connected", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
