# Overview
JPHP(or Java PHP) is a Java API to make Java and PHP server-side connection simpler.
Proper documentation is provided below the README.

# Source Code Setup
1. Copy and paste the jphp.jar file into your project's lib folder  
2. In your Java IDE, head on over to the "Project" tab -> "Java Build Path" ->  "Add Jar" -> Add the .jar file
3. Finished!  

# Documentation
Create an example.php PHP file:
```
<?php 
$test1 = $_POST['test1'];
$test2 = $_POST['test2'];
echo("success!");
?>
```
In Java, import the PHPConnection class
```
import com.shadowsych.connection.PHPConnection;
```
Set up a HashMap to send variables to the example.php file
```
Map<String,String> sendVariables = new HashMap<String,String>();  
sendVariables.put("test1", "This is the first test!");  
sendVariables.put("test2", "This is the second test!");  
```
Make a PHPConnection instance, and send a request
```
//you can use either POST or GET requests, this example uses POST requests
PHPConnection phpConn = new PHPConnection();
String results = phpConn.HTTPRequest("www.yourwebsite.com/example.php", "POST", sendVariables);
```
Finally, read the output from the PHP file (it should output "success!")
```
System.out.println(results);
```