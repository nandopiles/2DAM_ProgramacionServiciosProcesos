#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <string.h>

void gestion_padre1(){
	printf("HIJ@ MI@!!!! BIENVENID@!!! Eres mi hij@ 1\n\n");
}

void gestion_padre2(){
	printf("HIJ@ MI@!!!! BIENVENID@!!! Eres mi hij@ 2\n\n");
}

void gestion_hijo1(){
	printf("HIJ@ MI@!!!! BIENVENID@!!! Eres mi hij@ 11\n\n");
}

void gestion_padre3(){
	printf("HIJ@ MI@!!!! BIENVENID@!!! Eres mi hij@ 3\n\n");
}

void gestion_hijo2(){
	printf("HIJ@ MI@!!!! BIENVENID@!!! Eres mi hij@ 12\n\n");
}

//Todas los mensajes de los signal ^

int main(int argc, char *argv[]) {
	pid_t pid1, pid2, pid3, pid11, pid12;
	int bytesleidos;
	int fp_targaryen, fp_martell;
	char nom_padre[20] = "Aerys";
	char nom_hijo[20] = "Rhaegar";
	char busco_esposa[80] = "Soy ";
	char busco_esposa2[80] = "Soy ";
	char buffer[80] = " ";
	char esposa[80] = "";
	char esposa2[80] = "";
	
	//He duplicado algunas variables para poder pasar los distintos nombres
	
	printf("Soy %s [padre (%d, hijo de %d)]\n\n\n",nom_padre ,getpid(), getppid());

	strcat(busco_esposa, nom_padre);
	strcat(busco_esposa, " y busco mujer Targaryen fértil. Indique el nombre...");
	printf("%s\n",busco_esposa);
	
	//Acceso al programa agencia targaryen
	fp_targaryen = open("FifoTargaryen",1);
	if(fp_targaryen == -1){
		printf("Error al abrir el fichero");
		exit(1);
	}
	write(fp_targaryen, busco_esposa, strlen(busco_esposa));
	close(fp_targaryen);
	
	//Cierro el fifo de escritura arriba y lo abro como lectura abajo
	
	fp_targaryen = open("FifoTargaryen",0);
	bytesleidos = read(fp_targaryen, buffer, 1);
	while (bytesleidos != 0){
		strcat(esposa, buffer);
		bytesleidos = read(fp_targaryen, buffer, 1);
	}
	printf("Hola %s. Perpetuemos la casa Targaryen!!!\n",esposa);
	close(fp_targaryen);
	
	//Cierro el fifo y comienzo a crear hijos
	
	//Creacion del primer hijo
	pid1 = fork();
	switch (pid1){
		case -1:
			printf("Error al crear el hijo\n");
			break;
		case 0:
			printf("Soy %s (hijo1) (%d, hijo de %s = %d)\n", nom_hijo, getpid(), nom_padre, getppid());
			printf("Hola papi. Ya he nacido!!!\n\n");
			kill(getppid(),SIGUSR1);
			
			strcat(busco_esposa2, nom_hijo);
			strcat(busco_esposa2, " y busco mujer Martell fértil. Indique el nombre...");
			printf("%s\n",busco_esposa2);
			fp_martell = open("FifoMartell",1);
			if(fp_martell == -1){
				printf("Error al abrir el fichero\n");
				exit(1);
			}
			write(fp_martell, busco_esposa2, strlen(busco_esposa2));
			close(fp_martell);
			fp_martell = open("FifoMartell",0);
			bytesleidos = read(fp_martell, buffer, 1);
			while (bytesleidos != 0){
				strcat(esposa2, buffer);
				bytesleidos = read(fp_martell, buffer, 1);
			}
			printf("Hola %s. Perpetuemos la casa Targaryen!!!\n",esposa2);
			close(fp_martell);
			
			//Creacion del hijo 11
			pid11 = fork();
			switch (pid11) {
				case -1:
					printf("Error al crear el hijo\n");
					break;
				case 0:
					printf("Soy Rhaenys (hijo11) (%d, hijo de %s = %d)\n", getpid(), nom_hijo, getppid());
					printf("Hola papi. Ya he nacido!!!\n\n");
					kill(getppid(),SIGUSR1);
					break;
				default:
				//Paro el proceso hasta que reciba una señal de su hijo
					signal(SIGUSR1, gestion_hijo1);
					pause();
					
					//creacion del hijo 12
					pid12 = fork();
					switch (pid12) {
						case -1:
							printf("Error al crear el hijo\n");
							break;
						case 0:
							printf("Soy Aegon (hijo12) (%d, hijo de %s = %d)\n", getpid(), nom_hijo, getppid());
							printf("Hola papi. Ya he nacido!!!\n\n");
							kill(getppid(),SIGUSR1);
							break;
						default:
						//Paro el proceso hasta que reciba una señal de su hijo
							signal(SIGUSR1, gestion_hijo2);
							pause();
					}
			}
			break;
		default:
		//Paro el proceso hasta que reciba una señal de su hijo
			signal(SIGUSR1, gestion_padre1);
			pause();
			
			//Creacion del hijo 2
			pid2 = fork();
			switch (pid2){
				case -1:
					printf("Error al crear el hijo\n");
					break;
				case 0:
					printf("Soy Viserys (hijo2) (%d, hijo de %s = %d)\n", getpid(), nom_padre, getppid());
					printf("Hola papi. Ya he nacido!!!\n\n");
					kill(getppid(),SIGUSR1);
					break;
				default:
				//Paro el proceso hasta que reciba una señal de su hijo
					signal(SIGUSR1, gestion_padre2);
					pause();
					
					//Creacion del hijo 3
					pid3 = fork();
					switch (pid3) {
						case -1:
							printf("Error al crear el hijo\n");
							break;
						case 0:
							printf("Soy Daenerys (hijo3) (%d, hijo de %s = %d)\n", getpid(), nom_padre, getppid());
							printf("Hola papi. Ya he nacido!!!\n\n");
							kill(getppid(),SIGUSR1);
							break;
						default:
							//Paro el proceso hasta que reciba una señal de su hijo
							signal(SIGUSR1, gestion_padre3);
							pause();
					}
			}
	}
}
