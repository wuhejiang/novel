package cn.wuhejiang.model.impl;

import android.os.AsyncTask;
import android.util.Log;
import cn.wuhejiang.entity.MlJJ;
import cn.wuhejiang.interf.CallBack.XiaoshuoCallBack;
import cn.wuhejiang.model.IXiaoshuoDetailModel;
import cn.wuhejiang.utils.ParserHttp;



public class XiaoshuoDetailModel implements IXiaoshuoDetailModel {

	@Override
	public void loadXiaoshuoList(final XiaoshuoCallBack callBack,
			final String url) {
		new AsyncTask<String, String, MlJJ>() {

			@Override
			protected MlJJ doInBackground(String... params) {
				MlJJ mljj = ParserHttp.getMuLvList(url);
				Log.i("123", "XiaoshuoDetailModel: "+mljj.toString());
				return mljj;
			}

			protected void onPostExecute(MlJJ mljj) {
				callBack.onSucesseed(mljj);
			}
		}.execute();
	}

}
