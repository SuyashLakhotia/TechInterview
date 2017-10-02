# Design Patterns

## Observer Pattern

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

## Singleton Class

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

## Factory Method

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

## Model-View-Controller (MVC)

Model‐view‐controller (MVC) is a design pattern commonly used in user interfaces. The goal is to keep the "data" separate from the user interface. Essentially, a program that uses MVC uses separate programming entities to store the data (the "model"), display the data (the "view") and modify the data (the "controller"). In MVC, the view usually makes heavy use of listeners to listen to changes and events in the model.
