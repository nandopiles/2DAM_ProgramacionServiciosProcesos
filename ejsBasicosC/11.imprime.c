#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <string.h>

void main(){
  int fp;
  int bytesleidos;
  char buffer[15] = " ";
  char resultado[15] = " ";
  char delimitador[] = "-";
  int contador = 0;
  
  //PARTE1: CREA EL FIFO Y RECIBE LOS DATOS
  mkfifo("FIFONUM", S_IFIFO|0666);
  fp = open("FIFONUM", 0);
  bytesleidos = read(fp, buffer, 1);
  while (bytesleidos != 0){
    strcat(resultado, buffer);
    bytesleidos = read(fp, buffer, 1);
  }
  
  //PARTE2: HACEMOS SPLIT (STRTOK) SOBRE EL VECTOR E IMPRIMIMOS
  printf("\n*****RESULTADOS********\n");
  char *token = strtok(resultado,delimitador);
  if (token != NULL){
    while (token != NULL){
      switch(contador){
        case 0: printf("*     Minimo: %s      \n", token);
                break;
        case 1: printf("*     Maximo: %s      \n", token);
                break;
        case 2: printf("*     Media: %s      \n", token);
                break;
      }
      contador++;
      token = strtok(NULL, delimitador);
    }
  }
  close(fp);
}

