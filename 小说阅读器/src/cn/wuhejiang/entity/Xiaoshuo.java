package cn.wuhejiang.entity;

import java.io.Serializable;

public class Xiaoshuo implements Serializable{
	private String name;
	private String picUrl;
	private String author;
	private String text;
	private String type;
	private String lookPath;
	private String downLoad;
	public Xiaoshuo(String name, String picUrl, String author, String text,
			String type, String lookPath, String downLoad) {
		super();
		this.name = name;
		this.picUrl = picUrl;
		this.author = author;
		this.text = text;
		this.type = type;
		this.lookPath = lookPath;
		this.downLoad = downLoad;
	}
	public Xiaoshuo() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLookPath() {
		return lookPath;
	}
	public void setLookPath(String lookPath) {
		this.lookPath = lookPath;
	}
	public String getDownLoad() {
		return downLoad;
	}
	public void setDownLoad(String downLoad) {
		this.downLoad = downLoad;
	}
	@Override
	public String toString() {
		return "Xiaoshuo [name=" + name + ", picUrl=" + picUrl + ", author="
				+ author + ", text=" + text + ", type=" + type + ", lookPath="
				+ lookPath + ", downLoad=" + downLoad + "]";
	}
	
	
	
}
