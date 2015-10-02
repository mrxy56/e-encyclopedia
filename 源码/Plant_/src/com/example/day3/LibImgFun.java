package com.example.day3;

public class LibImgFun {

	static {
		System.loadLibrary("ImgFun");
	}

	public static native double[] ImgFun(int[] buf, int w, int h);

}
