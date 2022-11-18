#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>

void main(){
  int fp;
  int max, min, media, num, total, cuenta = 0;
  char max_cad[3]="";
  char min_cad[3]="";
  char media_cad[3]="";
  char vector[15] ="";
  
  //PARTEL: CALCULO DE MINIMO, MAXIMO Y MEDIA.
  printf("Introduzca un nunero:");
  scanf("%d", &num);
  while (num != 100){
    if (cuenta == 0){
      min = num;
      max = num;
      total = num;
      cuenta++;
    }else{
      if (num > max)
        max = num;
      if (num < min)
        min = num;
      total = total + num;
      cuenta++;
    }
    printf("Introduzca un numero:");
    scanf("%d", &num);
  }
  media = total/cuenta;
  
  //PARTE2: CONCATENO RESULTADOS EN UN VECTOR
  sprintf(min_cad,"%d",min);
  sprintf(max_cad, "%d",max);
  sprintf(media_cad, "%d", media);
  strcpy(vector,min_cad);
  strcat(vector,"-");
  strcat(vector,max_cad);
  strcat(vector,"-");
  strcat(vector,media_cad);
  strcat(vector, "\n");
  
  //PARTE3: ENVIO EL VECTOR AL FIFO
  fp = open("FIFONUM", 1);
  if (fp == -1){
    printf("Error al abrir el fichero...");
    exit(1);
  }
  printf("Se envian los resultados a imprimir...\n");
  write(fp, vector, strlen(vector));
  close(fp);
}

