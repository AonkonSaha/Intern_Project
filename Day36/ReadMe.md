## 📘 Quick Summary of Concepts

### 🔤 String Immutability & String Pool
- **Immutable Strings**: Once created, a `String` cannot be changed.
- **String Pool**: Java reuses string literals to save memory and improve performance.
- Ideal for constants and keys in collections like `HashMap`.

---

### 🧵 StringBuilder
- A **mutable** class for building and modifying strings.
- **Not thread-safe** but faster in single-threaded operations.
- Great for loops or dynamic string generation.

---

### 🧵 StringBuffer
- Similar to `StringBuilder` but **thread-safe** due to synchronization.
- Suitable for multi-threaded environments where string data is shared.

---

### 🌊 Java Stream API
- Functional-style programming for **processing collections**.
- Supports operations like `filter()`, `map()`, `reduce()`, and `collect()`.
- Promotes cleaner, more declarative code.

---

### 🧪 JUnit 5
- Modern **testing framework** for Java.
- Uses annotations like `@Test`, `@BeforeEach`, `@AfterEach` for test structure.
- Enables reliable unit testing with clear assertions.

---

### 🔧 Mockito
- A **mocking library** for creating fake objects in tests.
- Helps isolate the code under test by simulating dependencies.
- Ideal for testing services without requiring real database or API calls.

---

📅 **Date Explored:** May 27, 2025  
🛠️ **Tools Used:** Java 17, Maven/Gradle, JUnit 5, Mockito

> This section provides a quick reference for the key Java concepts I explored today.
