package cn.wuhejiang.interf;

/**
 * Created by wuhejiang2016-6-25
 */
public interface CallBack {
	public interface XiaoshuoCallBack{
		void onSucesseed(Object obj);
		void failed(Object error);
	}
}
