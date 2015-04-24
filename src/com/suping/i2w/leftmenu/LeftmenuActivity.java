package com.suping.i2w.leftmenu;

import com.suping.i2w.Main;
import com.suping.i2w.R;
import com.suping.i2w.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class LeftmenuActivity extends Activity implements OnClickListener{
	private ImageView backImg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_leftmenu);
		initWedgits();
		registerListener();
	}
	
	private void initWedgits(){
		backImg = (ImageView) findViewById(R.id.img_back);
		
	}
	
	private void registerListener(){
		backImg.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_back:
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
