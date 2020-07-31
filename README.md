## Introduction

This is a small Quiz app that shows 5 multiple choice questions and displays result at the end.

## Requirements

### User flow
- User clicks the app icon
- Arrives at the home screen which has an image of QUIZ and Start button
- App bar is not visible throught the app
- When user clicks on Start, it opens first question screen
- The Back button is disabled throughout the app
- In all the question screens, user can see
	* 1 question
	* 4 options
	* Next button
	* User can select multiple answers from the options
	* User can not move to next question until atleast one answer is selected
- After all the 5 questions, a Score screen appears that
	* Displays the score
	* Display number of correct answers
	* Display number of incorrect answers
 
 ### Constraints
 - Minimum SDK Version is 21
 - Once the app is started, need to do swipe-out to exit the app

## Technology

- Used Git with a commit history
- Followed standard coding guidelines 
- Implemented Unit Tests
- Used Kotlin
- Used AndroidX 
- Used MVVM arctitecture
- Used ViewModel and LiveData
- Used Retrofit
- Used RxJava

### Architecture & Package Structure

I used the MVVM (Model-View-ViewModel) Architecture for the app. The app is broken down into 3 layers.
There is a clear separation of concerns with each layer only relying on the layer below it.
The UI layer observes the changes in state and behave accordingly. 

![](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)



