package com.suping.i2w.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
	public static void toastLong(Context context,String str){
		Toast.makeText(context, str, Toast.LENGTH_LONG).show();
	}
	public static void toastShort(Context context,String str){
		Toast.makeText(context, str, Toast.LENGTH_LONG).show();
	}
	
}
