#ifndef DRIVER_INTERFACE_H_
#define DRIVER_INTERFACE_H_

#include <ftd2xx.h>

class DriverInterface {

public:

	static void init();

	static void remove();

	static int* readAcceleration();

private:

	static FT_HANDLE handle;

	static int extractValue(char h, char l);

};

#endif
