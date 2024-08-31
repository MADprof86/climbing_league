Climbing League Application
This project is a web application for managing climbing competitions. It allows users to create and edit competitions, manage boulders, and view results.
Aplication is currently inder developement.

Table of Contents
Features
Installation
Usage
Technologies
Contributing
License
Features
Competition Management: Create, edit, and view climbing competitions.
Boulder Management: Add, edit, and delete boulders associated with competitions.
Validation: Ensure that boulders linked to competition results cannot be deleted.
User Management: Basic user management with Spring Security.
Responsive Design: Uses Bootstrap for responsive design.
Installation
Clone the repository:


Skopiuj kod
git clone https://github.com/your-username/climbing-league.git
cd climbing-league
Install dependencies: Make sure you have Maven and Java installed. Then, run:


Skopiuj kod
mvn install
Set up the database: This application uses MySQL. Set up your database and configure your application.properties file with the correct database connection details.

Run the application:


Skopiuj kod
mvn spring-boot:run
Usage
Access the application: After running the application, it will be accessible at http://localhost:8080.

Login: Default credentials (you may want to change this in production):

Username: admin
Password: admin
Create/Edit Competitions: Navigate to the "Competitions" section where you can create and manage climbing competitions.

Manage Boulders: Within each competition, you can add, edit, or delete boulders. Note that boulders associated with competition results cannot be deleted directly.

Error Handling: The application displays user-friendly messages if any operation fails, like attempting to delete a boulder in use.
