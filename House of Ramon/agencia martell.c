#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <string.h>


void main(){
	int fp_martell;
	int bytesleidos;
	char buffer[80] = " ";
	char resultado[80] = "";
	char esposa[80] = "Elia Martell";
	
	mkfifo("FifoMartell", S_IFIFO|0666);
	printf("AGENCIA MATRIMONIAL DE LOS MARTELL... ASEGURAMOS DESCENDIENTES CON SANGRE DE RAMON!!!\n");
	printf("                           COLOCANDO A LOS MARTELL!!!                                    \n");
	printf("---------------------------------------------------------------------------------------\n");
	fp_martell = open("FifoMartell",0);
	bytesleidos = read(fp_martell, buffer, 1);
	while (bytesleidos != 0){
		strcat(resultado, buffer);
		bytesleidos = read(fp_martell, buffer, 1);
	}
	
	//Printeo el mensaje que me mandan y cierro el fifo en modo lectura
	printf("%s\n",resultado);
	close(fp_martell);
	
	//Abro el fifo en modo escritura y le mando la esposa
	printf("Solo me queda %s... TE HA TOCADO!!!\n",esposa);
	fp_martell = open("FifoMartell",1);
	write(fp_martell, esposa, strlen(esposa));
	close(fp_martell);
}
