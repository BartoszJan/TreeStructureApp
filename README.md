# TREE-STRUCTURE-APP

This application is an tool for build and manage tree structure. User can add new and modify nodes and structure.
In nodes values are stored numbers. Tree leaves represent the sum from all nodes on the way to the root

### The program use technologies:
*   Java 8
*   Spring Boot, Spring MVC
*   Angular 7.0
*   MySQL database

## Project Run

To install this application, run the following commands:

```bash
git clone https://github.com/BartoszJan/TreeStructureApp
```

This will get a copy of the project installed locally. To install all of its dependencies and start each app, follow
the instructions below.

To run the server first you need set up database connection settings in src/main/resources/application.properties.
Then run in project directory:

```bash
mvn spring-boot:run
```

To run the client go to tree-web directory and run:

```bash
npm install && npm start
```