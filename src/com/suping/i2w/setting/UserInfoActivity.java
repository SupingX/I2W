package com.suping.i2w.setting;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.TextView;

import com.suping.i2w.R;
import com.suping.i2w.util.I2WConstant;
import com.suping.i2w.util.SharedPreferenceUtil;
import com.suping.i2w.util.SysUtil;

public class UserInfoActivity extends Activity implements OnClickListener {
	private TextView tv_back;
	private TextView tv_sex;
	private TextView tv_height;
	private TextView tv_weight;
	private TextView tv_birthday;
	private TextView tv_height_unit;
	
	private CheckBox box_men;
	private CheckBox box_women;

	private LinearLayout lin_height;
	private LinearLayout lin_weight;
	private LinearLayout lin_birthday;
	
	private int tmp_kg;
	private int tmp_lb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info);
		initViews();
		setOnClick();
	}
	
	@Override
	protected void onResume() {
		//加载个人信息
		String unit = (String) SharedPreferenceUtil
				.get(getApplicationContext(), I2WConstant.HEIGHT_UNIT, "cm");
		if(unit.equals("cm")){
			int height = (int) SharedPreferenceUtil
					.get(getApplicationContext(), I2WConstant.HEIGHT, 160);
			SysUtil.print(unit+","+height);
			tv_height.setText(String.valueOf(height));
		}else if(unit.equals("ft")){
			int feet = (int) SharedPreferenceUtil
					.get(getApplicationContext(), I2WConstant.HEIGHT_FEET, 50);
			SysUtil.print(unit+","+feet);
			int inch = (int) SharedPreferenceUtil
					.get(getApplicationContext(), I2WConstant.HEIGHT_INCH, 50);
			SysUtil.print(unit+","+feet);
			tv_height.setText(String.valueOf(feet+"\""+inch+"'"));
		}
		
		tv_height_unit.setText(unit);
		
		tv_sex.setText("男");
		tv_weight.setText("89");
		tv_birthday.setText("1988-2-2");
		super.onResume();
	}
	private void initViews(){
		tv_back = (TextView) findViewById(R.id.tv_back);
		tv_sex = (TextView) findViewById(R.id.sex);
		tv_weight = (TextView) findViewById(R.id.tv_weight);
		tv_height = (TextView) findViewById(R.id.tv_height);
		tv_birthday = (TextView) findViewById(R.id.tv_birthday);
		tv_height_unit = (TextView) findViewById(R.id.tv_height_unit);
		box_men = (CheckBox) findViewById(R.id.men_check);
		box_women = (CheckBox) findViewById(R.id.women_check);
		lin_height = (LinearLayout) findViewById(R.id.height);
		lin_weight = (LinearLayout) findViewById(R.id.weight);
		lin_birthday = (LinearLayout) findViewById(R.id.birthday);
		
	}
	
	private void setOnClick() {
		tv_back.setOnClickListener(this);
		lin_height.setOnClickListener(this);
		lin_weight.setOnClickListener(this);
		lin_birthday.setOnClickListener(this);
		
		box_men.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(!isChecked){
					System.out.println("-------1-------");
					box_women.setChecked(true);
					box_women.setClickable(false);
					box_men.setClickable(true);
					tv_sex.setText("女");
				}
				else{
					System.out.println("------2--------");
					box_women.setChecked(false);
					box_women.setClickable(true);
					box_men.setClickable(false);
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
					box_women.setClickable(true);
					box_men.setClickable(false);
					tv_sex.setText("男");
				}
				else{
					System.out.println("-------1-------");
					box_men.setChecked(false);
					box_women.setClickable(false);
					box_men.setClickable(true);
					tv_sex.setText("女");
				}
			}
		});
	}


	@Override
	public void onClick(View v) {
		SharedPreferences shared = getSharedPreferences(I2WConstant.SHARED_NAME, MODE_PRIVATE);
		SharedPreferences.Editor editor = shared.edit();
		switch (v.getId()) {
		case R.id.tv_back:
			UserInfoActivity.this.finish();
//			startActivity(new Intent(UserInfoActivity.this,SettingActivity.class));
			break;
		case R.id.height:
//			ToastUtil.toastShort(UserInfoActivity.this, "1111");
//			UserInfoActivity.this.finish();
//			editor.putString("name", "suping");
//			editor.putFloat("weight", 98.2f);
//			editor.putBoolean("marryed", false);
//			editor.commit();
//			UserInfoActivity.this.finish();
			startActivity(new Intent(UserInfoActivity.this,SettingHeightActivity.class));
			break;
		case R.id.weight:
//			String name = shared.getString("name", "无名");
//			float weight = shared.getFloat("weight", 0.0f);
//			boolean marryed = shared.getBoolean("marryed", false);
//			ToastUtil.toastShort(UserInfoActivity.this, name+","+weight+","+((marryed)?"marryed":"no"));
			View view = this.getLayoutInflater().inflate(R.layout.dialog_setting_weight, null);
			final NumberPicker np_weight = (NumberPicker) view.findViewById(R.id.np_weight);
			NumberPicker np_weight_unit = (NumberPicker) view.findViewById(R.id.np_weight_unit);
			np_weight.setMaxValue(255);
			np_weight.setMinValue(32);
			String[] units = new String[]{"kg","lb"};
			np_weight_unit.setDisplayedValues(units);
			np_weight_unit.setMaxValue(units.length-1);
			np_weight_unit.setMinValue(0);
			
			//设置当重量单位变化时，数值会发生相应转化。
			np_weight_unit.setOnValueChangedListener(new OnValueChangeListener() {
				@Override
				public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
					switch (newVal) {
					//当单位为kg时，转化lb-->kg
					case 0:
						int v_lb = np_weight.getValue();
						//磅数没有变化
						if(tmp_lb==v_lb){
							np_weight.setValue(tmp_kg);
							
						}else{
							tmp_kg =(int) (np_weight.getValue()*0.4535924);
							
						}
						
						break;

					default:
						break;
					}
				}
			});
			
			AlertDialog.Builder mBuilder = new AlertDialog.Builder(UserInfoActivity.this);
			mBuilder.setTitle("设置体重")
					.setView(view)
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							
						}
					})
					.setNegativeButton("取消", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							
						}
					});
			mBuilder.create().show();
			break;
		case R.id.birthday:
			startActivity(new Intent(UserInfoActivity.this,SettingBirthdayActivity.class));
			break;

		default:
			break;
		}
	}
	
}
