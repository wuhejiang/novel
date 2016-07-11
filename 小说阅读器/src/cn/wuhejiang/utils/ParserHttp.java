package cn.wuhejiang.utils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import cn.wuhejiang.entity.A;
import cn.wuhejiang.entity.MlJJ;
import cn.wuhejiang.entity.MuLv;
import cn.wuhejiang.entity.Xiaoshuo;

/**
 * 用jsoup框架解析网页
 * 
 * @author Administrator
 * 
 */
public class ParserHttp {
	private static String txt;

	/**
	 * 解析小说
	 * 
	 * @param ty
	 * @param pager
	 * @return
	 */
	public static List<Xiaoshuo> getXiaoshuoList(int ty, int pager) {
		try {
			List<A> as = new ArrayList<A>();
			List<Xiaoshuo> xss = new ArrayList<Xiaoshuo>();
			Document doc = Jsoup.connect(HttpPath.getPath(ty, pager)).get();
			Elements e = doc.getElementsByClass("storelistbt3");
			Elements e1 = e.first().getElementsByClass("sw");
			for (int i = 0; i < e1.size(); i++) {
				String picUrl = e1.get(i).getElementsByTag("img").attr("src");
				String text = e1.get(i).getElementsByClass("txt").text();
				Elements a2 = e1.get(i).getElementsByTag("a");
				for (int j = 0; j < a2.size(); j++) {
					String href = a2.get(j).attr("href");
					String title = a2.get(j).attr("title");
					as.add(new A(href, title));
				}
				xss.add(new Xiaoshuo(as.get(0).getTitle(), picUrl, as.get(2)
						.getTitle(), text, as.get(3).getTitle(), as.get(0)
						.getHref(), as.get(6).getHref()));
				as.clear();
			}
			return xss;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解析目录
	 * 
	 * @param url
	 * @return
	 */
	public static MlJJ getMuLvList(String url) {
		try {
			List<MuLv> mls = new ArrayList<MuLv>();
			Document doc = Jsoup.connect(url).get();
			String jj = doc.getElementsByClass("showhead").text();
			int b=0;
			for (int i = 0; i < jj.length(); i++) {
				if(jj.charAt(i)=='简'){
					 b = i;
					 break;
				}
				
			}
			 txt = jj.substring(b, jj.length());
			Elements e = doc.getElementsByClass("showcontent");
			Elements a=e.first().getElementsByTag("a");
			for (int i = 0; i < a.size(); i++) {
				String path = a.get(i).attr("href");
				String zhangjie = a.get(i).text();
				mls.add(new MuLv(path, zhangjie));
			}
			MlJJ mljj = new MlJJ(txt, mls);
			return mljj;

		} catch (Exception e) {
		}

		return null;
	}
	
}
