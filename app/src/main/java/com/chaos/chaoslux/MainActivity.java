package com.chaos.chaoslux;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /*BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    public Set<BluetoothDevice> pairedDevices;
    private static final int ENABLE_BT_REQUEST_CODE = 1;
    BluetoothSocket bluetoothSocket;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    public static String EXTRA_DEVICE_ADDRESS;
    public static String address;

    //ConnectFragment widgets
    private Button btnPaired;
    private ListView deviceList;*/

    static int[] imageResources = {
            R.drawable.pedal_render_transparent_top_down,
            R.drawable.pedal_render_transparent_top_down,
            R.drawable.pedal_render_transparent_top_down,
            R.drawable.pedal_render_transparent_top_down,
            R.drawable.pedal_render_transparent_top_down,
            R.drawable.pedal_render_transparent_top_down,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window window = this.getWindow();
        //Drawable background = this.getResources().getDrawable(R.drawable.toolbar_gradient);
        //window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //window.setStatusBarColor(this.getResources().getColor(android.R.color.transparent));
        window.setNavigationBarColor(this.getResources().getColor(android.R.color.transparent));
        //window.setBackgroundDrawable(background);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*btnPaired = findViewById(R.id.button2);
        deviceList = findViewById(R.id.device_list);*/

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*if (bluetoothAdapter == null) {
            //Device doesn't support Bluetooth//
            Toast.makeText(getApplicationContext(), "This device doesn't support Bluetooth", Toast.LENGTH_LONG).show();
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
        });*/

        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        //add this line to display menu1 when the activity is loaded
        displaySelectedScreen(R.id.nav_connect);
    }

    /*public void pairedDevicesList()
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
            Toast.makeText(getApplicationContext(), "No Paired Bluetooth Devices Found", Toast.LENGTH_LONG).show();
        }

        final ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1, list);
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
            Intent i = new Intent(getApplicationContext(), ColorFragment.class);
            i.putExtra(EXTRA_DEVICE_ADDRESS, address);
        }
    };*/

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        displaySelectedScreen(item.getItemId());

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_connect:
                fragment = new ConnectFragment();
                break;
            case R.id.nav_color:
                fragment = new ColorFragment();
                break;
            case R.id.nav_metronome:
                fragment = new MetronomeFragment();
                break;
            case R.id.nav_tuner:
                fragment = new TunerFragment();
                break;
            case R.id.nav_record:
                fragment = new RecordFragment();
                break;
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }
    }

    /*public class ListBtnListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            pairedDevicesList();
        }
    }*/
}
