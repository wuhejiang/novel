package cn.wuhejiang.xiaoshuo.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import cn.wuhejiang.entity.MuLv;
import cn.wuhejiang.xiaoshuo.MyApplication;
import cn.wuhejiang.xiaoshuo.R;
import cn.wuhejiang.xiaoshuo.adapter.XiaosuoMlAdapter;

/**
 * create by wuhejiang2016-6-27
 */
public class XiaoshuoMlActivity extends Activity implements OnItemClickListener{
	private ListView listView;
	private List<MuLv> mls;
	

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xiaoshou_mulv_activity);
		listView = (ListView) findViewById(R.id.listview);
		mls= MyApplication.getApp().getMls();
		BaseAdapter mLadapter = new XiaosuoMlAdapter(this, mls);
		listView.setAdapter(mLadapter);
		listView.setOnItemClickListener(this);
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		  String path = mls.get(position).getPath();
		  String firstPath = mls.get(0).getPath();
		  String lastPath=mls.get(mls.size()-1).getPath();
		  Log.i("123", "XiaoshuoMlActivity: "+path);
		Intent intent = new Intent(XiaoshuoMlActivity.this,
				XiaoshuoContextActivity.class);
		intent.putExtra("path", path);
		intent.putExtra("firstPath", firstPath);
		intent.putExtra("lastPath", lastPath);
		startActivity(intent);
	}


}
