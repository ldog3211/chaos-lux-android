package com.chaos.chaoslux;


import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import static android.content.Context.VIBRATOR_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class ColorFragment extends Fragment {

    /*Button Rainbow, Off, Discnt, PurpleGreen, Random, PowerSave, UncleSam;*/
    BluetoothSocket btSocket = ConnectFragment.getSocket();

    private ViewPager colorPager;
    private TextView colorName;
    private Vibrator vibrate;
    private SeekBar brightness;
    /*private String lastText = "OFF";
    private int lastPage = 0;
    private int lastButtonBackground = R.drawable.off_button;*/

    public ColorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.color_fragment, container, false);
    }

    /*@Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("lastText", lastText);
        outState.putInt("lastPage", lastPage);
        outState.putInt("lastButtonBackground", lastButtonBackground);
    }*/

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //lastText = savedInstanceState.getString("lastText");
        //lastPage = savedInstanceState.getInt("lastPage");
        //lastButtonBackground = savedInstanceState.getInt("lastButtonBackground");
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("LUX Effects");
        brightness = getView().findViewById(R.id.brightness);
        vibrate = (Vibrator)getActivity().getSystemService(VIBRATOR_SERVICE);
        colorPager = getView().findViewById(R.id.viewPager2);
        colorPager.setPadding(200,0, 200,0);
        colorPager.setClipToPadding(false);
        colorPager.setPageMargin(10);
        colorPager.setHapticFeedbackEnabled(true);
        colorName = getView().findViewById(R.id.txtDescription);
        colorName.setText(MainActivity.lastText);
        colorName.setBackgroundResource(MainActivity.lastButtonBackground);
        setUpPagerAdapter();
        colorPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {}
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            public void onPageSelected(int position) {
                vibrate.vibrate(5);
                if (position == 0) {
                    colorName.setText("OFF");
                    colorName.setBackgroundResource(R.drawable.off_button);
                    MainActivity.lastText = colorName.getText().toString();
                    MainActivity.lastPage = 0;
                    MainActivity.lastButtonBackground = R.drawable.off_button;
                    turnOffLed();
                } else if (position == 1) {
                    colorName.setText("WAVE");
                    colorName.setBackgroundResource(R.drawable.rainbow_button);
                    MainActivity.lastText = colorName.getText().toString();
                    MainActivity.lastPage = 1;
                    MainActivity.lastButtonBackground = R.drawable.rainbow_button;
                    setRainbowLed();
                } else if (position == 2) {
                    colorName.setText("MARDI GRAS");
                    colorName.setBackgroundResource(R.drawable.purple_green_button);
                    MainActivity.lastText = colorName.getText().toString();
                    MainActivity.lastPage = 2;
                    MainActivity.lastButtonBackground = R.drawable.purple_green_button;
                    setPurpleAndGreenLed();
                } else if (position == 3) {
                    colorName.setText("RANDOM");
                    colorName.setBackgroundResource(R.drawable.random_button);
                    MainActivity.lastText = colorName.getText().toString();
                    MainActivity.lastPage = 3;
                    MainActivity.lastButtonBackground = R.drawable.random_button;
                    setRandomLed();
                } else if (position == 4) {
                    colorName.setText("UNCLE SAM");
                    colorName.setBackgroundResource(R.drawable.uncle_sam_button);
                    MainActivity.lastText = colorName.getText().toString();
                    MainActivity.lastPage = 4;
                    MainActivity.lastButtonBackground = R.drawable.uncle_sam_button;
                    setUncleSamLed();
                } else if (position == 5) {
                    colorName.setText("POWER SAVER");
                    colorName.setBackgroundResource(R.drawable.power_save_button);
                    MainActivity.lastText = colorName.getText().toString();
                    MainActivity.lastPage = 5;
                    MainActivity.lastButtonBackground = R.drawable.power_save_button;
                    setPowerSaveLed();
                }
            }
        });

        brightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressValue = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progressValue = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (btSocket != null) {
                    try {
                        int newValue = progressValue + 1000;
                        btSocket.getOutputStream().write(progressValue);
                    } catch (IOException e) {
                        Toast.makeText(getActivity().getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //Toast.makeText(getActivity().getApplicationContext(), "Not Connected", Toast.LENGTH_SHORT).show();
                    //colorName.setText("NOT CONNECTED");
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
                //Toast.makeText(getActivity().getApplicationContext(), "Not Connected", Toast.LENGTH_SHORT).show();
                // colorName.setText("NOT CONNECTED");
            }

        }

        private void turnOffLed()
        {
            if (btSocket != null) {
                try {
                    btSocket.getOutputStream().write(0);
                    btSocket.getOutputStream().write(0);
                } catch (IOException e) {
                    Toast.makeText(getActivity().getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            } else {
                //Toast.makeText(getActivity().getApplicationContext(), "Not Connected", Toast.LENGTH_SHORT).show();
                //colorName.setText("NOT CONNECTED");
            }
        }

        private void setRainbowLed()
        {
            if (btSocket != null) {
                try {
                    btSocket.getOutputStream().write(1);
                    btSocket.getOutputStream().write(1);
                } catch (IOException e) {
                    Toast.makeText(getActivity().getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            } else {
               // Toast.makeText(getActivity().getApplicationContext(), "Not Connected", Toast.LENGTH_SHORT).show();
                //colorName.setText("NOT CONNECTED");
            }
        }

        private void setPurpleAndGreenLed()
        {
            if (btSocket != null) {
                try {
                    btSocket.getOutputStream().write(2);
                    btSocket.getOutputStream().write(2);
                } catch (IOException e) {
                    Toast.makeText(getActivity().getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            } else {
                //Toast.makeText(getActivity().getApplicationContext(), "Not Connected", Toast.LENGTH_SHORT).show();
                //colorName.setText("NOT CONNECTED");
            }
        }

        private void setRandomLed()
        {
            if (btSocket != null) {
                try {
                    btSocket.getOutputStream().write(3);
                    btSocket.getOutputStream().write(3);
                } catch (IOException e) {
                    Toast.makeText(getActivity().getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            } else {
                //Toast.makeText(getActivity().getApplicationContext(), "Not Connected", Toast.LENGTH_SHORT).show();
                //colorName.setText("NOT CONNECTED");
            }
        }

        private void setPowerSaveLed()
        {
            if (btSocket != null) {
                try {
                    btSocket.getOutputStream().write(4);
                } catch (IOException e) {
                    Toast.makeText(getActivity().getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            } else {
                //Toast.makeText(getActivity().getApplicationContext(), "Not Connected", Toast.LENGTH_SHORT).show();
                //colorName.setText("NOT CONNECTED");
            }
        }

        private void setUncleSamLed()
        {
            if (btSocket != null) {
                try {
                    btSocket.getOutputStream().write(5);
                    btSocket.getOutputStream().write(5);
                } catch (IOException e) {
                    Toast.makeText(getActivity().getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            } else {
                //Toast.makeText(getActivity().getApplicationContext(), "Not Connected", Toast.LENGTH_SHORT).show();
                //colorName.setText("NOT CONNECTED");
            }
        }


}
