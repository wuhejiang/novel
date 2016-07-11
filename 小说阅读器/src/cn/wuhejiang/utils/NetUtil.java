package cn.wuhejiang.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetUtil {
	public static boolean haveNet(Context context){
		ConnectivityManager cm=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if(cm==null){
			return false;
		}
		NetworkInfo ni=cm.getActiveNetworkInfo();
		if(ni==null){
			return false;
		}
		return true;
	}
}
