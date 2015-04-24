package com.suping.i2w;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.ImageView;

import com.suping.i2w.leftmenu.LeftmenuActivity;
import com.suping.i2w.setting.SettingActivity;

public class Main extends Activity implements OnClickListener{
	
	private ImageView moreImg; 	//head中左上角图标按钮，显示更多功能
	private ImageView settingImg; 	//head中右上角图标按钮，显示更多个人设置
	private ImageView detia_sport; 	//运动圆点
	private ImageView detia_sleep; 	//睡眠圆点
	private List<View> mList; 	//content内容显示（运动视图 睡眠视图）列表
	private LayoutInflater inflater;
	private ViewPager mViewPager;
	@Override
	protected void onCreate(Bundle savedInstanceState)  {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
		initViewPager();
		registerListener();
	}
	
	private void initViews(){
		moreImg = (ImageView) findViewById(R.id.img_more);
		settingImg = (ImageView) findViewById(R.id.img_setting);
		detia_sport = (ImageView) findViewById(R.id.detia_sport);
		detia_sleep = (ImageView) findViewById(R.id.detia_sleep);
	}
	
	private void initViewPager(){
		mViewPager = (ViewPager) findViewById(R.id.guidePages);
		inflater = getLayoutInflater();
		View sportV = inflater.inflate(R.layout.layout_info_sport, null);
		View sleepV = inflater.inflate(R.layout.layout_info_sleep, null);
		mList = new ArrayList<>();
		mList.add(sportV);
		mList.add(sleepV);
		mViewPager.setAdapter(new ViewPagerAdapter(mList));
		mViewPager.setCurrentItem(0);
		mViewPager.setOnPageChangeListener(new ViewPagerOnChangeListener());
	}
	
	private void registerListener(){
		moreImg.setOnClickListener(this);
		settingImg.setOnClickListener(this);
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
		case R.id.img_setting:
		startActivity(new Intent(Main.this,SettingActivity.class));
		Main.this.finish();
		overridePendingTransition(
				R.anim.activity_from_right_to_left_enter 
				,R.anim.activity_from_right_to_left_exit);
		default:
			break;
		}
	}

	
	private class  ViewPagerAdapter extends PagerAdapter{
		private List<View> listVs;
		public ViewPagerAdapter(List<View> list){
			this.listVs = list;
		}
		@Override
		public int getCount() {
			return listVs.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0==(View)arg1;
		}
		@Override
		public Object instantiateItem(View container, int position) {
			((ViewPager)container).addView(listVs.get(position), 0);
			return listVs.get(position);
		}
		@Override
		public void destroyItem(View container, int position, Object object) {
			// TODO Auto-generated method stub
			((ViewPager)container).removeView(listVs.get(position));
		}
		
	}
	private class ViewPagerOnChangeListener implements OnPageChangeListener{

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}
		@Override
		public void onPageSelected(int arg0) {
			switch (arg0) {
			case 0:
				detia_sport.setImageResource(R.drawable.detia_btn_selected_page);
				detia_sleep.setImageResource(R.drawable.detia_btn_unselected_page);
				break;
			case 1:
				detia_sleep.setImageResource(R.drawable.detia_btn_selected_page);
				detia_sport.setImageResource(R.drawable.detia_btn_unselected_page);
				break;

			default:
				break;
			}
		}
		
	}


}
