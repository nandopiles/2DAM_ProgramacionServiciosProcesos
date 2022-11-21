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
  char buffer[10]=" ";
  
  mkfifo("FIFO2", S_IFIFO|0666);
  
  while (1){
    fp=open("FIFO2",0);
    bytesleidos=read(fp, buffer, 1);
    printf("OBTENIENDO información...\n\n");
    while (bytesleidos != 0){
      printf("%1c", buffer[0]);
      bytesleidos=read(fp, buffer, 1);
    }
    close(fp);
  }
}

