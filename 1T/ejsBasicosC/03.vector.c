#include <stdio.h>

void main(){
	int vector[5];
	int ant = -1;

	//Bucle donde el usuario va introduciendo numeros
	for (int i=0; i<5; i++){
		do{
			printf("Introduzca elemento de la posicion %d:",(i+1));
			scanf("%d",&vector[i]);
			if (ant >= vector[i]){
					printf("El numero %d no es mayor que %d!\n",vector[i],ant);
			}
		}
		while (ant >= vector[i]);
		ant = vector[i];
	}
	//Finalizamos imprimiendo el vector con el formato solicitado
	printf("El vector introducido es el siguiente: [ ");
	for (int i=0; i<5; i++){
		if (i != 4){
			printf("%d, ",vector[i]);
		}else{
			printf("%d ]\n",vector[i]);
		}
	}
}
