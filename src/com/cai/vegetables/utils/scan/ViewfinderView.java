﻿/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cai.vegetables.utils.scan;

import java.util.Collection;
import java.util.HashSet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.cai.vegetables.R;
import com.google.zxing.ResultPoint;

/**
 * This view is overlaid on top of the camera preview. It adds the viewfinder
 * rectangle and partial transparency outside it, as well as the laser scanner
 * animation and result points.
 * 
 * @author dswitkin@google.com (Daniel Switkin)
 */
public final class ViewfinderView extends View {

	private static final int[] SCANNER_ALPHA = { 0, 64, 128, 192, 255, 192,
			128, 64 };
	private static final long ANIMATION_DELAY = 100L;
	private static final int OPAQUE = 0xFF;

	private final Paint paint;
	private Bitmap resultBitmap;
	private final int maskColor;
	private final int resultColor;
	@SuppressWarnings("unused")
	private final int frameColor;
	@SuppressWarnings("unused")
	private final int laserColor;
	private final int resultPointColor;
	private int scannerAlpha;
	private Collection<ResultPoint> possibleResultPoints;
	private Collection<ResultPoint> lastPossibleResultPoints;

	private boolean laserLinePortrait = true;
	Rect mRect;
	int i = 0;
	GradientDrawable mDrawable;
	Paint textPaint;

	// This constructor is used when the class is built from an XML resource.
	public ViewfinderView(Context context, AttributeSet attrs) {
		super(context, attrs);

		// Initialize these once for performance rather than calling them every
		// time in onDraw().
		paint = new Paint();

		textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mRect = new Rect();
		int left = Color.parseColor("#999a9a");
		int center = Color.parseColor("#f8df01");
		int right = Color.parseColor("#999a9a");
		mDrawable = new GradientDrawable(
				GradientDrawable.Orientation.LEFT_RIGHT, new int[] { left,
						center, right });

		Resources resources = getResources();
		maskColor = resources.getColor(R.color.viewfinder_mask);
		resultColor = resources.getColor(R.color.result_view);
		frameColor = resources.getColor(R.color.viewfinder_frame);
		laserColor = resources.getColor(R.color.viewfinder_laser);
		resultPointColor = resources.getColor(R.color.possible_result_points);
		scannerAlpha = 0;
		possibleResultPoints = new HashSet<ResultPoint>(5);
	}

