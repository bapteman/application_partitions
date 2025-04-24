package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
//Importing required classes
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//Annotation
@Controller

//Class
public class DemoController {
	
	
	
 @RequestMapping("/hello")
 @ResponseBody

 // Method
 public String helloWorld()
 {

     // Print statement
     return "<h1>Hello World!<h1>"
     		+ "<h2> test application <h2>"
     		+ "<button onclick=\"window.location.href='/test';\">test</button>";
 }
 
 @RequestMapping("/test")
 @ResponseBody
 
 public String test() {
	 
	 return "<h1> test <h1>"
	 		+ "<h2> test application <h2>"
	 		+ "<button onclick=\"callJavaMethod()\">Click Me!</button>"
	 		+ "<script> function callJavaMethod() { fetch('/buttonClick').then(response => response.text()).then(data => alert(data));}</script>";
 }
 
 @Autowired
 private JdbcTemplate jdbcTemplate;
 
 @RequestMapping("/buttonClick")
 @ResponseBody
 public List<Partitions> handleButtonClick() throws Exception {
     // Call your Java method here
	 return Partitions.listPartitions(jdbcTemplate);
 }

 
 
}
