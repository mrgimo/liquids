#ifndef DRIVER_INTERFACE_H_
#define DRIVER_INTERFACE_H_

#include <ftd2xx.h>

class DriverInterface {

public:

	static void init();

	static void remove();

	static int* readData();

private:

	static unsigned char SYN_BYTE;

	static unsigned TENTH_BIT;
	static unsigned LAST_BIT;

	static int BLOCK_LENGTH;

	static FT_HANDLE handle;

	static bool isValid(unsigned char* data);

	static int extractValue(unsigned char h, unsigned char l);

};

#endif

