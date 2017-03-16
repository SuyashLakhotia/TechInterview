# Object Oriented Programming

## Object Oriented Model

- In OOP, computation is represented as the interaction among or communication between *objects*.
- An object is an entity that contains both, the attributes and the actions, of the real-world object.
    - The attributes of an object encompass the data/variables that characterize the state.
    - The state of an object encompasses all of the (usually static) properties of the object plus the current (usually dynamic) values of each of these properties.
    - The behavior/actions of an object encompass the methods that represent the services & operations an object provides.
- A **class** is a template/blueprint for objects. It contains data properties & methods. An object is a specific instance of a class.
- **Object Composition –** An object can include other objects as its data member(s). The class will contain object references as its instance variables. *has-a* relationship.

## OOP Concepts

- **Abstraction –** An abstraction denotes the essential characteristics of an object that distinguish it from all other kinds of objects and thus, provides crisply defined conceptual boundaries.
- **Encapsulation –** Encapsulation builds a barrier to protect an object's private data. Access to the private data can only be done through public methods of the object's class, such as accessors & mutators.
    - **Information Hiding –** Hides the implementation details of the class from users of the class.
- **Inheritance –** A mechanism that defines a new class that inherits the properties and behaviors (methods) of a parent class. Superclass/Base Class (Parent) → Subclass/Derived Class (Child). Any inherited behavior may be redefined and overridden in the subclass. Avoids duplication of code.
    - Multiple inheritance is when a class inherits from more than one superclass. A problem arises when there is more than one property/method to inherit with the same name.
- **Polymorphism –** The same message can be sent to different objects with different results. Sending object does not need to know the class of the receiving object or how the object will respond.

## Inheritance

- Inheritance is an important OOP feature that allows us to derive new classes from existing classes by absorbing their attributes and behaviors while also adding new capabilities. This enables code reuse and can greatly reduce programming effort. *is-a* relationship.
- The **superclass** is a generalization of the subclasses.
- The **subclasses** are specializations of the superclass.
- **Method Overriding –** A subclass inherits properties and methods from the superclass. When a subclass alters a method from a superclass by defining a method with exactly the same signature, it overrides that method. This can either be a refinement or a replacement of the superclass' method.
    - Implementing abstract methods of an abstract class or implementing methods of an interface is also method overriding.
- When a message is sent to an object, the search for a matching method begins at the class of the object → immediate superclass → and so on...
- **Method Overloading –** When a method is overloaded, it is designed to perform differently when supplied with different signatures i.e. same method name but different number of parameters or parameter types. *Not a behavior due to inheritance.*

### Types of Classes in Java

- A **concrete class** is a class with implementation for all methods.
- **Abstract Classes & Methods (`abstract`) -** Abstract methods don't have any implementation in the abstract class. The implementation must be provided by the subclass(es).
    - `public abstract class Rectangle {}`
    - `public abstract double findArea();`
- Multiple inheritance is not supported by Java. However, Java does support implementing multiple interfaces.
- An **interface** is like an abstract class except it contains only abstract methods and constants (i.e. `static final`). The `abstract` keyword is not needed when defining the methods in an interface.
    - `public interface Figure {}`
    - `static final int constant;`
    - `public double findArea();`
- A class implementing an interface has to provide an implementation for all the abstract methods. Otherwise, the new class will be abstract.
- Interfaces can inherit each other as per normal i.e. `extends`.

|                    Abstract Class                   |                       Interface                       |
|:---------------------------------------------------:|:-----------------------------------------------------:|
| `extends`                                           | `implements`                                          |
| Real base class.                                    | Not a real base class.                                |
| Can have object attributes (data members).          | Cannot have object attributes.                        |
| May have some methods declared as `abstract`.       | Can only have abstract methods.                       |
| May have `final` & non-final data attributes.       | Limited to only static constants i.e. `static final`. |
| Cannot be instantiated as objects with `new`.       | Cannot be instantiated as objects with `new`.         |

## Polymorphism

- In OOP, polymorphism is the ability of an object reference to refer to different object types; knowing which method to execute depends on where it is in the inheritance hierarchy.
- When a program invokes a method through a superclass variable, the appropriate subclass version of the method is executed based on the actual type of object stored in the superclass variable.
- The same method name & signature can cause different actions to occur, depending on the actual type of object on which the method is invoked.
- Benefits of Polymorphism:
    - Simplicity – Code can ignore type-specific details and just interact with the base type of the family. Makes it easier to write and understand the code.
    - Extensibility – New functionality can be added by creating new derived classes without modifying other derived classes.

### Binding

- **Binding –** Defines which method is to be executed (i.e. connecting a method call to a method body).
- **Static Binding –** Occurs when the method call is bound at compile time.
- **Dynamic Binding –** The selection of the method body to be executed is delayed until runtime (based on the actual object being referred).
    - Java uses this by default for all methods except `private`, `final` & `static`.

### Object Variable vs. Object Reference

- **Upcasting –** When an object of a derived class is assigned to a variable of a base class (or any ancestor class). However, subclass-only members cannot be referred to by a superclass variable.
- **Downcasting –** When an object of a base class is assigned to a variable of a derived class. This doesn't make sense in many cases and may be illegal.
- In Java, `object instanceof ClassName` will return true if object is an instance of `ClassName` or any descendent class of `ClassName`.

## Java Notes

>`this`

- `this` references the receiver object.

> `super`

- `super()` calls the constructor of the superclass and `super.X()` can be used to call a superclass' method.

> `static`

- `static` declares a class variable or class method that applies to the whole class instead of individual objects.

> `final`

- A `final` method cannot be overridden in subclasses and a `final` class cannot be a superclass.
- Improves security by ensuring no change in behavior and improves efficiency by reducing runtime type checking and binding.

### Visibility Modifiers

- `public` - Visible anywhere in an application.
- `protected` - Visible anywhere within the same package.
- `private` - Visible only within that class.

### Package

- A package contains a set of classes that are grouped together in the same directory. Non-private data can be accessed by any object in the same package.
- Packages allow for namespacing, thus, the same class name to be used in two different packages. For eg: `X.Deck` & `Y.Deck`.

## Design Principles

- Symptoms of Rotting Design:
    - Rigidity – The tendency of software to be difficult to change, even in simple ways. Every change causes a cascade of subsequent changes.
    - Fragility – The tendency of software to break in many places every time it is changed. Breakage may occur in areas that have no conceptual relationship with the area that was changed.
    - Immobility – The inability to reuse software/module from other projects or from parts of the same project. The module may have too much baggage that it depends on.
- Good design & programming must be easy to read, easy to maintain and modify, efficient, reliable and secure.
- The main design goal of OOD is to make software easier to change i.e. minimize impact of change.
- A modular program has well-defined, conceptually simple and independent units interacting through well-defined interfaces. Achieved through encapsulation, low coupling & high cohesion.

### SOLID

- **Single Responsibility Principle –** There should never be more than one reason for a class to change. If the class has more than one responsibility, then the responsibilities become coupled.
- **Open-Closed Principle –** A module should be open for extension but closed for modification. We want to be able to change what the modules do, without changing the source code of the modules.
- **Liskov Substitution Principle –** Subtypes must be substitutable for their base types. A user of a base class should continue to function if a derivative of that base class is passed to it.
- **Interface Segregation Principle –** Many client specific interfaces are better than one general purpose interface. Classes should not depend on interfaces that they do not use.
- **Don't Repeat Yourself –** Refactor to eliminate duplicated code and functionality.
- **Dependency Injection Principle –** High level modules should not depend upon low level modules. Both should depend upon abstractions. This allows the simple reuse of high level modules.
