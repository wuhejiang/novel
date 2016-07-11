package cn.wuhejiang.model;

import cn.wuhejiang.interf.CallBack.XiaoshuoCallBack;

/**
Created by wuhejiang2016-6-25
 */
public interface IXiaoshuoDetailModel {
	void loadXiaoshuoList(XiaoshuoCallBack callBack,String url);
}
