#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "openssl/blowfish.h"
/* 
implement the encrypt / decrypt function using the openSSL library

1.	void BF_set_key(BF_KEY *key, int len, const unsigned char *data)
2.	void BF_cbc_encrypt(const unsigned char *in, unsigned char *out, long length, BF_KEY *schedule, unsigned char *initialvec, int enc)
*/
const int BLOCKSIZE = 8;
//BF_KEY *key=(BF_KEY*)calloc(1,sizeof(BF_KEY));
void *fs_encrypt(void *plaintext, int bufsize, char *keystr, int *resultlen)
{	
	unsigned char *out=(unsigned char*)calloc(BLOCKSIZE+1,sizeof(char));
	unsigned char initialvec[8]={1,1,1,1,0,0,0,0};
	BF_KEY *key=(BF_KEY*)calloc(1,sizeof(BF_KEY));
	long length= strlen((char*)plaintext);
	BF_set_key(key,strlen(keystr),(const unsigned char*)keystr);
	BF_cbc_encrypt((const unsigned char*)plaintext,(unsigned char*)out,length,key,(unsigned char*)initialvec,BF_ENCRYPT);
	return out;
}

void *fs_decrypt(void *ciphertext, int bufsize, char *keystr, int *resultlen)
{	
	unsigned char *output=(unsigned char*)calloc(BLOCKSIZE+1,sizeof(char));
	unsigned char initialvec[8]={1,1,1,1,0,0,0,0};
	long length= strlen((char*)ciphertext);
	BF_KEY *key=(BF_KEY*)calloc(1,sizeof(BF_KEY));
	BF_set_key(key,strlen(keystr),(const unsigned char*)keystr);
	BF_cbc_encrypt((const unsigned char*)ciphertext,(unsigned char*)output,length,key,(unsigned char*)initialvec,BF_DECRYPT);
	return output;	
}
