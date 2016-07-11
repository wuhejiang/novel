package cn.wuhejiang.xiaoshuo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import cn.wuhejiang.entity.MuLv;
import cn.wuhejiang.model.impl.XiaoshuoContextModel;
import cn.wuhejiang.model.impl.XiaoshuoContextModel.AsytaskCallBack;
import cn.wuhejiang.utils.ParserHttp;
import cn.wuhejiang.xiaoshuo.R;

/**
 * create by wuhejiang2016-6-27
 */
public class XiaoshuoContextActivity extends Activity {
	private TextView tvContext;
	private XiaoshuoContextModel model;
	private String path;
	private String current;
	private long subCurrent;
	private String first;
	private String last;
	private long subFirst;
	private long subLast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xiaosuo_context_activity);

		tvContext = (TextView) findViewById(R.id.tv_context);

		Intent intent = getIntent();
		path = intent.getStringExtra("path");
		String firstPath = intent.getStringExtra("firstPath");
		String lastPath = intent.getStringExtra("lastPath");

		first = firstPath.substring(path.length() - 10, path.length() - 5);
		last = lastPath.substring(path.length() - 10, path.length() - 5);
		current = path.substring(path.length() - 10, path.length() - 5);
		subCurrent = Long.parseLong(current);
		subFirst = Long.parseLong(first);
		subLast = Long.parseLong(last);

		Log.i("123", "XiaoshuoContextActivity" + path);

		String url = "http://www.1192.org" + path;
		model = new XiaoshuoContextModel();
		model.loadText(new AsytaskCallBack() {

			@Override
			public void onLoadedText(String result) {
				tvContext.setText(result);
			}
		}, url);

	}

	public void doClick(View v) {
		switch (v.getId()) {
		case R.id.bt_next:
			next();
			break;
		case R.id.bt_previous:
			previous();
			break;

		}
	}

	// 下一章
	private void next() {
		subCurrent += 1;
		if (subCurrent > subLast) {
			Toast.makeText(this, "已经到最后一章了", Toast.LENGTH_SHORT).show();
		} else {
			Log.i("123", subCurrent + "");
			String url = "http://www.1192.org"
					+ path.substring(0, path.length() - 10) + subCurrent
					+ ".html";
			Log.i("123", url);
			model.loadText(new AsytaskCallBack() {

				@Override
				public void onLoadedText(String result) {
					tvContext.setText(result);
				}
			}, url);
		}
	}

	// 上一章
	private void previous() {
		subCurrent = subCurrent - 1;
		if (subCurrent < subFirst) {
			Toast.makeText(this, "已经到第一章了", Toast.LENGTH_SHORT).show();

		} else {
			Log.i("123", subCurrent + "");
			String url1 = "http://www.1192.org"
					+ path.substring(0, path.length() - 10) + subCurrent
					+ ".html";
			Log.i("123", url1);
			model.loadText(new AsytaskCallBack() {

				@Override
				public void onLoadedText(String result) {
					tvContext.setText(result);
				}
			}, url1);
		}
	}

}
