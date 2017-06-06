package com.rcv_bt;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.ParcelUuid;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

import static android.bluetooth.le.ScanSettings.SCAN_MODE_LOW_LATENCY;

public class MainActivity extends AppCompatActivity {
    private final static int REQUEST_ENABLE_BT = 1;
    BluetoothLeScanner b1;
    private BluetoothAdapter mBluetoothAdapter;
    private boolean mScanning;
    private Handler mHandler;
    TextView TName;
    TextView Trssi;
   // private LeDeviceListAdapter mLeDeviceListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         TName=(TextView)findViewById(R.id.textView);
         Trssi=(TextView)findViewById(R.id.textView5);

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "ble_not_supported", Toast.LENGTH_SHORT).show();
            finish();
        }
        else
        {
          //  Toast.makeText(this, "Bluetooth low energy  Supported !", Toast.LENGTH_SHORT).show();
        }

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);

        }
//

       b1 = mBluetoothAdapter.getBluetoothLeScanner();
       // Toast.makeText(this,mBluetoothAdapter.getName(), Toast.LENGTH_SHORT).show();

    }
//    private BluetoothAdapter.LeScanCallback mLeScanCallback =
//            new BluetoothAdapter.LeScanCallback() {
//
//                @Override
//                public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(MainActivity.this, device.getName().toString(), Toast.LENGTH_SHORT).show();}
//                    });
//                }
//            };
    void strt(View view)
    {   /*
        b1.startScan();
        ScanSettings settings = new ScanSettings.Builder().setScanMode(SCAN_MODE_LOW_LATENCY).build();
        */
    ScanSettings settings =new ScanSettings.Builder().setScanMode(SCAN_MODE_LOW_LATENCY).build();


        ScanFilter filter = new ScanFilter.Builder().setServiceUuid(ParcelUuid.fromString(serviceUUIDs[i].toStri‌​ng())).build();
        b1.startScan(new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                super.onScanResult(callbackType, result);
                BluetoothDevice d1 = result.getDevice();
                String s1 = d1.getAddress();
                Log.d("get",s1);
                String add=result.getDevice().getAddress();
                String Name=result.getDevice().getName();
                Integer g1 = result.getScanRecord().getAdvertiseFlags();
                Integer rssi=result.getRssi();
                result.getScanRecord();
                TName.setText(add);
                Trssi.setText(rssi.toString());
             //   Toast.makeText(MainActivity.this,Name+rssi.toString()+add , Toast.LENGTH_SHORT).show();

            }
        });
        Toast.makeText(this, "start", Toast.LENGTH_SHORT).show();

    }
    void st(View view)
    {
        this.
        Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show();
        b1.stopScan(new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                super.onScanResult(callbackType, result);

                BluetoothDevice d1 = result.getDevice();
                String s1 = d1.getAddress();
                Log.d("get",s1);

                Integer g1 = result.getScanRecord().getAdvertiseFlags();
                Integer rssi=result.getRssi();
                result.getScanRecord();
                Toast.makeText(MainActivity.this,s1 , Toast.LENGTH_SHORT).show();

            }
        });
    }
}
