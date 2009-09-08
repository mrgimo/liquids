#include "Example.h"

#include <iostream>

JNIEXPORT void JNICALL Java_Example_print
  (JNIEnv *, jobject)
{
    std::cout << "Hello World (from jni)!" << std::endl;
}
