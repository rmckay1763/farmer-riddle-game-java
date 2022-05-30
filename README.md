# Farmer Riddle Game Java

--

### **Synopsis**

Java implementation of [Farmer Riddle Game](https://github.com/rmckay1763/farmer-riddle-game) with a user interface.

---

### **Implementation**

The program follows the MVC design pattern.
- Classes in the `model` directory maintain state and provide methods for performing actions.
- Classes in the `view` directory display content and allow the user to send events to the `model`.
- Classes in the `controller` directory respond to events in the view by calling methods in the `model` and updating state in the `view`.

---

### **Usage**

The program uses gradle for project management.
- build
    ```
    gradlew clean build
    ```
- run
    ```
    gradlew clean run
    ```