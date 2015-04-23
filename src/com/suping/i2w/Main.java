package com.suping.i2w;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.suping.i2w.leftmenu.LeftmenuActivity;

public class Main extends Activity implements OnClickListener{
	private ImageView moreImg;
	@Override
	protected void onCreate(Bundle savedInstanceState)  {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initWedgits();
		registerListener();
	}
	
	private void initWedgits(){
		moreImg = (ImageView) findViewById(R.id.img_more);
		
	}
	
	private void registerListener(){
		moreImg.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_more:
			startActivity(new Intent(Main.this, LeftmenuActivity.class));
			// TODO Auto-generated method stub
			Main.this.finish();
			// 窗口动画
			overridePendingTransition(
					R.anim.activity_from_left_to_right_enter,
					R.anim.activity_from_left_to_right_exit
					);
			break;

		default:
			break;
		}
	}

}
