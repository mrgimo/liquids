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

JNIEXPORT jintArray JNICALL Java_ch_hsr_ifs_liquids_devices_Accelerometer_readAcceleration
		(JNIEnv* env, jclass){
	int* acceleration = DriverInterface::readAcceleration();

	jintArray data;
	data = env->NewIntArray(12);
	
	env->SetIntArrayRegion(data, 0, 12, (jint *) acceleration);

	return data;
}

