## Sommaire

* [Status](#status)
* [Répartition des points](#répartition-des-points)

## Status
| Date | Planned | Achieved | Blockers/Risk | 🟩🟧🟥 Flag |
| :--- | :--- | :--- | :--- | :---: |
| 08-02-22<br />(week 06) | <ul><li>Multiple drone leader conflict management</li><li>Add a scenario in the CI with disconnection and dynamic election of a leader</li></ul>| <ul><li>Dynamic election of the leader</li><li>User interface to visualize the truck, drones and connections</li></ul> | <ul><li>Manage the case of a lost drone in a fleet</li></ul> | 🟩 |
| 01-02-22<br />(week 05) | <ul><li>Dynamic election of the leader</li><li>Multiple drone leader conflict management</li><li>User interface to visualize the truck, drones and connections</li></ul>| <ul><li>Simulator to manage disconnections between drones automatically</li><li>Disconnection Drone Leader - Truck</li><li>Dispatch of drones in a fleet</li></ul> | <ul><li>Change the coordinate system to fit the user interface</li></ul> | 🟩 |
| 25-01-22<br />(week 04) | <ul><li>Disconnection Drone Leader - Truck</li><li>Dynamic election of the leader</li><li>Dispatch of drones in a fleet</li></ul>| <ul><li>Disconnection Drone Follower - Truck</li><li>Change of the direction of communication : drone -> truck</li><li>Update CI</li></ul> | <ul><li>Create a simulator to manage disconnections between drones automatically</li></ul> | 🟩 |
| 18-01-22<br />(week 03) | <ul><li>Change of the direction of communication : drone -> truck</li><li>Air traffic control compliance: a lost drone does not move</li><li>Disconnection Drone Follower - Truck</li></ul>| <ul><li>Definition of new interfaces </li><li>Refactoring of the drone</li><li>The truck can provide assignments for a fleet</li></ul> | <ul><li>Refactoring more important than expected</li> | 🟩 |
| 11-01-22<br />(week 02) | <ul><li>Delivery of several packages in a fleet</li><li>Air traffic control compliance: a lost drone does not move</li></ul>| <ul><li>Adding new scenarios</li><li>Architecture Update</li><li>Sprints Plannification</li></ul> | <li>The new architecture must still allow delivery by a single drone</li><li>The case where the leading drone falls is not yet handled</li> | 🟩 |
| 02-11-21<br />(week 44) | <ul><li>Report</li></ul>| <ul><li>Deliveries by big drones</li><li>Cancel deliveries too far away</li><li>Prepare Demo</li><li>Improve Logs for Demo</li><li>Fix the sending of notifications when the drone delivers and is disconnected</li></ul> | | 🟩 |
| 26-10-21<br />(week 43) | <ul><li>Deliveries by big drones</li><li>Warehouse Notification lost drone</li><li>Cancel deliveries too far away</li><li>Prepare Demo</li><li>Report</li></ul> | <ul><li>Scenario in CI</li><li>Resilient Notifications</li></ul> | <ul><li>Hard to test architecture (need to add some routes just for testing)</li></ul> | 🟩 |
| 19-10-21<br />(week 42) | <ul><li>Deliveries by drones (one drone <-> one package)</li><li>Simple Scheduling && Flight Plan</li><li>Important Drone Tracking</li><li>Basic Notifications</li><li>CI End to End </li></ul> | <ul><li>Deliveries by drones (one drone <-> one package)</li><li>Simple Scheduling && Flight Plan</li><li>Important Drone Tracking</li><li>Basic Notifications</li></ul> | <ul><li>In-memory Database</li><li>Persistence Issues : error while testing with some os</li><li>Persistence Issues : difficulties with Cascading</li></ul> | 🟩 |
| 12-10-21<br />(week 41) | <ul><li>Correction Epics/Sprints</li><li>Review architectural justifications</li><li>Projet Setup</li><li>First delivery by drone</li></ul> | <ul><li>Correction Epics/Sprints</li><li>Review architectural justifications</li><li>Projet Setup</li></ul> | <ul><li>Technological risk: our technological choices are heavy and will require time for configuration</li></ul> | 🟧 |
| 05-10-21<br />(week 40) | <ul><li>Add Personas to Users</li><li>Create Epics, US, Acceptance criteria</li><br /><li>Create component diagram</li><li>Create architecture diagram</li><li>Plan roadmap</li><li>Choose our technologies</li></ul> | <ul><li>Add Personas to Users</li><li>Create Epics, US, Acceptance criteria</li><br /><li>Create component diagram</li><li>Create architecture diagram</li><li>Plan roadmap</li><li>Choose our technologies</li></ul> | <ul><li>Manage the timeout for considering drones lost</li><li>Scheduling that is only triggered when a drone returns and not at regular intervals.</li><li>The truck can overload when there are too many drones</li><li>Data persistence between modules</li><li>Simulate the synchronisation between the drones and the truck : Physical contrainst of the POC (can't use ethernet cables for demo)</li></ul> | 🟩 |
| 28-09-21<br />(week 39)| <ul><li>Define users and scope</li><li>Description scenario</li><li>Identify risk</li></ul> | <ul><li>Define users and scope</li><li>Description scenario</li><li>Identify risk</li></ul> | | 🟩 |




## Répartition des points
| Bruel Martin | Esteve Thibaut | Lebrisse David | Meulle Nathan | Ushaka Kevin |
|:------------:|:--------------:|:--------------:|:-------------:|:------------:|
|     100      |      100       |      100       |      100      |     100      |
