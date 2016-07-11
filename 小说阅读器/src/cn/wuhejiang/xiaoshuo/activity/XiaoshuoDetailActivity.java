package cn.wuhejiang.xiaoshuo.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.wuhejiang.entity.MlJJ;
import cn.wuhejiang.entity.MuLv;
import cn.wuhejiang.entity.Xiaoshuo;
import cn.wuhejiang.interf.CallBack;
import cn.wuhejiang.model.impl.DownModel;
import cn.wuhejiang.presenter.impl.XiaoshuoDetailPresenter;
import cn.wuhejiang.utils.HandlerBitmap;
import cn.wuhejiang.utils.Tools;
import cn.wuhejiang.view.IXiaoshuoView;
import cn.wuhejiang.xiaoshuo.MyApplication;
import cn.wuhejiang.xiaoshuo.R;
import cn.wuhejiang.xiaoshuo.service.DownloadService;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

/**
 * create by wuhejiang2016-6-26
 */
public class XiaoshuoDetailActivity extends Activity implements IXiaoshuoView,
		OnClickListener {
	private Xiaoshuo xs;
	private Button btMu;
	private Button btShare;
	private Button btDownLown;
	private TextView tvName;
	private TextView tvAuthor;
	private TextView tvType;
	private TextView tvTime;
	private TextView tvZj;
	private ImageView pic1;
	private ImageView pic;
	private RequestQueue mQueue;
	private XiaoshuoDetailPresenter presenter;
	private String lookPath;
	private String type;
	private String pci;
	private String author;
	private String name;
	private List<MuLv> mls;
	private TextView tvTxt;
	private ArrayList<MuLv> mlss;
	private String dowmLoad;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xiaoshuo_detail_activity);
		setView();
		mlss=new ArrayList<MuLv>();
		Intent intent = getIntent();
		xs = (Xiaoshuo) intent.getSerializableExtra("xs");
		mQueue = Volley.newRequestQueue(this);
		// 显示数据
		setData();
		presenter = new XiaoshuoDetailPresenter(this, lookPath);
		presenter.getXiaoshuoList();
		setListeners();
		
		IntentFilter filter=new IntentFilter();
		filter.addAction("YI_XIA_ZAI");
		filter.addAction("SD_BU_CUN_ZAI");
		filter.addAction("KAI_SHI_XIA_ZAI");
		BroadcastReceiver receiver=new MyBroadCaseReceiver();
		//注册广播
		registerReceiver(receiver, filter);
	}
	/**
	 * 广播
	 */
	public class MyBroadCaseReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if("YI_XIA_ZAI".equals(action)){
				Tools.showInfo(XiaoshuoDetailActivity.this, "已经下载了");
			}
			if("SD_BU_CUN_ZAI".equals(action)){
				Tools.showInfo(XiaoshuoDetailActivity.this, "sd卡不存在");
			}
			if("KAI_SHI_XIA_ZAI".equals(action)){
				Tools.showInfo(XiaoshuoDetailActivity.this, "开始下载");
			}
			if("XIA_ZAI_WANG_CHENG".equals(action)){
				Tools.showInfo(XiaoshuoDetailActivity.this, "下载完成");
			}
		}
		
	}
	
	private void setListeners() {
		btMu.setOnClickListener(this);
		btDownLown.setOnClickListener(this);
		btShare.setOnClickListener(this);
	}

	private void setView() {
		tvName = (TextView) findViewById(R.id.tv_detail_name);
		tvAuthor = (TextView) findViewById(R.id.tv_detail_author);
		tvType = (TextView) findViewById(R.id.tv_detail_type);
		tvZj = (TextView) findViewById(R.id.tv_detail_zj);
		btDownLown = (Button) findViewById(R.id.bt_detail_downlown);
		btShare = (Button) findViewById(R.id.bt_share);
		btMu = (Button) findViewById(R.id.bt_detail_mulv);
		pic1 = (ImageView) findViewById(R.id.iv_pic1);
		pic = (ImageView) findViewById(R.id.iv_pic);
		tvTxt = (TextView) findViewById(R.id.tv_detail);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_detail_mulv:
			Intent intent = new Intent(this, XiaoshuoMlActivity.class);
			startActivity(intent);
			break;
		case R.id.bt_detail_downlown:
			setDialog();
			break;
		case R.id.bt_share:
			share();
			break;

		}
	}
	/**
	 * 分享
	 */
	private void share() {
		Intent intent=new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain*");//分享类型
		intent.putExtra(Intent.EXTRA_TEXT,lookPath);//分享的文本
		startActivity(Intent.createChooser(intent, "分享到："));
	}

	@Override
	public void onGetXiaoshuoList(Object obj) {
		MlJJ mljj = (MlJJ) obj;
		tvTxt.setText(mljj.getTxt());
		mls = mljj.getMls();
		for (int i = 1; i < mls.size(); i=i+2) {
			mlss.add(new MuLv(mls.get(i).getPath(), mls.get(i).getZhangjie()));
			
		}
		//把小说目录结合保存到MyApplication中
		MyApplication.getApp().saveMuLv(mlss);
		tvZj.setText("最新章节：" + mls.get(mls.size() - 1).getZhangjie());
	}

	/**
	 * 弹出对话框
	 */
	private void setDialog() {
		AlertDialog.Builder ab = new AlertDialog.Builder(
				XiaoshuoDetailActivity.this);
		ab.setTitle("文本下载");
		ab.setPositiveButton("下载", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent=new Intent(XiaoshuoDetailActivity.this , DownloadService.class);
				intent.putExtra("name", name);
				intent.putExtra("dowmLoad", dowmLoad);
				startService(intent);
			}

		});
		ab.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				return;
			}

		});
		ab.show();
	}

	private void setData() {
		name = xs.getName();
		author = xs.getAuthor();
		pci = xs.getPicUrl();
		type = xs.getType();
		dowmLoad = xs.getDownLoad();
		lookPath = xs.getLookPath();
		tvName.setText(name);
		tvAuthor.setText("作者:" + author);
		tvType.setText("类型：" + type);

		Glide.with(this).load(pci).into(pic1);
		ImageRequest ir = new ImageRequest(pci, new Listener<Bitmap>() {

			@Override
			public void onResponse(Bitmap response) {
				Bitmap b = HandlerBitmap.createBlurBitmap(response, 20);
				pic.setImageBitmap(b);
			}
		}, 0, 0, Config.RGB_565, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {

			}
		});
		mQueue.add(ir);

	}

	@Override
	public void onFailed(Object obj) {
	Toast.makeText(this, "网络信号不好", Toast.LENGTH_SHORT).show();
	}

}
