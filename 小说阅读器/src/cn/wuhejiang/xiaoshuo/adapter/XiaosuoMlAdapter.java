package cn.wuhejiang.xiaoshuo.adapter;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.wuhejiang.entity.MuLv;
import cn.wuhejiang.xiaoshuo.R;

/**
 *create by wuhejiang2016-6-27
 */
public class XiaosuoMlAdapter extends BaseAdapter {
	private Context context;
	private List<MuLv>mls;
	private LayoutInflater inflater;
	

	public XiaosuoMlAdapter(Context context, List<MuLv> mls) {
		super();
		this.context = context;
		this.mls = mls;
		inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mls.size();
	}

	@Override
	public MuLv getItem(int arg0) {
		return mls.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MuLv ml = mls.get(position);
		ViewHodel holder=null;
		if(convertView==null){
			convertView=inflater.inflate(R.layout.xiaoshuo_ml_item, null);
			holder=new ViewHodel();
			holder.tvMl=(TextView)convertView.findViewById(R.id.tv_ml);
			convertView.setTag(holder);
		}
		holder=(ViewHodel) convertView.getTag();
		
		holder.tvMl.setText(ml.getZhangjie());
		return convertView;
	}
	class ViewHodel{
		TextView tvMl;
	}
}
