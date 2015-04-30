package com.suping.i2w.setting;

import java.util.List;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.suping.i2w.R;
import com.suping.i2w.bluetooth.BleApplication;
import com.suping.i2w.bluetooth.BleService;

public class ConnectBluetoothActivity extends Activity {

	private ListView lv_bleDevicesListView;
	private List<BluetoothDevice> devicesArray;
	private TextView tv_stop, tv_scan;
	private boolean isScanning;
	private BroadcastReceiver mReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals(BleService.BLE_DEVICE_SCANING)) {
				Toast.makeText(getApplicationContext(), "is scaning", 0).show();
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_connect_bluetooth);
		initViews();
		scanBleDevices(true);

	}

	@Override
	protected void onResume() {
		super.onResume();
		registerReceiver(mReceiver, BleService.getIntentFilter());
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(mReceiver);
	}

	private void initViews() {
		lv_bleDevicesListView = (ListView) findViewById(R.id.ble_devices_list_view);
		tv_stop = (TextView) findViewById(R.id.stop);
		tv_scan = (TextView) findViewById(R.id.scan);
	}
	
	private void setClick(){
		tv_stop.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void scanBleDevices(final boolean enable) {
		BleApplication app = (BleApplication) getApplication();
		app.getBleService().scanBleDevices(enable);
	}

	private class BleDevicesAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return devicesArray.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if (devicesArray.size() > 0) {
				ViewHolder holder = new ViewHolder();
				convertView = LayoutInflater.from(getApplicationContext())
						.inflate(R.layout.item_ble_devices, parent, false);
				holder.deviceName = (TextView) convertView
						.findViewById(R.id.device_name);
				holder.deviceAddress = (TextView) convertView
						.findViewById(R.id.device_address);
				BluetoothDevice device = (BluetoothDevice) devicesArray
						.get(position);
				holder.deviceName.setText(device.getName());
				;
				holder.deviceAddress.setText(device.getAddress());
				;
				return convertView;
			} else {
				return null;
			}
		}
		private class ViewHolder {
			private TextView deviceName;
			private TextView deviceAddress;
		}
	}

}
