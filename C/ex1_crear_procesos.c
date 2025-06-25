#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

 int main (void)
{
  pid_t pid;  // Identificador del procesos.
  int resultado; // Resultado de la ejecución del proceso.
  int i, numeroHijos = 5; // Control y límite del bucle para crear hijos. 
  int totalHijosCreados=0; // Contador de los hijos realmente creados.
  int digitosFinales; // Dígitos finales del pid

  for (i=0;i<numeroHijos;i++)
  {
    pid = fork();
    switch (pid)
    {
      case -1:
       printf ("Error al crear el proceso hijo\n");
       exit(-1);
       break;
      case 0:
       printf ("Soy el proceso hijo con pid=%d\n",getpid());
       digitosFinales = getpid()%100;
       exit(digitosFinales);
       break;
      default:
        totalHijosCreados++;
    } // Fin del switch
  } // Fin del for
  // Este fragmento solo lo ejecuta el proceso padre
  
  for (i=0;i<totalHijosCreados;i++)
  {
    pid = wait(&resultado);
    printf ("PADRE: El proceso con %d ha finalizado.\n",pid);
    if (WIFEXITED(resultado)) 
      printf ("PADRE: Resultado obtenido del proceso hijo %d\n",WEXITSTATUS(resultado));
  }
  exit(0); // Punto de salida del padre.
}