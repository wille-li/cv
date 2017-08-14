package com.cv;

import static org.bytedeco.javacpp.opencv_imgproc.CV_AA;
import static org.bytedeco.javacpp.opencv_imgproc.CV_FONT_HERSHEY_COMPLEX_SMALL;
import static org.bytedeco.javacpp.opencv_imgproc.cvInitFont;

import java.io.IOException;

import javax.swing.JFrame;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_core.CvMemStorage;
import org.bytedeco.javacpp.opencv_core.CvRect;
import org.bytedeco.javacpp.opencv_core.CvSeq;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.opencv_face.FaceRecognizer;
import org.bytedeco.javacpp.opencv_imgproc.CvFont;
import org.bytedeco.javacpp.opencv_objdetect;
import org.bytedeco.javacpp.opencv_objdetect.CvHaarClassifierCascade;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter.ToIplImage;
import org.bytedeco.javacv.OpenCVFrameGrabber;

public class JavacnCameraTest {

	private static FaceRecognizer faceRecognizer;

	public static void main(String[] arg) throws InterruptedException,
			IOException {
		System.out.println(System.getProperty("java.library.path"));
		OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
		grabber.start(); // 开始获取摄像头数据
		Frame frame = grabber.grab();
		CanvasFrame canvas = new CanvasFrame("摄像头");
		canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas.setAlwaysOnTop(true);
		ToIplImage t = new ToIplImage();
		IplImage image = t.convert(frame);
		while (frame != null) {
			if (!canvas.isDisplayable()) {// 窗口是否关闭
				grabber.stop();// 停止抓取
				System.exit(2);// 退出
			}
			canvas.showImage(frame);// 获取摄像头图像并放到窗口上显示， 这里的Frame
									// frame=grabber.grab(); frame是一帧视频图像

			Thread.sleep(50);// 50毫秒刷新一次图像
			frame = grabber.grab();

		}
	}

	private static void detectAndCropAndPre(IplImage src,
			CvHaarClassifierCascade cascade) {
		int nearest = 0;

		IplImage greyImg = null;

		IplImage faceImg = null;

		IplImage sizedImg = null;

		IplImage equalizedImg = null;

		boolean faceIsTrue = false;

		CvRect r;
		
		CvFont font = new CvFont(CV_FONT_HERSHEY_COMPLEX_SMALL); 
		
		cvInitFont(font,CV_FONT_HERSHEY_COMPLEX_SMALL, 1.0, 0.8,1,1,CV_AA);
		
		//greyImg = opencv_core.cvCreateImage( opencv_core.cvGetSize(src), opencv_core.IPL_DEPTH_8U, 1 );
		
		//greyImg = convertImageToGreyscale(src);
		
		CvMemStorage storage = CvMemStorage.create();
		
		CvSeq sign = opencv_objdetect.cvHaarDetectObjects( src, cascade, storage, 1.1, 3, opencv_objdetect.CV_HAAR_DO_CANNY_PRUNING);
		if (sign.total()>0) {
			System.out.println(1);
		}else {
			System.out.println(2);
		}
	}

}
