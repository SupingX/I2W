package com.suping.i2w.setting;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.suping.i2w.Main;
import com.suping.i2w.R;

public class SettingActivity extends Activity implements OnClickListener {
	
	private TextView tv_back;
	private RelativeLayout rl_user_info;
	private RelativeLayout rl_user_goal;
	private RelativeLayout rl_user_connect;
	private RelativeLayout rl_user_about;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		loadViews();
		setClick();
	}
	
	private void loadViews(){
		tv_back = (TextView) findViewById(R.id.tv_back);
		rl_user_info = (RelativeLayout) findViewById(R.id.rl_user_info);
		rl_user_goal = (RelativeLayout) findViewById(R.id.rl_user_goal);
		rl_user_connect = (RelativeLayout) findViewById(R.id.rl_user_connect);
		rl_user_about = (RelativeLayout) findViewById(R.id.rl_user_about);
		
		
	}
	private void setClick(){
		tv_back.setOnClickListener(this);
		rl_user_info.setOnClickListener(this);
		rl_user_goal.setOnClickListener(this);
		rl_user_connect.setOnClickListener(this);
		rl_user_about.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_back:
			startActivity(new Intent(SettingActivity.this,Main.class));
			SettingActivity.this.finish();
			overridePendingTransition(
					//左右是指手机屏幕的左右
					R.anim.activity_from_left_to_right_enter,
					R.anim.activity_from_left_to_right_exit);
			break;
		case R.id.rl_user_info:
			//个人信息
			startActivity(new Intent(SettingActivity.this,UserInfoActivity.class));
			break;
		case R.id.rl_user_goal:
			//目标设置
			startActivity(new Intent(SettingActivity.this,GoalActivity.class));
			break;
		case R.id.rl_user_connect:
			//连接其他设备
			startActivity(new Intent(SettingActivity.this,ConnectBluetoothActivity.class));
			break;
		case R.id.rl_user_about:
			//关于
			startActivity(new Intent(SettingActivity.this,AboutAppActivity.class));
			break;
		default:
			break;
		}
	}
}
