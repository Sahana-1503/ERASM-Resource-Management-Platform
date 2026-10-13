# Enterprise Resource Allocation and Skill Management System (ERASM)

## Project Overview

The Enterprise Resource Allocation and Skill Management System (ERASM) is a Spring Boot-based application designed to manage employee information, skills, certifications, projects, resource requests, and project allocations within an organization.

The system provides REST APIs for managing resources, tracking employee utilization, and generating reports. It also incorporates authentication, authorization, exception handling, and application logging.

## Problem Statement

Organizations often face challenges in managing employee skills, project requirements, and resource allocation efficiently. Manual processes can lead to resource conflicts, poor visibility into employee availability, and difficulties in tracking skills and project assignments.

ERASM addresses these challenges by providing a centralized platform for employee skill management, resource allocation, project management, and utilization reporting.

## Objectives

- Centralize employee and skill information.
- Manage projects and their resource requirements.
- Track employee certifications and skills.
- Prevent employee allocations from exceeding 100%.
- Manage the resource request approval workflow.
- Generate skill, utilization, and project allocation reports.
- Provide secure access to application APIs.
- Maintain application logs and handle exceptions consistently.

## Technologies Used

| Technology | Purpose |
|---|---|
| Java 17 | Backend programming |
| Spring Boot | Application development |
| Spring Data JPA | Database operations |
| Hibernate | ORM |
| MySQL | Relational database |
| Spring Security | Application security |
| JWT | Token-based authentication |
| Maven | Dependency and build management |
| JUnit 5 | Unit testing |
| Mockito | Dependency mocking |
| JaCoCo | Code coverage analysis |
| Postman | API testing |
| Git and GitHub | Version control |

## Main Modules

- **User Management:** User registration and account management.
- **Role Management:** Management of application roles.
- **Employee Management:** Employee details and availability.
- **Skill Management:** Skill information and employee skill mapping.
- **Certification Management:** Employee certifications and issuing organizations.
- **Project Management:** Project details, clients, technologies, and budgets.
- **Resource Allocation:** Employee allocation to projects with allocation percentage validation.
- **Resource Requests:** Creation and status management of project resource requests.
- **Reporting:** Skill reports, employee utilization reports, and project allocation reports.
- **Security:** Authentication and authorization using the configured security mechanisms.
- **Audit Logging:** Activity tracking, where implemented.

## Database Design

The application uses MySQL for persistent data storage. The database contains entities for users, roles, employees, skills, employee skills, certifications, projects, allocations, resource requests, and audit logs, according to the implemented schema.

The database creation script and SQL scripts are provided separately with the project deliverables.

## Project Structure

```text
ERASM/
├── src/
│   ├── main/
│   │   ├── java/com/erasm/
│   │   │   ├── controller/
│   │   │   ├── service/
│   │   │   ├── service/implementation/
│   │   │   ├── repository/
│   │   │   ├── entity/
│   │   │   ├── dto/
│   │   │   ├── mapper/
│   │   │   ├── security/
│   │   │   ├── exception/
│   │   │   └── response/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── .gitignore
├── pom.xml
└── README.md
```

## Prerequisites

- Java Development Kit (JDK) 17
- Maven
- MySQL Server and MySQL Workbench
- Postman
- Git

## Setup and Installation

1. Clone the repository:

   ```bash
   git clone <YOUR_GITHUB_REPOSITORY_URL>
   ```

2. Navigate to the project directory:

   ```bash
   cd ERASM
   ```

3. Create the required MySQL database and execute the provided database creation script.

4. Configure the database URL, username, and password in `src/main/resources/application.properties`. Use your local database credentials and do not commit passwords or other secrets to GitHub.

5. Build the application:

   ```bash
   ./mvnw clean install
   ```

   On Windows, use:

   ```bash
   mvnw.cmd clean install
   ```

6. Run the application:

   ```bash
   ./mvnw spring-boot:run
   ```

   On Windows, use:

   ```bash
   mvnw.cmd spring-boot:run
   ```

7. Send requests to the API endpoints configured in the application's controllers.

## Testing

The project uses JUnit 5 and Mockito for automated testing and JaCoCo for code coverage analysis. Postman is used for API testing.

Run the automated tests on Windows using:

```bash
mvnw.cmd clean test
```

Generate the JaCoCo report using:

```bash
mvnw.cmd verify
```

Open the generated JaCoCo `index.html` report under `target/site/jacoco/` if the configured Maven build generates it.

The previously generated JaCoCo report showed approximately 86% overall instruction coverage. Refer to the latest generated report for the final coverage result.

## Deliverables

The project submission includes the following items, as applicable:

- Complete Java source code
- GitHub repository
- Project documentation in PDF or Word format
- Database creation script and SQL scripts
- Postman collection containing the implemented API requests
- Test cases and test report
- JUnit test execution report
- JaCoCo code coverage report
- Application and API testing screenshots

## Git Branching Strategy

The repository follows the requested branch naming convention:

- `main` — stable project version
- `develop` — integration branch
- `feature/*` — new functionality
- `release/*` — release preparation
- `hotfix/*` — urgent fixes

Commit messages follow conventional prefixes such as `feat:`, `fix:`, `refactor:`, `test:`, and `docs:`.

## Future Enhancements

- Develop a responsive frontend.
- Introduce intelligent employee-to-project recommendations.
- Add email notifications for resource request updates.
- Provide downloadable PDF and Excel reports.
- Support cloud deployment and enhanced analytics.

## Author

Developed as part of the Enterprise Resource Allocation and Skill Management System training project.
