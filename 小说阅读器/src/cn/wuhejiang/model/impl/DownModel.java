package cn.wuhejiang.model.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import cn.wuhejiang.interf.CallBack.XiaoshuoCallBack;

/**
 * create by wuhejiang2016-7-1
 */
public class DownModel {
	public void downXiaoshuo(final String path, final String name,
			final XiaoshuoCallBack xiaoshuoCallBack) {
		new AsyncTask<String, String, String>() {
			private File fileDir;

			@Override
			protected String doInBackground(String... params) {
				try {
					if (Environment.getExternalStorageState().equals(
							Environment.MEDIA_MOUNTED)) {
						fileDir = new File(
								Environment
										.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
								name + ".txt");
						if (fileDir.exists()) {
							return "已经下载过了";
						}
						// 父目录不存在 创建父目录
						if (!fileDir.getParentFile().exists()) {
							fileDir.getParentFile().mkdirs();
						}

					} else {
						return "sd卡不可读";
					}
					URL url = new URL("http://www.1192.org" + path);
					Log.i("123", path);
					HttpURLConnection ucon = (HttpURLConnection) url
							.openConnection();
					InputStream is = ucon.getInputStream();
					FileOutputStream os = new FileOutputStream(fileDir);

					int leng = 0;
					byte[] buffer = new byte[1024 * 10];
					while ((leng = is.read(buffer)) != -1) {
						os.write(buffer, 0, leng);
						os.flush();
					}
					os.close();
					return "下载成功";
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return "下载失败";
			}

			protected void onPostExecute(String result) {
				Log.i("123", "onPostExecute: " + result);
				xiaoshuoCallBack.onSucesseed(result);
			}
		}.execute();
	}

	
}
