/*
<!--
Employee Management WebSocket Application

Objective:
-----------
Your task is to develop a WebSocket-based Employee Management System using Node.js. 
This system will allow clients to:
    1. Insert Employee Records (INSERT <name> <salary>)
    2. Retrieve Employee List (RETRIEVE)
    3. Handle Invalid Commands (e.g., INVALID should return "Invalid command")
Your goal is to implement and test a WebSocket-based server and client, 
ensuring that all operations work correctly.

Requirements:
-------------
1. Implement WebSocket Server
	The server should:
		-> Accept multiple client connections.
		-> Process client messages and handle commands:
			1. INSERT <name> <salary> → Adds an employee to an in-memory array.
			2. RETRIEVE → Returns all stored employees.
			3. Any other command should return "Invalid command."
		-> Maintain an in-memory array of employees (no database required).
		-> Log each received command on the console.
		
		
Expected Behavior
-----------------

============================================================================================
Client Command			Server Response
============================================================================================
INSERT Alice 50000		"Employee inserted successfully."
INSERT Bob 60000		"Employee inserted successfully."
RETRIEVE				"ID: 1, Name: Alice, Salary: 50000"
                        "ID: 2, Name: Bob, Salary: 60000"
INVALID					"Invalid command."
============================================================================================

Note: 
-> The server should run on port 8080.
-> The system should allow multiple clients to connect.


EXAMPLE URL value=>   ws://10.11.xx.xx:8080
-->

*/
const WebSocket = require('ws');
const server = new WebSocket.Server({ port: 8080 });
const mongoose = require("mongoose");

mongoose.connect("mongodb://localhost:27017/FS_Elite", {
  useNewUrlParser: true,
  useUnifiedTopology: true,
})
  .then(() => console.log("Connected to MongoDB"))
  .catch((err) => console.error("MongoDB connection error:", err));


const employeeSchema = new mongoose.Schema({
    ID:Number,
    name: String,
    salary: Number,
    role: String,
    department: String,
    experience: Number
});

const employee = mongoose.model("Employee", employeeSchema);

server.on('connection', (socket) => {
    console.log('New client connected');

    socket.on('message', async(mess) => {
        const message = mess.toString()
        console.log("Message received: " + message);

        const parts = message.split(" ");
        console.log(parts);
        if (parts[0] === 'INSERT' && parts.length === 6) {
            let allObj = await employee.find({});
            let newIdx = allObj.length+1;
            const name = parts[1];
            const salary = parseInt(parts[2]);
            const role = parts[3];
            const department = parts[4];
            const experience = parseInt(parts[5]);
            if (isNaN(salary) || salary <= 0 || isNaN(experience)) {
                console.log("Invalid salary.");
                socket.send("Invalid salary.");
                return;
            }
            let newObj = new employee();
            newObj.ID=newIdx;
            newObj.name=name;
            newObj.salary=salary;
            newObj.role=role;
            newObj.department=department;
            newObj.experience=experience;
            await newObj.save();
            socket.send(`ID: ${newObj.ID}`);
        } 
        else if (parts[0] === 'RETRIEVE' && parts.length===1) {
                let employees = await employee.find({});
                employees.forEach((emp) => {
                    socket.send(`ID: ${emp.ID},Name: ${emp.name}, Salary: ${emp.salary}, Role: ${emp.role}, Department: ${emp.department}, Experience: ${emp.experience} years`);
                });
            
        } 
        else if(parts[0]==='RETRIEVE_BY_DEPT' && parts.length===2){
            const newDept = parts[1];
            let extractedObj = await employee.find({department:newDept});
            console.log(extractedObj);
            extractedObj.forEach((emp) => {
                socket.send(`ID: ${emp.ID},Name: ${emp.name}, Salary: ${emp.salary}, Role: ${emp.role}, Department: ${emp.department}, Experience: ${emp.experience} years`);
            });
        }
        else {
            socket.send("Invalid command.");
        }
    });

    socket.on('close', () => {
        console.log("Client Disconnected.");
    });
});

console.log('WebSocket server started at ws://localhost:8080');
