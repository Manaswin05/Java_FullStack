# ☕ JavaCore - Java Concepts & JDBC Practice Repository

<div align="center">

![Java](https://img.shields.io/badge/Java-17+-orange)
![JDBC](https://img.shields.io/badge/JDBC-Database-blue)
![Eclipse](https://img.shields.io/badge/IDE-Eclipse-purple)
![SQL](https://img.shields.io/badge/Database-SQL-green)
![Status](https://img.shields.io/badge/Status-Active-success)

A complete collection of Java core concepts, hands-on examples, JDBC implementations, and practice modules designed to strengthen Java development skills.

</div>

---

## 📖 About This Repository

This repository contains practical implementations and exercises covering various Java concepts and technologies. The purpose of this project is to explore Java fundamentals while building real-world coding practices.

The project includes:

- Core Java concepts
- JDBC database connectivity
- Collection Framework
- Data structures
- File handling
- Multithreading
- DAO pattern
- Services and business logic
- SQL integration
- Object-oriented programming concepts

---

## 📂 Project Structure

```text
src/
│
├── com.mmcoe.Action
├── com.mmcoe.bank
├── com.mmcoe.cart
├── com.mmcoe.collectionAPI
├── com.mmcoe.dao
├── com.mmcoe.datastructure
├── com.mmcoe.fileIO
├── com.mmcoe.jdbc
├── com.mmcoe.multiThreading
├── com.mmcoe.personTree
├── com.mmcoe.pojo
├── com.mmcoe.service
├── com.mmcoe.stock
│
├── SQL
├── streams
│
├── resources
│   ├── msgs_en.properties
│   ├── msgs_fr.properties
│   └── msgs_hi.properties
```

---

## 🚀 Features

✨ JDBC Database Connectivity  
✨ ResultSet and ResultSetMetaData examples  
✨ SQL query execution  
✨ Java Collection Framework examples  
✨ Multithreading implementations  
✨ File input/output operations  
✨ DAO architecture pattern  
✨ Object-oriented design principles  
✨ Resource bundle and localization support  

---

## 🛠 Technologies Used

| Technology | Purpose |
|------------|----------|
| Java | Core application development |
| JDBC | Database connectivity |
| SQL | Database operations |
| Eclipse IDE | Development environment |
| Collections API | Data handling |
| Multithreading | Concurrent execution |

---

## 💻 Sample JDBC Example

```java
String sql = "select * from cust";

Connection conn = JdbcFactory.getConnection();
Statement stmt = conn.createStatement();

ResultSet rs = stmt.executeQuery(sql);

while(rs.next()){
    System.out.println(rs.getString("cid"));
}
```

---

## ▶️ How to Run

### Clone the repository

```bash
git clone https://github.com/yourusername/JavaCore.git
```

### Open in Eclipse

1. Open Eclipse IDE
2. Click **File → Import**
3. Select **Existing Projects into Workspace**
4. Choose the cloned repository
5. Run the desired Java class

---

## 🎯 Learning Objectives

This repository was built to:

- Understand Java fundamentals
- Practice database connectivity using JDBC
- Improve problem-solving skills
- Learn software design patterns
- Explore real-world Java application structure

---

## 📸 Project Preview

Current implementation includes JDBC query execution with metadata handling and dynamic result processing.

Example output:

```text
Connection Successful

3
1,
Polo,
```

---

## 🔮 Future Improvements

- Add Maven support
- Add JUnit test cases
- Add logging framework
- Add Spring Boot modules
- Improve project documentation
- Add REST API examples

---

## 🤝 Contributions

Contributions, suggestions, and improvements are welcome.

1. Fork this repository
2. Create your feature branch

```bash
git checkout -b feature/new-feature
```

3. Commit changes

```bash
git commit -m "Added new feature"
```

4. Push branch

```bash
git push origin feature/new-feature
```

5. Open a Pull Request

---

## 📜 License

This project is open-source and available under the MIT License.

---

<div align="center">

Made with ❤️ and Java

Keep learning. Keep building. Keep coding.

</div>
