# OS Fundamentals

## Big Endian vs. Little Endian

- Big Endian stores the MSB in the smallest address.
- Little Endian stores the LSB in the smallest address.

## Stack & Heap (Memory Space)

- The stack is used for static memory allocation while the heap is used for dynamic memory allocation.
- Variables allocated on the stack have their memory allocated at compile time and access is very fast.
- Variables allocated on the heap have their memory allocated at runtime and accessing this memory is slightly slower.

## Processes & Threads

- A process is an instance of a program in execution. It is an independent entity to which system resources are allocated. Each process executes in a separate address space and one process cannot access the data of another process. However, inter-process communication is possible using pipes, files, sockets etc.
- A thread is a particular execution path of a process. It exists within a process and shares the process' resources. Multiple threads within the same process will share the same heap space but each thread still has its own registers and its own stack.

## Mutexes & Semaphores

- A mutex is like a lock. Mutexes are used in parallel programming to ensure that only one thread can access a shared resource at a time.
- A mutex provides mutual exclusion. For example, either a producer or a consumer can have the key (mutex) to proceed with their work. As long as the buffer is being filled by the producer, the consumer needs to wait and vice-versa.
- A semaphore is a signalling mechanism that restricts the number of simultaneous users for a shared resource up to a maximum number. Threads can request access to the resource (by decrementing the semaphore) and can signal that they have finished using the resource (by incrementing the semaphore).
- Even though the implementation of a mutex is similar to that of a binary semaphore they are not the same. A mutex is a locking mechanism used to synchronize access to a resource while a semaphore is more like a signaling mechanism.

## Deadlocks

A deadlock is a situation where a thread is waiting for a resource that another thread holds, while the second thread is waiting for the resource held by the first thread (or an equivalent situation with several threads). Since each thread is waiting for the other to unlock the resource, both threads remain waiting forever.

### Conditions for Deadlock

1. **Mutual Exclusion:** Only one process can access a resource at a given time (or there are limited number of the same resource).
2. **Hold and Wait:** Processes already holding a resource can request additional resources without releasing their current resources.
3. **No Preemption:** One process cannot forcibly remove another process' resource.
4. **Circular Wait:** Two or more processes form a circular chain where each process is waiting on another resource in the chain.

Deadlocks can be prevented by removing any one of the four conditions above. However, most deadlock prevention algorithms focus on avoiding circular wait.

## Livelock

A thread often acts in response to the action of another thread. If the other thread's action is also a response to the action of another thread, then livelock may result. As with deadlock, livelocked threads are unable to make further progress. However, the threads are not blocked — they are simply too busy responding to each other to resume work.

## Starvation

Starvation describes a situation where a thread is unable to gain regular access to shared resources and is unable to make progress. This happens when shared resources are made unavailable for long periods by "greedy" threads.

## Scheduling

- **Long-Term Scheduler:** Determines which programs are admitted into the system for processing. It selects processes from the queue and loads them into memory for execution.
- **Short-Term Scheduler:** Selects a process among the processes that are ready to execute and allocates the CPU to one of them. Its main objective is to increase system performance according to certain criteria.
- **Medium-Term Scheduler:** Responsible for removing processes from memory in case of long waits (for eg. I/O wait). The suspended process is moved to secondary storage and swapped for another process in the queue. Once the wait is over, the suspended process is swapped back in for continued execution.
