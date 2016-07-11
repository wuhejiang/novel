package cn.wuhejiang.entity;
/**
Created by wuhejiang2016-6-25
 */
public class A {
	private String href;
	private String title;
	public A(String href, String title) {
		super();
		this.href = href;
		this.title = title;
	}
	
	
	public A() {
		super();
	}



	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}


	@Override
	public String toString() {
		return "A [href=" + href + ", title=" + title  + "]";
	}

	
}
