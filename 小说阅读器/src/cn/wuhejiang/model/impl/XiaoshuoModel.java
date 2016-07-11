package cn.wuhejiang.model.impl;

import java.util.List;
import android.os.AsyncTask;
import android.util.Log;
import cn.wuhejiang.entity.Xiaoshuo;
import cn.wuhejiang.interf.CallBack.XiaoshuoCallBack;
import cn.wuhejiang.model.IXiaoshuoModel;
import cn.wuhejiang.utils.ParserHttp;



public class XiaoshuoModel implements IXiaoshuoModel {

	@Override
	public void loadXiaoshuoList(final XiaoshuoCallBack callBack,
			final int type, final int pager) {
		new AsyncTask<String, String, List<Xiaoshuo>>() {
			@Override
			protected List<Xiaoshuo> doInBackground(String... params) {
				List<Xiaoshuo>xss=ParserHttp.getXiaoshuoList(type, pager);
				return xss;
			}

			protected void onPostExecute(List<Xiaoshuo> result) {
				callBack.onSucesseed(result);
			}
		}.execute();
	}

}
