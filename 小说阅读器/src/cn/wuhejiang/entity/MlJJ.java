package cn.wuhejiang.entity;

import java.util.List;

/**
 *分装小说目录和简介
 * 
 *create by wuhejiang2016-6-27
 */
public class MlJJ {
	private String txt;
	List<MuLv>mls;
	public MlJJ() {
		super();
	}
	public MlJJ(String txt, List<MuLv> mls) {
		super();
		this.txt = txt;
		this.mls = mls;
	}
	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
	public List<MuLv> getMls() {
		return mls;
	}
	public void setMls(List<MuLv> mls) {
		this.mls = mls;
	}
	@Override
	public String toString() {
		return "MlJJ [txt=" + txt + ", mls=" + mls + "]";
	}
	
	
}
