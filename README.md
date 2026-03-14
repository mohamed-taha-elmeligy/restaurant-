# Restaurant Management System

A command-line restaurant management app built entirely with pure Java and OOP principles — no frameworks, no databases, just clean object-oriented design.

I built this to sharpen my understanding of how OOP concepts work in practice before jumping into Spring Boot. Every decision in the codebase is intentional: from the inheritance hierarchy to the generic repository pattern.

---

## What it does

Manages the core operations of a restaurant:

- Add and manage customers, waiters, and tables
- Create and track reservations
- Build menus with categories and items
- Process orders and assign them to tables
- Generate bills with automatic total calculation

All through an interactive command-line interface.

---

## OOP concepts applied

### Generic Repository Pattern
One repository class handles all entity types — no duplication:

```java
public class Repository<K, T extends Model> implements CrudOperation<K, T> {
    private final ConcurrentMap<K, T> database;
    // One implementation, works for Customer, Waiter, Table, etc.
}
```

### Inheritance Hierarchy
```
Model (Abstract)
└── Person (Abstract)
    ├── Customer
    └── Waiter
```

ID management lives in `Model` once — every entity inherits it automatically.

### Polymorphic CLI
```java
CliOperations<Integer, ?> cliOperations;
cliOperations = waiterCli;    // or customerCli, or tableCli
displayOption("Waiter", cliOperations); // same method, different behavior
```

### Interface Segregation
Read and write operations are split into separate interfaces:
```java
public interface CliOperations<K, T> extends ReadCli, WriteCli<K, T> {}
```

### Thread-Safe Data Structures
```java
private final ConcurrentMap<K, T> database;         // Thread-safe map
private static final AtomicInteger baseId = ...;    // Thread-safe ID counter
```

---

## Project Structure

```
src/main/java/com/emts/
├── Main.java
├── domain/
│   ├── Restaurant.java        # Facade & orchestrator
│   ├── models/                # 10+ domain entities
│   ├── repositories/          # Generic + specific repositories
│   └── cli/                   # CLI handlers per entity
├── enums/
├── exception/                 # 12 custom exception types
└── util/                      # Console I/O, validation, interfaces
```

---

## Running it

**Requirements:** Java 21, Maven 3.6+

```bash
git clone <repo-url>
cd restaurant_oop
mvn clean compile
mvn exec:java -Dexec.mainClass="com.emts.Main"
```

---

## What I'd do differently now

- Replace in-memory `ConcurrentHashMap` with a real database
- Add a Spring Boot layer on top (the Repository pattern makes this straightforward)
- Write proper unit tests with mock repositories

---

Built by [Mohamed Taha](https://github.com/mohamed-taha-elmeligy) — open to internship and junior backend opportunities.
