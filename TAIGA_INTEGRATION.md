# Taiga Integration Guide

This document maps the code components to Taiga tasks and demonstrates the integration workflow.

## Taiga Project Structure

### User Story: "Implement Basic Java Application with CI/CD"
**Description**: As a development team, we want to create a Java application with automated testing and continuous integration.

---

## Task Breakdown

### Epic: Development Phase

#### Task TG-1: Implement Calculator Class
- **Status**: Ready for Testing
- **Assignee**: Development Team
- **Description**: Create a Calculator class with basic arithmetic operations
- **Implementation**: `src/main/java/com/asu/ser516/Calculator.java`
- **Features**:
  - Addition
  - Subtraction
  - Multiplication
  - Division (with zero-check)
  - Power calculation
  - Square root (with negative-check)

#### Task TG-2: Implement String Utilities
- **Status**: Ready for Testing
- **Assignee**: Development Team
- **Description**: Create utility class for string manipulation
- **Implementation**: `src/main/java/com/asu/ser516/StringUtils.java`
- **Features**:
  - String reversal
  - Palindrome detection
  - Vowel counting
  - Word capitalization
  - Word counting

---

### Epic: Testing Phase

#### Task TG-3: Write Unit Tests for Calculator
- **Status**: In Progress
- **Assignee**: QA Team
- **Description**: Comprehensive JUnit tests for Calculator class
- **Implementation**: `src/test/java/com/asu/ser516/CalculatorTest.java`
- **Test Cases**:
  - TG-3.1: Test addition of positive numbers
  - TG-3.2: Test addition with negative numbers
  - TG-3.3: Test subtraction operation
  - TG-3.4: Test multiplication operation
  - TG-3.5: Test division operation
  - TG-3.6: Test division by zero exception
  - TG-3.7: Test power operation
  - TG-3.8: Test square root operation
  - TG-3.9: Test square root of negative exception
  - TG-3.10: Test chained operations

#### Task TG-4: Write Unit Tests for String Utilities
- **Status**: In Progress
- **Assignee**: QA Team
- **Description**: Comprehensive JUnit tests for StringUtils class
- **Implementation**: `src/test/java/com/asu/ser516/StringUtilsTest.java`
- **Test Cases**:
  - TG-4.1: Test string reversal
  - TG-4.2: Test reverse with null input
  - TG-4.3: Test palindrome detection
  - TG-4.4: Test palindrome with null input
  - TG-4.5: Test vowel counting
  - TG-4.6: Test vowel count with null input
  - TG-4.7: Test word capitalization
  - TG-4.8: Test capitalize with empty string
  - TG-4.9: Test capitalize with null input
  - TG-4.10: Test word counting
  - TG-4.11: Test word count with null input
  - TG-4.12: Test handling of multiple spaces

---

### Epic: CI/CD Phase

#### Task TG-5: Configure Jenkins Build Pipeline
- **Status**: To Do
- **Assignee**: DevOps Team
- **Description**: Set up automated build and test pipeline in Jenkins
- **Implementation**: `Jenkinsfile`
- **Pipeline Stages**:
  1. Checkout - Pull code from GitHub
  2. Build - Compile Java source code
  3. Test - Run all JUnit tests
  4. Package - Create JAR artifact
  5. Archive - Store build artifacts

---

## Integration Workflow

### 1. Development Process
```
Developer creates/updates code
    ↓
Commits with Taiga task reference (e.g., "TG-3: Add calculator tests")
    ↓
Pushes to GitHub
    ↓
Updates Taiga task status
```

### 2. CI/CD Process
```
Code pushed to GitHub
    ↓
Jenkins webhook triggered (or manual build)
    ↓
Jenkins pulls latest code
    ↓
Runs build and tests
    ↓
Reports results
    ↓
Updates can be linked back to Taiga
```

### 3. Commit Message Convention
Use the following format in commit messages to link to Taiga tasks:
```
TG-X: Brief description of change

Longer explanation if needed.

Taiga-Task: #X
```

Example:
```
TG-3: Add unit tests for Calculator class

Implemented comprehensive JUnit tests covering all calculator
operations including edge cases and exception handling.

Taiga-Task: #3
```

---

## Testing Locally

### Prerequisites
- Java 11 or higher
- Maven 3.6+

### Running Tests
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=CalculatorTest

# Run with coverage report
mvn test jacoco:report
```

---

## Jenkins Setup Instructions

### 1. Create New Pipeline Job
1. Go to Jenkins Dashboard
2. Click "New Item"
3. Enter name: "Taiga-Jenkins-Demo"
4. Select "Pipeline"
5. Click "OK"

### 2. Configure Pipeline
1. Under "Pipeline" section:
   - Definition: "Pipeline script from SCM"
   - SCM: "Git"
   - Repository URL: [Your GitHub repository URL]
   - Branch: "*/main" or "*/master"
   - Script Path: "Jenkinsfile"

### 3. Configure Build Triggers (Optional)
- Poll SCM: `H/5 * * * *` (checks every 5 minutes)
- Or configure GitHub webhook for instant triggers

### 4. Save and Build
1. Click "Save"
2. Click "Build Now"
3. View build progress and test results

---

## Monitoring and Reporting

### In Jenkins:
- Build history shows all Taiga task references
- Test results show pass/fail for each test case
- Console output includes Taiga task echoes
- Artifacts are archived for each successful build

### In Taiga:
- Task comments can include Jenkins build numbers
- Attach Jenkins build URLs to relevant tasks
- Update task status based on build results

---

## Best Practices

1. **Always reference Taiga tasks** in commit messages
2. **Update Taiga status** after Jenkins builds
3. **Link Jenkins build URLs** in Taiga task comments
4. **Review test results** before marking tasks complete
5. **Keep task IDs consistent** across code, commits, and Taiga

---

## Troubleshooting

### Build Fails
1. Check Jenkins console output
2. Verify which tests failed
3. Look at test report details
4. Fix code locally
5. Re-run tests locally
6. Commit fix with Taiga reference
7. Push and verify Jenkins build

### Integration Issues
1. Verify GitHub repository URL in Jenkins
2. Check branch name matches
3. Ensure Jenkinsfile is in repository root
4. Verify Maven is configured in Jenkins
5. Check Java version compatibility
