#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/wait.h>

void main(){
	pid_t pid, hijo_pid;
	pid = fork();

	if (pid == -1){	//Ha ocurrido un error
		printf("No se ha podido crear el proceso hijo...\n");
		exit(-1);
	}
	if (pid == 0){ //Nos encontramos en el proceso hijo
	printf("Soy el proceso hijo, mi PID es %d y el PID"
			" de mi padre es %d\n",getpid() ,getppid());
	}
	else{ //Nos encontramos en el proceso padre
	hijo_pid = wait (NULL);
	printf("Soy el proceso padre, mi PID es %d,"
			"el PID de mi padre es %d y mi"
			" hijo %d terminó\n" ,getpid(),getppid(),hijo_pid);
	}
}
