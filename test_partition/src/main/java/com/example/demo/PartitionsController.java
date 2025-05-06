package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PartitionsController {
	
	@Autowired
	 private JdbcTemplate jdbcTemplate;

    // standard constructors
    

    @GetMapping("/partitions")
    public List<Partitions> getUsers() throws Exception {
        return Partitions.listPartitions(jdbcTemplate);
    }

    @PostMapping("/partitions")
    public String addPartitions(@RequestParam ("name") String name, @RequestParam("lien") String lien) {
    	Partitions newPartition = new Partitions(name, lien);
    	return Partitions.createPartition(newPartition, jdbcTemplate);
    }
    
    @DeleteMapping("/partitions")
    public String delete(@RequestParam("partitionId") int partitionId) throws Exception{
    	return Partitions.deletePartition(partitionId, jdbcTemplate);    	 
     }
    
    @GetMapping("/onePartitions")
    public Partitions displayOne(@RequestParam("partitionId") int partitionId) throws Exception {
    	Partitions partition = Partitions.getPartitionById(partitionId, jdbcTemplate);
    	if(partition.getId() == -1) {
    		System.out.println("partition inexistante");
    		return partition;
    	} else {
    		return partition;
    	}
     }
}
