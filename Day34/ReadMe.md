# 🏷️ Custom Annotation in Spring Boot

This project demonstrates how to create and use **custom annotations** in a Spring Boot application using **Spring AOP**. It allows you to modularize cross-cutting concerns like logging, validation, or authorization in a clean and reusable way.

## 🚀 What This Project Shows

- How to define a custom annotation (e.g., `@LogExecutionTime`)
- How to handle it using Spring AOP
- How to apply it to controller or service methods

## 🛠️ Technologies Used

- Java 23
- Spring Boot
- Spring AOP
- Maven

## 🧩 How It Works

### 1. Create Custom Annotation

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogExecutionTime {
}
