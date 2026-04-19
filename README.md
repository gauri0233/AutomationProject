# AutomationProject — QA Engineering 1-Day Practical Assessment

A complete QA automation suite built in **Java + Selenium WebDriver + TestNG** for the [SauceDemo](https://www.saucedemo.com) e-commerce demo application and the [ReqRes](https://reqres.in) public REST API. This project covers manual testing, test automation using the Page Object Model, API testing, CI integration via GitHub Actions, and full defect documentation.

---

## Application Under Test

| Type | URL |
|------|-----|
| Web App | https://www.saucedemo.com |
| REST API |[ https://reqres.in ](https://jsonplaceholder.typicode.com)|

---

## Test Users

| Username | Password | Behaviour |
|----------|----------|-----------|
| `standard_user` | `secret_sauce` | Fully functional — primary test account |
| `locked_out_user` | `secret_sauce` | Login blocked — tests error handling |
| `problem_user` | `secret_sauce` | Intentional UI bugs — used for defect discovery |
| `performance_glitch_user` | `secret_sauce` | Simulates slow backend |

---

## Repository Structure

AutomationProject/
├── src/
│ ├── main/java/pages/ # Page Object Model classes
│ │ ├── LoginPage.java
│ │ ├── ProductsPage.java
│ │ ├── CartPage.java
│ │ └── CheckoutPage.java
│ └── test/java/tests/ # Test spec files
│ ├── LoginTest.java
│ ├── CartTest.java
│ ├── CheckoutTest.java
│ └── SortingTest.java
├── api-tests/
│ └── ReqRes_Collection.json # Postman collection
├── ci/
│ └── .github/workflows/qa.yml # GitHub Actions CI pipeline
├── docs/
│ ├── test-plan.md
│ ├── test-cases.md
│ ├── bug-reports.md
│ ├── api-results.md
│ └── test-summary.md
├── pom.xml
├── .gitignore
└── README.md


---

## Prerequisites

| Tool | Version Required |
|------|-----------------|
| Java JDK | 11 or higher |
| Apache Maven | 3.6+ |
| Google Chrome | Latest stable |
| ChromeDriver | Match your Chrome version |

> ChromeDriver must be on your system `PATH` or set in project config.

---

## Setup Instructions

https://github.com/gauri0233/AutomationProject.git
cd AutomationProject

Automated Test Scenarios
1)Successful login with standard_user — verify product page loads
2)Add two products to cart — verify cart badge count updates correctly
3)Complete full checkout flow end-to-end — verify order confirmation message
4)Login with locked_out_user — assert correct error message is displayed
5)Sort products by price low-to-high — verify order of first three items


How to Run API Tests
1)Open Postman
2)Import [api-tests/ReqRes_Collection.json](https://jsonplaceholder.typicode.com)
3)Click Run Collection

API Endpoints Tested
| Method | Endpoint           | What is Verified                                              |
| ------ | ------------------ | ------------------------------------------------------------- |
| GET    | /posts?_page=2     | Status 200, data array not empty                              |
| GET    | /posts/2           | Status 200, post ID matches, title and body present           |
| GET    | /posts/999         | Status 404, error handled correctly                           |
| POST   | /posts             | Status 201, response contains id field                        |
| PUT    | /posts/2           | Status 200, updated title reflected in response               |
| DELETE | /posts/2           | Status 200, empty object {} returned                          |
| GET    | /posts/2/comments  | Status 200, comments array not empty, email field present     |
| GET    | /comments?postId=2 | Status 200, all comments have matching postId                 |
| GET    | /users             | Status 200, returns array of 10 users, name and email present |
| GET    | /todos/1           | Status 200, completed field is boolean                        |

Documentation

| File                 | Contents                                                          |
| -------------------- | ----------------------------------------------------------------- |
| docs/test-plan.md    | Scope, approach, risk areas, tools, assumptions                   |
| docs/test-cases.md   | 20+ structured manual test cases with Pass/Fail evidence          |
| docs/bug-reports.md  | 5+ defects with steps to reproduce, severity, and screenshots     |
| docs/api-results.md  | API test execution summary and results                            |
| docs/test-summary.md | Overall pass/fail counts, automation coverage, quality assessment |

 



