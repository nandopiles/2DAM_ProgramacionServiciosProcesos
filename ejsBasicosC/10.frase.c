#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <string.h>

void main(){
  int fp;
  char frase[50];
  
  printf("Escriba una frase para calcular numero de vocales:\n");
  fgets(frase,50,stdin);
  
  fp = open("FIFOVOCAL", 1);
  if (fp == -1){
    printf("Error al abrir el fichero...%d",fp);
    exit(1);
  }
  printf("Mandando información al FIFO...\n");
  write(fp, frase, strlen(frase));
  close(fp);
}

