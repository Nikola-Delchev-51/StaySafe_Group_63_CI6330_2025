# StaySafe_Group_63_CI6330_2025
StaySafe is a mobile app designed to enhance personal safety by allowing friends and family to check in on planned walking, jogging, cycling or taxi trips. At a minimum, the app should allow a user to notify friends of their departure location and departure time and their intended destination with an ETA for arrival and enable them to set a status e.g. "on route" or "arrived".

### Additional features might include (but are not limited to):
- Recording or pushing live location data
- Sending safety alerts and updates
- Detecting anomalies such as departure from planned routes or unexpected motion
- Displaying of information on maps
- Providing a panic button
- Using the camera and associating images with route or safety status

It is anticipated that these features would be achieved from:
- Interaction with the provided MAD StaySafe API
- Integration of mobile sensors e.g. pedometer, accelerometer, GPS, camera
- Interaction with external APIs such as Google Maps or X
- The use of persistent storage on the phone or using the MAD StaySafe API

### Dummy Data and the StaySafe API
To support your development, dummy data will be supplied for the key CRUD-able entities envisaged for this application. This data will be stored in the StaySafe API which you can access with a browser using the endpoint URLs listed below. Alternatively, you can GET, POST, PUT and DELETE directly to the StaySafe API from within your application.

The StaySafe API is a RESTful API similar to the Unibase API that you were encouraged to use in the React Native portfolio assessment. It provides a number of GET, POST, PUT and DELETE endpoints for the key entities expected in the application. Specifically, these entities are:
- **User**
- **Contact**
- **Activity**
- **Status**
- **Location**
- **Position**

### Entity and Datatype Definitions
Entities are data structures which describe the key complex data types found within the application. They are typically persisted in some form of relational or key/value database and retrievable through some unique ID.

| Entity   | Definition | Object |
|----------|------------|--------|
| **User** | Defines an app user | `{ UserID, UserFirstname, UserLastname, UserPhone, UserUsername }` |
| **Contact** | Stores user contacts | `{ ContactID, ContactUserID, ContactContactID, ContactLabel, ContactDateCreated }` |
| **Activity** | Represents a planned journey | `{ ActivityID, ActivityName, ActivityUserID, ActivityFrom, ActivityTo, ActivityStatus }` |
| **Location** | Defines a specific place | `{ LocationID, LocationName, LocationAddress, LocationPostcode, LocationCoordinate }` |
| **Status** | Tracks activity status | `{ StatusID, StatusName, StatusOrder }` |
| **Position** | Stores GPS coordinates of an activity | `{ PositionID, PositionActivityID, PositionActivityName, PositionCoordinate, PositionDatetime }` |

If additional object properties are needed, please contact **Graeme Jones**.

### The StaySafe API Endpoints
To be released later in TW19 
