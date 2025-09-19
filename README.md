# Knowledge Acquired – Java Multithreading 🧵

This work allowed me to strengthen key knowledge about **concurrent programming in Java**, understanding how to manage multiple threads and coordinate their execution in a safe and efficient way.  

---

## 🔑 Main Concepts

### 1. Thread creation and management
- Differences between **extending `Thread`** and **implementing `Runnable`**.  
- Life cycle of a thread: creation, start, execution, termination.  
- Use of methods like `start()`, `run()`, `sleep()`, `join()` to control execution.  

### 2. Process synchronization
- Use of the **`synchronized`** keyword to prevent race conditions.  
- Coordination between threads using **`wait()`**, **`notify()`**, and **`notifyAll()`**.  
- Understanding of **monitors** to control shared resources.  

### 3. Communication and shared resources
- How multiple threads interact with **shared data structures**.  
- Common issues: **data inconsistency**, **deadlocks**, and how to avoid them.  
- Applied design patterns: **producer-consumer** and **reader-writer**.  

### 4. Concurrency in distributed applications
- Use of **TCP sockets** for inter-process communication.  
- Sending information through **object serialization**.  
- Managing multiple clients concurrently in a server environment.  

### 5. Object-oriented design and concurrency
- Separation of responsibilities into classes to organize concurrent logic.  
- Application of **modularity and encapsulation** principles in multithreaded systems.  
- Safe handling and resetting of shared state after each execution.  

---

## 📚 Skills Developed
- Ability to program **multithreaded applications** in Java.  
- Clear understanding of **parallelism vs concurrency**.  
- Application of **synchronization and coordination** mechanisms.  
- Detection and prevention of classic concurrency problems (deadlocks, race conditions, busy waiting).  
- Development of **concurrent client-server applications** using sockets.  
- Improved logical reasoning for structuring concurrent workflows.  

---

## 🚀 Conclusion
This learning experience enables me to:
- Tackle projects where multiple processes must run simultaneously.  
- Design **robust and scalable systems** with many clients or actors working at once.  
- Apply concurrency foundations to advanced areas such as:  
  - **parallel processing**,  
  - **high-performance servers**,  
  - **distributed simulations**,  
  - **real-time systems**.  

In summary, I consolidated a practical vision of **concurrency in Java**, understanding both its benefits and its challenges.  

---

# Conocimientos Adquiridos – Java Multithreading 🧵

Este trabajo me permitió afianzar conocimientos clave sobre la **programación concurrente en Java**, comprendiendo cómo gestionar múltiples hilos y coordinar su ejecución de forma segura y eficiente.  

---

## 🔑 Conceptos principales

### 1. Creación y gestión de hilos
- Diferencias entre **extender la clase `Thread`** e **implementar `Runnable`**.  
- Ciclo de vida de un hilo: creación, inicio, ejecución, finalización.  
- Uso de métodos como `start()`, `run()`, `sleep()`, `join()` para controlar la ejecución.  

### 2. Sincronización de procesos
- Empleo de la palabra clave **`synchronized`** para evitar condiciones de carrera.  
- Coordinación entre hilos usando **`wait()`**, **`notify()`** y **`notifyAll()`**.  
- Comprensión del uso de **monitores** en la gestión de recursos compartidos.  

### 3. Comunicación y recursos compartidos
- Cómo varios hilos interactúan con **estructuras de datos compartidas**.  
- Problemas comunes: **inconsistencia de datos**, **interbloqueos (deadlocks)** y cómo evitarlos.  
- Patrones de diseño aplicados: **productor-consumidor** y **lector-escritor**.  

### 4. Concurrencia en aplicaciones distribuidas
- Uso de **sockets TCP** para la comunicación entre procesos.  
- Envío de información mediante **serialización de objetos**.  
- Gestión de múltiples clientes de manera concurrente en un servidor.  

### 5. Diseño orientado a objetos y concurrencia
- Separación de responsabilidades en distintas clases para organizar la lógica concurrente.  
- Aplicación de principios de **modularidad y encapsulación** en sistemas multihilo.  
- Manejo seguro y reinicio del estado compartido tras cada ejecución.  

---

## 📚 Competencias desarrolladas
- Programar aplicaciones **multihilo** en Java de forma estructurada.  
- Comprender la diferencia entre **paralelismo** y **concurrencia**.  
- Aplicar mecanismos de **sincronización y coordinación** entre procesos.  
- Detectar y prevenir problemas clásicos de concurrencia (bloqueos, condiciones de carrera, espera activa).  
- Desarrollar aplicaciones **cliente-servidor concurrentes** con sockets.  
- Mejorar la capacidad de **razonamiento lógico** para estructurar flujos de trabajo concurrentes.  

---

## 🚀 Conclusión
Este aprendizaje me permite:
- Afrontar proyectos donde varios procesos deben ejecutarse de forma simultánea.  
- Diseñar **sistemas robustos y escalables** con múltiples clientes o actores trabajando a la vez.  
- Aplicar las bases de la concurrencia en ámbitos más avanzados como:  
  - **procesamiento paralelo**,  
  - **servidores de alto rendimiento**,  
  - **simulaciones distribuidas**,  
  - **sistemas de tiempo real**.  

En resumen, consolidé una visión práctica de la **concurrencia en Java**, entendiendo tanto sus beneficios como sus desafíos.  
