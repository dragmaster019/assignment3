# assignment3

Here i will write the documentation of api endpoint and how to run-

I have used mongo db database as no sql database. The name of the database is "Assignment". I have use java 17 and maven to build this.

if you want to run this go to src/java/resource -> Application.properties and set port no as your wish i have set to 8080.

run the AssignmentApplication.java file after installing all important dependency it will run sucessfully on port no 8080.
The basic flow and communication between each layer is->

model-> Respository (which is interface type)-> service ->controller.

In model all nessasry entity have described like employee and all the variables is also declarded here along with gater and setter.

After connecting successfully go to postman and run the following API endpoints-> (Find all REST API endpoint at src/java/controller/employeeController)

1. Base URL-> "http://localhost:8080/employee"

2.  To list down all the employee use this URL-> "http://localhost:8090/employee/listallemp" If the employee is not found in the list then NOT_FOUND http req will show. If any external error occurs it will throw an exception BAD_REQUEST.

3. pagination and sorting for employee->  URL-> "http://localhost:8080/employee/listAllpage?sortBy=xyc&pageNo=0&pageSize=3" it takes 3 parameter which is retrive from employee.service class String sortBYy, int PageNo , Integer pageSize. the field is editable you can set as per your requirment. Exception handeling is implemented

4. Show any particular Employee detailts by his ID(@Primary) -> http://localhost:8080/employee/get/ {Give employee id as per your choice you want to see}

5. Employee's Nth manager -> URL-> "http://localhost:8080/employee/getNthManager/ Employee id of any specific employee/ Nth which no of level you want to see ex- 1,2,3...."

6. Create New emloyee-> URL-> http://localhost:8090/api/create this is POST method to create a new employee you can go to Postman body function and type this JSON format:

                                                                        {
                                                    "employeeName": "Shreya",
                                                    "phoneNumber": 1234567,
                                                    "empEmail": "shreya@gmail.com",
                                                    "reportsTo": "2",
                                                    "empImgUrl": "https://n.com"
                                                }


   7. Update exsisting Employee-> URL -> http://localhost:8080/employee/update you can choose any specific employee and update their any specific field.Like phone no but you have pass it through JSON format.
  
                                                            {
                                                                "employee id ":  1 ,
                                                                "phone no" : 98783456,
  

                                                       }

   8. Delete Employee By id : URL-> "http://localhost:8080/employee/delete/1" you can choose any perticular employee id here i have choose 1 and employeeid with 1 will be delete from the database.
  
This is the basic Rest API of the assignment besides this Sl4j has bee used for email function. When we particullary create any employee a automatic email will sent to the 1st level of manager.
Please ensure you are mainting correct email id and structure. 



For logic emplementation you can check service class and for nessasry api end point you can check controller. I hope you can set up the project if you dont feel free to mail at sarthakmondal999@gmail.com.
