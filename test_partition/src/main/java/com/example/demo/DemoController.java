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
	 		+ "<button onclick=\"displayData()\">affiche les partitions</button>"
	 		+ "<script> function displayData() { fetch('/display').then(response => response.text()).then(data => alert(data));}</script>"
	 		+ "<button onclick=\"deleteData()\">supprime une partition</button>"
	 		+ "<script> function deleteData() { fetch('/delete').then(response => response.text()).then(data => alert(data));}</script>"
	 		+ "<button onclick=\"addData()\">ajoute une partition</button>"
	 		+ "<script> function addData() { fetch('/insert').then(response => response.text()).then(data => alert(data));}</script>";
 }
 
 @Autowired
 private JdbcTemplate jdbcTemplate;
 
 @RequestMapping("/display")
 @ResponseBody
 public List<Partitions> display() throws Exception {
	return Partitions.listPartitions(jdbcTemplate);
 }

 @RequestMapping("/delete")
 @ResponseBody
 public String delete() throws Exception{
	try {
		List<Partitions> partitions = Partitions.listPartitions(jdbcTemplate);
		Partitions.deletePartition(partitions.get(partitions.size()-1).getId(), jdbcTemplate);
		return "deleted last element";
	} catch (Exception e) {
		return "erreur lors de la deletion" + e.getMessage();
	}
	 
 }
 
 @RequestMapping("/insert")
 @ResponseBody
 public String insert(){
	Partitions newPartition = new Partitions("nom aleatoire", "lien aléatoire");
	Partitions.createPartition(newPartition, jdbcTemplate);
	return "partition crée";
 }
 
}
