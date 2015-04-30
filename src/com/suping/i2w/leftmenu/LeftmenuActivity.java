package com.suping.i2w.leftmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.suping.i2w.Main;
import com.suping.i2w.R;

public class LeftmenuActivity extends Activity implements OnClickListener{
	private TextView tv_back;
	private TextView tv_title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_leftmenu);
		initWedgits();
		registerListener();
		tv_title.setText("更多");
	}
	
	private void initWedgits(){
		tv_back = (TextView) findViewById(R.id.tv_back);
		tv_title = (TextView) findViewById(R.id.tv_title);
		
	}
	
	private void registerListener(){
		tv_back.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_back:
			startActivity(new Intent(LeftmenuActivity.this, Main.class));
			// TODO Auto-generated method stub
			LeftmenuActivity.this.finish();
			// 窗口动画
			overridePendingTransition(
					R.anim.activity_from_right_to_left_enter,
					R.anim.activity_from_right_to_left_exit);
			break;

		default:
			break;
		}
	}
}
