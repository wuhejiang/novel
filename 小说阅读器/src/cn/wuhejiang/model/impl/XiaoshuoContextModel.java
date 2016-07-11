package cn.wuhejiang.model.impl;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.os.AsyncTask;
import cn.wuhejiang.model.IXiaoshuoContextModel;

/**
 *create by wuhejiang2016-6-30
 */
public class XiaoshuoContextModel implements IXiaoshuoContextModel{

	@Override
	public void loadText(final AsytaskCallBack callBack,final String url) {
		new AsyncTask<String, String, String>() {

			@Override
			protected String doInBackground(String... params) {
				try {
					Document doc = Jsoup.connect(url).get();
					String e = doc.getElementsByClass("pdbox").text();
					return e;
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				return null;
			}
			protected void onPostExecute(String result) {
				callBack.onLoadedText(result);
			}
		}.execute();
	}
	public interface AsytaskCallBack{
		void onLoadedText(String result);
	}
}
