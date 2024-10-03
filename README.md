## Database

- The database contains a single table, **USER**, which stores information about users (e.g., name, email).
- Each record includes a **processed** field (boolean) to indicate whether the record has been processed.

## REST API

- The application exposes a REST endpoint `/users` to accept user information in JSON format and store it in the database.
- The endpoint accepts **POST** requests with the user details and inserts new records into the **USER** table.

## Database Polling

- A scheduled task polls the **USER** table periodically (every 5 seconds) to find unprocessed users.
- Once unprocessed records are found, they are processed (printing user details to the console) and marked as processed.

## Processing Records

- To ensure that each record is processed **exactly once**, even in a cloud environment with multiple pods, the application must avoid duplicate processing due to concurrency issues.
- I have opted for the **PESSIMISTIC_WRITE** locking mechanism instead of using a `@Version` field with optimistic locking.
  - This approach prevents concurrency issues by locking the record for processing, ensuring that no other pods can access it until the processing is complete.

## To Build & Run

- **Build the project:**
    - Run the following command to clean and install dependencies:
      - mvn clean install



- **Run the application:**
    - Once built, execute the following command to start the application:
      - mvn spring-boot:run
      

  
