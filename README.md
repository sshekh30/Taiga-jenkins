# SER516 Examples — Software Agility CI/CT Demos

This repository contains a set of examples used in **SER516 (Software Agility)** at Arizona State University. Each example demonstrates a specific concept in CI/CT (Continuous Integration / Continuous Testing) workflows.

---

## Examples Overview

### Example_1 — Taiga + Jenkins Integration
Demonstrates how a project management tool (Taiga) integrates with a CI pipeline (Jenkins). Shows how commit messages can reference Taiga tasks and how Jenkins builds can be linked back to project tasks for full traceability from task → code → build.

### Example_2 — Java Unit Testing with JUnit 5
Demonstrates how to write basic unit tests in Java using **JUnit 5** and run them automatically through a **Jenkins** pipeline triggered on every commit. Uses **Maven** for build automation.

### Example_3 — Python Unit Testing with pytest
Demonstrates how to write basic unit tests in Python using **pytest** and run them automatically through a **Jenkins** pipeline triggered on every commit. Uses **Poetry** for dependency management.

---

## How to Use

Each example has its own `README.md` with step-by-step instructions. Start with the README inside the example folder you are working on.

---

## Tools Used Across Examples

| Tool    | Purpose                                          |
|---------|--------------------------------------------------|
| Taiga   | Project management and task tracking             |
| Jenkins | CI server — runs pipelines automatically on each commit |
| Maven   | Java build automation and dependency management  |
| JUnit 5 | Java unit testing framework                      |
| Poetry  | Python dependency management                     |
| pytest  | Python unit testing framework                    |
