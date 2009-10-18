#include "driver_interface.h"

FT_HANDLE	DriverInterface::handle = 0;

void DriverInterface::init() {
	DWORD numberOfDevices;
	FT_CreateDeviceInfoList(&numberOfDevices);

	FT_DEVICE_LIST_INFO_NODE* infos;
	infos = new FT_DEVICE_LIST_INFO_NODE[numberOfDevices];

	FT_GetDeviceInfoList(infos, &numberOfDevices);

	FT_HANDLE* handles;
	handles = new FT_HANDLE[numberOfDevices];
	for (unsigned i = 0; i < numberOfDevices; i++) {
		FT_OpenEx(infos[i].SerialNumber, FT_OPEN_BY_SERIAL_NUMBER, &handles[i]);
		
		DWORD received;
		char* data = new char[12];

		FT_Read(handles[i], data, 12, &received);
		if(data[0] == 0xaa && received == 12) {
			handle = handles[i];
		}
	}

	delete[] infos;
}

void DriverInterface::remove() {
	FT_Close(handle);
	delete handle;
}

int* DriverInterface::readAcceleration() {
	DWORD received;
	char* data = new char[12];

	FT_Read(handle, data, 12, &received);
	if(data[0] == 0xaa && received == 12) {
		return 0;
	}
	
	int* acceleration = new int[4];

	acceleration[0] = static_cast<int>(data[1]);
	acceleration[1] = extractValue(data[2], data[3]);
	acceleration[2] = extractValue(data[4], data[5]);
	acceleration[3] = extractValue(data[6], data[7]);

	return acceleration;
}

int DriverInterface::extractValue(char h, char l) {
	int value = (static_cast<int>(h) << 8) | static_cast<int>(l);
	if ((value & 0x00000020) == 0x00000020) {
		return value & ~0x00000020 | 0x80000000;
	}

	return value;
}

