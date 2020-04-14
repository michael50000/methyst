package com.Clover.springboot.controller;

import java.util.List;

import org.hibernate.Session;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Clover.springboot.HibernateUtil.HibernateUtil;
import com.Clover.springboot.Model.Student;

@RestController
public class JsonController {
	
	@RequestMapping(value = "/api/getStudents", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Student> getStudents() {
	    
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
           return session.createQuery("from Student", Student.class).list();
        }
	}
	


}
