LOCAL_PATH := $(call my-dir)  
include $(CLEAR_VARS)  
OPENCV_LIB_TYPE:=STATIC
ifeq ("$(wildcard $(OPENCV_MK_PATH))","")  
#try to load OpenCV.mk from default install location  
include E:\plan\win8opencvforandroid\OpenCV-2.4.6-android-sdk-r2\OpenCV-2.4.6-android-sdk\sdk\native\jni\OpenCV.mk 
else  
include $(OPENCV_MK_PATH)  
endif  
LOCAL_MODULE    := ImgFun
LOCAL_SRC_FILES := ImgFun.cpp
include $(BUILD_SHARED_LIBRARY)