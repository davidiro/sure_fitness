SureFitness — Android Fitness Tracking Application

SureFitness is a mobile fitness tracking application built for Android that helps users monitor their health, calculate BMI, set personalized fitness goals, receive reminders, and access curated meal recommendations.
This project demonstrates core Android development concepts including Firebase Authentication, SharedPreferences data persistence, modular UI architecture, and user‑focused health tracking features.

🌟 Features
🔐 User Authentication
Secure account management powered by Firebase Authentication:

User Registration

User Login

Session Management

Persistent User Accounts

📊 BMI Calculator
A built‑in BMI calculator that classifies health categories based on user input:

Height & Weight Input

Automatic BMI Calculation

Health Category Classification

BMI Categories:

Below 18.5 — Underweight

18.5 – 24.9 — Normal

25 – 29.9 — Overweight

30+ — Obese

🎯 Fitness Goal Management
Users can create, update, and track personal fitness goals:

Create Goals

Track Progress

Store Goal Information

Update Targets

Examples:  
Lose Weight • Gain Muscle • Improve Cardio • Daily Exercise Targets

🥗 Meal Planning & Recommendations
Structured nutritional guidance with:

Healthy Meal Suggestions

Categorized Meal Plans

RecyclerView‑based meal list

Smooth navigation between meal categories

⏰ Notifications & Reminders
Built‑in motivational and goal‑tracking reminders:

Workout Reminders

Daily Fitness Motivation

Goal Notifications

👤 User Profile
A personalized profile section where users manage:

Personal Information

Fitness Data

Health‑related details

🧱 Application Architecture
SureFitness follows a modular Android architecture:

Code
User
->
Android UI
->
Activities / Fragments
->
Firebase Authentication
->
Local Storage (SharedPreferences)


🛠️ Technologies Used
Programming

Java

Android SDK

Android Studio

Authentication

Firebase Authentication

Data Storage

SharedPreferences

UI / UX

XML Layouts

Material Design Components

RecyclerView

Bottom Navigation View

Notifications

📂 Project Structure
Code
SureFitness/
│── Activities
│   ├── Login
│   ├── Register
│   └── Main Dashboard
│
│── Fragments
│   ├── Home
│   ├── BMI
│   ├── Goals
│   └── Profile
│
│── Authentication
│   └── Firebase Auth
│
│── Notifications
│
│── SharedPreferences
│
│── Resources
│   ├── Layouts
│   ├── Images
│   └── Strings
🚀 Future Improvements
Planned enhancements include:

Firebase Firestore Database

Workout History Tracking

Progress Analytics Dashboard

Calorie Tracking

Exercise Logging

Cloud Synchronization

Push Notifications

Dark Mode

Data Visualization Charts

Fitness Progress Reports

🎓 Learning Outcomes
Through SureFitness, the following skills were developed:

Android Application Architecture

Firebase Services

Mobile User Authentication

Navigation Components

State Management

Data Persistence

Health & Fitness App Development

UI/UX Design

Object‑Oriented Programming

👤 Author David Irofuala 
SureFitness was developed as a portfolio project showcasing Android mobile development, Firebase integration, user authentication, and health‑focused software solutions.
