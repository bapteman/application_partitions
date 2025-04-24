package com.example.demo;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public class Partitions {

	String name;
	String lien;
	
	// Constructors
    public Partitions(int id, String name, String lien) {
        this.name = name;
        this.lien = lien;
    }
    
    public Partitions() {
    	
    }

    //getters and setters
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
        String sql = "INSERT INTO partitions (name, lien) VALUES ("
                + "'test', 'test.application')";
         
        //jdbcTemplate.update(sql);
        
        String sql2 = "SELECT * FROM partitions";
        List<Partitions> partitions = jdbcTemplate.query(sql2, (rs, rowNum) -> 
        	new Partitions(
        		rs.getInt("id"), 
        		rs.getString("name"), 
        		rs.getString("lien")
       			)
       		);
        partitions.forEach(partition -> System.out.println(partition));
        return partitions;
           
    }
    
    public static void createPartition(Partitions partition, JdbcTemplate jdbcTemplate) {
    	
    }
}
