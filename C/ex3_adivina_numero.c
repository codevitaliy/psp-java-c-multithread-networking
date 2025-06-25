#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <wait.h>
#include <fcntl.h>
#include <unistd.h>
#include <time.h>
#include <signal.h>

//Constantes
#define SIZE 512

//Funciones prototipo
void codigo_hijo();
void codigo_padre(pid_t,int);
void manejador(int signum);

//Variables globales
int tP2H[2]; // Tubería para enviar mensajes desde el Padre al Hijo.
int tH2P[2]; // Tubería para enviar mensajes desde el Hijo al Padre.
char mensaje[SIZE]; // Buffer para almacenar los mensajes enviados/recibidos.
int intentos = 0; // Número de intentos que ha necesitado el hijo para acertar.

int main(void){
    pid_t pid; // Identificador del proceso hijo en el padre (en el proceso hijo vale 0).
    int numero; // Número aleatorio que constituye el número a encontrar en el juego.
    
    pipe (tP2H); // Crea la tubería para la comunicación Padre -> Hijo.
    pipe (tH2P); // Crea la tubería para la comunicación Hijo  -> Padre.

    srand(time(NULL)); //Altero la semilla de numeros aleatorios en base al tiempo.
    numero=rand() % 101; //Número aleatorio entre 0 y 100.

    pid = fork(); // Intentamos crear el proceso hijo.
    switch(pid){
        case -1: 
            printf("El resultado de la llamada a fork no ha podido crear el proceso hijo.\n");
            exit(-1);
            break;
        case 0:
            codigo_hijo(); // Función asociada al proceso hijo.
            break;
        default:
            codigo_padre(pid,numero); // Función asociada al proceso padre.
            break;
    }
    return 0;
}

void codigo_padre(pid_t pid,int numero)
{
    int fin = 0; // Flag para terminar el juego.
    int n;       // Número que le ha propuesto el Hijo.
    signal (SIGUSR1,manejador); // Asociamos la función manejador a la recepción de la señal SIGUSR1.
    close (tP2H[0]); // Cerramos el extremo de lectura (0) de la tubería P2H.
    close (tH2P[1]); // Cerramos el extremo de escritura (1) de la tubería H2P.
    printf ("P: El número objetivo es el %d\n",numero);
    printf ("P: El Padre espera la propuesta del hijo....\n");

    while (fin == 0)
    {
        pause();  // Esperamos la señal del Hijo.
        n = atoi (mensaje); // Convertimos el mensaje extrayendo el número.
        printf ("P: El padre recibe: %d\n",n);
        intentos++;
        if (numero == n)
        { 
            strcpy (mensaje,"<ACERTASTE>");
            fin = 1;
        } 
        else
            if (numero > n) 
                strcpy (mensaje,"<TE HAS QUEDADO CORTO>");
            else
                strcpy (mensaje,"<TE HAS PASADO>");
        printf ("P: El padre envía %s al hijo\n",mensaje);
        write (tP2H[1],mensaje,strlen(mensaje));
        kill (pid,SIGUSR2); // Enviamos señal al hijo.
    } 
    close (tP2H[1]);
    close (tH2P[0]);
    printf ("P: El hijo ha necesitado %d intentos para acertar.\n");
    waitpid(pid, NULL, 0);
    printf ("P: Padre termina.\n");
}

void codigo_hijo()
{
    int fin = 0;  // Flag para terminar el juego.
    int lim_inf=0,lim_sup=101; // Límites inferior y superior para acertar el número.
    int n; // Número que propone el Hijo.
    signal (SIGUSR2,manejador); // Asociamos la función manejador a la recepción de la señal SIGUSR1.
    sleep(2); // Forzamos una breve pausa en el hijo para proporcionar tiempo a que el padre esté operativo.
    close (tP2H[1]);  // Cerramos el extremo de escritura (1) de la tubería P2H.
    close (tH2P[0]);  // Cerramos el extremo de lectura (0) de la tubería H2P.
    printf ("H: El Hijo comienza su ejecución\n");
    n=rand() % 101;  // Calcula un número aleatorio para ver si acierta.
    
    while (fin == 0)
    {
        printf ("H: ¿Se trata del %d?\n",n);
        sprintf (mensaje,"%d",n);  // Convierte el número en cadena.
        write (tH2P[1],mensaje,strlen(mensaje)); // Envia el mensaje.
        kill (getppid(),SIGUSR1);                      // Envía la señal. 
        pause();                                // Espera la respuesta del padre.
        printf ("H: Recibo %s\n",mensaje);
        if (strcmp(mensaje,"<ACERTASTE>")==0)   // Evalúa la respuesta del padre.
            fin = 1;
        else
            if (strcmp(mensaje,"<TE HAS QUEDADO CORTO>")==0) // Si se queda corto tenemos un límite inferior.
            { 
                lim_inf = n;
                n = (rand() % (lim_sup-lim_inf)) + lim_inf;
            } 
            else
                if (strcmp(mensaje,"<TE HAS PASADO>")==0)   // Si se pasa tenemos un límite superior.
                { 
                    lim_sup = n;
                    n = (rand() % (lim_sup-lim_inf)) + lim_inf;
                }  
                else
                {
                    printf ("Se ha recibido un mensaje no previsto: %s\n",mensaje);
                    fin = 1;
                    kill (getppid(),SIGKILL); 
                }  
    } 
    close (tP2H[0]);
    close (tH2P[1]);
    printf ("H: El hijo termina\n");
}

void manejador (int signum){
    int bytesrecibidos;
    if(signum==SIGUSR1) //Señal que envia el hijo al padre.
    {
        sleep(1);
        bytesrecibidos = read(tH2P[0], mensaje, SIZE);
        mensaje[bytesrecibidos] = '\0'; 
    }
    else
        if(signum==SIGUSR2) //Señal que envia el padre al hijo.
        {   
            sleep(1);
            bytesrecibidos = read(tP2H[0], mensaje, SIZE);
            mensaje[bytesrecibidos] = '\0';
        }
        else
        {
            printf("Se ha recibido una señal desconocida\n");
        }
}