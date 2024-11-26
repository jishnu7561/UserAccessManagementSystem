# User Access Management System

The User Access Management System is a web-based application designed to manage user access to various software applications within an organization. It provides an efficient way to handle user roles, access requests, and approvals. This system enhances security and streamlines the process of managing user access. This is a basic model of User Access Management.


## Technologies Used

- **Backend** : Java Servlets
- **Frontend** : JavaServer Pages (JSP), HTML, CSS,   JavaScript
- **Database** : PostgreSQL
- **Build Tool** : Maven
- **Server** : Apache Tomcat


### Database Script

 *CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
    role TEXT NOT NULL
);*

 *CREATE TABLE software (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    description TEXT,
    access_levels TEXT
);*

 *CREATE TABLE requests (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(id),
    software_id INTEGER REFERENCES software(id),
    access_type TEXT,
    reason TEXT,
    status TEXT
);*
### Setup Instructions
#### Prerequisites
- JDK 17 or later
- Apache Tomcat 9
- PostgreSQL
- Maven

