# 🚀 API Test Automation Suite

This project is an **API Automation Framework** built using **Java, Maven, and REST Assured**, and executed via **Jenkins CI/CD pipeline**.

---

## 📌 Project Overview

This framework performs end-to-end API testing including:

- 🔹 GET requests
- 🔹 POST (Create)
- 🔹 PUT (Update)
- 🔹 DELETE operations
- 🔹 Retry mechanism for API failures
- 🔹 Test data generation
- 🔹 CI/CD execution using Jenkins

---

## ⚙️ Tech Stack

- Java
- Maven
- REST Assured
- Jenkins
- Git
- GitHub / GitLab
- SLF4J (Logging)

---

## 🧪 Test Coverage

The framework includes:

### ✅ Employee APIs
- Fetch all employees
- Fetch specific employee
- Create employee
- Update employee
- Delete employee

---

## 🔄 CI/CD Pipeline (Jenkins)

The pipeline executes the following stages:

### 1️⃣ Clean Workspace
- Deletes previous build files to ensure a fresh run

### 2️⃣ Checkout Code
- Clones repository from:https://github.com/shubhamsadgure/api-automation-project.git

### 3️⃣ Run Tests
- Executes Maven command:
```bash
mvn clean test

📂 Project Structure

API-Build/
│
├── src/
│   ├── main/
│   └── test/
│       └── java/
│           └── Dummy_API_Test_Cases/
│
├── target/
│   └── surefire-reports/
│
├── pom.xml
└── Jenkinsfile

📈 Reports
target/surefire-reports/

🚀 How to Run Locally
git clone https://github.com/shubhamsadgure/api-automation-project.git
cd api-automation-project
mvn clean test

Author
Developed by: Shubham Sadgure

