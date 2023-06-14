# Vehicle-Management-System


- Project Summary

This project is a Jakarta EE web application which contains domain model as the persistence layer with EJB as service layer, security structures and configuration.
Vehicle Management system consists of vehicle, Admin, Customer, Booking and location.
Admin owns the any number of vehicles, Customer can book multiple vehicles with multiple bookings for different locations where the vehicles contains booking date, booking time, booking type and price.

- Design

For the business domain I have selected vehicle management system where a user can select and book vehicles from various location. By tracking the customer activity the management can control the cost and availability of the rooms. Below is the entity relationship model of Vehicle Management system which contains entities like Vehicle, VehicleBooking, VehicleAdmin, VehicleCustomer.

One to Many relationships: One Vehicle Admin can have many Vehicle
Many to One relationship: One Vehicle can have many bookings, One Vehicle Admin can manage many bookings, one customer can have many bookings.
Many to Many relationships: Many customers can have many Vehicle and many Vehicle can have many customers.

- Requirements

For this project we are using Apache netbeans as IDE, payara server, Mysql for database and persistence JPA, Maven, Jakarta validation, Junit Jupite, Jboss Logging and opentest4j

- Screen Captures

SignUp
<img width="1440" alt="Screenshot 2023-05-06 at 11 26 03 PM" src="https://user-images.githubusercontent.com/122757532/236658037-e6af91ca-56f0-4306-b1cf-e0ed3ff3d3d0.png">

JavaDoc:
<img width="1440" alt="Screenshot 2023-05-06 at 11 26 17 PM" src="https://user-images.githubusercontent.com/122757532/236658055-46dcd9df-a372-4681-8628-0ed864af0e2f.png">

Login:
<img width="1440" alt="Screenshot 2023-05-06 at 11 27 07 PM" src="https://user-images.githubusercontent.com/122757532/236658075-887d3a21-0578-4911-bc72-8fadb702ea3a.png">

<img width="1436" alt="Screenshot 2023-05-06 at 11 27 39 PM" src="https://user-images.githubusercontent.com/122757532/236658085-0c283415-8519-427c-b650-b486d9e5f8e1.png">

Welcome Page:
<img width="1439" alt="Screenshot 2023-05-06 at 11 27 49 PM" src="https://user-images.githubusercontent.com/122757532/236658091-cf1d6dee-1234-4ea1-bdfb-50f7be1eb160.png">

Admin tab:

 <img width="1438" alt="Screenshot 2023-05-06 at 11 28 00 PM" src="https://user-images.githubusercontent.com/122757532/236658096-4b46fb55-c8ce-45f0-85d7-916c4d1bd5d8.png">

- Expected Results(CRUD Operation And Scheduling Bookings)

<img width="1440" alt="Screenshot 2023-05-06 at 11 28 42 PM" src="https://user-images.githubusercontent.com/122757532/236658120-2532c831-c0e3-4ac3-a8c7-56cbfeac3808.png">

<img width="1438" alt="Screenshot 2023-05-06 at 11 28 51 PM" src="https://user-images.githubusercontent.com/122757532/236658126-ac53785a-a1ea-4df2-a5b4-9f2a15473df2.png">

<img width="1440" alt="Screenshot 2023-05-06 at 11 28 59 PM" src="https://user-images.githubusercontent.com/122757532/236658128-a2396a99-3a9d-4ee8-be9f-18b3ef5799c7.png">

<img width="1440" alt="Screenshot 2023-05-06 at 11 29 17 PM" src="https://user-images.githubusercontent.com/122757532/236658133-d375b036-d26e-4bc5-9464-e935ff0c8436.png">

<img width="1440" alt="Screenshot 2023-05-06 at 11 29 28 PM" src="https://user-images.githubusercontent.com/122757532/236658138-ead99d0c-4265-4476-8fce-0e11810125f7.png">

<img width="1439" alt="Screenshot 2023-05-06 at 11 29 39 PM" src="https://user-images.githubusercontent.com/122757532/236658142-15eb8c82-6da4-4be7-a016-537a0c78d34c.png">

<img width="1440" alt="Screenshot 2023-05-06 at 11 31 10 PM" src="https://user-images.githubusercontent.com/122757532/236658149-fcff397f-88a5-45c5-b48d-f31553f32622.png">

Login:
username: vehicleAdmin1
password: vehicleAdmin1

username: vehicleAdmin2
password: vehicleAdmin2

username: customer1
password: customer1

username: customer2
password: customer2

Sign Up:
username: Krishna
password: Krishna

- Development Insights

This section will display all the details about the vehicle, admin, customer and the all the details related to the bookings.
This particular course including the project helped me enhance my knowledge related to JAVA EE specifications, database persistence, security and persentation components. I understood extensively aboutg. JPA Query Language, JPA annotations, authorizationa and authentication and I also learnt how webservices can be produced based on EJB components many other concepts.

ALthough I am new to Java, I loved the concepts and the way you taught. I understood more about many concepts in Java and specially after doing the project and the assignments, I am sure I will be using Java and many other concepts well in future and also would learn more about hibernate.
Thank a lot prof for your support and the classes you taught will be really helpful for me in future.
