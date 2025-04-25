package com.example.demo;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public class Partitions {

	int id;
	String name;
	String lien;
	
	// Constructors
    public Partitions(int id, String name, String lien) {
    	this.id = id;
    	this.name = name;
        this.lien = lien;
    }
    
    public Partitions(String name, String lien) {
    	this.name = name;
        this.lien = lien;
    }
    
    public Partitions() {
    	
    }

    //getters and setters
    public int getId() {
    	return id;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    @Override
    public String toString() {
        return "Partition{name='" + name + "', lien='" + lien + "'}";
    }
    
    
    
    
    public static List<Partitions> listPartitions(JdbcTemplate jdbcTemplate) throws Exception {
       
        
        String sql2 = "SELECT * FROM partitions";
        List<Partitions> partitions = jdbcTemplate.query(sql2, (rs, rowNum) -> 
        	new Partitions(
        		rs.getInt("id"), 
        		rs.getString("name"), 
        		rs.getString("lien")
       			)
       		);
        return partitions;
           
    }
    
    public static void displayPartitions(JdbcTemplate jdbcTemplate) throws Exception {
    	listPartitions(jdbcTemplate).forEach(partition -> System.out.println(partition));
    }
    
    public static void createPartition(Partitions partition, JdbcTemplate jdbcTemplate) {
    	 String sql = "INSERT INTO partitions (name, lien) VALUES ('"
                 + partition.getName() + "','" + partition.getLien()  + "')";
          
         jdbcTemplate.update(sql);
    }
    
    public static void deletePartition(int id, JdbcTemplate jdbcTemplate) {
    	 String sql = "DELETE FROM partitions WHERE id = " + id;
          
         jdbcTemplate.update(sql);
         System.out.println("last element deleted");
    }
}
