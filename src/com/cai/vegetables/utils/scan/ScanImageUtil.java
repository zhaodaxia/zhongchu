package com.cai.vegetables.utils.scan;

import java.util.Hashtable;
import java.util.Vector;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;

/**
 * 解析二维码及条形码图片
 * @author lion wang
 *
 */
public class ScanImageUtil {
    /**
     * 扫描解析条形码二维码
     * @param 图片地址
     * @return
     */
	public static Result scaning(String path){
		if (TextUtils.isEmpty(path)) {  
            return null;  
        }  
		MultiFormatReader multiFormatReader = new MultiFormatReader(); 
		Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType,Object>(2);
		// 可以解析的编码类型  
		Vector<BarcodeFormat> decodeFormats = new Vector<BarcodeFormat>();  
		if (decodeFormats == null || decodeFormats.isEmpty()) {  
		    decodeFormats = new Vector<BarcodeFormat>();  
		  
		    // 这里设置可扫描的类型，我这里选择了都支持  
		    decodeFormats.addAll(DecodeFormatManager.ONE_D_FORMATS);  
		    decodeFormats.addAll(DecodeFormatManager.QR_CODE_FORMATS);  
		    decodeFormats.addAll(DecodeFormatManager.DATA_MATRIX_FORMATS);  
		}  
		hints.put(DecodeHintType.POSSIBLE_FORMATS, decodeFormats);  
		// 设置继续的字符编码格式为UTF8  
		// hints.put(DecodeHintType.CHARACTER_SET, "UTF8");  
		  
		// 设置解析配置参数  
		multiFormatReader.setHints(hints); 
		Result rawResult = null;
		BitmapFactory.Options options = new BitmapFactory.Options();  
        options.inJustDecodeBounds = true; // 先获取原大小  
        Bitmap  scanBitmap = BitmapFactory.decodeFile(path, options);  
        options.inJustDecodeBounds = false; // 获取新的大小  
        int sampleSize = (int) (options.outHeight / (float) 200);  
        if (sampleSize <= 0)  
            sampleSize = 1;  
        options.inSampleSize = sampleSize;  
        scanBitmap = BitmapFactory.decodeFile(path, options);  
		try {
			rawResult = multiFormatReader
					.decodeWithState(new BinaryBitmap(new HybridBinarizer(
							new BitmapLuminanceSource(scanBitmap))));
			return rawResult;
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static final boolean isChineseCharacter(String chineseStr) {
		char[] charArray = chineseStr.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			// 是否是Unicode编码,除了"�"这个字符.这个字符要另外处理
			if ((charArray[i] >= '\u0000' && charArray[i] < '\uFFFD')
					|| ((charArray[i] > '\uFFFD' && charArray[i] < '\uFFFF'))) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * Get a file path from a Uri. This will get the the path for Storage Access
	 * Framework Documents, as well as the _data field for the MediaStore and
	 * other file-based ContentProviders.
	 * 
	 * @param context
	 *            The context.
	 * @param uri
	 *            The Uri to query.
	 * @author paulburke
	 */
	@SuppressLint("NewApi")
	public static String getPath(final Context context, final Uri uri) {

//		final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
		final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.BASE;
		// DocumentProvider
		if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
			// ExternalStorageProvider
			if (isExternalStorageDocument(uri)) {
				final String docId = DocumentsContract.getDocumentId(uri);
				final String[] split = docId.split(":");
				final String type = split[0];

				if ("primary".equalsIgnoreCase(type)) {
					return Environment.getExternalStorageDirectory() + "/"
							+ split[1];
				}

				// TODO handle non-primary volumes
			}
			// DownloadsProvider
			else if (isDownloadsDocument(uri)) {

				final String id = DocumentsContract.getDocumentId(uri);
				final Uri contentUri = ContentUris.withAppendedId(
						Uri.parse("content://downloads/public_downloads"),
						Long.valueOf(id));

				return getDataColumn(context, contentUri, null, null);
			}
			// MediaProvider
			else if (isMediaDocument(uri)) {
				final String docId = DocumentsContract.getDocumentId(uri);
				final String[] split = docId.split(":");
				final String type = split[0];

				Uri contentUri = null;
				if ("image".equals(type)) {
					contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				} else if ("video".equals(type)) {
					contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
				} else if ("audio".equals(type)) {
					contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
				}

				final String selection = "_id=?";
				final String[] selectionArgs = new String[] { split[1] };

				return getDataColumn(context, contentUri, selection,
						selectionArgs);
			}
		}
		// MediaStore (and general)
		else if ("content".equalsIgnoreCase(uri.getScheme())) {
			return getDataColumn(context, uri, null, null);
		}
		// File
		else if ("file".equalsIgnoreCase(uri.getScheme())) {
			return uri.getPath();
		}

		return null;
	}

	/**
	 * Get the value of the data column for this Uri. This is useful for
	 * MediaStore Uris, and other file-based ContentProviders.
	 * 
	 * @param context
	 *            The context.
	 * @param uri
	 *            The Uri to query.
	 * @param selection
	 *            (Optional) Filter used in the query.
	 * @param selectionArgs
	 *            (Optional) Selection arguments used in the query.
	 * @return The value of the _data column, which is typically a file path.
	 */
	public static String getDataColumn(Context context, Uri uri,
			String selection, String[] selectionArgs) {

		Cursor cursor = null;
		final String column = "_data";
		final String[] projection = { column };

		try {
			cursor = context.getContentResolver().query(uri, projection,
					selection, selectionArgs, null);
			if (cursor != null && cursor.moveToFirst()) {
				final int column_index = cursor.getColumnIndexOrThrow(column);
				return cursor.getString(column_index);
			}
		} finally {
			if (cursor != null)
				cursor.close();
		}
		return null;
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is ExternalStorageProvider.
	 */
	public static boolean isExternalStorageDocument(Uri uri) {
		return "com.android.externalstorage.documents".equals(uri
				.getAuthority());
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is DownloadsProvider.
	 */
	public static boolean isDownloadsDocument(Uri uri) {
		return "com.android.providers.downloads.documents".equals(uri
				.getAuthority());
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is MediaProvider.
	 */
	public static boolean isMediaDocument(Uri uri) {
		return "com.android.providers.media.documents".equals(uri
				.getAuthority());
	}
}
