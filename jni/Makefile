SRC_FILES=driver_interface.cpp native_interface.cpp
C_FLAGS=-Wall -shared

OUT_FILE = libaccelerometer.so

JAVA_INCLUDE_PATH=/usr/lib/jvm/java-6-sun/include

INCLUDE_PATH=-I$(JAVA_INCLUDE_PATH) -I$(JAVA_INCLUDE_PATH)/linux -Ilinux/libftd2xx0.4.16

LIB_PATH=-Llinux/libftd2xx0.4.16/static_lib

LIBS=-ldl -lpthread -lftd2xx

all :
	g++ $(C_FLAGS) $(INCLUDE_PATH) $(LIB_PATH) -o $(OUT_FILE) -Wl,-soname,$(OUT_FILE) $(SRC_FILES) $(LIBS)

clean :
	-rm $(OUT_FILE)
