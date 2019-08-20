package com.chaos.chaoslux;


import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConnectFragment extends Fragment {

    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private Set<BluetoothDevice> pairedDevices;
    private static final int ENABLE_BT_REQUEST_CODE = 1;
    static BluetoothSocket bluetoothSocket;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    public static String address;

    public ConnectFragment() {
        // Required empty public constructor
    }

    //widgets
    private Button btnPaired;
    private ListView deviceList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.connect_fragment, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Connect a Device");
        //Calling widgets
        btnPaired = getView().findViewById(R.id.button2);
        deviceList = getView().findViewById(R.id.device_list);

        if (bluetoothAdapter == null) {
            //Device doesn't support Bluetooth//
            Toast.makeText(getActivity().getApplicationContext(), "This device doesn't support Bluetooth", Toast.LENGTH_LONG).show();
        } else {
            //Device does support Bluetooth//
        }

        if (!bluetoothAdapter.isEnabled()) {
            //Create an intent with the ACTION_REQUEST_ENABLE action, which we use to display our system Activity//
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            //Pass this Intent to startActivityForResult(). ENABLE_BT_REQUEST_CODE is a locally defined integer
            //      that must be greater than 0//
            startActivityForResult(enableIntent, ENABLE_BT_REQUEST_CODE);
            //Toast.makeText(getActivity().getApplicationContext(), "Enabling Bluetooth...", Toast.LENGTH_LONG).show();
        }

        btnPaired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                pairedDevicesList();
            }
        });
    }

    private void pairedDevicesList()
    {
        pairedDevices = bluetoothAdapter.getBondedDevices();
        ArrayList list = new ArrayList();

        // If there's 1 or more paired devices... //
        if (pairedDevices.size()>0)
        {
            // ...then loop through these devices. //
            for(BluetoothDevice bt : pairedDevices)
            {
                // Retrieve each device's public identifier and MAC address, and incorporate into ListView //
                list.add(bt.getName() + "\n" + bt.getAddress());
            }
        }
        else
        {
            Toast.makeText(getActivity().getApplicationContext(), "No Paired Bluetooth Devices Found", Toast.LENGTH_LONG).show();
        }

        final ArrayAdapter adapter = new ArrayAdapter(getActivity().getApplicationContext(), R.layout.device_list_text, list);
        deviceList.setAdapter(adapter);
        deviceList.setOnItemClickListener(myListClickListener); //Method called when the device from the list is clicked
    }

    public AdapterView.OnItemClickListener myListClickListener = new AdapterView.OnItemClickListener()
    {
        public void onItemClick (AdapterView<?> av, View v, int arg2, long arg3)
        {
            // Get the device MAC address, the last 17 chars in the View
            String info = ((TextView) v).getText().toString();
            address = info.substring(info.length() - 17);
            try
            {
                BluetoothDevice btDevice = bluetoothAdapter.getRemoteDevice(address);//connects to the device's address and checks if it's available
                bluetoothSocket = btDevice.createRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                bluetoothSocket.connect();//start connection
            }
            catch (IOException e) {
            }
        }
    };

    public static BluetoothSocket getSocket() {
        return bluetoothSocket;
    }

    /*private void connectBT() {
        try
        {
            BluetoothDevice btDevice = bluetoothAdapter.getRemoteDevice(address);//connects to the device's address and checks if it's available
            bluetoothSocket = btDevice.createRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
            BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
            bluetoothSocket.connect();//start connection
        }
        catch (IOException e) {
        }
    }*/

}
