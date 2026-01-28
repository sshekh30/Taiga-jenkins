# Step-by-Step Setup Instructions

This guide walks you through setting up the complete Taiga-Jenkins integration demo.

---

## Phase 1: Upload to GitHub

### Step 1: Create GitHub Repository
1. Go to https://github.com
2. Click the "+" icon → "New repository"
3. Repository name: `taiga-jenkins-demo`
4. Description: "Demo project for Taiga and Jenkins integration"
5. Choose "Public" or "Private" (Public recommended for demo)
6. **DO NOT** initialize with README, .gitignore, or license (we have our own)
7. Click "Create repository"

### Step 2: Initialize Local Repository
```bash
cd taiga-jenkins-demo
git init
git add .
git commit -m "Initial commit: TG-1, TG-2, TG-3, TG-4, TG-5 - Complete project setup"
```

### Step 3: Push to GitHub
```bash
# Replace YOUR_USERNAME with your GitHub username
git remote add origin https://github.com/YOUR_USERNAME/taiga-jenkins-demo.git
git branch -M main
git push -u origin main
```

---

## Phase 2: Set Up Taiga Project

### Step 1: Create New Project in Taiga
1. Log into Taiga at https://swent0linux.asu.edu/
2. Click "New Project"
3. Project Details:
   - Name: "Taiga-Jenkins Integration Demo"
   - Description: "Demonstration of Taiga project management integrated with Jenkins CI/CD"
   - Project Type: "Scrum" or "Kanban"
4. Click "Create Project"

### Step 2: Create User Story
1. Go to "Backlog" or "User Stories"
2. Click "+ New User Story"
3. Title: "Implement Basic Java Application with CI/CD"
4. Description: "As a development team, we want to create a Java application with automated testing and continuous integration."
5. Click "Save"

### Step 3: Create Tasks for Development Phase

#### Task 1: Implement Calculator Class
- Title: "TG-1: Implement Calculator Class"
- Description: "Create Calculator.java with basic arithmetic operations"
- Status: "New" or "Ready for Testing"
- Assigned to: Your name or team member

#### Task 2: Implement String Utilities
- Title: "TG-2: Implement String Utilities"
- Description: "Create StringUtils.java for string manipulation"
- Status: "New" or "Ready for Testing"
- Assigned to: Your name or team member

### Step 4: Create Tasks for Testing Phase

#### Task 3: Write Unit Tests for Calculator
- Title: "TG-3: Write Unit Tests for Calculator"
- Description: "Comprehensive JUnit tests for Calculator class"
- Status: "In Progress"
- Assigned to: Your name or team member

#### Task 4: Write Unit Tests for String Utilities
- Title: "TG-4: Write Unit Tests for String Utilities"
- Description: "Comprehensive JUnit tests for StringUtils class"
- Status: "In Progress"
- Assigned to: Your name or team member

### Step 5: Create Task for CI/CD Phase

#### Task 5: Configure Jenkins Build Pipeline
- Title: "TG-5: Configure Jenkins Build Pipeline"
- Description: "Set up automated build and test pipeline in Jenkins"
- Status: "To Do"
- Assigned to: Your name or team member

---

## Phase 3: Set Up Jenkins Pipeline

### Step 1: Access Jenkins
1. Navigate to https://swent0linux.asu.edu:8080 (or your Jenkins URL)
2. Log in with your admin credentials

### Step 2: Check Maven Installation
1. Go to "Manage Jenkins" → "Global Tool Configuration"
2. Scroll to "Maven" section
3. Note the name of your Maven installation (e.g., "Maven-3.6.3")
4. If Maven is not installed:
   - Click "Add Maven"
   - Name: "Maven-3.6.3"
   - Check "Install automatically"
   - Choose version 3.6.3 or later
   - Click "Save"

### Step 3: Create New Pipeline Job
1. From Jenkins Dashboard, click "New Item"
2. Enter name: "Taiga-Jenkins-Demo"
3. Select "Pipeline"
4. Click "OK"

### Step 4: Configure Pipeline
1. **General Section**:
   - Description: "Taiga-Jenkins integration demo with JUnit tests"
   
2. **Build Triggers** (Optional):
   - Check "Poll SCM" if you want automatic builds
   - Schedule: `H/5 * * * *` (checks every 5 minutes)
   
3. **Pipeline Section**:
   - Definition: Select "Pipeline script from SCM"
   - SCM: Select "Git"
   - Repository URL: `https://github.com/YOUR_USERNAME/taiga-jenkins-demo.git`
   - Credentials: Add if private repository (click "Add" → "Jenkins")
   - Branch Specifier: `*/main` (or `*/master` if you used master)
   - Script Path: `Jenkinsfile`

