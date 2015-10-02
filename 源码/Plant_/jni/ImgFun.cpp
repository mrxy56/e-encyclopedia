#include <iostream>
#include<string.h>
#include <jni.h>
#include<cstring>
#include <opencv2/opencv.hpp>
using namespace std;
using namespace cv;



class MyContour      //͹���������ݸ�ʽת����
{
public:
	CvContour header;
	CvSeqBlock block;
	CvMat* vector;
	CvSeq * seq;

	MyContour(CvSeq * hull_seq) {
		vector = cvCreateMat(1, hull_seq->total, CV_32SC2);
		for (int i = 0; i != hull_seq->total; ++i) {
			CV_MAT_ELEM(*vector, CvPoint, 0, i) = ** CV_GET_SEQ_ELEM(CvPoint*, hull_seq, i);
		}
		seq = cvPointSeqFromMat(CV_SEQ_KIND_CURVE + CV_SEQ_FLAG_CLOSED, vector, &header, &block);
	}

	~MyContour() {
		cvReleaseMat(&vector);
	}
};



bool GetHistogram(unsigned char *pImageData, int nWidth, int nHeight, int nWidthStep,
				  int *pHistogram)
{
	int i = 0;
	int j = 0;
	unsigned char *pLine = NULL;
	// ���ֱ��ͼ
	//memset(pHistogram,0,sizeof(pHistogram));

	for(int i=0;i<256;i++)  pHistogram[i]=0;
	for (pLine = pImageData, j = 0; j < nHeight; j++, pLine += nWidthStep)
	{
		for (i = 0; i < nWidth; i++)
		{
			pHistogram[pLine[i]]++;
		}
	}
	return true;
}
// ���ȡ��ֵ
// 1. pImageData ͼ������
// 2. nWidth ͼ����
// 3. nHeight ͼ��߶�
// 4. nWidthStep ͼ���д�С
// ����������ֵ
int Otsu(unsigned char *pImageData, int nWidth, int nHeight, int nWidthStep)
{
	int i = 0;
	int j = 0;
	int nTotal = 0;
	int nSum = 0;
	int A = 0;
	int B = 0;
	double u = 0;
	double v = 0;
	double dVariance = 0;
	double dMaximum = 0;
	int nThreshold = 0;
	int nHistogram[256];
	// ��ȡֱ��ͼ
	for(int i=0;i<256;i++)  nHistogram[i]=0;
	GetHistogram(pImageData, nWidth, nHeight, nWidthStep, nHistogram);
	for (i = 0; i < 256; i++)
	{
		nTotal += nHistogram[i];
		nSum += (nHistogram[i] * i);
	}
	for (j = 0; j < 256; j++)
	{
		A = 0;
		B = 0;
		for (i = 0; i < j; i++)
		{
			A += nHistogram[i];
			B += (nHistogram[i] * i);
		}
		if (A > 0)
		{
			u = B / A;
		}
		else
		{
			u = 0;
		}
		if (nTotal - A > 0)
		{
			v = (nSum - B) / (nTotal - A);
		}
		else
		{
			v = 0;
		}
		dVariance = A * (nTotal - A) * (u - v) * (u - v);
		if (dVariance > dMaximum)
		{
			dMaximum = dVariance;
			nThreshold = j;
		}
	}
	return nThreshold;

}



