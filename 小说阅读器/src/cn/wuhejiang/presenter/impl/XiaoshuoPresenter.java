package cn.wuhejiang.presenter.impl;

import java.util.List;

import android.util.Log;
import cn.wuhejiang.entity.Xiaoshuo;
import cn.wuhejiang.interf.CallBack.XiaoshuoCallBack;
import cn.wuhejiang.model.IXiaoshuoModel;
import cn.wuhejiang.model.impl.XiaoshuoModel;
import cn.wuhejiang.presenter.IXiaoshuoPresenter;
import cn.wuhejiang.view.IXiaoshuoView;


/**
 * Created by wuhejiang2016-6-25
 */
public class XiaoshuoPresenter implements IXiaoshuoPresenter {
	private IXiaoshuoView view;
	private IXiaoshuoModel model;
	private int pager;
	private int type;
	
	public XiaoshuoPresenter() {
		super();
	}

	public XiaoshuoPresenter(IXiaoshuoView view, int type, int pager) {
		super();
		this.view = view;
		this.type = type;
		this.pager = pager;
		model = new XiaoshuoModel();
	}

	
	public IXiaoshuoView getView() {
		return view;
	}

	public void setView(IXiaoshuoView view) {
		this.view = view;
	}

	public int getPager() {
		return pager;
	}

	public void setPager(int pager) {
		this.pager = pager;
	}

	@Override
	public void getXiaoshuoList() {
		model.loadXiaoshuoList(new XiaoshuoCallBack() {
			@Override
			public void onSucesseed(Object obj) {
				List<Xiaoshuo> xss = (List<Xiaoshuo>) obj;
				view.onGetXiaoshuoList(xss);
			}

			@Override
			public void failed(Object error) {
				view.onFailed(error);
			}
		}, type, pager);
	}
}
