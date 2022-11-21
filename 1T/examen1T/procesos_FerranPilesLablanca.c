//includes
#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <string.h>

/**
 * Imprimix un missatge al rebre la senyal
*/
void manejador(){
    printf("--Senyal rebuda - Soc un fill qualsevol (pid= %d, fill de %d)\n", getpid(), getppid());
}

/**
 * Printeja un error
*/
void error(){
	printf("(-) Error creació fill\n");
}

void main(){
	pid_t fill_1, fill_2, pare;  
	
	printf("Pare. pid=%d\n\n", getpid()); 
	fill_1 = fork();
	switch (fill_1) {
		//Error
		case -1:
			error();
			exit(-1);	
			break;
		//fill 1
		case 0:
			printf("--Soc fill_1 i COMENÇE!\n");
			signal(SIGUSR1, manejador);
			pause();
			printf("--Soc fill_1 i ACAVE!\n");
			break;
		//pare
		default:
			fill_2 = fork();
			switch(fill_2){
				//error
				case -1:
					error();
					exit(-1);
					break;
				//fill 2
				case 0:
					printf("--Soc el fill_2 i COMENÇE!\n");
					signal(SIGUSR1, manejador);
					pause();
					printf("--Soc el fill_2 i ACAVE!\n");
					break;
				//pare
				default:
				sleep(1);
				kill(fill_1, SIGUSR1);
				kill(fill_2, SIGUSR1);
				sleep(1);
				printf("Ja ha acavat el meu Fill en pid=%d\n", fill_1);
				printf("Ja ha acavat el meu Fill en pid=%d\n", fill_2); 
				//espera a que els fills acaven
				pare = wait(NULL);
				printf("Soc el pare, ja no tinc fills... I ACAVE!!!\n");
			}
		}
	}
