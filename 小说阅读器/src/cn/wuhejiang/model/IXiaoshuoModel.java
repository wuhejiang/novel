package cn.wuhejiang.model;

import cn.wuhejiang.interf.CallBack.XiaoshuoCallBack;



/**
Created by wuhejiang2016-6-25
 */
public interface IXiaoshuoModel {
	void loadXiaoshuoList(XiaoshuoCallBack callBack, int type, int pager);
}
