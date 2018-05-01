package com.uikit;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.ColorUtils;


public class ShadowDrawable extends Drawable {


    float shadowWidth;
    int radius;
    int maxRadius;

    int[] shadowColors;
    float[] stops;

    Paint paint;

    Paint cornerShadowPaint;
    Paint edgeShadowPaint;
    Path cornerShadowPath;

    RectF rectF = new RectF();


    public ShadowDrawable(int background, float radius, int shadowColor, float shadowWidth) {
        this(background, radius, new int[]{ColorUtils.setAlphaComponent(shadowColor, 255), ColorUtils.setAlphaComponent(shadowColor, 128), ColorUtils.setAlphaComponent(shadowColor, 0)}, null, shadowWidth);
    }

    public ShadowDrawable(int background, float radius, int shadowColor, int[] shadowAlpha, float shadowWidth) {
        this(background, radius, new int[]{ColorUtils.setAlphaComponent(shadowColor, shadowAlpha[0]), ColorUtils.setAlphaComponent(shadowColor, shadowAlpha[1]), ColorUtils.setAlphaComponent(shadowColor, shadowAlpha[2])}, null, shadowWidth);
    }

    public ShadowDrawable(int background, float radius, int shadowColor, float[] stops, float shadowWidth) {
        this(background, radius, new int[]{ColorUtils.setAlphaComponent(shadowColor, 255), ColorUtils.setAlphaComponent(shadowColor, 128), ColorUtils.setAlphaComponent(shadowColor, 0)}, stops, shadowWidth);
    }

    public ShadowDrawable(int background, float radius, int shadowColor, int[] shadowAlpha, float[] stops, float shadowWidth) {
        this(background, radius, new int[]{ColorUtils.setAlphaComponent(shadowColor, shadowAlpha[0]), ColorUtils.setAlphaComponent(shadowColor, shadowAlpha[1]), ColorUtils.setAlphaComponent(shadowColor, shadowAlpha[2])}, stops, shadowWidth);
    }

    public ShadowDrawable(int background, float radius, int[] shadowColors, float[] stops, float shadowWidth) {
        this.radius = (int) (radius + 0.5f);
        this.shadowWidth = shadowWidth;
        this.shadowColors = shadowColors;
        this.stops = stops;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(background);

        cornerShadowPaint = new Paint();
        edgeShadowPaint = new Paint();
    }

    @Override
    public void draw(@NonNull Canvas canvas) {


        float height = canvas.getClipBounds().height();
        float width = canvas.getClipBounds().width();

        maxRadius = (int) (height - 2 * shadowWidth) / 2;
        radius = radius > maxRadius ? maxRadius : radius;
        radius = radius < 0 ? 0 : radius;

        cornerShadowPath = new Path();
        cornerShadowPath.setFillType(Path.FillType.EVEN_ODD);
        cornerShadowPath.moveTo(-radius, 0);
        cornerShadowPath.rLineTo(-shadowWidth, 0);

        RectF innerBounds = new RectF(-radius, -radius, radius, radius);
        RectF outerBounds = new RectF(innerBounds);
        outerBounds.inset(-shadowWidth, -shadowWidth);
        cornerShadowPath.arcTo(outerBounds, 180, 90, false);
        cornerShadowPath.arcTo(innerBounds, 270, -90, false);
        cornerShadowPath.close();

        cornerShadowPaint.setAntiAlias(true);
        cornerShadowPaint.setShader(new RadialGradient(0, 0, shadowWidth + radius,
                shadowColors,
                stops
                , Shader.TileMode.CLAMP));

        edgeShadowPaint.setAntiAlias(true);
        edgeShadowPaint.setShader(new LinearGradient(0, 0, 0,
                -radius - shadowWidth,
                shadowColors,
                stops, Shader.TileMode.CLAMP));


        boolean drawHEdgeShadow = width - 2 * radius - 2 * shadowWidth > 0;
        boolean drawVEdgeShadow = height - 2 * radius - 2 * shadowWidth > 0;

        rectF.left = canvas.getClipBounds().left + shadowWidth;
        rectF.right = canvas.getClipBounds().right - shadowWidth;
        rectF.top = canvas.getClipBounds().top + shadowWidth;
        rectF.bottom = canvas.getClipBounds().bottom - shadowWidth;
        int save = canvas.save();
        canvas.translate(rectF.left + radius, rectF.top + radius);
        canvas.drawPath(cornerShadowPath, cornerShadowPaint);
        if (drawHEdgeShadow) {
            canvas.drawRect(0, -radius - shadowWidth, width - 2 * radius - 2 * shadowWidth, 0, edgeShadowPaint);
        }
        canvas.restoreToCount(save);


        save = canvas.save();
        canvas.translate(rectF.left + radius, rectF.bottom - radius);
        canvas.rotate(270);
        canvas.drawPath(cornerShadowPath, cornerShadowPaint);

        if (drawVEdgeShadow) {
            canvas.drawRect(0, -radius - shadowWidth, height - 2 * radius - 2 * shadowWidth, 0, edgeShadowPaint);
        }
        canvas.restoreToCount(save);

        save = canvas.save();
        canvas.translate(rectF.right - radius, rectF.bottom - radius);
        canvas.rotate(180);
        canvas.drawPath(cornerShadowPath, cornerShadowPaint);
        if (drawHEdgeShadow) {
            canvas.drawRect(0, -radius - shadowWidth, width - 2 * radius - 2 * shadowWidth, 0, edgeShadowPaint);
        }
        canvas.restoreToCount(save);

        save = canvas.save();
        canvas.translate(rectF.right - radius, rectF.top + radius);
        canvas.rotate(90);
        canvas.drawPath(cornerShadowPath, cornerShadowPaint);
        if (drawVEdgeShadow) {
            canvas.drawRect(0, -radius - shadowWidth, height - 2 * radius - 2 * shadowWidth, 0, edgeShadowPaint);
        }

        canvas.restoreToCount(save);

        canvas.drawRoundRect(rectF, radius, radius, paint);

    }


    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {
        paint.setAlpha(alpha);
        cornerShadowPaint.setAlpha(alpha);
        edgeShadowPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
