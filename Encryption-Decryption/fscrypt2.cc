#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "openssl/blowfish.h"

/* 
implement the encrypt / decrypt function using the openSSL library

1.	void BF_set_key(BF_KEY *key, int len, const unsigned char *data)
2.	void BF_ecb_encrypt(const unsigned char *in, unsigned char *OUTPUT, BF_KEY *key, int enc)

implement your own CBC operation mode
*/
const int BLOCKSIZE = 8;

void *fs_encrypt(void *plaintext, int bufsize, char *keystring, int *resultlen)
{
	BF_KEY *key=(BF_KEY*)calloc(1,sizeof(BF_KEY));
	char *OUTPUT=(char*)calloc(BLOCKSIZE+1,sizeof(char));
	BF_set_key(key,strlen(keystring),(const unsigned char*)keystring);
	BF_ecb_encrypt((const unsigned char*)plaintext,(unsigned char*)OUTPUT,key,BF_ENCRYPT);

	return OUTPUT;

}

void *fs_decrypt(void *ciphertext, int bufsize, char *keystring, int *resultlen)
{
	BF_KEY *key=(BF_KEY*)calloc(1,sizeof(BF_KEY));
	char *OUTPUT1=(char*)calloc(BLOCKSIZE+1,sizeof(char));
	BF_set_key(key,strlen(keystring),(const unsigned char*)keystring);
	BF_ecb_encrypt((const unsigned char*)ciphertext,(unsigned char*)OUTPUT1,key,BF_DECRYPT);

	return OUTPUT1;

}



