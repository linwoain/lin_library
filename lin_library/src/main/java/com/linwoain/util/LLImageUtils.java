package com.linwoain.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import com.linwoain.library.LApplication;

/**
 * 与图片相关的工具
 *
 * @author linwoain
 * @version 2014年9月11日 下午12:20:25
 */
public class LLImageUtils {

    private LLImageUtils() {
    }

    /**
     * 以指定宽高设置图片到imageView,若保持宽高比不变，更改一个参数，另一个请置为0
     *
     * @param imageView 图片的容器
     * @param width     要设置的宽
     * @param height    要设置的高
     * @param resId     资源ID
     */
    public static void setBitmap(ImageView imageView, int width, int height, int resId) {
        Context context = LApplication.getContext();
        setBitmap(imageView, width, height, resId, context);
    }

    public static void setBitmap(ImageView imageView, int width, int height, int resId, Context context) {
        if (width == 0 && height == 0) {
            throw new RuntimeException("图片宽高不能同时为0");
        }
        float proportion; // 宽高比

        Options opts = new Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), resId, opts);
        if (width == 0 || height == 0) {
            proportion = 100 * opts.outWidth / opts.outHeight;
            if (width <= 0) {
                width = (int) (height * proportion / 100);
            }
            if (height <= 0) {
                height = (int) (width / proportion * 100);
            }
        }
        opts.outHeight = height;
        opts.outWidth = width;
        opts.inJustDecodeBounds = false;

        LLogUtils.d("height:" + height + ",width:" + width);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId, opts);
        imageView.setImageBitmap(bitmap);
        LayoutParams params = imageView.getLayoutParams();
        params.width = width;
        params.height = height;
        imageView.setLayoutParams(params);
    }

    /**
     * 以指定宽高设置控件的背景
     *
     * @param view   控件对象
     * @param width  宽度
     * @param height 高度
     * @param ResId  资源id
     */
    public static void setImageBackground(View view, int width, int height, int ResId) {
        Context context = LApplication.getContext();
        setImageBackground(view, width, height, ResId, context);
    }

    @SuppressWarnings("deprecation")
    public static void setImageBackground(View view, int width, int height, int ResId, Context context) {
        Options opts = new Options();
        if (width == 0 || height == 0) {
            opts.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(context.getResources(), ResId, opts);
            float proportion = (float) opts.outWidth / opts.outHeight;

            if (width <= 0) {
                width = (int) (height * proportion);
            }
            if (height <= 0) {
                height = (int) (width / proportion);
            }
        }

        opts.outHeight = height;
        opts.outWidth = width;
        opts.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), ResId, opts);
        // view.setMinimumHeight(height);
        view.setBackgroundDrawable(new BitmapDrawable(bitmap));
    }

    /**
     * 设置ImageView的对象指定，若只需对其宽高其中一项，另一项请设置为0
     *
     * @param imageView 图片控件
     * @param width 宽度
     * @param height 高度
     * @param bitmap 位图
     */
    public static void setBitmap(ImageView imageView, int width, int height, Bitmap bitmap) {
        if (width == 0 || height == 0) {
            float proportion = (float) bitmap.getWidth() / bitmap.getHeight();
            if (width <= 0) {
                width = (int) (height * proportion);
            }
            if (height <= 0) {
                height = (int) (width / proportion);
            }
        }
        Bitmap bmp = Bitmap.createScaledBitmap(bitmap, width, height, true);
        imageView.setImageBitmap(bmp);
    }

    // 1.图片加载方法，方便用户加载图片

    /**
     * 加载本地图片
     *
     * @param bitAdress ：图片地址，一般指向R下的drawable目录
     * @return Bitmap 返回的位图
     */
    public  static Bitmap CreatImage(int bitAdress) {
        Context context = LApplication.getContext();
        Bitmap bitmaptemp = null;
        bitmaptemp = BitmapFactory.decodeResource(context.getResources(), bitAdress);
        return bitmaptemp;
    }

    // 2.图片平均分割方法，将大图平均分割为N行N列，方便用户使用

    /**
     * 图片分割
     *
     * @param g      ：画布
     * @param paint  ：画笔
     * @param imgBit ：图片
     * @param x      ：X轴起点坐标
     * @param y      ：Y轴起点坐标
     * @param w      ：单一图片的宽度
     * @param h      ：单一图片的高度
     * @param line   ：第几列
     * @param row    ：第几行
     */
    public static void cuteImage(Canvas g, Paint paint, Bitmap imgBit, int x, int y, int w, int h, int line, int row) {
        g.clipRect(x, y, x + w, h + y);
        g.drawBitmap(imgBit, x - (line * w), y - (row * h), paint);
        g.restore();
    }

    // 3.图片缩放，对当前图片进行缩放处理

    /**
     * 图片的缩放方法
     *
     * @param bgimage   ：源图片资源
     * @param newWidth  ：缩放后宽度
     * @param newHeight ：缩放后高度
     * @return Bitmap
     */
    public static Bitmap zoomImage(Bitmap bgimage, int newWidth, int newHeight) {
        // 获取这个图片的宽和高
        int width = bgimage.getWidth();
        int height = bgimage.getHeight();
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 计算缩放率，新尺寸除原始尺寸
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(bgimage, 0, 0, width, height, matrix, true);
    }

    // 4.绘制带有边框的文字，一般在游戏中起文字的美化作用

    /**
     * 绘制带有边框的文字
     *
     * @param strMsg ：绘制内容
     * @param g      ：画布
     * @param paint  ：画笔
     * @param setx   ：：X轴起始坐标
     * @param sety   ：Y轴的起始坐标
     * @param fg     ：前景色
     * @param bg     ：背景色
     */
    public static void drawText(String strMsg, Canvas g, Paint paint, int setx, int sety, int fg, int bg) {
        paint.setColor(bg);
        g.drawText(strMsg, setx + 1, sety, paint);
        g.drawText(strMsg, setx, sety - 1, paint);
        g.drawText(strMsg, setx, sety + 1, paint);
        g.drawText(strMsg, setx - 1, sety, paint);
        paint.setColor(fg);
        g.drawText(strMsg, setx, sety, paint);
        g.restore();
    }

    /**
     * 获得圆角图片的方法
     *
     * @param bitmap  位图
     * @param roundPx px
     * @return Bitamp
     */

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

}