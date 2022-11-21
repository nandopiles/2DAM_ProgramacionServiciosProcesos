#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <string.h>

void main(){
  int fp;
  char saludo[]="Un saludo!!\n";
  
  fp=open("FIFO2", 1);
  if (fp==-1){
    printf("Error al abrir el fichero...");
    exit(1);
  }
  printf("Mandando información al FIFO...\n");
  write(fp, saludo, strlen(saludo));
  close(fp);
}

