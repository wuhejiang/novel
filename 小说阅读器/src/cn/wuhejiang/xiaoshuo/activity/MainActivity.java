package cn.wuhejiang.xiaoshuo.activity;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.wuhejiang.entity.Xiaoshuo;
import cn.wuhejiang.presenter.impl.XiaoshuoPresenter;
import cn.wuhejiang.utils.NetUtil;
import cn.wuhejiang.utils.SlideMenu;
import cn.wuhejiang.view.IXiaoshuoView;
import cn.wuhejiang.xiaoshuo.R;
import cn.wuhejiang.xiaoshuo.adapter.XiaoshuoAdapter;

public class MainActivity extends Activity implements OnClickListener,
		IXiaoshuoView {
	private SlideMenu slideMenu;
	private TextView tvQt;
	private TextView tvKb;
	private TextView tvKh;
	private TextView tvXh;
	private TextView tvWx;
	private TextView tvDs;
	private TextView tvLs;
	private TextView tvWy;
	private ImageView menuImg;
	private GridView gridView;
	private XiaoshuoPresenter presenter;
	private XiaoshuoAdapter adapter;
	private List<Xiaoshuo> xss;
	private TextView tvType;
	private static final String TYPE_XH = "玄幻魔法";
	private static final String TYPE_WX = "武侠修真";
	private static final String TYPE_DS = "都市言情";
	private static final String TYPE_LS = "历史军事";
	private static final String TYPE_WY = "网游动漫";
	private static final String TYPE_KH = "科幻小说";
	private static final String TYPE_KB = "恐怖灵异";
	private static final String TYPE_QT = "其他小说";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setViews();
		if(!NetUtil.haveNet(this)){
			Toast.makeText(this, "当前网络不可用，请检查网络是否已连接", Toast.LENGTH_SHORT).show();
			return;
		}else{
			presenter = new XiaoshuoPresenter(this, 1, 1);
			presenter.getXiaoshuoList();
		}
		setListeners();
	}

	private void setListeners() {
		tvXh.setOnClickListener(this);
		tvWx.setOnClickListener(this);
		tvDs.setOnClickListener(this);
		tvLs.setOnClickListener(this);
		tvWy.setOnClickListener(this);
		tvKh.setOnClickListener(this);
		tvKb.setOnClickListener(this);
		tvQt.setOnClickListener(this);

		menuImg.setOnClickListener(this);

		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Xiaoshuo xs = xss.get(position);
				Intent intent = new Intent(MainActivity.this,
						XiaoshuoDetailActivity.class);
				intent.putExtra("xs", xs);
				startActivity(intent);
			}
		});
		gridView.setOnScrollListener(new OnScrollListener() {
			boolean isBottom = false;
			int i = 1;

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				switch (scrollState) {
				case SCROLL_STATE_IDLE:
					if (isBottom) {
						presenter = new XiaoshuoPresenter(MainActivity.this, 1,
								i += 1);
						presenter.getXiaoshuoList();
					}
					break;
				}
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				Log.i("123", "firstVisibleItem=" + firstVisibleItem
						+ "visibleItemCount=" + visibleItemCount
						+ "totalItemCount=" + totalItemCount);
				if (firstVisibleItem + visibleItemCount == totalItemCount) {
					isBottom = true;
				} else {
					isBottom = false;
				}
			}
		});
	}

	private void setViews() {
		menuImg = (ImageView) findViewById(R.id.title_bar_menu_btn);
		slideMenu = (SlideMenu) findViewById(R.id.slide_menu);
		tvXh = (TextView) findViewById(R.id.tv_xh);
		tvWx = (TextView) findViewById(R.id.tv_wx);
		tvDs = (TextView) findViewById(R.id.tv_ds);
		tvLs = (TextView) findViewById(R.id.tv_ls);
		tvWy = (TextView) findViewById(R.id.tv_wy);
		tvKh = (TextView) findViewById(R.id.tv_kh);
		tvKb = (TextView) findViewById(R.id.tv_kb);
		tvQt = (TextView) findViewById(R.id.tv_qt);
		tvType = (TextView) findViewById(R.id.title_bar_name);
		gridView = (GridView) findViewById(R.id.gv_xiaoshuo);
	}

	/**
	 * 侧拉菜单的显示和关闭
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_bar_menu_btn:
			if (slideMenu.isMainScreenShowing()) {
				slideMenu.openMenu();
			} else {
				slideMenu.closeMenu();
			}
			break;
		case R.id.tv_xh:
			tvType.setText(TYPE_XH);
			presenter = new XiaoshuoPresenter(this, 1, 1);
			presenter.getXiaoshuoList();
			break;
		case R.id.tv_wx:
			tvType.setText(TYPE_WX);
			presenter = new XiaoshuoPresenter(this, 2, 1);
			presenter.getXiaoshuoList();
			break;
		case R.id.tv_ds:
			tvType.setText(TYPE_DS);
			presenter = new XiaoshuoPresenter(this, 3, 1);
			presenter.getXiaoshuoList();
			break;
		case R.id.tv_ls:
			tvType.setText(TYPE_LS);
			presenter = new XiaoshuoPresenter(this, 4, 1);
			presenter.getXiaoshuoList();
			break;
		case R.id.tv_wy:
			tvType.setText(TYPE_WY);
			presenter = new XiaoshuoPresenter(this, 5, 1);
			presenter.getXiaoshuoList();
			break;
		case R.id.tv_kh:
			tvType.setText(TYPE_KH);
			presenter = new XiaoshuoPresenter(this, 6, 1);
			presenter.getXiaoshuoList();
			break;
		case R.id.tv_kb:
			tvType.setText(TYPE_KB);
			presenter = new XiaoshuoPresenter(this, 7, 1);
			presenter.getXiaoshuoList();
			break;
		case R.id.tv_qt:
			tvType.setText(TYPE_QT);
			presenter = new XiaoshuoPresenter(this, 8, 1);
			presenter.getXiaoshuoList();
			break;

		}

	}

	@Override
	public void onGetXiaoshuoList(Object result) {
		xss = (List<Xiaoshuo>) result;
		if (xss.isEmpty()) {
			Toast.makeText(this, "已经到底了", Toast.LENGTH_SHORT).show();
		} else {
			if (adapter == null) {
				adapter = new XiaoshuoAdapter(this, xss);
				gridView.setAdapter(adapter);
			} else {
				adapter.setContext(this);
				adapter.setXss(xss);
				gridView.setAdapter(adapter);
			}
		}

	}

	@Override
	public void onFailed(Object obj) {
		Toast.makeText(this, "网络信号不好", Toast.LENGTH_SHORT).show();
	}

}
