#include <stdio.h>
#include <unistd.h>

void main(){
	pid_t pid_actual, pid_padre;

	pid_actual = getpid();
	pid_padre = getppid();

	printf("PID de este proceso es: %d\n", pid_actual);
	printf("PID del proceso padre es: %d\n", pid_padre);
}
