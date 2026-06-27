# SLIQ Mobile Automation Framework

A robust, Java-based mobile test automation framework designed using the **Page Object Model (POM)** pattern. It automates smoke and regression journeys for the SLIQ Android application on physical devices and emulators.

---

## Key Features
* **Hybrid/React Native Support**: Interacts natively under the `NATIVE_APP` context to bypass slow WebView context-switching and ChromeDriver version mismatch errors.
* **Smart Keyboard Suppression**: Uses custom capabilities to suppress software keyboards during execution, speeding up input entries by 40-50%.
* **Auto Keyboard Restoration**: Automatically runs `adb shell ime reset` at teardown to restore the device's default keyboard (e.g., Gboard) for normal phone use post-test.
* **Dynamic, Descriptive Reporting**: Generates timestamped HTML reports (e.g. `SLIQ_Automation_Suite_2026-06-27_15-46-00.html`) and failure screenshots under unique names to prevent run history overwrites.
* **Readable Logger**: Structured Log4j2 pattern layout outputting clean line-by-line actions with class names and numbers.

---

## Prerequisites & Installation

### 1. Java Development Kit (JDK 21)
1. Download JDK 21 from the [Oracle Downloads page](https://www.oracle.com/java/technologies/downloads/).
2. Run the installer and configure the `JAVA_HOME` environment variable:
   * **Variable Name**: `JAVA_HOME`
   * **Variable Value**: `C:\Program Files\Java\jdk-21` (or your installation path)
3. Add `%JAVA_HOME%\bin` to your system's `Path` environment variable.
4. Verify installation in terminal:
   ```bash
   java -version
   ```

### 2. Apache Maven
1. Download Maven from the [Apache Maven page](https://maven.apache.org/download.cgi).
2. Extract the ZIP archive and configure environment variables:
   * **Variable Name**: `MAVEN_HOME`
   * **Variable Value**: Path to extracted folder (e.g., `C:\maven\apache-maven-3.9.6`)
3. Add `%MAVEN_HOME%\bin` to your system's `Path`.
4. Verify installation in terminal:
   ```bash
   mvn -version
   ```

### 3. Android SDK & Command Line Tools (ADB)
1. Install **Android Studio** from [Android Developer page](https://developer.android.com/studio).
2. Configure Android Home environment variables:
   * **Variable Name**: `ANDROID_HOME`
   * **Variable Value**: `C:\Users\<YourUsername>\AppData\Local\Android\Sdk`
3. Add the following directories to your system's `Path`:
   * `%ANDROID_HOME%\platform-tools` (contains the `adb` executable)
   * `%ANDROID_HOME%\tools`
   * `%ANDROID_HOME%\tools\bin`
4. Enable **Developer Options** and **USB Debugging** on your physical phone:
   * Go to *Settings -> About Phone -> Tap "Build Number" 7 times*.
   * Go to *Settings -> Developer Options -> Enable USB Debugging*.
5. Connect your phone via USB and verify connection:
   ```bash
   adb devices
   ```

### 4. Node.js & Appium Server
1. Download and install **Node.js** from [Node.js Downloads](https://nodejs.org/).
2. Install the **Appium Server** globally via npm:
   ```bash
   npm install -g appium
   ```
3. Install the **UiAutomator2 Driver** for Android:
   ```bash
   appium driver install uiautomator2
   ```
4. Start the Appium Server:
   ```bash
   appium
   ```
   *(By default, this launches the server listening on http://127.0.0.1:4723)*

---

## Configuration

The framework reads target device and execution configurations from the properties file:
* **File Location**: [Configuration/DeviceSpecification.properties](file:///c:/Users/umesh.k/eclipse-workspace/valuenable/SLIQ_Mobile_Automation_Framework/Configuration/DeviceSpecification.properties)

Ensure the properties match your execution environment:
```properties
URL=http://127.0.0.1:4723               # Appium Server Port URL (for local runs)
DEVICE=Android                          # OS platform
UDID=9b010059305331323800d45b3428c0     # Device UDID (get from 'adb devices')
PACKAGE=com.valuenable.las.dev          # App bundle Package ID
ACTIVITY=com.valuenable.las.MainActivity # Main Launcher Activity name

# ===================================================================
# EXECUTION SWITCH ENGINE
# ===================================================================
RUN_MODE=LOCAL_REAL                     # Options: LOCAL_REAL, LOCAL_EMULATOR, CLOUD_BROWSERSTACK

# ===================================================================
# BROWSERSTACK CLOUD INTEGRATION DETAILS (Required only if RUN_MODE=CLOUD_BROWSERSTACK)
# ===================================================================
BROWSERSTACK_URL=http://hub.browserstack.com/wd/hub
BROWSERSTACK_USERNAME=your_browserstack_username
BROWSERSTACK_ACCESS_KEY=your_browserstack_access_key
BROWSERSTACK_APP_URL=bs://uploaded_app_hash_id
```

---

## Executing Tests

### 1. From Eclipse / IntelliJ IDE
1. Open the project in Eclipse.
2. Locate the [testng.xml](file:///c:/Users/umesh.k/eclipse-workspace/valuenable/SLIQ_Mobile_Automation_Framework/testng.xml) file in the root folder.
3. Right-click `testng.xml` -> **Run As** -> **TestNG Suite**.

### 2. From the Terminal (Command Line)
To execute the complete automation suite, run:
```bash
mvn clean test
```
To run a custom test suite file, pass the `-DsuiteXmlFile` parameter:
```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

---

## Viewing Test Outputs & Debugging

When a test completes or fails, check the following directories:

### 1. Extent Reports
* **Location**: [Reports/](file:///c:/Users/umesh.k/eclipse-workspace/valuenable/SLIQ_Mobile_Automation_Framework/Reports/)
* **Format**: Named by TestNG suite name and timestamp: `SLIQ_Automation_Suite_YYYY-MM-DD_HH-MM-SS.html`
* Open the HTML file in any web browser to see a visual execution timeline, failure logs, and embedded screenshots.

### 2. Action Logs
* **Location**: [logs/](file:///c:/Users/umesh.k/eclipse-workspace/valuenable/SLIQ_Mobile_Automation_Framework/logs/)
* **Format**: `automation_YYYY-MM-DD_HH-MM-SS.log`
* Open the latest log to see detailed steps and trace the execution path.

### 3. Failure Screenshots
* **Location**: [Screenshots/](file:///c:/Users/umesh.k/eclipse-workspace/valuenable/SLIQ_Mobile_Automation_Framework/Screenshots/)
* **Format**: Named by test method and failure timestamp: `testMethodName_YYYY-MM-DD_HH-MM-SS.png`
* Visually inspect the screen layout at the exact millisecond of failure.
