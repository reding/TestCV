LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

OpenCV_INSTALL_MODULES := on
OpenCV_CAMERA_MODULES := off

OPENCV_LIB_TYPE :=STATIC

include $(LOCAL_PATH)/native/jni/OpenCV.mk

LOCAL_MODULE := OpenCV

LOCAL_LDLIBS +=  -lm -llog

include $(BUILD_SHARED_LIBRARY)