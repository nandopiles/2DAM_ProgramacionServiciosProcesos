#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <signal.h>

void gestion_padre(){
  printf("Padre recibe señal!....\n");
}

void gestion_hijo(){
  printf("Hijo recibe señal!....\n");
}
void main(){
  pid_t pid_padre, pid_hijo;
  
  pid_padre = getpid();
  pid_hijo = fork();
  
  switch(pid_hijo)
  {
    case -1:
      printf("Error al crear el proceso hijo...\n");
      exit(-1);
    case 0:
      signal (SIGUSR1, gestion_hijo);
      while(1){
        sleep(1);
        kill (pid_padre, SIGUSR1);
        pause();
      }
      break;
    default:
      signal(SIGUSR1, gestion_padre);
      while(1){
        pause();
        sleep(1);
        kill(pid_hijo, SIGUSR1);
      }
      break;
  }
}

