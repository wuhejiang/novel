package cn.wuhejiang.xiaoshuo.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.IntentService;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import cn.wuhejiang.xiaoshuo.R;

public class DownloadService extends IntentService {
	private File fileDir;
	private static final int NOTIFICATION_ID = 100;

	public DownloadService() {
		super("download");
	}

	public DownloadService(String name) {
		super(name);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		String name = intent.getStringExtra("name");
		String dowmLoad = intent.getStringExtra("dowmLoad");
		try {
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {
				fileDir = new File(
						Environment
								.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
						name + ".txt");
				if (fileDir.exists()) {
					Intent i = new Intent("YI_XIA_ZAI");
					sendBroadcast(i);
					return;// 已下载
				}
				// 父目录不存在 创建父目录
				if (!fileDir.getParentFile().exists()) {
					fileDir.getParentFile().mkdirs();
				}

			} else {
				Intent i = new Intent("SD_BU_CUN_ZAI");
				sendBroadcast(i);
				return;
			}
			Intent i = new Intent("KAI_SHI_XIA_ZAI");
			sendBroadcast(i);
			// 执行下载音乐操作
			String title = name + "开始下载";
			sendNotification(title, "", 100, 0, true);
			URL url = new URL("http://www.1192.org" + dowmLoad);
			HttpURLConnection ucon = (HttpURLConnection) url.openConnection();
			ucon.setRequestMethod("GET");
			InputStream is = ucon.getInputStream();
			FileOutputStream os = new FileOutputStream(fileDir);
			int leng = 0;
			int current = 0;
			// int total =
			// Integer.parseInt(ucon.getHeaderField("Content-Length"));
			byte[] buffer = new byte[1024 * 10];
			while ((leng = is.read(buffer)) != -1) {
				os.write(buffer, 0, leng);
				os.flush();
				current += leng;
				sendNotification("", "", 1000, current, false);
			}
			os.close();
			// 重新出现通知的滚动消息
			cancelNotification();
			Intent j = new Intent("XIA_ZAI_WANG_CHENG");
			sendBroadcast(j);
			title = name + "下载完成";
			sendNotification(title, "小说下载完成", 0, 0, false);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 清除通知
	public void cancelNotification() {
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		manager.cancel(NOTIFICATION_ID);
	}

	/**
	 * 发送通知
	 */
	private void sendNotification(String ticker, String text, int max,
			int progress, boolean b) {
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Notification.Builder builder = new Builder(this);
		builder.setTicker(ticker).setContentTitle("小说下载").setContentText(text)
				.setSmallIcon(R.drawable.ic_launcher);
		builder.setProgress(max, progress, b);
		manager.notify(NOTIFICATION_ID, builder.build());
	}

}
