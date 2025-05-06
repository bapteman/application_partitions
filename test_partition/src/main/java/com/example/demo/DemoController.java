package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
//Importing required classes
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	 
	 return   "<h1> application partitions <h1>"
			 
			 //affichage de toutes les partitions
			+ "<h2> afficher toute les partitions <h2>"
	 		+ "<button onclick=\"displayData()\">affiche les partitions</button>"
	 		+ "<script> function displayData() { "
	 		+ "fetch('/display').then(response => response.text()).then(data => alert(data));}</script>"
	 		
	 		//suppresion de partitions (pour l'instant derniere en date)
	 		+ "<h2> supprimer partitions <h2>"
	 		+ "<input type=\"number\" id=\"partitionToDeleteId\" placeholder=\"Enter partition ID\" />"
	 		+ "<button onclick=\"deleteData()\">supprime une partition</button>"
	 		+ "<script> function deleteData() { const partitionToDeleteId = document.getElementById(\"partitionToDeleteId\").value;"
	 		+ "fetch(`/delete?partitionId=${partitionToDeleteId}`).then(response => response.text()).then(data => alert(data));}</script>"
	 		
	 		//ajout d'une nouvelle partition 
	 		+ "<h2> ajouter une partition <h2>"
	 		+ "<input type = \"text\" id = \"newPartitionName\" placeholder = \"new Partition name\"/>"
	 		+ "<input type = \"text\" id = \"newPartitionLink\" placeholder = \"new Partition link\"/>"
	 		+ "<button onclick=\"addPartition()\">ajoute une partition</button>"
	 		+ "<script> function addPartition() { "
	 		+ "const newPartitionName = document.getElementById(\"newPartitionName\");"
	 		+ "const newPartitionLink = document.getElementById(\"newPartitionLink\");"
	 		+ "fetch(`/insert?newPartitionName=${newPartitionName.value}&newPartitionLink=${newPartitionLink.value}`).then(response => response.text()).then(data => alert(data));}</script>"
	 		
	 		//affichage d'une partition par son ID TODO : changer le filtre par le nom et en retourner plusieurs si plusieurs matchs (avec genre contains())
	 		+ "<h2> afficher une partition specifique<h2>"
	 		+ "<input type=\"number\" id=\"partitionId\" placeholder=\"Enter partition ID\" />"
	 		+ "<button onclick=\"displayOne()\">affiche la partition</button>"
	 		+ "<script> function displayOne(){"
	 		+ "const partitionId = document.getElementById('partitionId').value;"
	 		+ "if(!partitionId){alert(\"enter an id\"); return;}"
	 		+ "fetch(`/displayOne?partitionId=${partitionId}`).then(response => response.text()).then(data => alert(data));}</script>"
	 		
	 		//style
	 		+ "<style> button : margin-right 10px </style>"
	 		;
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
 public String delete(@RequestParam("partitionId") int partitionId) throws Exception{
	try {
		if(Partitions.getPartitionById(partitionId, jdbcTemplate).getId() != -1) {
			Partitions.deletePartition(partitionId, jdbcTemplate);
			return "deleted element with id : " + partitionId;
		} else {
			return "no element with id : " + partitionId;
		}
		
	} catch (Exception e) {
		return "erreur lors de la deletion" + e.getMessage();
	}
	 
 }
 
 @RequestMapping("/insert")
 @ResponseBody
 public String insert(@RequestParam("newPartitionName") String newPartitionName, @RequestParam("newPartitionLink") String newPartitionLink){
	Partitions newPartition = new Partitions(newPartitionName, newPartitionLink);
	Partitions.createPartition(newPartition, jdbcTemplate);
	return "partition crée";
 }
 
 @RequestMapping("/displayOne")
 @ResponseBody
 public String displayOne(@RequestParam("partitionId") int partitionId) throws Exception {
	Partitions partition = Partitions.getPartitionById(partitionId, jdbcTemplate);
	if(partition.getId() == -1) {
		return "partition inexistante, verifiez l'index";
	} else {
		return partition.toString();
	}
 }
 
}
