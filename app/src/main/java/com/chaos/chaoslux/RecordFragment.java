package com.chaos.chaoslux;


import android.bluetooth.BluetoothSocket;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecordFragment extends Fragment {

    BluetoothSocket btSocket = ConnectFragment.getSocket();
    private Button startRecording;
    private Button stopRecording;

    public RecordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.record_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Record");
        startRecording = getView().findViewById(R.id.startRecording);
        stopRecording = getView().findViewById(R.id.stopRecording);

        startRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (btSocket != null) {
                    try {
                        btSocket.getOutputStream().write(8);
                        //btSocket.getOutputStream().write(6);
                    } catch (IOException e) {
                        Toast.makeText(getActivity().getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Not Connected", Toast.LENGTH_SHORT).show();
                }
            }
        });

        stopRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (btSocket != null) {
                    try {
                        btSocket.getOutputStream().write(9);
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
