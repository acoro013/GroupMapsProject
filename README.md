# APPLICATION NAME

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)

## Overview
### Description
Finds locations of interest: events, tourist attractions, entertainment locations.

### App Evaluation
- **Category:** Maps & Navigation, Travel & Local
- **Mobile:** This app would be primarily developed for mobile but would perhaps be just as viable on a computer. Functionality wouldn't be limited to mobile devices, however mobile version could potentially have more features.
- **Story:** Finds locations of interest.
- **Market:** Any individual could choose to use this app.
- **Habit:** This app could be used as often or unoften as the user wanted depending on how deep their social life is, and what exactly they're looking for.
- **Scope:** Firstly, finds locations of interest (maybe based on location and time). Secondly, launches Google Map.

## Product Spec
### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* Gets locations of interest
* Gets distance
* Details of locations of interest
* Launches Google Map

**Optional Nice-to-have Stories**

* User logs in or Guest account
* Profile pages for each user
* Profile images
* Settings (Accessibility, Notification, General, etc.)
* Preference settings
* Saves chosen location of interest
* Gets weather condition
* Set reminder for events
* Reviews
* Organizes events between users

### 2. Screen Archetypes

**Required Must-have Screens**

* Search Screen
* Location Detail Screen

**Optional Nice-to-have Screens**

* Login 
* Register - User signs up or logs into their account
   * Upon Download/Reopening of the application, the user is prompted to log in or use a Guest account.
* Profile Screen 
   * Allows user to upload a photo and fill in users' information that is interesting to them and others.
* Settings and Preference Screen
   * Lets people change language, and app notification settings.
* Saved Screen
   * Lets user see saved locations of interest.
* Events Screen
   * Lets user create events and see other users' events.
   * Event Detail

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Search

Optional:
* Saved
* Profile
* Settings
* Events

**Flow Navigation** (Screen to Screen)
* Forced Log-in -> Account creation or Guest account if no log in is available
* Search Screen is the main screen after Login
* Profile -> Text field to be modified. 
* Settings -> Toggle settings

## Wireframes
* These are our wireframes
<img src='https://github.com/acoro013/GroupMapsProject/blob/master/wireframeOne.jpg' title='Video Walkthrough' width='' alt='Video Walkthrough' />
<img src='https://github.com/acoro013/GroupMapsProject/blob/master/wireframeTwo.jpg' title='Video Walkthrough' width='' alt='Video Walkthrough' />

## Schema 
### Models
#### Location of Interest

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | objectId      | String   | unique id for the location of interest (default field) |
   | image         | File     | image representing the location of interest |
   | longitude     | String   | longitude of location of interest |
   | latitude      | String   | latitude of location of interest |
   | description   | String   | description of location of interest |
   | type          | String   | classification of location of interest |
   | time          | DateTime | time of location of interest (optional) |
   
#### Events

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | objectId      | String   | unique id for the event (default field) |
   | user          | User     | creator of the event |
   | locationOfInterest | Location of Interest | location of interest of the event |
   | description   | String   | description of location of interest |
   | time          | DateTime | time of event |
   
### Networking
#### List of network requests by screen
   - Search Screen
      - (Read/Get) Query location of interest
   - Save Screen
      - (Create/Post) Create the location of interest in the app's database
   - Events Screen
      - (Create/Post) Create the event
      - (Read/Get) Query events
   - Profile Screen
      - (Read/GET) Query logged in user object
      - (Update/PUT) Update user profile image
