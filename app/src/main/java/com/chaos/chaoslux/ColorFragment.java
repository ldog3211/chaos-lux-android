package com.chaos.chaoslux;


import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class ColorFragment extends Fragment {

    /*Button Rainbow, Off, Discnt, PurpleGreen, Random, PowerSave, UncleSam;*/
    BluetoothSocket btSocket = ConnectFragment.getSocket();

    private ViewPager colorPager;
    private TextView colorName;

    public ColorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.color_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Color/Pattern Selector");

        colorPager = getView().findViewById(R.id.viewPager2);
        colorPager.setPadding(200,0,200,0);
        colorPager.setClipToPadding(false);
        colorPager.setPageMargin(10);
        colorName = getView().findViewById(R.id.txtDescription);
        setUpPagerAdapter();
        colorPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {}
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            public void onPageSelected(int position) {
                if (position == 0) {
                    colorName.setText("OFF");
                    colorName.setBackgroundResource(R.drawable.off_button);
                    turnOffLed();
                } else if (position == 1) {
                    colorName.setText("SPECTRUM");
                    colorName.setBackgroundResource(R.drawable.rainbow_button);
                    setRainbowLed();
                } else if (position == 2) {
                    colorName.setText("MARDI GRAS");
                    colorName.setBackgroundResource(R.drawable.purple_green_button);
                    setPurpleAndGreenLed();
                } else if (position == 3) {
                    colorName.setText("RANDOM");
                    colorName.setBackgroundResource(R.drawable.random_button);
                    setRandomLed();
                } else if (position == 4) {
                    colorName.setText("UNCLE SAM");
                    colorName.setBackgroundResource(R.drawable.uncle_sam_button);
                    setUncleSamLed();
                } else if (position == 5) {
                    colorName.setText("POWER SAVER");
                    colorName.setBackgroundResource(R.drawable.power_save_button);
                    setPowerSaveLed();
                }
            }
        });

        /*//call the widgets
        Rainbow = getView().findViewById(R.id.rainbow);
        Off = getView().findViewById(R.id.off_btn);
        Discnt = getView().findViewById(R.id.dis_btn);
        PurpleGreen = getView().findViewById(R.id.purpleGreen);
        Random = getView().findViewById(R.id.random);
        PowerSave = getView().findViewById(R.id.powerSave);
        UncleSam = getView().findViewById(R.id.uncleSam);

        //commands to be sent to bluetooth
        Rainbow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setRainbowLed();      //set rainbow led pattern
            }
        });

        Off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                turnOffLed();   //method to turn off
            }
        });

        Discnt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Disconnect(); //close connection
            }
        });

        PurpleGreen.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setPurpleAndGreenLed(); //set purple and green pattern
            }
        });

        Random.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setRandomLed(); //set random led pattern
            }
        });

        PowerSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setPowerSaveLed(); //set purple and green pattern
            }
        });

        UncleSam.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setUncleSamLed(); //set purple and green pattern
            }
        });*/

    }

        private void setUpPagerAdapter() {
            ImagePagerAdapter pagerAdapter = new ImagePagerAdapter(getActivity().getApplicationContext());
            colorPager.setAdapter(pagerAdapter);
        }

        private void Disconnect()
        {
            if (btSocket != null) //If the btSocket is busy
            {
                try {
                    btSocket.close(); //close connection
                } catch (IOException e) {
                    Toast.makeText(getActivity().getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity().getApplicationContext(), "Not Connected", Toast.LENGTH_SHORT).show();
            }

        }

        private void turnOffLed()
        {
            if (btSocket != null) {
                try {
                    btSocket.getOutputStream().write("0".getBytes());
                    btSocket.getOutputStream().write("0".getBytes());
                } catch (IOException e) {
                    Toast.makeText(getActivity().getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity().getApplicationContext(), "Not Connected", Toast.LENGTH_SHORT).show();
            }
        }

        private void setRainbowLed()
        {
            if (btSocket != null) {
                try {
                    btSocket.getOutputStream().write("1".getBytes());
                    btSocket.getOutputStream().write("1".getBytes());
                } catch (IOException e) {
                    Toast.makeText(getActivity().getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity().getApplicationContext(), "Not Connected", Toast.LENGTH_SHORT).show();
            }
        }

        private void setPurpleAndGreenLed()
        {
            if (btSocket != null) {
                try {
                    btSocket.getOutputStream().write("2".getBytes());
                    btSocket.getOutputStream().write("2".getBytes());
                } catch (IOException e) {
                    Toast.makeText(getActivity().getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity().getApplicationContext(), "Not Connected", Toast.LENGTH_SHORT).show();
            }
        }

        private void setRandomLed()
        {
            if (btSocket != null) {
                try {
                    btSocket.getOutputStream().write("3".getBytes());
                    btSocket.getOutputStream().write("3".getBytes());
                } catch (IOException e) {
                    Toast.makeText(getActivity().getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity().getApplicationContext(), "Not Connected", Toast.LENGTH_SHORT).show();
            }
        }

        private void setPowerSaveLed()
        {
            if (btSocket != null) {
                try {
                    btSocket.getOutputStream().write("4".getBytes());
                } catch (IOException e) {
                    Toast.makeText(getActivity().getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity().getApplicationContext(), "Not Connected", Toast.LENGTH_SHORT).show();
            }
        }

        private void setUncleSamLed()
        {
            if (btSocket != null) {
                try {
                    btSocket.getOutputStream().write("5".getBytes());
                    btSocket.getOutputStream().write("5".getBytes());
                } catch (IOException e) {
                    Toast.makeText(getActivity().getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity().getApplicationContext(), "Not Connected", Toast.LENGTH_SHORT).show();
            }
        }


}
