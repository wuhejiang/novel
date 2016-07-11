package cn.wuhejiang.entity;

import java.io.Serializable;


/**
 *create by wuhejiang2016-6-26
 */
public class MuLv implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String path; 
	private String zhangjie;
	public MuLv(String path, String zhangjie) {
		super();
		this.path = path;
		this.zhangjie = zhangjie;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getZhangjie() {
		return zhangjie;
	}
	public void setZhangjie(String zhangjie) {
		this.zhangjie = zhangjie;
	}
	@Override
	public String toString() {
		return "MuLv [path=" + path + ", zhangjie=" + zhangjie + "]";
	}
	
}