extern "C" {
JNIEXPORT jdoubleArray JNICALL Java_com_example_day3_LibImgFun_ImgFun(
		JNIEnv* env, jobject obj, jintArray buf, int w, int h);
JNIEXPORT jdoubleArray JNICALL Java_com_example_day3_LibImgFun_ImgFun(
		JNIEnv* env, jobject obj, jintArray buf, int w, int h) {

	double contArea=1; //ҶƬ���
	double recArea=1; //��С��Χ�����
	double contPerimeter=1; //ҶƬ�ܳ�
	double ConvexPerimeter=1; //ҶƬ͹���ܳ�
	double ConvexArea=1; //ҶƬ͹�����
	double LongAxis=1; //ҶƬ����
	double ShortAxis=1; //ҶƬ����
	double CircumscribedRadius=1; //���Բ�뾶
	double InsideRadius=1; //����Բ�뾶
	CvBox2D box;   //��С��Χ��
	CvRect rect;
	//��״����

	double AspectRatio; //�ݺ����
	double Rectangularity; //���ζ�
	double AreaConvexity; //�����͹��
	double PerimeterConvexity; //�ܳ���͹��
	double Sphericity; //��״��
	double Circularity; //Բ�ζ�
	double Eccentricity; //ƫ����
	double FormFacter; //��״����


	jint *cbuf;
	cbuf = env->GetIntArrayElements(buf, 0);
	if (cbuf == NULL) {
		return 0;
	}
	Mat myimg(h, w, CV_8UC4, cbuf);
	IplImage s=IplImage(myimg);
	IplImage *src =&s;
	CvSeq *contours; //�洢��ȡ������
	IplImage* grey_pImg=NULL; //�Ҷ�ͼ
	IplImage* black_pImg=NULL; //��ֵͼ
	CvMemStorage *storage= cvCreateMemStorage(0);
	int mode = CV_RETR_CCOMP; //������ȡ����ģʽ
	int contours_num = 2; //��ȡ��������Ŀ

	bool flag=false;



	contours = 0;

//	grey_pImg = cvCreateImage(cvGetSize(src), src->depth, 1);
	black_pImg = cvCreateImage(cvGetSize(src), src->depth, 1);

	cvCvtColor(src, black_pImg, CV_BGR2GRAY); //����ȡ��ͼ��ת��Ϊ�Ҷ�ͼ��

	unsigned int threshold = Otsu((unsigned char *)src->imageData, src->width, src->height, src->widthStep);
//	cout << "threshold:" << threshold << endl;
	cvThreshold(black_pImg, black_pImg, 50, 255, CV_THRESH_BINARY); //��ֵ��

	IplConvKernel*element = cvCreateStructuringElementEx(2, 2, 0, 0,
			CV_SHAPE_ELLIPSE, 0);
	cvErode(black_pImg, black_pImg, element, 9);
	cvDilate(black_pImg, black_pImg, element, 9);

	mode = CV_RETR_LIST;
	contours_num = cvFindContours(black_pImg, storage, &contours,
			(int) sizeof(CvContour), mode, CV_CHAIN_APPROX_NONE, cvPoint(0, 0));

	for (; contours != 0; contours = contours->h_next) {

		rect = cvBoundingRect(contours, 1);

		double area=rect.height*rect.width;
		double areaScreen=src->height*src->width;
		if (rect.height < src->height*99/100
				&& (rect.height > src->height / 3 || rect.width> src->width / 3))

//		if(areaScreen>area&&area>areaScreen/6)
		{

			flag=true;
			contArea = fabs(cvContourArea(contours, CV_WHOLE_SEQ)); //ҶƬ���
			contPerimeter = cvArcLength(contours, CV_WHOLE_SEQ, -1); //ҶƬ�ܳ�
			LongAxis = rect.height; //ҶƬ����
			ShortAxis = rect.width; //ҶƬ����

			CvPoint2D32f center;
			float radius; //���Բ�뾶
			cvMinEnclosingCircle(contours, &center, &radius);
			CircumscribedRadius = radius;

			CvSeq *tb = cvConvexHull2(contours, storage, CV_CLOCKWISE, 0);

			MyContour cont(tb);
			ConvexArea = cvContourArea(cont.seq);

			ConvexArea = fabs(cvContourArea(cont.seq, CV_WHOLE_SEQ));
			ConvexPerimeter = cvArcLength(cont.seq, CV_WHOLE_SEQ, -1);
			box = cvMinAreaRect2(contours, 0);
			double r =
					box.size.height > box.size.width ?
							box.size.width : box.size.height;
			InsideRadius = r / 2;
			recArea = box.size.height * box.size.width;

		}

	}

	if(!flag)
	{

		return NULL;
	}

	AspectRatio = box.size.width / box.size.height;
	Rectangularity = contArea / recArea;
	AreaConvexity = contArea / ConvexArea;
	PerimeterConvexity = contPerimeter / ConvexPerimeter; //�ܳ���͹��
	Sphericity = 4 * 3.14* contArea / (ConvexPerimeter * ConvexPerimeter); //��״��
	Circularity = InsideRadius / CircumscribedRadius; //Բ�ζ�
	Eccentricity = LongAxis / ShortAxis; //ƫ����
	FormFacter = 4 * 3.14*contArea / (contPerimeter * contPerimeter); //��״����
	double result[11] = { AspectRatio, Rectangularity, AreaConvexity,
			PerimeterConvexity, Sphericity, Circularity,
			FormFacter,box.size.width ,box.size.height,src->width,src->height};


//	double result[9] ={contours_num,threshold,contArea,contPerimeter,ConvexPerimeter,ConvexArea,LongAxis,ShortAxis,recArea};

	jdoubleArray data = env->NewDoubleArray(11);
	env->SetDoubleArrayRegion(data, 0, 11, result);
	return data;

}
}