4. Click "Save"

### Step 5: Run First Build
1. Click "Build Now"
2. Watch the build progress in "Build History"
3. Click on the build number (e.g., "#1")
4. Click "Console Output" to see detailed logs

### Step 6: Review Build Results
After the build completes:
1. Check "Test Result" tab to see JUnit test results
2. Check "Artifacts" to download the built JAR file
3. Review console output for Taiga task references

---

## Phase 4: Verify Integration

### Step 1: Verify Code → GitHub → Jenkins Flow
1. Make a small change to the code locally
2. Commit with Taiga reference:
   ```bash
   git add .
   git commit -m "TG-1: Update Calculator with modulo operation"
   git push
   ```
3. Wait for Jenkins to detect change (if polling enabled) or click "Build Now"
4. Verify build runs successfully

### Step 2: Link Jenkins Build to Taiga
1. Go to your Jenkins build (e.g., build #1)
2. Copy the build URL (e.g., `https://swent0linux.asu.edu:8080/job/Taiga-Jenkins-Demo/1/`)
3. Go to Taiga task TG-5
4. Add a comment: "Jenkins build completed: [URL]"
5. Update task status to "Done"

### Step 3: Test Complete Workflow
1. Create a new task in Taiga (e.g., "TG-6: Add modulo operation")
2. Implement the feature in code
3. Write tests for the feature
4. Commit with Taiga reference
5. Push to GitHub
6. Jenkins builds automatically
7. Review results in Jenkins
8. Update Taiga task status

---

## Phase 5: Demonstrate to Professor

### What to Show:

#### 1. Taiga Project Structure
- Show the user story
- Show all tasks (TG-1 through TG-5)
- Show task descriptions and assignments
- Show how tasks map to code components

#### 2. GitHub Repository
- Show the code structure
- Show commit messages with Taiga references
- Show how Jenkinsfile is in the repository

#### 3. Jenkins Build
- Trigger a manual build
- Show build pipeline stages:
  - Checkout
  - Build
  - Test
  - Package
  - Archive
- Show JUnit test results
- Show console output with Taiga task echoes
- Show archived artifacts

#### 4. Integration Flow
- Demonstrate: Taiga Task → Code Change → GitHub Push → Jenkins Build → Taiga Update
- Show test results in Jenkins
- Show how failed tests would appear
- Show how to link Jenkins builds back to Taiga tasks

---

## Expected Results

### Successful Build Should Show:
✅ All stages complete successfully
✅ All 22 JUnit tests pass (10 Calculator + 12 StringUtils tests)
✅ JAR file created and archived
✅ Test report available
✅ Console output shows Taiga task references

### Jenkins Test Results:
- CalculatorTest: 10 tests passed
- StringUtilsTest: 12 tests passed
- Total: 22 tests, 0 failures, 0 errors

---

## Troubleshooting

### Issue: "Maven not found"
**Solution**: 
1. Go to "Manage Jenkins" → "Global Tool Configuration"
2. Add Maven installation
3. Update Jenkinsfile to match Maven installation name

### Issue: "Tests fail"
**Solution**:
1. Run tests locally first: `mvn test`
2. Fix any issues locally
3. Commit and push fixes
4. Rebuild in Jenkins

### Issue: "Git repository not found"
**Solution**:
1. Verify GitHub repository URL is correct
2. Check if repository is private (add credentials if needed)
3. Verify branch name (main vs master)

### Issue: "Build hangs at test stage"
**Solution**:
1. Check console output for specific error
2. Verify Java version compatibility (needs Java 11+)
3. Check Maven dependencies are downloading correctly

---

## Next Steps

After successful demonstration:

1. **Add More Tests**: Create additional test cases for edge cases
2. **Add Code Coverage**: Integrate JaCoCo for test coverage reports
3. **Add More Pipeline Stages**: Deploy, notifications, etc.
4. **Configure Webhooks**: For instant builds on push
5. **Add More Taiga Integration**: Use Taiga API for automatic updates
6. **Student Access**: Create accounts for students to practice

---

## Questions for Professor

Before demonstrating, clarify:
1. Should students have individual projects or shared project?
2. Should we use GitHub webhooks for automatic builds?
3. What level of Taiga detail do students need?
4. Should we add deployment stages to the pipeline?
5. Any specific reporting requirements?

---

## Summary

This setup demonstrates:
✅ Taiga for project management and task tracking
✅ GitHub for source code version control
✅ Jenkins for automated CI/CD pipeline
✅ JUnit for automated testing
✅ Integration between all three tools
✅ Clear task-to-code traceability
✅ Automated build and test workflow

The workflow shows how modern software development teams manage projects, track work, write tests, and automate quality checks!
