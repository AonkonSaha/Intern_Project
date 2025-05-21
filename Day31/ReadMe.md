
# 🔧 Dependency Injection, Cyclic Dependencies, and Bean Ambiguity in Spring

This document provides a comprehensive overview of **Dependency Injection**, the **Cyclic Dependency problem**, and how to handle **multiple implementations of an interface** in Spring Framework.

---

## ✅ 1. Dependency Injection (DI) Principle

### 🔍 What is Dependency Injection?

**Dependency Injection (DI)** is a software design pattern that enables the **inversion of control (IoC)** by letting a framework (like Spring) inject an object's dependencies rather than having the object create them.

### ✅ Benefits of DI:
- ✔️ Loose coupling
- ✔️ Improved testability
- ✔️ Better maintainability
- ✔️ Compliance with SOLID principles (SRP, DIP)

### 📦 Example (Constructor Injection in Spring Boot):
```java
@Service
public class OrderService {
    private final PaymentService paymentService;

    @Autowired
    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void placeOrder() {
        paymentService.pay();
    }
}
```

---

## 🔄 2. Cyclic Dependency

### 🔍 What is a Cyclic Dependency?

A **cyclic dependency** occurs when two or more beans depend on each other **directly or indirectly**, forming a loop.

### ❗ Why is it a problem?

- ❌ Causes application startup failure
- ❌ Leads to `BeanCurrentlyInCreationException`
- ❌ Indicates poor architecture or tight coupling
- ❌ Risk of stack overflow and infinite loops

### 🔁 Example of Cyclic Dependency:
```java
@Component
class A {
    @Autowired B b;
}

@Component
class B {
    @Autowired A a;
}
```

---

## 🛠️ How to Resolve Cyclic Dependency

### 🔧 1. Refactor the Design
- Introduce **interfaces**, **abstract classes**, or a **mediator service**
- Apply design patterns like **Observer** or **Event-based communication**

### 🔧 2. Use Setter Injection
Delays the dependency assignment until after object construction.

```java
@Component
class A {
    private B b;

    @Autowired
    public void setB(B b) {
        this.b = b;
    }
}
```

### 🔧 3. Use `@Lazy` Annotation
Defers bean initialization until it's actually needed.

```java
@Component
class A {
    @Autowired
    public A(@Lazy B b) {
        this.b = b;
    }
}
```

---

## 🧭 3. Handling Multiple Implementations of an Interface

### ❓ The Problem:
When multiple beans implement the same interface, Spring throws a `NoUniqueBeanDefinitionException`.

### ✅ Solutions:

#### 🔸 1. Use `@Primary`
Marks one bean as the default option.

```java
@Service
@Primary
public class EmailNotificationService implements NotificationService { }
```

#### 🔸 2. Use `@Qualifier`
Specifies exactly which bean to inject.

```java
@Service("smsService")
public class SmsNotificationService implements NotificationService { }

@Autowired
@Qualifier("smsService")
private NotificationService notificationService;
```

#### 🔸 3. Use `@Profile`
Injects beans conditionally based on active environment profile (`dev`, `prod`, etc.)

```java
@Service
@Profile("dev")
public class DevNotificationService implements NotificationService { }

@Service
@Profile("prod")
public class ProdNotificationService implements NotificationService { }
```

---

## 📌 Summary Table

| Concept                     | Description                                      | Problem                          | Solution                                  |
|----------------------------|--------------------------------------------------|----------------------------------|-------------------------------------------|
| **Dependency Injection**    | Inject dependencies externally                  | Tight coupling                   | Use constructor or setter injection       |
| **Cyclic Dependency**       | Circular reference between beans                | Startup failure, stack overflow | Refactor, `@Lazy`, setter injection       |
| **Multiple Implementations**| More than one bean for an interface            | Ambiguous injection              | Use `@Primary`, `@Qualifier`, `@Profile`  |


