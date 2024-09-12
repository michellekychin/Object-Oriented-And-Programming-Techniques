# Metrobus Ticketing System

This repository contains the **Metrobus Ticketing System**, a Java-based project developed as part of the **Object-Oriented Programming Techniques** course (AACS2204) at Tunku Abdul Rahman University of Management and Technology. The system is designed to automate ticket sales and bus operations, providing an efficient solution for managing passengers and transactions.

## Introduction
The Metrobus Ticketing System is designed to facilitate the sale and management of bus tickets. It allows both **customers** and **administrators** to interact with the system through separate interfaces. The system handles customer registrations, ticket purchases, payment processing, and the generation of receipts. Additionally, administrators can log in to view all transaction histories.

This project demonstrates key object-oriented programming concepts such as encapsulation, polymorphism, inheritance, and aggregation.

## Features
- **User Registration:** Customers can create accounts and log in to purchase tickets.
- **Admin Interface:** Admins can view transaction histories and manage the system.
- **Ticket Management:** Customers can select ticket types (daily, weekly, monthly) and purchase them.
- **Payment Processing:** Supports payment with debit cards.
- **Bus Operation View:** Customers can view the operational schedule and routes of buses.
- **Error Handling:** Robust error handling with exception management.
  
## Class Diagram  
The system consists of several classes:  
- **User (Superclass)**: Inherited by `Admin` and `Customer`.
- **Admin**: Handles system management and transaction viewing.
- **Customer**: Manages ticket purchases and payments.
- **Ticket**: Represents different ticket types (OneDayTicket, WeeklyTicket, MonthlyTicket).
- **Transaction**: Handles ticket purchases and transaction history.
- **Payment**: Manages the payment process and receipt generation.

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/Metrobus-Ticketing-System.git
   ```
2. Compile the Java files:
   ```bash
   javac *.java
   ```
3. Run the system:
   ```bash
   java Main
   ```

## Usage
- **Customer Usage:**
  - Register as a customer.
  - Log in with your credentials.
  - View available tickets, purchase tickets, and view bus operation schedules.
  
- **Admin Usage:**
  - Log in as an admin using the default credentials.
  - View transaction histories of all customers.

## Contributors
- **Elisha Tiong Pei Pei** - Transaction class, admin interface.
- **Natalie Koa Hao Yee** - Admin, customer, user classes, customer interface.
- **Michelle Chin Koh Ying** - Payment class, ticket purchase interface.
- **Tan Shieh Ling** - Ticket class, menu interfaces.
