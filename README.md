# 🍽️ Restaurant Management System – OOP Edition

> A comprehensive restaurant management system built in Java using Object-Oriented Programming (OOP) principles.

![Java](https://img.shields.io/badge/Java-21-blue)
![Maven](https://img.shields.io/badge/Maven-3.6%2B-blue)
![License](https://img.shields.io/badge/License-Open%20Source-green)
![Status](https://img.shields.io/badge/Status-Active-success)

---

## 📋 Table of Contents

* [About the Project](#about-the-project)
* [Features](#features)
* [Requirements](#requirements)
* [Installation & Setup](#installation--setup)
* [How to Use](#how-to-use)
* [Project Structure](#project-structure)
* [Core Entities](#core-entities)
* [Error Handling](#error-handling)
* [Contributing](#contributing)
* [License](#license)

---

## 🎯 About the Project

**Restaurant Management System** is an advanced Java application for managing restaurant operations.
The project is built using core **Object-Oriented Programming (OOP)** principles, including:

* **Abstraction**: Abstract base classes for shared behaviors
* **Encapsulation**: Hiding internal details and exposing controlled interfaces
* **Inheritance**: Reusing common properties between entities
* **Polymorphism**: Using interfaces and overridden implementations

> ⚠️ This project is intended for **learning and practice purposes**.

---

## ✨ Key Features

### 👥 Employee & Customer Management

* Manage waiters and customers
* Phone number validation
* Contact information tracking

### 🍽️ Table Management

* Create and update table status
* Track table availability
* Table states (AVAILABLE / OCCUPIED / RESERVED)

### 📅 Reservation Management

* Create reservations
* Manage date and time
* Link reservations to customers and tables

### 🍕 Menu Management

* Manage menu items
* Group menu items
* Multiple menus support
* Prices and descriptions

### 📝 Order Management

* Create orders
* Add order items
* Track order status
* Calculate totals and taxes

### 💳 Billing

* Generate bills
* Calculate subtotal, tax, and total
* Payment handling

### 🛡️ Advanced Error Handling

* Custom exceptions for each domain
* Data validation
* Clear error messages

---

## 📋 Requirements

### Environment & Tools

* **Java Development Kit (JDK)**: 21 or higher
* **Maven**: 3.6.0+
* **IDE (Recommended)**:

  * IntelliJ IDEA
  * Eclipse
  * VS Code (Java Extensions)

### System Requirements

* OS: Windows / macOS / Linux
* RAM: 4GB minimum
* Storage: 500MB

---

## 🚀 Installation & Setup

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/yourusername/restaurant-oop.git
cd restaurant-oop
```

### 2️⃣ Install Dependencies

```bash
mvn clean install
```

### 3️⃣ Build the Project

```bash
mvn clean package
```

### 4️⃣ Run the Application

```bash
mvn exec:java -Dexec.mainClass="com.emts.Main"
```

Or:

```bash
java -cp target/restaurant_oop-1.0-SNAPSHOT.jar com.emts.Main
```

---

## 💻 How to Use

### Main Menu

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

Choose:
```

### Example: Add a New Table

```
Enter Table Number: 5
Enter Table Capacity: 4
✓ Table added successfully!
```

### Example: Create an Order

1. Select **Order Page**
2. Create a new order
3. Choose table, customer, waiter
4. Add menu items

---

## 📁 Project Structure

```
restaurant-oop/
├── pom.xml
├── src/main/java/com/emts/
│   ├── Main.java
│   ├── domain/
│   │   ├── models/
│   │   ├── repositories/
│   │   └── cli/
│   ├── exception/
│   ├── enums/
│   └── util/
└── src/test/
```

---

## 🔧 Core Entities

### Model

```java
public abstract class Model implements Printable {
    protected long id;
}
```

### Person

```java
public abstract class Person extends Model {
    protected String name;
    protected String phoneNumber;
}
```

### Table

```java
public class Table extends Model {
    private int tableNumber;
    private int capacity;
    private TableStatus status;
}
```

### Order

```java
public class Order extends Model {
    private long tableId;
    private long customerId;
    private long waiterId;
    private List<OrderItem> orderItems;
    private double total;
}
```

### Bill

```java
public class Bill extends Model {
    private long orderId;
    private double subtotal;
    private double tax;
    private double total;
}
```

---

## 🛡️ Error Handling

* Custom exceptions for each entity
* Input validation
* Clear and descriptive error messages

```java
try {
    tableRepository.create(newTable);
} catch (TableException e) {
    Console.print("Error: " + e.getMessage());
}
```

---

## 🎓 Applied OOP Principles

* **Encapsulation**: Private fields with getters/setters
* **Inheritance**: Shared base classes
* **Polymorphism**: Repository interfaces
* **Abstraction**: Abstract classes and contracts

---

## 🧪 Testing

```bash
mvn test
```

---

## 🤝 Contributing

1. Fork the repository
2. Create a new branch
3. Commit your changes
4. Open a Pull Request

---

## 📜 License

This project is licensed under the **MIT License**.

---

## 👨‍💻 Author

**Mohamed Taha Elmeligy**

* GitHub: [@mohamed-taha-elmeligy](https://github.com/mohamed-taha-elmeligy)

---

## ⭐ Support

If you like this project, don’t forget to give it a ⭐ on GitHub.
