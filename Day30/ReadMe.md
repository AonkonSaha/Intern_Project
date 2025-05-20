<h1> Exploration of Dependency Injection, Inversion of Control, and Proxy Classes</h1>
<h3>
Objective:</br>
To gain a deeper understanding of the Dependency Injection principle and the Inversion of Control concept, and to explore the role of proxy classes within modern frameworks such as Spring.</br>

Key Concepts Explored:</br>
Inversion of Control (IoC):</br>

IoC is a design principle in which the control of object creation and dependency management is transferred from the application code to a container or framework.</br>

This approach promotes loose coupling, modularity, and testability.</br>

Dependency Injection (DI):</br>

DI is a technique used to supply an object with its required dependencies from the outside rather than creating them internally.</br>

Explored three main types:</br>

Constructor Injection</br>

Setter Injection</br>

Field Injection</br>

Interface Injection Insight:</br>

I learned that when injecting an interface type, the IoC container (such as Spring) automatically determines the appropriate implementation class at runtime based on the available bean definitions.</br>

This allows the system to remain flexible and decoupled, as the consuming class only relies on the interface contract, not the concrete implementation.</br>

This runtime resolution is possible through Spring’s internal bean factory and type-matching mechanisms.</br>

Benefits of DI and IoC:</br>

Encourages separation of concerns.</br>

Improves testability with mock dependencies.</br>

Enhances maintainability and flexibility by decoupling components.</br>

Proxy Classes:</br>

Proxy classes act as intermediaries between the client and the actual object.</br>

In Spring, proxy objects are often used to apply cross-cutting concerns like logging, security, and transaction management.</br>

Types:</br>

JDK Dynamic Proxy – used when working with interfaces.</br>

CGLIB Proxy – used when dealing with concrete classes.</br>

The use of proxies allows methods to be intercepted and behavior to be added without altering the core business logic.</br>

Practical Takeaways:</br>
Implemented and tested DI in a Spring Boot project using various injection techniques.</br>

Observed how Spring resolves interface-based dependencies at runtime and injects the correct implementation.</br>

Experimented with AOP configurations to see how proxy classes work in real applications.</br>


</h3>