package cn.wuhejiang.utils;
/**
 * С˵��ҳ��ַ
 * @author Administrator
 *
 */
public class HttpPath {
	public static String getPath(int type,int pager){
		String path="http://www.1192.org/fenlei/"+type+"_"+pager+".html";
		return path;
	}
}
