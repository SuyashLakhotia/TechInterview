# Concurrency in Java

> Adapted from the brilliant [Oracle tutorial](https://docs.oracle.com/javase/tutorial/essential/concurrency/index.html) on Java concurrency.

In concurrent programming, there are two basic units of execution - processes & threads. A process has a self-contained execution environment and is often seen as synonymous with programs and applications (though this may not be true). Inter-process communication usually happens via pipes or sockets as processes have their own memory space. Threads, on the other hand, exist within a process (each process has at least one) and share the process's resources. From the application programmer's point of view, you start with just one `main` thread (not counting "system" threads for memory management, signal handling etc.), which has the ability to create new threads.

## Thread Objects

Each thread is associated with an instance of the `Thread` class. An application that creates a `Thread` instance must provide the code that will run in the thread by either providing a `Runnable` object or by sub-classing `Thread` as the `Thread` class itself implements the `Runnable` interface.

```java
public class HelloRunnable implements Runnable {
    public void run() {
        System.out.println("Hello from a thread!");
    }

    public static void main(String args[]) {
        (new Thread(new HelloRunnable())).start();
    }
}
```

```java
public class HelloThread extends Thread {
    public void run() {
        System.out.println("Hello from a thread!");
    }

    public static void main(String args[]) {
        (new HelloThread()).start();
    }
}
```

The first approach is more flexible and separates the `Runnable` task from the `Thread` object that executes the task.

### Sleep

`Thread.sleep(int milliseconds)` causes the current thread to suspend execution for a specified period of time. However, the sleep period may be terminated by interrupts.

### Interrupt

An interrupt is an indication to a thread that it should stop what it is doing and do something else. A thread sends an interrupt by invoking `.interrupt()` on the `Thread` object for the thread to be interrupted. For the interrupt mechanism to work correctly, the interrupted thread must support its own interruption.

If the interrupted thread is frequently invoking methods that throw `InterruptedException`, it simply returns from the run method after it catches that exception. If a thread is not invoking such methods, it must periodically invoke `Thread.interrupted()`, which returns true if an interrupt has been received.

Note that the interrupt mechanism is implemented using an internal flag known as the interrupt status. Invoking `Thread.interrupt()` sets this flag. When a thread checks for an interrupt by invoking the static method `Thread.interrupted()`, interrupt status is cleared. The non-static `.isInterrupted()` method, which is used by one thread to query the interrupt status of another, does not change the interrupt status flag.

### Joins

The `.join()` method allows one thread to wait for the completion of another. For example, if a thread wants to wait for the completion of `t`,

```java
t.join();
```

causes the current thread to pause execution until `t`'s thread terminates. Like `.sleep()`, `join()` responds to an interrupt by throwing a `InterruptedException`.

## Synchronization

Threads communicate each other primarily by sharing access to fields and the object references fields refer to. While this is extremely efficient, it leads to *thread interference* and *memory consistency errors*.

### Thread Interference

Interference happens when two operations, running in different threads, but acting on the same data, *interleave*. This means that the two operations consist of multiple steps and the sequences of steps overlap. For example, `c++` & `c--` being invoked by two concurrent threads. Thread interference bugs are unpredictable and hard to debug.

### Memory Consistency Errors

Memory consistency errors happen when two or more threads have an inconsistent view of the data. Avoiding memory consistency errors requires understanding of the *happens-before* relationship.  This relationship is simply a guarantee that memory writes by one specific statement are visible to another specific statement.

Invoking `Thread.start()` and `Thread.join()` at the right places ensure that the statements have a predictable happens-before relationship.

### Synchronized Methods

```java
public synchronized void methodName() { }
```

Adding the `synchronized` keyword to a method guarantees two things:

1. It is not possible for two invocations of (the same or different) synchronized methods on the same object to interleave.
2. When a synchronized method exits, it automatically establishes a happens-before relationship with any subsequent invocation of a synchronized method for the same object.

### Intrinsic Locks

Synchronization is built around an internal entity known as the *intrinsic lock* or *monitor lock*. Every object has an intrinsic lock associated with it. By convention, a thread that needs exclusive and consistent access to an object's fields has to acquire the object's intrinsic lock (a.k.a. own the intrinsic lock) before accessing them and release the intrinsic lock when it's done with them. Other threads will block when they attempt to acquire the lock.

When a thread invokes a synchronized method, it automatically acquires the intrinsic lock for that method's object. When a static synchronized method is invoked, the thread acquires the intrinsic lock for the `Class` object associated with the class.

Another way to create synchronized code is through synchronized statements. Unlike synchronized methods, synchronized statements must specify the object that provides the intrinsic lock:

```java
public void addSomething(int a) {
    synchronized(this) {
        counter += a;
    }
    System.out.println(a);
}
```

```java
public class ABC {
    private int a = 0;
    private int b = 0;
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public void inc() {
        synchronized(lock1) {
            a++;
        }

        synchronized(lock1) {
            b++;
        }
    }
}
```

Note that a thread can acquire a lock it already owns. For example, if a synchronized method invokes another synchronized method of the same object.

### Atomic Access

In programming, an atomic action is one that effectively happens all at once. An atomic action cannot stop in the middle. It either happens completely or it doesn't happen at all. In Java, the following actions are atomic:

1. Reads and writes are atomic for reference variables and for most primitive variables (all types except `long` and `double`).
2. Reads and writes are atomic for all variables declared `volatile`.
