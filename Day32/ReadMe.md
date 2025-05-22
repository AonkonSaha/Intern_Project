# 🏥 Appointment Booking System – Secure & SOLID Design

This project implements a secure and maintainable Appointment Booking System built using Spring Boot, Thymeleaf, Spring Security, and MySQL. It follows clean architecture principles with strong attention to web security best practices.

---

## ✅ SOLID Principles Applied

The system is designed with SOLID principles to ensure clean, modular, and extensible code:

- **S – Single Responsibility:**  
  Each class (e.g., `AppointmentService`, `DoctorController`) handles one clearly defined responsibility.

- **O – Open/Closed:**  
  The application supports extensions (e.g., new appointment rules) without changing existing logic.

- **L – Liskov Substitution:**  
  Interfaces allow for easy swapping of implementations (e.g., notification services).

- **I – Interface Segregation:**  
  Clients are exposed only to the methods they need.

- **D – Dependency Inversion:**  
  High-level components depend on abstractions, enabling easy testing and maintenance.

---

## 🔐 Security Practices Implemented

### 🛡️ CSRF (Cross-Site Request Forgery)

- **Risk:** Attackers trick logged-in users into submitting malicious requests.
- **Prevention:**  
  - Enabled by default via Spring Security.  
  - Hidden CSRF tokens included in all forms:

    ```html
    <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />
    ```

---

### 🛡️ XSS (Cross-Site Scripting)

- **Risk:** Attackers inject malicious scripts into web pages.
- **Prevention:**  
  - Auto-escaped output using Thymeleaf's `th:text`.
  - Enforced **Content Security Policy (CSP)** headers:
    ```java
    response.setHeader("Content-Security-Policy", "script-src 'self'");
    ```

---

### 🛡️ CORS (Cross-Origin Resource Sharing)

- **Risk:** Unauthorized third-party domains accessing backend APIs.
- **Prevention:**
  - CORS configured to allow only trusted origins:
    ```java
    registry.addMapping("/api/**")
            .allowedOrigins("https://your-client.com")
            .allowedMethods("GET", "POST");
    ```

---

### 🛡️ SQL Injection

- **Risk:** User input manipulating raw SQL queries.
- **Prevention:**
  - All database operations use **Spring Data JPA** and **Prepared Statements**.
  - Example:
    ```java
    User findByUsername(String username);
    ```

---

## 🔐 Session-Based Authentication & Authorization

- **Authentication:**
  - On login, a secure HTTP session is created.
  - A session ID is stored in a secure, HttpOnly cookie.

- **Authorization:**
  - Role-based access using annotations like:
    ```java
    @PreAuthorize("hasRole('ADMIN')")
    ```
  - Session is invalidated on logout.

- **Best Practices Used:**
  - `HttpOnly`, `Secure`, and `SameSite` cookie flags.
  - CSRF protection on all state-changing requests.
  - URL and method-level security rules.

---

## 📌 Technologies Used

- Spring Boot
- Spring Security
- Thymeleaf
- Spring Data JPA
- MySQL
- HTML, CSS, JavaScript

---


