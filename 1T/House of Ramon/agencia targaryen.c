#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <string.h>


void main(){
	int fp_targaryen;
	int bytesleidos;
	char buffer[80] = " ";
	char resultado[80] = "";
	char esposa[80];
	
	mkfifo("FifoTargaryen", S_IFIFO|0666);
	printf("AGENCIA MATRIMONIAL DE LOS TARGARYEN... ASEGURAMOS DESCENDIENTES CON SANGRE DE RAMON!!!\n");
	printf("---------------------------------------------------------------------------------------\n");
	fp_targaryen = open("FifoTargaryen",0);
	bytesleidos = read(fp_targaryen, buffer, 1);
	while (bytesleidos != 0){
		strcat(resultado, buffer);
		bytesleidos = read(fp_targaryen, buffer, 1);
	}
	
	//Recibo el mensaje, lo printeo y cierro el fifo de lectura
	printf("%s\n",resultado);
	close(fp_targaryen);
	
	fgets(esposa,80,stdin);
	
	//Abro el fifo en modo escritura y le mando la esposa que he introducido por teclado
	fp_targaryen = open("FifoTargaryen",1);
	write(fp_targaryen, esposa, strlen(esposa)-1);
	close(fp_targaryen);
}
