# Rental System – Spring Boot + Thymeleaf

## Περιγραφή
Πληροφοριακό σύστημα διαχείρισης ενοικίασης ακινήτων.  
Αποτελείται από:
- Backend REST API (Spring Boot, Spring Data JPA, Spring Security)
- Frontend UI (Thymeleaf + JavaScript) που καταναλώνει αποκλειστικά το REST API.

## Τεχνολογίες
- Java 17+
- Spring Boot 3.4.9
- Spring Data JPA
- Spring Security 6
- H2 Database
- Thymeleaf
- Maven

## Εκτέλεση
1. Κάνε clone το repository:
   ```bash
   git clone <link-to-repo>
   cd rental-system
   ```
2. Εκτέλεσε με Maven:
   ```bash
   mvn clean spring-boot:run
   ```
   ή:
   ```bash
   mvn clean package
   java -jar target/rental-system-*.jar
   ```

## URLs
- Frontend: http://localhost:8080/ui  
- Λίστα ακινήτων: http://localhost:8080/ui/properties  
- REST API:
  - GET /properties
  - GET /properties/available
  - GET /properties/{id}
  - POST /properties
  - PUT /properties/{id}
  - DELETE /properties/{id}
  - GET /users
  - POST /users

## Demo Users
- Admin → username: `admin`, password: `admin123`  
- User → username: `user`, password: `user123`  

## Demo Data
Φορτώνονται αυτόματα μερικά ακίνητα στην εκκίνηση (μέσω DemoDataInitializer).

## Ασφάλεια
- /properties/** → δημόσια πρόσβαση  
- /users/** → μόνο ROLE_ADMIN  
- Basic Auth για τα προστατευμένα endpoints

## Σημειώσεις
Το Frontend δεν μιλάει απευθείας με τη βάση· όλες οι λειτουργίες γίνονται μέσω REST API.
