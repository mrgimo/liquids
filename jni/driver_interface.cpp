#include "driver_interface.h"

unsigned char DriverInterface::SYN_BYTE = 0xaa;

unsigned DriverInterface::TENTH_BIT = 0x00000020;
unsigned DriverInterface::LAST_BIT = 0x80000000;

int DriverInterface::BLOCK_LENGTH = 12;

FT_HANDLE DriverInterface::handle = 0;

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
		unsigned char* data = new unsigned char[BLOCK_LENGTH];

		FT_Read(handles[i], data, BLOCK_LENGTH, &received);
		if(static_cast<int>(received) == BLOCK_LENGTH && isValid(data)) {
			handle = handles[i];
			break;
		}
	}

	delete[] infos;
}

bool DriverInterface::isValid(unsigned char* data) {
	unsigned char pA = 0;
	unsigned char pB = 0;

	for(int i = 0; i < BLOCK_LENGTH - 2; i++) {
		pA += data[i];
		pB += pA;
	}

	return data[0] == SYN_BYTE && data[BLOCK_LENGTH - 2] == pA
			&& data[BLOCK_LENGTH - 1] == pB;
}

void DriverInterface::remove() {
	FT_Close(handle);
	delete handle;
}

int* DriverInterface::readData() {
	DWORD received;
	unsigned char* data = new unsigned char[BLOCK_LENGTH];

	FT_Read(handle, data, BLOCK_LENGTH, &received);
	if(static_cast<int>(received) != BLOCK_LENGTH || !isValid(data)) {
		delete[] data;
		return 0;
	}
	
	int* output = new int[4];

	output[0] = static_cast<int>(data[1]);

	output[1] = extractValue(data[2], data[3]);
	output[2] = extractValue(data[4], data[5]);
	output[3] = extractValue(data[6], data[7]);

	delete[] data;

	return output;
}

int DriverInterface::extractValue(unsigned char h, unsigned char l) {
	int value = (static_cast<unsigned>(h) << 8) | static_cast<unsigned>(l);

	if (value & TENTH_BIT) {
		return (value & ~TENTH_BIT) | LAST_BIT;
	}

	return value;
}

