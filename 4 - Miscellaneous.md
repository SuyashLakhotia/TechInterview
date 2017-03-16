# Miscellaneous

## Time Complexity

### Asymptotic Bounds

> f(n) = O(g(n))

- `g(n)` is the asymptotic upper bound of `f(n)`.

> f(n) = Θ(g(n))

- `g(n)` is the asymptotic tight bound of `f(n)`.

> f(n) = Ω(g(n))

- `g(n)` is the asymptotic lower bound of `f(n)`.

### Complexity Classes

- `O(1)`: Constant
- `O(log n)`: Logarithmic
- `O(n)`: Linear
- `O(n log n)`: Loglinear
- `O(n^2)`: Quadratic
- `O(n^c)`: Polynomial
- `O(c^n)`: Exponential
- `O(n!)`: Factorial

## Bit Manipulation

### Bitwise Operators

- `&`: AND
- `|`: OR
- `^`: XOR
- `~`: NOT
- `<<`: Binary Left Shift
- `>>`: Binary Right Shift
- `>>>`: Zero Fill Right Shift

### Bit Facts & Tricks

```
  x ^ 0s = x      x & 0s = 0      x | 0s = x
  x ^ 1s = ~x     x & 1s = x      x | 1s = 1s
   x ^ x = 0       x & x = x       x | x = x
 ```

### Two's Complement

- Computers typically store integers in two's complement representation.
- Range of unsigned numbers that can be stored with `N` bits is `0` - `+(2^N - 1)`.
- Range of signed numbers that can be stored with `N` bits in two's complement representation is `-(2^(N - 1))` - `+(2^(N - 1) - 1)`.
- Binary representation of `-K` is `concat(1, bin(2^(N - 1) - K))`.

### Arithmetic vs. Logical Shift

- In an arithmetic right shift (`>>`), the bits are shifted and the sign bit is put in the MSB.
- In a logical right shift (`>>>`), the bits are shifted and a `0` is put in the MSB.

### Common Bit Tasks

#### Get Bit

```java
boolean getBit(int num, int i) {
    return ((num & (1 << i)) != 0);
}
```

#### Set Bit

```java
int setBit(int num, int i) {
    return num | (1 << i);
}
```

#### Clear Bit(s)

```java
int clearBit(int num, int i) {
    int mask = ~(1 << i);
    return num & mask;
}

int clearBitsMSBThroughI(int num, int i) {
    int mask = (1 << i) - 1;
    return num & mask;
}

int clearBitsIThrough0(int num, int i) {
    int mask = (-1 << (i + 1));
    return num & mask;
}
```

#### Toggle Bit

```java
int toggleBit(int num, int i) {
    return num ^= (1 << i);
}
```

#### Update Bit

```java
int updateBit(int num, int i, boolean setBit) {
    int value = setBit ? 1 : 0;
    int mask = ~(1 << i);
    return (num & mask) | (value << i);
}
```

#### Multiply/Divide by 2<sup>n</sup>

```java
num = num << n;   // multiply
num = num >> n;   // divide
```

## OS Fundamentals

### Big Endian vs. Little Endian

- Big Endian stores the MSB in the smallest address.
- Little Endian stores the LSB in the smallest address.

### Stack & Heap (Memory Space)

- The stack is used for static memory allocation while the heap is used for dynamic memory allocation.
- Variables allocated on the stack have their memory allocated at compile time and access is very fast.
- Variables allocated on the heap have their memory allocated at runtime and accessing this memory is slightly slower.

### Processes & Threads

- A process is an instance of a program in execution. It is an independent entity to which system resources are allocated. Each process executes in a separate address space and one process cannot access the data of another process. However, inter-process communication is possible using pipes, files, sockets etc.
- A thread is a particular execution path of a process. It exists within a process and shares the process' resources. Multiple threads within the same process will share the same heap space but each thread still has its own registers and its own stack.

### Mutexes & Semaphores

- A mutex is like a lock. Mutexes are used in parallel programming to ensure that only one thread can access a shared resource at a time.
- Semaphores are more general than mutexes. They differ only in that a semaphore's integer may start at a number greater than 1. The number at which a semaphore starts is the number of threads that may access the resource at once.

### Deadlocks

A deadlock is a situation where a thread is waiting for a resource that another thread holds, while the second thread is waiting for the resource held by the first thread (or an equivalent situation with several threads). Since each thread is waiting for the other to unlock the resource, both threads remain waiting forever.

#### Conditions for Deadlock

1. **Mutual Exclusion:** Only one process can access a resource at a given time (or there are limited number of the same resource).
2. **Hold and Wait:** Processes already holding a resource can request additional resources without releasing their current resources.
3. **No Preemption:** One process cannot forcibly remove another process' resource.
4. **Circular Wait:** Two or more processes form a circular chain where each process is waiting on another resource in the chain.

Deadlocks can be prevented by removing any one of the four conditions above. However, most deadlock prevention algorithms focus on avoiding circular wait.

### Scheduling

- **Long-Term Scheduler:** Determines which programs are admitted into the system for processing. It selects processes from the queue and loads them into memory for execution.
- **Short-Term Scheduler:** Selects a process among the processes that are ready to execute and allocates the CPU to one of them. Its main objective is to increase system performance according to certain criteria.
- **Medium-Term Scheduler:** Responsible for removing processes from memory in case of long waits (for eg. I/O wait). The suspended process is moved to secondary storage and swapped for another process in the queue. Once the wait is over, the suspended process is swapped back in for continued execution.

## Design Patterns

### Observer Pattern

The Observer pattern involves having observers register for changes to subjects. Whenever the subject changes, each of the registered observers is notified as opposed to the observers continuously polling the subject's state.

```java
public class Subject {
    private List<Observer> observers = new ArrayList<>();
    private int state;

    public int getState() { return state; }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
```

```java
public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}
```

```java
public class BinaryObserver extends Observer {
    public BinaryObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Binary String: " + Integer.toBinaryString(subject.getState()));
    }
}
```

```java
public class HexaObserver extends Observer {
    public HexaObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Hex String: " + Integer.toHexString(subject.getState()).toUpperCase());
    }
}
```

### Singleton Class

The Singleton pattern ensures that a class has only one instance and ensures access to that instance through the application. It can be useful when you need a *global* object with exactly one instance.

```java
public class Singleton {
    private static Singleton _instance = null;
    protected Singleton() { ... }
    public static Singleton getInstance() {
        if (_instance == null) {
            _instance = new Singleton();
        }
        return _instance;
    }
}
```

### Factory Method

The Factory Method offers an interface for creating an instance of a class, with its subclasses deciding which class to instantiate. The Factory method can also be implemented with a parameter representing which class to instantiate.

```java
public class CardGameFactory {
    public static CardGame createCardGame(String type) {
        if (type.equalsIgnoreCase("POKER")) {
            return new PokerGame();
        } else if (type.equalsIgnoreCase("BLACKJACK")) {
            return new BlackJackGame();
        }
        return null;
    }
}
```

### Model-View-Controller (MVC)

Model‐view‐controller (MVC) is a design pattern commonly used in user interfaces. The goal is to keep the "data" separate from the user interface. Essentially, a program that uses MVC uses separate programming entities to store the data (the "model"), display the data (the "view") and modify the data (the "controller"). In MVC, the view usually makes heavy use of listeners to listen to changes and events in the model.
