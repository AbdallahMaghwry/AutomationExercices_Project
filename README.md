# 🧪 Automation Exercise — Test Automation Framework (TAF)

A robust, maintainable, and scalable Test Automation Framework (TAF) built to test [Automation Exercise](https://automationexercise.com/) — a real-world e-commerce web application. The framework covers both **UI** and **API** testing using industry best practices.

---

## ✅ Features

- **Java + TestNG** based test execution
- **Design Patterns:** Page Object Model (POM), Fluent, Bot, Factory
- **Custom Fluent Wait Utility** for stable element interactions
- **Cross-browser support** (Chrome, Edge)
- **Headless execution** support for CI environments
- **Log4j2** structured logging
- **Allure Reporting** with screenshots and screen recordings
- **Data-driven testing** using JSON And Properties files
- **API testing** using RestAssured
- **CI/CD integration** via GitHub Actions
- **Custom Listeners** for screenshots, logs, and test lifecycle management
- **Environment-based execution** (local, headless)

---

## 🧱 Project Structure

```
TAF/
├── .github/
│   ├── dependabot.yml
│   └── workflows/
│       └── E2E Regression Pipeline.yml
├── src/
│   ├── main/
│   │   ├── java/com/automationexercices/
│   │   │   ├── apis/
│   │   │   │   ├── Builder.java
│   │   │   │   └── UserManagementAPI.java
│   │   │   ├── drivers/
│   │   │   │   ├── AbstractDriver.java
│   │   │   │   ├── APITest.java
│   │   │   │   ├── Browsers.java
│   │   │   │   ├── ChromeFactory.java
│   │   │   │   ├── EdgeFactory.java
│   │   │   │   ├── GUIDriver.java
│   │   │   │   ├── UITest.java
│   │   │   │   └── WebDriverProvider.java
│   │   │   ├── listeners/
│   │   │   │   └── TestNGListeners.java
│   │   │   ├── media/
│   │   │   │   ├── ScreenRecordManager.java
│   │   │   │   └── ScreenShotManager.java
│   │   │   ├── Pages/
│   │   │   │   ├── component/
│   │   │   │   │   └── NavigationBarComponent.java
│   │   │   │   ├── APITestingPage.java
│   │   │   │   ├── CartPage.java
│   │   │   │   ├── CheckOutPage.java
│   │   │   │   ├── ContactUsPage.java
│   │   │   │   ├── DeleteAccountPage.java
│   │   │   │   ├── LogoutPage.java
│   │   │   │   ├── PaymentPage.java
│   │   │   │   ├── ProductDetailsPage.java
│   │   │   │   ├── ProductPage.java
│   │   │   │   ├── SignupLoginPage.java
│   │   │   │   ├── SignupPage.java
│   │   │   │   ├── TestCasesPage.java
│   │   │   │   └── VideoTutorialsPage.java
│   │   │   ├── utils/
│   │   │   │   ├── Actions/
│   │   │   │   │   ├── AlertActions.java
│   │   │   │   │   ├── BrowserActions.java
│   │   │   │   │   ├── ElementActions.java
│   │   │   │   │   └── FrameActions.java
│   │   │   │   ├── dataReader/
│   │   │   │   │   ├── JsonReader.java
│   │   │   │   │   └── PropertyReader.java
│   │   │   │   ├── Logs/
│   │   │   │   │   └── LogsManager.java
│   │   │   │   ├── report/
│   │   │   │   │   ├── AllureAttachmentManager.java
│   │   │   │   │   ├── AllureBinaryManager.java
│   │   │   │   │   ├── AllureConstants.java
│   │   │   │   │   ├── AllureEnvironmentManager.java
│   │   │   │   │   └── AllureReportGenerator.java
│   │   │   │   ├── FileUtils.java
│   │   │   │   ├── OSUtils.java
│   │   │   │   ├── TerminalUtils.java
│   │   │   │   ├── TimeManager.java
│   │   │   │   └── WaitManager.java
│   │   │   └── validations/
│   │   │       ├── BaseAssertion.java
│   │   │       ├── Validation.java
│   │   │       └── Verification.java
│   │   └── resources/
│   │       ├── META-INF/services/
│   │       │   └── org.testng.ITestNGListener
│   │       ├── allure.properties
│   │       ├── db.properties
│   │       ├── environment.properties
│   │       ├── extensions/
│   │       │   └── HaramBlur.crx
│   │       ├── log4j2.properties
│   │       ├── OS.properties
│   │       ├── seleniumGrid.properties
│   │       ├── video.properties
│   │       └── waits.properties
│   └── test/
│       ├── java/com/automationexercices/tests/
│       │   ├── api/
│       │   │   └── RegisterTestAPI.java
│       │   ├── ui/
│       │   │   ├── CartTest.java
│       │   │   ├── CheckoutTest.java
│       │   │   ├── LoginTest.java
│       │   │   ├── PaymentTest.java
│       │   │   ├── ProductsDetailsTest.java
│       │   │   ├── ProductTest.java
│       │   │   └── RegisterTest.java
│       │   └── BaseTest.java
│       └── resources/test-data/
│           ├── cart-data.json
│           ├── checkout-data.json
│           ├── login-data.json
│           ├── payment-data.json
│           ├── product-details-data.json
│           ├── products-data.json
│           └── register-data.json
└── test-output/                           # Allure results and reports
```

---

## 🚀 How to Run

### Run all tests
```bash
TARGET: "%regex[.*Test.*],com.automationexercices.tests.**.**,com.automationexercices.tests.**"
run: mvn -Dtest="${TARGET}" clean test
```

### Run on a Chrome browser (headless)
```bash
TARGET: "%regex[.*Test.*],com.automationexercices.tests.**.**,com.automationexercices.tests.**"
mvn -Dtest="${TARGET}" -DexecutionType="LocalHeadless" -Dextensions="disabled" -DbrowserType="Chrome" clean test

```

### Run on a Edge browser (headless)
```bash
TARGET: "%regex[.*Test.*],com.automationexercices.tests.**.**,com.automationexercices.tests.**"
mvn -Dtest="${TARGET}" -DexecutionType="LocalHeadless" -Dextensions="enabled" -DbrowserType="Edge" clean test
```

---

## 🔄 CI/CD Pipeline

The project uses **GitHub Actions** to run the full regression suite automatically on every push or pull request to `master`, and on a daily schedule.

![E2E Regression](https://github.com/AbdallahMaghwry/AutomationExercices_Project/actions/workflows/E2E%20Regression%20Pipeline.yml/badge.svg)

| Job | OS | Browser |
|---|---|---|
| Chrome_Linux_Test | Ubuntu Latest | Chrome |
| Edge_Windows_Test | Windows Latest | Edge |

---

## 🛠️ Tech Stack

| Tool | Purpose |
|---|---|
| Java 21 | Programming language |
| TestNG | Test framework |
| Selenium WebDriver 4 | UI automation |
| RestAssured | API testing |
| Allure | Test reporting |
| Log4j2 | Logging |
| Maven | Build & dependency management |
| GitHub Actions | CI/CD |
| Git | Version control |

---

## 👨‍💻 Author

**Abdallah Mohammed Maghwry**  
Junior Test Automation Engineer  
🔗 [LinkedIn](https://www.linkedin.com/in/abdallah-mohammed-maghwry/) | 📧 abdallahm23122001@gmail.com
