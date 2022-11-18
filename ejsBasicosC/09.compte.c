#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <string.h>

void main(){
	int fp;
	int bytesleidos;
	char buffer[10] =" ";
	int cont_a = 0;
	int cont_e = 0;
	int cont_i = 0;
	int cont_o = 0;
	int cont_u = 0;
	int cont_vocales = 0;
	
	mkfifo("FIFOVOCAL", S_IFIFO|0666);
	
	fp = open("FIFOVOCAL", 0);
	bytesleidos = read(fp, buffer, 1);
	printf("OBTENIENDO información...\n");
	
	while (bytesleidos != 0){
		switch (buffer[0]){
			case 'a': cont_a++;
				  break;
			case 'e': cont_e++;
				  break;
			case 'i': cont_i++;
				  break;
			case 'o': cont_o++;
				  break;
			case 'u': cont_u++;
				  break;
		}
		bytesleidos = read(fp, buffer, 1);
	}
	
	cont_vocales = cont_a + cont_e + cont_i + cont_o + cont_u;
	printf("Contador de a: %d\n",cont_a);
	printf("Contador de e: %d\n",cont_e);
	printf("Contador de i: %d\n",cont_i);
	printf("Contador de o: %d\n",cont_o);
	printf("Contador de u: %d\n",cont_u);
	printf("TOTAL VOCALES: %d\n",cont_vocales);
	close(fp);
}
