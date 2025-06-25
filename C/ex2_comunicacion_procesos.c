#include <sys/types.h>
#include <wait.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>
#include <signal.h>

//Constantes
#define SIZE 512

//Funciones prototipo
void codigo_hijo(void);
void codigo_padre(pid_t);
void manejador(int signum);

// Variables globales
int tP2H[2]; // Tubería para enviar mensajes desde el Padre al Hijo.
int tH2P[2]; // Tubería para enviar mensajes desde el Hijo al Padre.
char mensaje[SIZE]; // Buffer para almacenar los mensajes enviados/recibidos.

int main(void)
{
    pid_t pid; // Identificador del proceso hijo en el padre (en el proceso hijo vale 0).
    
    pipe (tP2H); // Crea la tubería para la comunicación Padre -> Hijo.
    pipe (tH2P); // Crea la tubería para la comunicación Hijo  -> Padre.

    pid = fork(); // Intentamos crear el proceso hijo
    switch(pid)
    {
        case -1: 
            printf("El resultado de la llamada a fork no ha podido crear el proceso hijo.\n");
            exit(-1);
            break;
        case 0:
            codigo_hijo(); // Función asociada al proceso hijo.
            break;
        default:
            codigo_padre(pid); // Función asociada al proceso padre.
            break;
    }
    return 0;
}

void codigo_hijo()
{
    int fin = 0;  // Flag para terminar la conversación.
    signal (SIGUSR1,manejador); // Asociamos la función manejador a la recepción de la señal SIGUSR1.
    close (tP2H[1]); // Cerramos el extremo de escritura (1) de la tubería P2H.
    close (tH2P[0]); // Cerramos el extremo de lectura (0) de la tubería H2P.
    printf ("H: El Hijo espera el mensaje del padre....\n");

    while (fin == 0)
    {
        pause();  // Esperamos la señal del Padre
        printf ("H: El hijo recibe: %s\n",mensaje);
        if (strcmp(mensaje,"FIN\n") == 0)
            fin = 1;
        else
            strcpy (mensaje,"<RECIBIDO>");
        
        write (tH2P[1],mensaje,strlen(mensaje));
        kill (getppid(),SIGUSR2);
    } 
    close (tP2H[0]);
    close (tH2P[1]);
    printf ("H: Hijo termina.\n");
}

void codigo_padre(pid_t pid)
{
    int fin = 0;  // Flag para terminar la conversación.
    signal (SIGUSR2,manejador); // Asociamos la función manejador a la recepción de la señal SIGUSR2.
    sleep(2); // Forzamos una breve pausa en el padre para proporcionar tiempo a que el hijo esté operativo.
    close (tP2H[0]);  // Cerramos el extremo de lectura (0) de la tubería P2H.
    close (tH2P[1]);  // Cerramos el extremo de escritura (1) de la tubería H2P.
    printf ("P: El Padre comienza su ejecución\n");
    
    while (fin == 0)
    {
        printf ("P: ¿Qué le decimos al hijo (FIN=terminar):");
        fgets(mensaje,SIZE,stdin);
        write (tP2H[1],mensaje,strlen(mensaje)); // Envia el mensaje 
        kill (pid,SIGUSR1);                      // Envía la señal 
        pause();                                // Espera la respuesta del hijo.
        printf ("P: Recibo %s\n",mensaje);
        if (strcmp(mensaje,"FIN\n")==0)   // Evalúa la respuesta del Hijo
            fin = 1;
    } 
    waitpid(pid, NULL, 0);
    close (tP2H[1]);
    close (tH2P[0]);
    printf ("El padre termina\n");
}

void manejador (int signum)
{
    int bytesrecibidos;
    if(signum==SIGUSR1) //Señal que envia el padre al hijo
    {
        bytesrecibidos = read(tP2H[0], mensaje, SIZE);
        mensaje[bytesrecibidos] = '\0'; 
    }
    else
        if(signum==SIGUSR2) //Señal que envia el padre al hijo
        {   
            bytesrecibidos = read(tH2P[0], mensaje, SIZE);
            mensaje[bytesrecibidos] = '\0';
        }
        else
            printf("Se ha recibido una señal desconocida\n");
}