	@SuppressLint("DrawAllocation")
	@Override
	public void onDraw(Canvas canvas) {
		Rect frame = CameraManager.get().getFramingRect();
		if (frame == null) {
			return;
		}

		int width = canvas.getWidth();
		int height = canvas.getHeight();

		// Draw the exterior (i.e. outside the framing rect) darkened
		paint.setColor(resultBitmap != null ? resultColor : maskColor);
		canvas.drawRect(0, 0, width, frame.top, paint);
		canvas.drawRect(0, frame.top, frame.left, frame.bottom + 1, paint);
		canvas.drawRect(frame.right + 1, frame.top, width, frame.bottom + 1,
				paint);
		canvas.drawRect(0, frame.bottom + 1, width, height, paint);

		if (resultBitmap != null) {
			// Draw the opaque result bitmap over the scanning rectangle
			paint.setAlpha(OPAQUE);
			canvas.drawBitmap(resultBitmap, frame.left, frame.top, paint);
		} else {

			paint.setColor(Color.parseColor("#1d4159"));
			// 画出四个角
			// canvas.drawRect(frame.left - 10, frame.top - 10, frame.left + 20,
			// frame.top, paint);
			// canvas.drawRect(frame.left - 10, frame.top - 10, frame.left,
			// frame.top + 20, paint);
			// canvas.drawRect(frame.right - 20, frame.top - 10, frame.right +
			// 10, frame.top, paint);
			// canvas.drawRect(frame.right, frame.top - 10, frame.right + 10,
			// frame.top + 20, paint);
			//
			// canvas.drawRect(frame.left - 10, frame.bottom + 10, frame.left +
			// 20, frame.bottom, paint);
			// canvas.drawRect(frame.left - 10, frame.bottom + 10, frame.left,
			// frame.bottom - 20, paint);
			// canvas.drawRect(frame.right - 20, frame.bottom + 10, frame.right
			// + 10, frame.bottom, paint);
			// canvas.drawRect(frame.right, frame.bottom + 10, frame.right + 10,
			// frame.bottom - 20, paint);
			//
			// 左上角

			canvas.drawRect(frame.left - 2, frame.top - 2, frame.left + 15,
					frame.top + 5, paint);

			canvas.drawRect(frame.left - 2, frame.top - 2, frame.left + 5,
					frame.top + 15, paint);

			// 右上角

			canvas.drawRect(frame.right - 15, frame.top - 2, frame.right + 2,
					frame.top + 6, paint);

			canvas.drawRect(frame.right - 5, frame.top - 2, frame.right + 2,
					frame.top + 15, paint);

			// 左下角

			canvas.drawRect(frame.left - 2, frame.bottom - 5, frame.left + 15,
					frame.bottom + 2, paint);

			canvas.drawRect(frame.left - 2, frame.bottom - 15, frame.left + 5,
					frame.bottom + 2, paint);

			// 右下角

			canvas.drawRect(frame.right - 15, frame.bottom - 5,
					frame.right + 2, frame.bottom + 2, paint);

			canvas.drawRect(frame.right - 5, frame.bottom - 15,
					frame.right + 2, frame.bottom + 2, paint);

			@SuppressWarnings("unused")
			int middle = frame.width() / 2;
			// textPaint.setTextSize(26);
			// textPaint.setColor(Color.WHITE);
			// String text =
			// getResources().getString(R.string.dimension_content);
			// canvas.drawText(text, middle - 112, frame.bottom + 60,
			// textPaint);

			// Draw a red "laser scanner" line through the middle to show
			// decoding is active
			paint.setColor(Color.RED);
			paint.setAlpha(SCANNER_ALPHA[scannerAlpha]);
			scannerAlpha = (scannerAlpha + 1) % SCANNER_ALPHA.length;

			// 上下走的线
			if (laserLinePortrait) {

				if ((i += 2) < frame.bottom - frame.top) {
					/*
					 * canvas.drawRect(frame.left + 2, frame.top - 2 + i,
					 * frame.right - 1, frame.top + 2 + i, paint);
					 */
					int r = 8;
					mDrawable.setShape(GradientDrawable.RECTANGLE);
					mDrawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
					setCornerRadii(mDrawable, r, r, r, r);
					mRect.set(frame.left + 2, frame.top - 3 + i,
							frame.right - 1, frame.top + 3 + i);
					mDrawable.setBounds(mRect);
					mDrawable.draw(canvas);
					invalidate();
				} else {
					i = 0;
				}

			} else {
				float left = frame.left + (frame.right - frame.left) / 2 - 2;
				canvas.drawRect(left, frame.top, left + 2, frame.bottom - 2,
						paint);
			}

			Collection<ResultPoint> currentPossible = possibleResultPoints;
			Collection<ResultPoint> currentLast = lastPossibleResultPoints;
			if (currentPossible.isEmpty()) {
				lastPossibleResultPoints = null;
			} else {
				possibleResultPoints = new HashSet<ResultPoint>(5);
				lastPossibleResultPoints = currentPossible;
				paint.setAlpha(OPAQUE);
				paint.setColor(resultPointColor);
				for (ResultPoint point : currentPossible) {
					canvas.drawCircle(frame.left + point.getX(), frame.top
							+ point.getY(), 6.0f, paint);
				}
			}
			if (currentLast != null) {
				paint.setAlpha(OPAQUE / 2);
				paint.setColor(resultPointColor);
				for (ResultPoint point : currentLast) {
					canvas.drawCircle(frame.left + point.getX(), frame.top
							+ point.getY(), 3.0f, paint);
				}
			}

			// Request another update at the animation interval, but only
			// repaint the laser line,
			// not the entire viewfinder mask.
			postInvalidateDelayed(ANIMATION_DELAY, frame.left, frame.top,
					frame.right, frame.bottom);
		}
	}

	public void drawViewfinder() {
		resultBitmap = null;
		invalidate();
	}

	/**
	 * Draw a bitmap with the result points highlighted instead of the live
	 * scanning display.
	 * 
	 * @param barcode
	 *            An image of the decoded barcode.
	 */
	public void drawResultBitmap(Bitmap barcode) {
		resultBitmap = barcode;
		invalidate();
	}

	public void addPossibleResultPoint(ResultPoint point) {
		possibleResultPoints.add(point);
	}

	public void setCornerRadii(GradientDrawable drawable, float r0, float r1,
			float r2, float r3) {
		drawable.setCornerRadii(new float[] { r0, r0, r1, r1, r2, r2, r3, r3 });
	}
}
