package cn.wuhejiang.xiaoshuo.adapter;

import java.util.List;
import java.util.zip.Inflater;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.wuhejiang.entity.Xiaoshuo;
import cn.wuhejiang.xiaoshuo.R;

import com.bumptech.glide.Glide;

/**
 * create by wuhejiang2016-6-26
 */
public class XiaoshuoAdapter extends BaseAdapter {
	private Context context;
	private List<Xiaoshuo> xss;

	public XiaoshuoAdapter() {
		super();
	}

	public XiaoshuoAdapter(Context context, List<Xiaoshuo> xss) {
		super();
		this.context = context;
		this.xss = xss;
		
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public List<Xiaoshuo> getXss() {
		return xss;
	}

	public void setXss(List<Xiaoshuo> xss) {
		this.xss = xss;
	}


	@Override
	public int getCount() {
		return xss.size();
	}

	@Override
	public Xiaoshuo getItem(int position) {
		return xss.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Xiaoshuo xs = xss.get(position);
		ViewHolder holder = null;
		if (convertView == null) {
			LayoutInflater inflater=LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.xiaoshuo_gv_item, null);
			holder = new ViewHolder();
			holder.bookName = (TextView) convertView.findViewById(R.id.tv_name);
			holder.bookPic = (ImageView) convertView
					.findViewById(R.id.iv_xiaoshuo_pic);
			convertView.setTag(holder);
		}
		holder = (ViewHolder) convertView.getTag();
		holder.bookName.setText(xs.getName());
		Glide.with(context).load(xs.getPicUrl()).into(holder.bookPic);
		return convertView;
	}

	class ViewHolder {
		TextView bookName;
		ImageView bookPic;
	}

}
