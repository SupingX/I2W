package com.suping.i2w.setting;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.suping.i2w.R;
import com.suping.i2w.util.I2WConstant;
import com.suping.i2w.util.SharedPreferenceUtil;
import com.suping.i2w.util.SysUtil;
import com.suping.i2w.util.ToastUtil;
import com.suping.i2w.view.CircleSeekBar;
import com.suping.i2w.view.CircleSeekBar.OnSeekBarChangeListener;

public class GoalActivity extends Activity implements OnClickListener,OnSeekBarChangeListener {
	private CircleSeekBar mCircleSeekBar;
	private TextView tv_steps,tv_kael,tv_km;
	private TextView tv_few,tv_middle,tv_high;
	private ImageView img_few,img_middle,img_high;
	private TextView tv_back,tv_title;
	private int goal;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goal);
		initViews();
		tv_title.setText("目标设置");
		setOnclick();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		goal = (int) SharedPreferenceUtil.get(getApplicationContext(),I2WConstant.GOAL , 10000);
		tv_steps.setText(goal+"");
		mCircleSeekBar.setProgress(goal);
		ToastUtil.toastShort(getApplicationContext(), "goal:"+goal);
		check(goal);
	}
	
	@Override
	protected void onDestroy() {
		SharedPreferenceUtil.put(getApplicationContext(), I2WConstant.GOAL, goal);
		ToastUtil.toastShort(getApplicationContext(), "goal:"+goal);
		this.finish();
		super.onDestroy();
	}
	
	private void initViews(){
		mCircleSeekBar= (CircleSeekBar) findViewById(R.id.circle_seekbar);
		tv_steps = (TextView) findViewById(R.id.steps);
		tv_kael = (TextView) findViewById(R.id.kael);
		tv_km = (TextView) findViewById(R.id.instance);
		tv_few = (TextView) findViewById(R.id.tv_few);
		tv_middle = (TextView) findViewById(R.id.tv_middle);
		tv_high = (TextView) findViewById(R.id.tv_high);
		tv_back = (TextView) findViewById(R.id.tv_back);
		tv_title = (TextView) findViewById(R.id.tv_title);
		img_few = (ImageView) findViewById(R.id.img_few);
		img_middle = (ImageView) findViewById(R.id.img_middle);
		img_high = (ImageView) findViewById(R.id.img_high);
	}
	
	private void setOnclick(){
		img_few.setOnClickListener(this);
		img_middle.setOnClickListener(this);
		img_high.setOnClickListener(this);
		tv_back.setOnClickListener(this);
		mCircleSeekBar.setOnSeekBarChangeListener(this);
	}
	
	/**
	 * 点击事件
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_few:
			mCircleSeekBar.setProgress(5000);
			goal = 5000;
			validate(1);
			break;
		case R.id.img_middle:
			mCircleSeekBar.setProgress(10000);
			goal = 10000;
			validate(2);
			break;
		case R.id.img_high:
			mCircleSeekBar.setProgress(15000);
			goal = 15000;
			validate(3);
			break;
		case R.id.tv_back:
			this.finish();
			break;
		default:
			break;
		}
	}
	
	/**
	 * 更新UI
	 */
	private void validate(int i){
		switch (i) {
		case 1:
			tv_few.setTextColor(getResources().getColor(R.color.settings_goal_selected_text_color));
			tv_middle.setTextColor(getResources().getColor(R.color.settings_goal_text_color));
			tv_high.setTextColor(getResources().getColor(R.color.settings_goal_text_color));
			img_few.setBackgroundResource(R.drawable.settings_goal_mild_pressed);
			img_middle.setBackgroundResource(R.drawable.selector_goal_middle);
			img_high.setBackgroundResource(R.drawable.selector_goal_high);
			
			break;
		case 2:
			tv_few.setTextColor(getResources().getColor(R.color.settings_goal_text_color));
			tv_middle.setTextColor(getResources().getColor(R.color.settings_goal_selected_text_color));
			tv_high.setTextColor(getResources().getColor(R.color.settings_goal_text_color));
			img_few.setBackgroundResource(R.drawable.selector_goal_few);
			img_middle.setBackgroundResource(R.drawable.settings_goal_moderate_pressed);
			img_high.setBackgroundResource(R.drawable.selector_goal_high);
			break;
		case 3:
			tv_few.setTextColor(getResources().getColor(R.color.settings_goal_text_color));
			tv_middle.setTextColor(getResources().getColor(R.color.settings_goal_text_color));
			tv_high.setTextColor(getResources().getColor(R.color.settings_goal_selected_text_color));
			img_few.setBackgroundResource(R.drawable.selector_goal_few);
			img_middle.setBackgroundResource(R.drawable.selector_goal_middle);
			img_high.setBackgroundResource(R.drawable.settings_goal_severe_pressed);
			break;

		default:
			break;
		}
		DecimalFormat df1 = new DecimalFormat("0.00");
		String kael = df1.format(((goal/1000)*18.842));
		String km = df1.format((goal/1000)*0.416);
		tv_steps.setText(goal+"");
		tv_kael.setText(kael);
		tv_km.setText(km);
	}
	/**
	 * 进度拖动条拖动事件
	 */
	@Override
	public void onProgressChanged(int progress) {
		goal=progress;
		check(goal);
		tv_steps.setText(goal+"");
		
	}

	@Override
	public void onStartTrackingTouch() {
		
	}

	@Override
	public void onStopTrackingTouch() {
		
	}
	
	/**
	 * 检查goal的级数 少量运动/适量运动/高强度运动
	 * @param goal
	 */
	private void check(int goal){
		if(goal<6400&&goal>=1000){
			validate(1);
		}else if(goal<12800&&goal>=6400){
			validate(2);
		}else if(goal<20000&&goal>=12800){
			validate(3);
		}
	}
}
