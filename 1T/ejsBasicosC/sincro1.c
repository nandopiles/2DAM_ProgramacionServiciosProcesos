#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <signal.h>

void manejador(){
  printf("Hijo recibe señal!...\n");
}

void main(){
  pid_t pid;
  pid=fork();
  
  switch (pid){
    case -1: printf("Error al crear el proceso hijo...\n");
              exit(-1);
    case 0: signal(SIGUSR1, manejador);
            while(1){
            };
            break;
    default: sleep(1);
              kill(pid, SIGUSR1);
              sleep(1);
              kill(pid, SIGUSR1);
              sleep(1);
              break;
  }
}

