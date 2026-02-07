# Restaurant Management System - OOP Project

A comprehensive command-line restaurant management system built with **Object-Oriented Programming (OOP)** principles using Java. This application provides complete management capabilities for a restaurant including tables, waiters, customers, reservations, menu items, orders, and billing.

![Java](https://img.shields.io/badge/Java-21-blue)
![Maven](https://img.shields.io/badge/Maven-3.6%2B-blue)
![License](https://img.shields.io/badge/License-Open%20Source-green)
![Status](https://img.shields.io/badge/Status-Active-success)

## Project Overview

This is a full-featured restaurant management application that demonstrates solid OOP design patterns including:
- **Encapsulation** - Through model classes with private attributes and getter/setter methods
- **Inheritance** - Base classes for common functionality (Model, Person)
- **Polymorphism** - Repository pattern and CLI operations
- **Abstraction** - Generic repositories and utility interfaces

### Key Features

- 📊 **Table Management** - Track table status and assignments
- 👥 **Staff Management** - Manage waiters and their assignments
- 🍽️ **Menu Management** - Create and organize menu items and menu groups
- 🛏️ **Customer Management** - Track customer information and history
- 📅 **Reservations** - Book and manage table reservations
- 🛒 **Order Management** - Create and process orders with order items
- 💳 **Billing System** - Generate and manage bills with automatic calculations

## Technology Stack

- **Language:** Java 21
- **Build Tool:** Maven 3.6+
- **Architecture:** Layered Architecture with Repository Pattern
- **Design Patterns:** Singleton, Factory, Repository, CLI Operations

## Project Structure

```
restaurant_oop/
├── src/
│   ├── main/
│   │   └── java/com/emts/
│   │       ├── domain/
│   │       │   ├── cli/                  # CLI command handlers
│   │       │   │   ├── BillCli.java
│   │       │   │   ├── CustomerCli.java
│   │       │   │   ├── OrderCli.java
│   │       │   │   ├── MenuCli.java
│   │       │   │   ├── TableCli.java
│   │       │   │   ├── WaiterCli.java
│   │       │   │   └── ...
│   │       │   ├── models/               # Domain entities
│   │       │   │   ├── common/
│   │       │   │   │   ├── Model.java    # Base model class
│   │       │   │   │   └── Person.java   # Base person class
│   │       │   │   ├── Bill.java
│   │       │   │   ├── Customer.java
│   │       │   │   ├── MenuItem.java
│   │       │   │   ├── Order.java
│   │       │   │   ├── OrderItem.java
│   │       │   │   ├── Reservation.java
│   │       │   │   ├── Table.java
│   │       │   │   ├── Waiter.java
│   │       │   │   └── ...
│   │       │   ├── repositories/         # Data access layer
│   │       │   │   ├── common/
│   │       │   │   │   └── Repository.java
│   │       │   │   ├── BillRepository.java
│   │       │   │   ├── CustomerRepository.java
│   │       │   │   ├── OrderRepository.java
│   │       │   │   ├── TableRepository.java
│   │       │   │   ├── WaiterRepository.java
│   │       │   │   └── ...
│   │       │   └── Restaurant.java       # Main orchestrator
│   │       ├── enums/
│   │       │   └── TableStatus.java      # Table status enumeration
│   │       ├── exception/                # Custom exceptions
│   │       │   ├── common/
│   │       │   │   └── RestaurantException.java
│   │       │   ├── BillException.java
│   │       │   ├── CustomerException.java
│   │       │   ├── OrderException.java
│   │       │   └── ...
│   │       ├── util/
│   │       │   ├── cli/
│   │       │   │   ├── CliOperations.java
│   │       │   │   ├── ReadCli.java
│   │       │   │   └── WriteCli.java
│   │       │   ├── crud/
│   │       │   │   ├── ReadRepository.java
│   │       │   │   └── WriteRepository.java
│   │       │   ├── Console.java          # Console I/O utilities
│   │       │   ├── PhoneNumber.java      # Phone validation
│   │       │   └── Printable.java        # Print interface
│   │       └── Main.java                 # Application entry point
│   └── resources/                        # Application resources
└── pom.xml                               # Maven configuration
```

## Domain Models

### Core Entities

#### **Model** (Base Class)
- Abstract base class for all entities
- Provides ID management and common functionality

#### **Person** (Abstract Base Class)
- Extends Model
- Represents people in the system (Customer, Waiter)
- Contains common person attributes

#### **Customer**
- Manages customer information
- Tracks phone numbers and contact details
- Links to reservations and orders

#### **Waiter**
- Staff member information
- Manages assigned tables
- Tracks served orders

#### **Table**
- Restaurant table representation
- Has status (AVAILABLE, OCCUPIED, RESERVED)
- Tracks capacity and current customers

#### **Reservation**
- Booking management
- Links customers to tables
- Manages reservation dates and times

#### **MenuItem**
- Individual food/beverage items
- Contains pricing and descriptions
- Grouped by category (GroupMenuItem)

#### **Menu**
- Collection of menu items
- Organizes items into dining experience (breakfast, lunch, etc.)

#### **Order**
- Customer order container
- Links to table and waiter
- Contains multiple order items

#### **OrderItem**
- Individual items in an order
- References menu items
- Tracks quantity and special requests

#### **Bill**
- Invoice for orders
- Automatic total calculation
- Manages payment status

### Enumerations

#### **TableStatus**
- `AVAILABLE` - Table ready for customers
- `OCCUPIED` - Currently serving customers
- `RESERVED` - Pre-booked table

## Repository Pattern

The application implements the Repository Pattern for data access:

```java
// Base repository interface
public interface Repository<ID, T> { }

// Specific repositories implement CRUD operations
// - ReadRepository
// - WriteRepository
// - Full Repository combining both
```

All repositories use `ConcurrentHashMap` for thread-safe in-memory storage.

## Exception Handling

Custom exception hierarchy for domain-specific error handling:

```
RestaurantException (Base)
├── BillException
├── CustomerException
├── OrderException
├── TableException
├── WaiterException
├── ReservationException
├── MenuException
├── MenuItemException
└── PhoneNumberException
```

## Getting Started

### Prerequisites

- Java 21 or higher
- Maven 3.6 or higher

### Installation

1. **Clone the repository:**
```bash
git clone <repository-url>
cd restaurant_oop
```

2. **Build the project:**
```bash
mvn clean compile
```

3. **Package the application:**
```bash
mvn package
```

### Running the Application

**Using Maven:**
```bash
mvn exec:java -Dexec.mainClass="com.emts.Main"
```

**Using Java directly:**
```bash
java -jar target/restaurant_oop-1.0-SNAPSHOT.jar
```

**Or compile and run:**
```bash
javac -d target/classes src/main/java/com/emts/**/*.java
java -cp target/classes com.emts.Main
```

## Usage Guide

### Main Menu

Upon launching, the application presents the following options:

```
1-  Table Page
2-  Waiter Page
3-  Customer Page
4-  Reservation Page
5-  MenuItem Page
6-  Menu Page
7-  GroupMenuItem Page
8-  OrderItem Page
9-  Order Page
10- Bill Page
11- Exit
```

### Operations Available

Each module supports **CRUD operations**:

- **Create (Add)** - Add new items to the system
- **Read (View)** - Display all or specific items
- **Update (Edit)** - Modify existing items
- **Delete (Remove)** - Remove items from the system
- **List** - View all items with pagination

### Example Workflows

#### Creating a Reservation
1. Select Option 4 (Reservation Page)
2. Choose "Add Reservation"
3. Select customer from existing customers
4. Select available table
5. Enter reservation date and time
6. Confirm reservation

#### Processing an Order
1. Select Option 9 (Order Page)
2. Choose "Add Order"
3. Select table and waiter
4. Add order items from menu
5. Adjust quantities as needed
6. Complete order

#### Generating a Bill
1. Select Option 10 (Bill Page)
2. Choose "Create Bill"
3. Select completed order
4. System automatically calculates total
5. View and print bill

## Key Classes & Responsibilities

### Restaurant.java
Central orchestrator that manages all CLI modules and user interactions. Implements the Facade pattern to simplify access to subsystems.

### CliOperations Interface
Generic interface for CRUD operations:
```java
public interface CliOperations<ID, T> {
    void create();
    void read();
    void update();
    void delete();
    void list();
}
```

### Console.java
Utility class for console input/output operations:
- `print()` - Display messages
- `intIn()` - Read integer input
- `stringIn()` - Read string input
- `line()` - Display separator line

### PhoneNumber.java
Validates and formats phone numbers using regex patterns and custom validation logic.

## OOP Principles Demonstrated

### 1. **Encapsulation**
```java
private String name;
private String email;

public String getName() { return name; }
public void setName(String name) { this.name = name; }
```

### 2. **Inheritance**
```java
public abstract class Model {
    protected int id;
}

public abstract class Person extends Model {
    protected String name;
    protected String email;
}

public class Customer extends Person { }
public class Waiter extends Person { }
```

### 3. **Polymorphism**
```java
private CliOperations<Integer, ?> cliOperations;

public void showWaiters() {
    this.cliOperations = this.waiterCli;  // Polymorphic assignment
}
```

### 4. **Abstraction**
```java
public abstract class Repository<ID, T> {
    // Abstract methods implemented by subclasses
}
```

## Configuration

### pom.xml Properties

```xml
<maven.compiler.source>21</maven.compiler.source>
<maven.compiler.target>21</maven.compiler.target>
<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
```

## Error Handling

The system uses try-catch blocks and custom exceptions:

```java
try {
    // Business logic
} catch (CustomerException e) {
    Console.print("Error: " + e.getMessage());
} catch (RestaurantException e) {
    Console.print("System error: " + e.getMessage());
}
```

## Data Storage

- **In-Memory:** Uses `ConcurrentHashMap` for thread-safe data storage
- **No Persistence:** Data is lost when application exits
- **Scalability:** Can be extended with database implementation

### Future Enhancement: Database Integration
```java
// Repository interface allows easy migration to database
public interface Repository<ID, T> {
    // Implement with database operations
    T findById(ID id);
    List<T> findAll();
    // etc.
}
```

## Testing

Unit tests are located in `src/test/java` (extendable):

```bash
mvn test
```

## Best Practices Implemented

- ✅ Single Responsibility Principle (SRP)
- ✅ Open/Closed Principle (OCP)
- ✅ Liskov Substitution Principle (LSP)
- ✅ Interface Segregation Principle (ISP)
- ✅ Dependency Inversion Principle (DIP)
- ✅ DRY (Don't Repeat Yourself)
- ✅ YAGNI (You Aren't Gonna Need It)

## Performance Considerations

- **ConcurrentHashMap:** Thread-safe concurrent access
- **Lazy Loading:** Entities loaded on demand
- **Memory Efficient:** Hash-based lookups O(1) average case

## Troubleshooting

### Issue: "Main class not found"
**Solution:** Ensure Java files are compiled:
```bash
mvn clean compile
```

### Issue: "Input not being read correctly"
**Solution:** Check if Scanner is properly initialized in Console.java

### Issue: "Entity not found"
**Solution:** Verify entity was created before attempting operations

## Future Enhancements

- [ ] Database persistence (MySQL, PostgreSQL)
- [ ] REST API layer
- [ ] GUI interface using JavaFX or Swing
- [ ] Authentication and authorization
- [ ] Report generation (PDF, Excel)
- [ ] Multi-language support
- [ ] Discount and promotional codes
- [ ] Inventory management
- [ ] Payment processing integration
- [ ] Analytics and reporting dashboard

## Contributing

To contribute to this project:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## Code Style Guide

- Follow Java naming conventions (camelCase for variables/methods, PascalCase for classes)
- Use meaningful variable names
- Keep methods small and focused (Single Responsibility)
- Comment complex logic
- Use meaningful commit messages

## License

This project is open source and available under the [MIT License](LICENSE).

## Author

**EMTS Development Team**
- Email: dev@emts.com
- Package: com.emts

## Acknowledgments

- Inspired by real-world restaurant management needs
- Built following OOP best practices and design patterns
- Community feedback and contributions welcome

## Support

For questions or issues:
1. Check existing GitHub issues
2. Create a new issue with detailed description
3. Include error logs and steps to reproduce
4. Contact development team

---

**Version:** 1.0-SNAPSHOT  
**Last Updated:** February 2024  
**Status:** Active Development
