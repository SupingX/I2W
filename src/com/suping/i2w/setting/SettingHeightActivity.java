package com.suping.i2w.setting;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.NumberPicker.Formatter;
import android.widget.NumberPicker.OnScrollListener;
import android.widget.NumberPicker.OnValueChangeListener;

import com.suping.i2w.R;
import com.suping.i2w.util.FtCmConvertUtil;
import com.suping.i2w.util.I2WConstant;
import com.suping.i2w.util.SharedPreferenceUtil;
import com.suping.i2w.util.SysUtil;
import com.suping.i2w.util.ToastUtil;

public class SettingHeightActivity extends Activity implements OnClickListener{
	private NumberPicker np_height;
	private NumberPicker np_unit;
	private NumberPicker np_feet;
	private NumberPicker np_inch;
	private LinearLayout lin_ft;
	private Button btn_positive;
	private Button btn_negative;
	
	private String [] units;
	private int heightTmp,feetTmp,inchTmp;
	private static final int minHeightCm = 92;
	private static final int maxHeightCm = 241;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);   
		setContentView(R.layout.activity_setting_height);
		initViews();
		initNumberPickers();
		setOnclick();
	}
	
	@Override
	protected void onResume() {
		
		String unitString = (String) SharedPreferenceUtil
				.get(getApplicationContext(), I2WConstant.HEIGHT_UNIT, "none");
		if(unitString.equals("cm")){
			heightTmp = (int) SharedPreferenceUtil
					.get(getApplicationContext(), I2WConstant.HEIGHT, 160);
			feetTmp = FtCmConvertUtil.cm2Foot(heightTmp);
			inchTmp = FtCmConvertUtil.cm2InchMinusFoot(heightTmp);
			np_height.setVisibility(View.VISIBLE);
			lin_ft.setVisibility(View.GONE);
			np_unit.setValue(1);
			np_height.setValue(heightTmp);
			np_feet.setValue(feetTmp);
			np_inch.setValue(inchTmp);
		}else if(unitString.equals("ft")){
			feetTmp = (int) SharedPreferenceUtil
					.get(getApplicationContext(), I2WConstant.HEIGHT_FEET, 0);
			inchTmp = (int) SharedPreferenceUtil
					.get(getApplicationContext(), I2WConstant.HEIGHT_INCH, 0);
		
			heightTmp = FtCmConvertUtil.footInch2Cm(feetTmp, inchTmp);
			np_height.setVisibility(View.GONE);
			lin_ft.setVisibility(View.VISIBLE);
			np_unit.setValue(2);
			np_feet.setValue(feetTmp);
			np_inch.setValue(inchTmp);
			np_height.setValue(heightTmp);
		}else {
			np_feet.setValue(feetTmp);
			np_unit.setValue(inchTmp);
			np_height.setValue(feetTmp);
			np_unit.setValue(1);
		}
		
		super.onResume();
	}
	
	private void initNumberPickers() {
		units = new String[]{"cm","ft"};
		np_height.setMaxValue(maxHeightCm);
		np_height.setMinValue(minHeightCm);
		
		
		((EditText)(np_height.getChildAt(0))).setFocusable(false);
		((EditText)(np_height.getChildAt(0))).setFocusableInTouchMode(false);
		((EditText)(np_height.getChildAt(0))).setInputType(InputType.TYPE_NULL);
		
		np_unit.setDisplayedValues(units);
		np_unit.setMaxValue(units.length);
		np_unit.setMinValue(1);
		((EditText)(np_unit.getChildAt(0))).setFocusable(false);
		((EditText)(np_unit.getChildAt(0))).setFocusableInTouchMode(false);
		((EditText)(np_unit.getChildAt(0))).setInputType(InputType.TYPE_NULL);
		
		np_feet.setMaxValue(FtCmConvertUtil.getMaxHeightFoot(minHeightCm,maxHeightCm));
		np_feet.setMinValue(FtCmConvertUtil.getMinHeightFoot(minHeightCm, maxHeightCm));
		((EditText)(np_feet.getChildAt(0))).setFocusable(false);
		((EditText)(np_feet.getChildAt(0))).setFocusableInTouchMode(false);
		((EditText)(np_feet.getChildAt(0))).setInputType(InputType.TYPE_NULL);
		
		np_inch.setMaxValue(FtCmConvertUtil.getMaxHeightInch(minHeightCm, maxHeightCm));
		np_inch.setMinValue(FtCmConvertUtil.getMinHeightInch(minHeightCm, maxHeightCm));
		((EditText)(np_inch.getChildAt(0))).setFocusable(false);
		((EditText)(np_inch.getChildAt(0))).setFocusableInTouchMode(false);
		((EditText)(np_inch.getChildAt(0))).setInputType(InputType.TYPE_NULL);
		
	}

	private void initViews() {
		np_height = (NumberPicker) findViewById(R.id.np_height);
		np_unit = (NumberPicker) findViewById(R.id.np_unit);
		np_feet = (NumberPicker) findViewById(R.id.np_feet);
		np_inch = (NumberPicker) findViewById(R.id.np_inch);
		lin_ft = (LinearLayout) findViewById(R.id.lin_ft);
		btn_positive = (Button) findViewById(R.id.btn_positive);
		btn_negative = (Button) findViewById(R.id.btn_negative);
	}

	private void setOnclick() {
		btn_positive.setOnClickListener(this);
		btn_negative.setOnClickListener(this);
		//滑动
		np_height.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChange(NumberPicker view, int scrollState) {
				switch (scrollState) {
				case OnScrollListener.SCROLL_STATE_FLING:
					SysUtil.print("--- i am fying ---");
					break;
				case OnScrollListener.SCROLL_STATE_IDLE:
					SysUtil.print("--- i am stoped ---");
					break;
				case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
					SysUtil.print("--- i am touching ---");
					break;

				default:
					break;
				}
				
			}
		});
		//值改变
		np_height.setOnValueChangedListener(new OnValueChangeListener() {
			
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				SysUtil.print(oldVal+"--->"+heightTmp);
			}
		});
		
		//格式化？
		np_height.setFormatter(new Formatter() {
			@Override
			public String format(int value) {
				String tmp = String.valueOf(value);
				if(value<99){
					tmp = "0"+tmp;
				}
				return tmp;
			}
		});
		//单位变化  cm-->np_height   ft-->np_feet + np_inch
		np_unit.setOnValueChangedListener(new OnValueChangeListener() {
			
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				switch (newVal) {
				//单位是cm时
				case 1:
					np_height.setVisibility(View.VISIBLE);
					lin_ft.setVisibility(View.GONE);
					//英寸和英尺没变过,则用临时变量的值
					if(np_feet.getValue()==feetTmp&&np_inch.getValue()==inchTmp){
						np_height.setValue(heightTmp);
					}else {
					//有变化就重新计算新的cm值	
						int height = FtCmConvertUtil
								.footInch2Cm(np_feet.getValue(), np_inch.getValue());
						np_height.setValue(height);
						//将新的cm值存入临时变量
						heightTmp = height;
					}
					break;
				case 2:
					np_height.setVisibility(View.GONE);
					lin_ft.setVisibility(View.VISIBLE);
					//值没变
					int heightV = np_height.getValue();
					if(heightV==heightTmp){
						np_feet.setValue(feetTmp);
						np_inch.setValue(inchTmp);
					}else{
						int feet = FtCmConvertUtil.cm2Foot(heightV);
						int inch = FtCmConvertUtil.cm2InchMinusFoot(heightV);
						np_feet.setValue(feet);
						np_inch.setValue(inch);
						feetTmp = feet;
						inchTmp = inch;
					}
					break;

				default:
					break;
				}
			}
		});
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_positive:
			if(np_unit.getValue()==1){
				String v_unit = "cm";
				int v_height = np_height.getValue();
				ToastUtil.toastLong(SettingHeightActivity.this, v_height+","+v_unit);
				SharedPreferenceUtil.put(getApplicationContext(), I2WConstant.HEIGHT, v_height);
				SharedPreferenceUtil.put(getApplicationContext(), I2WConstant.HEIGHT_UNIT, v_unit);
			}
			if(np_unit.getValue()==2){
				String v_unit = "ft";
				int v_feet = np_feet.getValue();
				int v_inch = np_inch.getValue();
				ToastUtil.toastLong(SettingHeightActivity.this, v_feet+","+v_inch+","+v_unit);
				SharedPreferenceUtil.put(getApplicationContext(), I2WConstant.HEIGHT_FEET, v_feet);
				SharedPreferenceUtil.put(getApplicationContext(), I2WConstant.HEIGHT_INCH, v_inch);
				SharedPreferenceUtil.put(getApplicationContext(), I2WConstant.HEIGHT_UNIT, v_unit);
			}
			
			
			this.finish();
			break;
		case R.id.btn_negative:
			this.finish();
			break;
		default:
			break;
		}
	}
	
}
