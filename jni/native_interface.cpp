#include "native_interface.h"
#include "driver_interface.h"

JNIEXPORT void JNICALL Java_ch_hsr_ifs_liquids_devices_Accelerometer_init
		(JNIEnv *, jclass) {
	DriverInterface::init();
}

JNIEXPORT void JNICALL Java_ch_hsr_ifs_liquids_devices_Accelerometer_remove
		(JNIEnv *, jclass){
	DriverInterface::remove();
}

JNIEXPORT jintArray JNICALL Java_ch_hsr_ifs_liquids_devices_Accelerometer_readData
		(JNIEnv* env, jclass){
	jint* data = DriverInterface::readData();
	if(data == 0) {
		delete[] data;
		return NULL;
	}

	jintArray output;
	output = env->NewIntArray(12);
	
	env->SetIntArrayRegion(output, 0, 12, static_cast<jint*>(data));

	delete[] data;

	return output;
}

