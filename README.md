# DesignPatterns
It's a repo to practice writing various software design patterns

## 1. Creational patterns 
##### - Singleton - Ensures one and only one object is created.
#####	- Builder -  Encapsulates the creation of an object (let’s call it a builder), and have our client ask the builder to construct the structure for it.  
#####	- Prototype - Creates a prototypical instance, which is cloned to produce new objects.
#####	- Abstract Factory - Allows a client to create families of objects without specifying their concrete classes.
#####	- Factory Method - Subclasses decide which concrete classes to create.

## 2. Behavioral patterns
#####	- Template method - Subclasses decide which concrete classes to create.
#####	- Visitor
#####	- Mediator - Communication between objects is encapsulated with a mediator object. Objects no longer communicate directly with each other, but instead communicate through the mediator. This reduces the dependencies between communicating objects, thereby lowering the coupling.
#####	- Iterator - Provides a way to traverse a collection of objects without exposing its implementation.
#####	- Command - Encapsulates a request as an object.
#####	- Memento - Provides the ability to restore an object to its previous state (undo via rollback)
#####	- Observer - Allows objects to be notified when state changes.
#####	- Interpeter -  Defines a class-based representation for its grammar along with an  interpreter to interpret its sentences. To represent the language, you use a class to represent each rule in the language. 
#####	- Chain of Responsibility - Creates a chain of objects to examine requests. Each object in turn examines a request and either handles it, or passes it on to the next object in the chain.
#####	- State - Encapsulates state-based behaviors and uses delegation to switch between behaviors.
#####	- Strategy - Encapsulates interchangeable behaviors and uses delegation to decide which one to use.

## 3. Structural patterns 
#####	- Decorator - Wraps an object to provide new behavior.
#####	- Composite - Clients treat collections of objects and individual objects uniformly.
#####	- Proxy - Wraps an object to control access to it.
#####	- Facade - Simplifies the interface of a set of classes.
#####	- Bridge - 	The Bridge Pattern allows you to vary the implementation and the abstraction by placing the two in separate class hierarchies.
#####	- Flyweight - A flyweight is an object that minimizes memory use by sharing as much data as possible with other similar objects; it is a way to use objects in large numbers when a simple repeated representation would use an unacceptable amount of memory.
#####	- Adapter - Wraps an object and provides a different interface to it.
---------------------------
## 4. Class patterns
#####	- Template method
#####	- Adapter
#####	- Factory Method
#####	- Interpreter

## 5. Object patterns
#####	- Composite
#####	- Visitor
#####	- Decorator
#####	- Iterator
#####	- Command
#####	- Memento
#####	- Proxy
#####	- Facade
#####	- Observer
#####	- Strategy
#####	- Chain of responsibility
#####	- Bridge
#####	- Mediator
#####	- Flyweight
#####	- Prototype
#####	- State
#####	- Abstract Factory
#####	- Builder
#####	- Singleton