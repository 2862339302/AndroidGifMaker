package com.example.androidgifmaker;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

public class ScreenShot {
	public static String TAG = "ScreenShot";
	public static ArrayList<Bitmap> bitmaps; //Add your bitmaps from internal or external storage.

	// ��ȡָ��Activity�Ľ��������浽png�ļ�
	private static Bitmap takeScreenShot(Activity activity) {
		// View������Ҫ��ͼ��View
		View view = activity.getWindow().getDecorView();
		view.setDrawingCacheEnabled(true);
		view.buildDrawingCache();
		Bitmap b1 = view.getDrawingCache();

		// ��ȡ״̬���߶�
		Rect frame = new Rect();
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		int statusBarHeight = frame.top;
		Log.i(TAG, "״̬���ĸ߶�" + statusBarHeight);
		
		int wintop = activity.getWindow().findViewById(android.R.id.content).getTop();
		int titleBarHeight = wintop-statusBarHeight;
		Log.i(TAG, "�������ĸ߶�:"+ titleBarHeight);

		// ��ȡ��Ļ���͸�
		int width = activity.getWindowManager().getDefaultDisplay().getWidth();
		int height = activity.getWindowManager().getDefaultDisplay().getHeight();
		Log.i(TAG, "��Ļ�Ŀ�ȣ�" + width);
		Log.i(TAG, "��Ļ�ĸ߶ȣ�" + height);
		// ȥ��������
		// Bitmap b = Bitmap.createBitmap(b1, 0, 25, 320, 455);
		Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height - statusBarHeight);
		view.destroyDrawingCache();
		return b;
	}

	// ���浽sdcard
	private static void savePic(Bitmap b, String strFileName) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(strFileName);
			if (null != fos) {
				b.compress(Bitmap.CompressFormat.PNG, 90, fos);
				fos.flush();
				fos.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void prepareShoot(){
		bitmaps = new ArrayList<Bitmap>();
	}

	// �������
	public static void shoot(Activity a) {
//		Date now = new Date();
//		DateFormat date = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM); // ��ʾ����ʱ�䣨��ȷ���룩
//		String str2 = date.format(now);
//		String[] strs = str2.split(":");
//		StringBuffer str3 = new StringBuffer();
//		for (String str : strs) {
//			str3.append(str);
//			str3.append("_");
//		}
//		str3.deleteCharAt(str3.length() - 1);
//		ScreenShot.savePic(ScreenShot.takeScreenShot(a), "sdcard/save_" + str3 + ".png");
		if(bitmaps!=null){
			bitmaps.add(ScreenShot.takeScreenShot(a));
		}
	}
}
