# Android Notifications Demo App

This Android application demonstrates various aspects of Firebase Cloud Messaging (FCM) notifications using the Model-View-ViewModel (MVVM) architecture and Hilt dependency injection.

## Features

1. **Notification Payload:**
   - Displays a notification received from FCM using the notification payload.

2. **Data Payload:**
   - Handles FCM messages with data payload and updates the UI accordingly.

3. **Composite Payload:**
   - Demonstrates the handling of FCM messages with both notification and data payloads.

4. **Subscribe to Topic:**
   - Allows the user to subscribe to an FCM topic and receive topic-specific notifications.

5. **Send Notification to Topic:**
   - Sends an FCM notification to the subscribed topic using the app.

6. **Unsubscribe from Topic:**
   - Lets the user unsubscribe from a previously subscribed FCM topic.

## MVVM Architecture

The app follows the MVVM architecture pattern for clean separation of concerns:

- **Model:** Handles data and business logic.
- **View:** Responsible for UI components and user interactions.
- **ViewModel:** Acts as a mediator between the Model and View, holding and managing UI-related data.

## Dependency Injection with Hilt

The app utilizes Hilt for dependency injection, making it easy to manage and provide dependencies across the app components.

## Usage

1. Clone the repository.

   ```bash
   git clone https://github.com/Avs-Pavan/AndroidFCMNotificationDemo.git

1. **Open the project in Android Studio.**

2. **Update the `google-services.json` file with your Firebase configuration.**

3. **Run the app on an Android device or emulator.**

4. **Explore the different notification features and functionalities.**

## Postman Collection for Testing

For testing FCM notifications, use the [Fcm-notification-postman-collection](https://github.com/Avs-Pavan/Fcm-notification-postman-collection) Postman collection. This collection provides predefined requests to simulate various scenarios and payloads for testing FCM functionality.

## Configuration

Before running the app, make sure to configure your Firebase project:

1. Set up a Firebase project in the [Firebase Console](https://console.firebase.google.com/).

2. Add an Android app to your Firebase project and download the `google-services.json` file.

3. Replace the existing `google-services.json` file in the `app` directory with the one you downloaded.

## Additional Resources

- Firebase Cloud Messaging Documentation: [FCM Documentation](https://firebase.google.com/docs/cloud-messaging)
- Hilt Documentation: [Hilt Documentation](https://developer.android.com/training/dependency-injection/hilt-android)

Feel free to explore the code, customize the app, and integrate additional features as needed. If you have any questions, refer to the documentation or reach out to the community for assistance. Happy coding!
