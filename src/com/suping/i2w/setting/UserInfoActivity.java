package com.suping.i2w.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.suping.i2w.R;

public class UserInfoActivity extends Activity implements OnClickListener {
	private TextView tv_back;
	private TextView tv_sex;
	private CheckBox box_men;
	private CheckBox box_women;
	private boolean isMen;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info);
		initViews();
		setOnClick();
	}
	private void initViews(){
		tv_back = (TextView) findViewById(R.id.tv_back);
		tv_sex = (TextView) findViewById(R.id.sex);
		box_men = (CheckBox) findViewById(R.id.men_check);
		box_women = (CheckBox) findViewById(R.id.women_check);
		
	}
	
	private void setOnClick() {
		tv_back.setOnClickListener(this);
		box_men.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(!isChecked){
					System.out.println("-------1-------");
					box_women.setChecked(true);
					tv_sex.setText("女");
				}
				else{
					System.out.println("------2--------");
					box_women.setChecked(false);
					tv_sex.setText("男");
				}
			}
		});
		box_women.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(!isChecked){
					System.out.println("------2--------");
					box_men.setChecked(true);
					tv_sex.setText("男");
				}
				else{
					System.out.println("-------1-------");
					box_men.setChecked(false);
					tv_sex.setText("女");
				}
			}
		});
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_back:
			UserInfoActivity.this.finish();
			startActivity(new Intent(UserInfoActivity.this,SettingActivity.class));
			break;

		default:
			break;
		}
	}
	
}
