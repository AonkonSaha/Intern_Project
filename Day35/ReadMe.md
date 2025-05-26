# 🐘 JPA N+1 Query Problem - Demo and Solutions

This project demonstrates the **N+1 query problem** in Spring Boot using JPA/Hibernate and explains **how to resolve** it with various techniques.

---

## 🧠 What is the N+1 Query Problem?

The N+1 query problem occurs when:
- JPA executes **1 query** to load the parent entities (e.g., `Post`)
- Then executes **N additional queries** to load the child entities (e.g., `Comment`) for each parent

### 🔥 Example (Triggers N+1 Problem)

```java
List<Post> posts = postRepository.findAll(); // 1 query
for (Post post : posts) {
    System.out.println(post.getComments().size()); // N queries for comments
}


###✅ Solution Approaches
##🟢 1. JPQL Fetch Join (Recommended)
Use JOIN FETCH in a custom query to load parent and child entities in a single SQL query.
#@Query("SELECT p FROM Post p JOIN FETCH p.comments")
#List<Post> findAllWithComments();
##✔ Efficient, minimizes queries
##❌ Less flexible if multiple nested relationships are involved

##@EntityGraph Annotation (Spring Data JPA)
Tells Spring to eagerly load specified associations using a single SQL join.

#@EntityGraph(attributePaths = "comments")
#@Query("SELECT p FROM Post p")
#List<Post> findAllWithComments();
##✔ Cleaner and declarative
##✔ Reusable across queries
##❌ Not as obvious as JOIN FETCH for SQL tuning

###🟠Hibernate Batch Fetching
##✔ Keeps associations lazy
##✔ Prevents multiple individual queries
##❌ Still sends multiple queries (batched), not always optimal

###🟣 Use DTO Projections (When Only Data Needed)
##✔ Efficient and lightweight
##❌ Not suitable when entity state is needed (e.g., for persistence)

###📌 Best Practice
#Use JOIN FETCH or @EntityGraph for most scenarios.
#Use batch fetching if you're forced to work with LAZY loading.
#Use DTO projections for read-only APIs that don’t need entity tracking.