# Quick Reference Card - Taiga-Jenkins Integration Demo

## 📋 Project Overview
- **Project Name**: Taiga-Jenkins Integration Demo
- **Purpose**: Demonstrate complete workflow from Taiga task tracking → GitHub code → Jenkins CI/CD
- **Technology Stack**: Java 11, Maven, JUnit 4, Jenkins, Taiga, GitHub

---

## 📁 Project Structure
```
taiga-jenkins-demo/
├── src/
│   ├── main/java/com/asu/ser516/
│   │   ├── Calculator.java          (TG-1: Arithmetic operations)
│   │   └── StringUtils.java         (TG-2: String utilities)
│   └── test/java/com/asu/ser516/
│       ├── CalculatorTest.java      (TG-3: 10 test cases)
│       └── StringUtilsTest.java     (TG-4: 12 test cases)
├── pom.xml                           (Maven configuration)
├── Jenkinsfile                       (TG-5: CI/CD pipeline)
├── README.md                         (Project overview)
├── SETUP_INSTRUCTIONS.md             (Step-by-step setup guide)
├── TAIGA_INTEGRATION.md              (Task mapping documentation)
└── .gitignore                        (Git ignore rules)
```

---

## 🎯 Taiga Tasks Created

| Task ID | Title | Status | Component |
|---------|-------|--------|-----------|
| TG-1 | Implement Calculator Class | Ready for Testing | Calculator.java |
| TG-2 | Implement String Utilities | Ready for Testing | StringUtils.java |
| TG-3 | Write Unit Tests for Calculator | In Progress | CalculatorTest.java |
| TG-4 | Write Unit Tests for String Utilities | In Progress | StringUtilsTest.java |
| TG-5 | Configure Jenkins Build Pipeline | To Do | Jenkinsfile |

---

## 🧪 Test Coverage

### Calculator Tests (10 tests)
- ✅ Addition (positive numbers)
- ✅ Addition (negative numbers)
- ✅ Subtraction
- ✅ Multiplication
- ✅ Division
- ✅ Division by zero (exception)
- ✅ Power calculation
- ✅ Square root
- ✅ Square root negative (exception)
- ✅ Chained operations

### StringUtils Tests (12 tests)
- ✅ String reversal
- ✅ Reverse null input
- ✅ Palindrome detection
- ✅ Palindrome null input
- ✅ Vowel counting
- ✅ Vowel count null input
- ✅ Word capitalization
- ✅ Capitalize empty string
- ✅ Capitalize null input
- ✅ Word counting
- ✅ Word count null input
- ✅ Multiple spaces handling

**Total: 22 tests, 0 failures expected**

---

## 🚀 Jenkins Pipeline Stages

1. **Checkout** - Pull code from GitHub
2. **Build** - Compile Java code (`mvn clean compile`)
3. **Test** - Run JUnit tests (`mvn test`)
4. **Package** - Create JAR file (`mvn package`)
5. **Archive** - Store artifacts for download

---

## 📝 Quick Commands

### Local Testing
```bash
# Compile code
mvn compile

# Run all tests
mvn test

# Run specific test
mvn test -Dtest=CalculatorTest

# Build JAR
mvn package

# Clean build
mvn clean
```

### Git Commands
```bash
# Initialize and push to GitHub
git init
git add .
git commit -m "Initial commit: TG-1 through TG-5"
git remote add origin https://github.com/YOUR_USERNAME/taiga-jenkins-demo.git
git branch -M main
git push -u origin main
```

### Making Changes
```bash
# Make code changes
# Stage and commit with Taiga reference
git add .
git commit -m "TG-X: Description of change"
git push
```

---

## 🔗 Integration Flow

```
Developer → Taiga Task Created
              ↓
          Code Changes
              ↓
       Commit with TG-X reference
              ↓
          Push to GitHub
              ↓
       Jenkins detects change
              ↓
      Jenkins runs pipeline
              ↓
         Tests execute
              ↓
      Results reported
              ↓
    Update Taiga task status
```

---

## ✅ Success Criteria

A successful demonstration shows:
1. ✅ Taiga project with 5 tasks created
2. ✅ GitHub repository with all code
3. ✅ Jenkins pipeline configured and running
4. ✅ All 22 tests passing
5. ✅ JAR artifact created
6. ✅ Clear traceability: Task → Code → Test → Build
7. ✅ Console output shows Taiga references

---

## 🎬 Demo Script

### Part 1: Show Taiga (2 minutes)
1. Open Taiga project
2. Show user story
3. Show all 5 tasks with descriptions
4. Explain task mapping to code

### Part 2: Show GitHub (2 minutes)
1. Open repository
2. Show project structure
3. Show code files
4. Show commit messages with TG-X references
5. Show Jenkinsfile

### Part 3: Show Jenkins (5 minutes)
1. Open Jenkins job
2. Click "Build Now"
3. Show build stages progressing
4. Show console output with Taiga references
5. Show test results (22 passed)
6. Show archived JAR artifact

### Part 4: Explain Integration (3 minutes)
1. Explain how Taiga tasks map to code
2. Show how commits reference tasks
3. Explain Jenkins automation
4. Show how to update Taiga from Jenkins results

---

## 🐛 Common Issues & Solutions

| Issue | Solution |
|-------|----------|
| Maven not found | Configure in Jenkins Global Tool Configuration |
| Tests fail | Run `mvn test` locally first to debug |
| Git repo not found | Check URL and credentials |
| Build hangs | Check Java version (needs 11+) |

---

## 📊 Expected Jenkins Output

```
Stages:
✅ Checkout      [SUCCESS]
✅ Build         [SUCCESS]
✅ Test          [SUCCESS]
✅ Package       [SUCCESS]
✅ Archive       [SUCCESS]

Test Results:
✅ CalculatorTest: 10/10 passed
✅ StringUtilsTest: 12/12 passed
Total: 22 tests, 0 failures

Artifacts:
✅ taiga-jenkins-demo-1.0-SNAPSHOT.jar (Created)
```

---

## 🎓 Learning Outcomes

Students will learn:
1. Project management with Taiga
2. Version control with Git/GitHub
3. Continuous integration with Jenkins
4. Automated testing with JUnit
5. Maven build lifecycle
6. Task-to-code traceability
7. Professional development workflow

---

## 📞 Support Files

- **SETUP_INSTRUCTIONS.md** - Complete step-by-step setup guide
- **TAIGA_INTEGRATION.md** - Detailed task mapping and integration guide
- **README.md** - Project overview and basic usage
- **This file** - Quick reference for demo

---

## ✨ Key Points to Emphasize

1. **Traceability**: Every code change links to a Taiga task
2. **Automation**: Jenkins automatically builds and tests
3. **Quality**: 22 automated tests ensure code quality
4. **Integration**: Three tools working together seamlessly
5. **Professional Workflow**: Industry-standard practices

---

**Good luck with your demonstration! 🚀**
