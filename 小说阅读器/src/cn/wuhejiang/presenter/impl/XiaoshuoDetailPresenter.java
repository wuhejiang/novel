package cn.wuhejiang.presenter.impl;

import android.util.Log;
import cn.wuhejiang.entity.MlJJ;
import cn.wuhejiang.interf.CallBack.XiaoshuoCallBack;
import cn.wuhejiang.model.IXiaoshuoDetailModel;
import cn.wuhejiang.model.impl.XiaoshuoDetailModel;
import cn.wuhejiang.presenter.IXiaoshuoPresenter;
import cn.wuhejiang.view.IXiaoshuoView;


/**
 *create by wuhejiang2016-6-26
 */
public class XiaoshuoDetailPresenter implements IXiaoshuoPresenter{
	private IXiaoshuoDetailModel model;
	private IXiaoshuoView view;
	private String path;

	public XiaoshuoDetailPresenter(IXiaoshuoView view, String path) {
		super();
		model = new XiaoshuoDetailModel();
		this.view=view;
		this.path = path;
	}


	@Override
	public void getXiaoshuoList() {
		model.loadXiaoshuoList(new XiaoshuoCallBack() {
			
			@Override
			public void onSucesseed(Object obj) {
				MlJJ mljj= (MlJJ) obj;
				view.onGetXiaoshuoList(mljj);
			}
			
			@Override
			public void failed(Object error) {
				// TODO Auto-generated method stub
				
			}
		}, path);
	}

}
