# Spring Framework Basics: `@ComponentScan` and Bean Scopes

This document explains the use of the `@ComponentScan` annotation and the different Spring Bean scopes, helping you understand how Spring manages bean creation, lifecycle, and dependency injection.

---

## `@ComponentScan` Annotation

### What is it?

`@ComponentScan` tells Spring where to look for components (classes annotated with `@Component`, `@Service`, `@Repository`, `@Controller`, etc.) to automatically detect and register them as beans in the Spring application context.

### Why use it?

Without `@ComponentScan`, you must manually declare all beans in configuration files. This annotation simplifies configuration by automatically scanning specified packages and registering beans.

### Usage Example

```java
@Configuration
@ComponentScan(basePackages = "com.example.myapp")
public class AppConfig {
    // Spring will scan the 'com.example.myapp' package and sub-packages for beans
}

♻️ Bean @Scope
The @Scope annotation defines the lifecycle and visibility of a bean in the Spring container.

🔄 Common Scopes

| Scope       | Description                                      |
| ----------- | ------------------------------------------------ |
| `singleton` | (Default) A single instance per Spring container |
| `prototype` | A new instance every time the bean is requested  |
| `request`   | One instance per HTTP request (Web only)         |
| `session`   | One instance per HTTP session (Web only)         |
