package com.event.management.event.management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.event.managment.event.managment.Event;

@CrossOrigin
@RestController
@RequestMapping("/event")
public class EventController {

@GetMapping
public String printStrings() {
return "Legion of Titans";
}

@GetMapping
@RequestMapping("/name")
public String printString() {
return "Rithwik and dilip";

}

@GetMapping
@RequestMapping("/details")
public Sample returnDetails() {
Sample sample = new Sample();
sample.setName("N.Rithwik");
// sample.setId("123");
sample.setType("String");
sample.setVenue("coda");
sample.setDate("2020");
return sample;

}

@GetMapping
@RequestMapping("details/list")
public List<Sample> returnList() {
List<Sample> sampleList = new ArrayList<>();

Sample sample = new Sample();
sample.setName("N.rithwik");
// sample.setId("123");
sample.setType("String");
sample.setVenue("coda");
sample.setDate("2020");
sampleList.add(sample);
return sampleList;
}

@GetMapping
@RequestMapping("/event/list")
public List<Sample> getEvents() throws SQLException {
Connection con = null;
List<Sample> eventList = new ArrayList<>();
try {
Class.forName("com.mysql.jdbc.Driver");
con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample", "root", "root");
Statement stmt = con.createStatement();
ResultSet res = stmt.executeQuery("Select * from event");
while (res.next()) {
Sample event = new Sample();
event.setId(res.getInt("id"));
event.setName(res.getString("name"));
event.setType(res.getString("type"));
event.setDate(res.getString("date"));
event.setVenue(res.getString("venue"));
eventList.add(event);
}
} catch (Exception e) {
e.printStackTrace();
} finally {
con.close();
}
return eventList;
}

@PostMapping
@RequestMapping("/add")
public ArrayList<Event> addString(@RequestBody Event event) throws SQLException{
	
	
	ArrayList<Event> e=new ArrayList<>();
	c(event.getName(),event.getType(),event.getDate(),event.getVenue(),e);
	
	
	return e;
	
}


public void c(String b,String c,String d,String e,ArrayList<Event> l) throws SQLException {
	/*
	Event s=new Event();
	s.setName(b);
	s.setType(c);
	s.setDate(d);
	s.setVenue(e);
	*/
	Connection connection= null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/sample","root","root");
		PreparedStatement pst = connection.prepareStatement("insert into event(name,type,date,venue)values(?,?,?,?)");
		pst.setString(1,b);
		pst.setString(2,c);
		pst.setString(3,d);
		pst.setString(4,e);
		pst.executeUpdate();
		
	}catch(Exception i) {
		i.printStackTrace();
	}finally {
		connection.close();
	}
	System.out.println("done");
}
}


