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
					return;// ������
				}
				// ��Ŀ¼������ ������Ŀ¼
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
			// ִ���������ֲ���
			String title = name + "��ʼ����";
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
			// ���³���֪ͨ�Ĺ�����Ϣ
			cancelNotification();
			Intent j = new Intent("XIA_ZAI_WANG_CHENG");
			sendBroadcast(j);
			title = name + "�������";
			sendNotification(title, "С˵�������", 0, 0, false);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ���֪ͨ
	public void cancelNotification() {
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		manager.cancel(NOTIFICATION_ID);
	}

	/**
	 * ����֪ͨ
	 */
	private void sendNotification(String ticker, String text, int max,
			int progress, boolean b) {
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Notification.Builder builder = new Builder(this);
		builder.setTicker(ticker).setContentTitle("С˵����").setContentText(text)
				.setSmallIcon(R.drawable.ic_launcher);
		builder.setProgress(max, progress, b);
		manager.notify(NOTIFICATION_ID, builder.build());
	}

}
