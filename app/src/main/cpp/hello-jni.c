#include <string.h>
#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_gsbusiness_fancylivecricketscore_Constant_NewsApidata(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "http://onlineid.cricnet.co.in/api/values/");
}

JNIEXPORT jstring JNICALL
Java_com_gsbusiness_fancylivecricketscore_Comman_TestTeamApiData(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "http://cricnet.co.in/ManagePlaying/PlayerImage/thumb/");
}
JNIEXPORT jstring JNICALL
Java_com_gsbusiness_fancylivecricketscore_Comman_BaseAPIData(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "http://cricpro.cricnet.co.in/api/values/");
}

JNIEXPORT jstring JNICALL
Java_com_gsbusiness_fancylivecricketscore_Comman_ImageURLApiData(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "http://cricnet.co.in/ManagePlaying/PlayerImage/");
}
JNIEXPORT jstring JNICALL
Java_com_gsbusiness_fancylivecricketscore_Comman_TeamImageApiData(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "http://cricnet.co.in/ManagePlaying/TeamImages/");
}
JNIEXPORT jstring JNICALL
Java_com_gsbusiness_fancylivecricketscore_Constant_Apimasterv1(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "http://dreamteam.gamdiyo.com/appv1/apimasterv1/");
}

