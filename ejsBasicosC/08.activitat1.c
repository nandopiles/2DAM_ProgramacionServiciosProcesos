#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/wait.h>

void main(){
	pid_t pid, hijo_pid;
	int var = 6;

	pid = fork();
	if (pid == -1){ //Ha ocurrido un error
		printf("No se ha podido crear el proceso hijo...\n");
		exit(-1);
	}
	if (pid == 0){ //Nos encontramos en el proceso hijo
		var = var - 5;
		printf("Variable en Proceso Hijo: %d\n",var);
	}
	else{ //Nos encontramos en el proceso padre
		printf("El valor inicial de la variable es: %d\n",var);
		hijo_pid = wait(NULL);
		var = var + 5;
		printf("Variable en Proceso Padre: %d\n",var);
	}
}
