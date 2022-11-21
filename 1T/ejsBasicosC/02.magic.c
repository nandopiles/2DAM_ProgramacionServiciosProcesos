#include <stdio.h>
void main(){
	int num = -1;
	int intento = 0;
	//1ª PARTE - EL JUGADOR 1 INTRODUCE UN NUMERO PARA ADIVINAR
	while ((num <1) || (num > 100)){
		printf("JUGADOR1: Introduzca un numero (1-100) para adivinarlo:\n");
		scanf("%d",&num);
		if ((num < 1) || (num > 100)){
			printf("El numero %d no esta incluido en el rango 1-100\n",num);
		}
	}
	//2ª PARTE - EL JUGADOR 2 INTENTARA EN BUCLE ADIVINAR EL NUMERO
	while (intento != num){
		printf("JUGADOR2: Introduzca un numero (1-100):\n");
		scanf("%d",&intento);
		if (intento == num){
			printf("Enhorabuena, ya has adivinado el numero!\n");
		}else{
			if (intento > num){
				printf ("El numero es inferior. Sigue intenténdolo...\n");
			}else{
				printf ("El numero es superior. Sigue intenténdolo...\n");
			}
		}
	}
}
