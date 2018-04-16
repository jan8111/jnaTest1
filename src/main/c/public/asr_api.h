/* =======================================================================================
*  Project          Auto Speech Recognition (ASR)
*  (c) Copyright    2014-2017
*  Company          Shanghai UVoice Technology CO., LTD
*                   All rights reserved
*  Secrecy Level    STRICTLY CONFIDENTIAL
* --------------------------------------------------------------------------------------*/
/**
 *  @internal
 *  @file asr_api.h
 *
 *  Prototypes of the Auto Speech Recognition (ASR) API functions.
 *
 *  This header file contains all prototypes of the API functions
 *  of the Auto Speech Recognition (ASR) module.
 */
/*======================================================================================*/
/** @addtogroup DEF
*  @{*/
#ifndef _UNIVOICE_ASR_API_H_
#define _UNIVOICE_ASR_API_H_

#include "asr_type.h"

#ifdef __cplusplus
extern "C"
{
#endif

/////////////////////////////////////////////////////////////////////////////////////////////////
//global api
RecogRetCode recognizer_setWorkPath(const char* path);
RecogRetCode recognizer_setDicPath(const char* path);
const char* recognizer_getVersion();
void recognizer_setOutputFanti();
void recognizer_setPrintFunc(PrintFunc pFunc);
RecogRetCode recognizer_getModel(const char* type, unsigned char **model, unsigned int *modelLen);

//model api
RecogRetCode recognizer_addAcoustic(const char* name, const char* type, const char* path, int device);
RecogRetCode recognizer_addAcousticMem(const char* name, const char* type, const unsigned char* gram, const int gramLen);
//ret: 1-yes 0-no
Int32 recognizer_hasAcoustic(const char* name);
const char* recognizer_getAcousticVersion(const char* name);
//type:"wfst","wfst-compress","bnf","list_line","list_json","list_xml"
//slot name format "grammar#slot" or "#slot"
RecogRetCode recognizer_addDecoder(const char* name, const char* type, const char* path);
RecogRetCode recognizer_addDecoderMem(const char* name, const char* type, const unsigned char* gram, const int gramLen);
RecogRetCode recognizer_delDecoder(const char* name);
RecogRetCode recognizer_renameDecoder(const char* old_name, const char* new_name);
RecogRetCode recognizer_aliasDecoder(const char* src_name, const char* alias_name);
int recognizer_getDecoderStateNum(const char* name);
//ret: 1-yes 0-no
Int32 recognizer_hasDecoder(const char* name);
const char* recognizer_getDecoderVersion(const char* name);

/////////////////////////////////////////////////////////////////////////////////////////////////
//context api
RecogRetCode recognizer_createContext(HANDLE* base);
void recognizer_destroyContext(HANDLE base);
RecogRetCode recognizer_setContextAcoustic(HANDLE base, const char* acoustic_name);
RecogRetCode recognizer_attachContextDecoder(HANDLE base, const char* decoder_name, bool bSlot);
RecogRetCode recognizer_detachContextDecoder(HANDLE base, const char* decoder_name, bool bSlot);
RecogRetCode recognizer_setContextRescore(HANDLE base, const char* decoder_name, const char* rescore_name);
RecogRetCode recognizer_setContextAcousticParam(HANDLE base, const UnivoiceAcousticParam* param);
RecogRetCode recognizer_getContextAcousticParam(HANDLE base, const UnivoiceAcousticParam* param);
RecogRetCode recognizer_setContextDecoderParam(HANDLE base, const char* decoder_name, const UnivoiceDecoderParam* param);
RecogRetCode recognizer_getContextDecoderParam(HANDLE base, const char* decoder_name, const UnivoiceDecoderParam* param);
RecogRetCode recognizer_setContextVadParam(HANDLE base, const UnivoiceVadParam* param);
RecogRetCode recognizer_getContextVadParam(HANDLE base, const UnivoiceVadParam* param);

/////////////////////////////////////////////////////////////////////////////////////////////////
//session api
RecogRetCode recognizer_createSession(HANDLE* session, HANDLE base, int sample_rate);
void recognizer_destroySession(HANDLE session);
RecogRetCode recognizer_setSessionCallBack(HANDLE session, UnivoiceSessionCallback* callback, void* pInst);
RecogRetCode recognizer_startSession(HANDLE session, const Int32 nThreadIndex);
RecogRetCode recognizer_resumeSession(HANDLE session, Int8* data, UInt32 len);
RecogRetCode recognizer_resumeSessionWithFeature(HANDLE session, const signed char* data, int size);
RecogRetCode recognizer_resumeSessionWithAcoustic(HANDLE session, const signed char* data, int size);
RecogRetCode recognizer_stopSession(HANDLE session);
RecogRetCode recognizer_getSessionResStr(HANDLE session, const char** result);
RecogRetCode recognizer_getSessionResJson(HANDLE session, const char** result);
RecogRetCode recognizer_getSessionAnalyzeResStr(HANDLE session, const char** result);
RecogRetCode recognizer_getSessionAnalyzeResJson(HANDLE session, const char** result);
RecogRetCode recognizer_getSessionLat(HANDLE session, const char** latticeOut);
RecogRetCode recognizer_getSessionLatJson(HANDLE session, const char** latticeOut);
RecogRetCode recognizer_getSessionNBest2(HANDLE session, const char** * nbestStr, int* num);
RecogRetCode recognizer_getSessionNBestJson(HANDLE session, const char** nbestStr);

/////////////////////////////////////////////////////////////////////////////////////////////////
//lexion
//word should be utf-8 format, phone can use pingyin or phoneset
//only available when use FstMaker lib
void recognizer_initFst();
void recognizer_destroyFst();
const char* recognizer_getFstMakerVersion();
void recognizer_setLexicon(char* word, char* phone);
const char* recognizer_getLexicon(char* word);
void recognizer_clearLexicon();
#ifdef __cplusplus
}
#endif

#endif
/**@}*/
