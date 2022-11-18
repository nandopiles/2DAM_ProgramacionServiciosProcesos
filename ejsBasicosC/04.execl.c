#include <stdio.h>
#include <unistd.h>

void main(){
	printf("Esto es un ejemplo de exec():\n");
	printf("Los archivos en el directorio son:\n");
	execl("/bin/ls","ls","-l",(char*)NULL);
}
