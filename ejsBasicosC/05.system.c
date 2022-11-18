#include <stdio.h>
#include <stdlib.h>

void main(){
	printf("Ejemplo de uso de system():\n");
	printf("%d", system("ls --format=single-column -i > listado.txt"));
	printf("%d", system("geany listado.txt"));
	printf("That's all folks!!!\n");
}
