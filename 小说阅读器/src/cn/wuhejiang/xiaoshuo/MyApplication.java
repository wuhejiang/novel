package cn.wuhejiang.xiaoshuo;

import java.util.ArrayList;
import java.util.List;
import cn.wuhejiang.entity.MuLv;
import android.app.Application;

/**
 *create by wuhejiang2016-6-29
 */
public class MyApplication extends Application{
	private List<MuLv> mls;
	private static MyApplication app;

	@Override
	public void onCreate() {
		super.onCreate();
		MyApplication.app=this;
		mls=new ArrayList<MuLv>() ;
		
	}
	
	public static MyApplication getApp(){
		return app;
	}
	
	public List<MuLv> getMls(){
		return mls;
	}
	
	public void saveMuLv(List<MuLv> mls){
		this.mls=mls;
	}
}
