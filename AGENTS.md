# AGENTS.md

## Project snapshot

This repo is a Java 11 + Maven automation playground focused on Selenium Web UI and REST API testing. It mixes learning examples, page-object style classes, and direct test scripts rather than a single production-ready framework.

Current dependencies in `pom.xml` include:

- Selenium Java
- WebDriverManager
- TestNG
- RestAssured
- Apache POI
- JUnit Jupiter API

## Repository layout

- `src/main/java/stepdefinition/` contains Selenium page-object style classes used as reusable browser interaction wrappers.
  - `AmazonPage.java` demonstrates a minimal page object with a `WebDriver` + `WebDriverWait` constructor.
  - `LoginPage.java` contains login flows and validation helpers.
  - `PracticePages.java` is a larger collection of UI actions for radio buttons, dropdowns, alerts, tabs, windows, tables, and hide/show elements.
- `src/test/java/Web/Base/` contains base browser setup classes.
  - `BaseClass.java` sets up Chrome via `WebDriverManager` and exposes common URL constants.
- `src/test/java/Web/Tests/` contains browser test scripts and demo classes.
  - Some classes are `main`-driven exploratory scripts rather than proper `@Test` suites.
- `src/test/java/api/CRUD/` contains REST API CRUD examples using RestAssured.
- `src/test/java/api/Pojo/` contains POJO models and request/response examples.
- `src/test/resources/` stores Excel/text test data such as `loginTestData.xlsx` and `loginTestCases.txt`.

## Build and test commands

Run the project test suite from the repository root:

```bash
mvn test
```

Target specific examples when needed:

```bash
mvn -Dtest=api.CRUD.GetUserTest test
mvn -Dtest=api.Pojo.CreateUserTest test
```

Important: not every class in `src/test/java` is a formal TestNG/JUnit test. Some classes exist as quick learning scripts and use `public static void main(String[] args)`. Treat those as examples, not as the default test entry point.

## Coding conventions and patterns

### Selenium / page-object patterns

- Page-object classes live under `src/main/java/stepdefinition` and usually follow this pattern:

```java
public class ExamplePage {
    public WebDriver driver;
    public WebDriverWait wait;

    public ExamplePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}
```

- Locators are kept as private `By` fields, then actions are exposed as methods such as `clickTodayDeal()`, `EnterUsername()`, `switchToChildWindow()`, etc.
- Use `ExpectedConditions` for waits instead of raw sleeps when waiting for page state.
- `PracticePages.java` is the best reference for multi-step browser interactions (alerts, windows, tabs, dynamic tables).
- `LoginPage.java` is the best reference for login flows and validation checks.

### Browser setup pattern

The project uses `WebDriverManager` plus `ChromeDriver` in the base test layer:

```java
WebDriverManager.chromedriver().setup();
driver = new ChromeDriver();
driver.manage().window().maximize();
```

Keep browser setup simple and centralized in the `Web.Base` layer unless a specific test needs custom behavior.

### API testing pattern

The project prefers RestAssured request chaining in this style:

```java
given()
    .baseUri("https://jsonplaceholder.typicode.com")
    .header("Accept", "application/json")
.when()
    .get("users/1")
.then()
    .statusCode(200)
    .body("id", equalTo(1));
```

POJO-based API payloads are also used in `src/test/java/api/Pojo/`.

```java
EmployeePojo ep = new EmployeePojo("morpheus", "leader", new String[]{"Java", "C"}, "XYZ", "morpheus@xyz.com");
Response response = given()
    .contentType(ContentType.JSON)
    .body(ep)
    .when()
    .post("https://reqres.in/api/users");
```

Use `api.Pojo` for request object models and keep assertions close to the test case.

## Working rules for AI agents

- Keep changes minimal and aligned with the existing package layout.
- Do not introduce a brand-new framework or restructure the project unless the task explicitly requires it.
- Prefer matching existing naming patterns:
  - `stepdefinition` for page-object-like browser logic
  - `Web.Base` for browser setup
  - `Web.Tests` for UI tests
  - `api.CRUD` and `api.Pojo` for API work
- Use the same `WebDriver` + `WebDriverWait` constructor style when adding new page-object classes.
- When adding tests, prefer the repo’s current conventions: TestNG `@Test` methods for automated tests, and simple `main` methods only for experimentation.
- Keep XPath selectors explicit and readable; this repo often uses direct path expressions rather than abstract frameworks.
- If a feature touches browser automation, reference the patterns in `BaseClass.java` and `PracticePages.java` before introducing new abstractions.

## Example files to follow

- `src/main/java/stepdefinition/LoginPage.java` — typical login page object and wait handling
- `src/main/java/stepdefinition/PracticePages.java` — handling advanced Selenium actions
- `src/test/java/Web/Base/BaseClass.java` — Chrome startup and driver lifecycle
- `src/test/java/api/CRUD/GetUserTest.java` — RestAssured request/response assertion style
- `src/test/java/api/Pojo/PostApiUsingPojo.java` — POJO request serialization and header inspection

