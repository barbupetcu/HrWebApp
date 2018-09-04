package com.facultate.disertatie.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.facultate.disertatie.entity.Dept;
import com.facultate.disertatie.entity.RefPriority;
import com.facultate.disertatie.repository.DeptRepository;
import com.facultate.disertatie.repository.RefPriorityRepository;

@RestController
public class DataController{
	
	@Autowired
	private DeptRepository deptRepository;
	
	@Autowired
	private RefPriorityRepository refPriorityRepository;
	
	@RequestMapping(value = "/api/depts", method = RequestMethod.GET)
	public HashMap<String, Object> getDepts(){
		HashMap<String, Object> response = new HashMap<String, Object>();
		response.put("success", false);
		
		List<Dept> depts = deptRepository.findAll();
		
		if (depts != null) {
			response.put("success", true);
			response.put("dept", depts);
		}
		
		return response;
	}
	
	@RequestMapping(value = "/api/priority", method = RequestMethod.GET)
	public List<RefPriority> getPriority(){

		List<RefPriority> priority = refPriorityRepository.findAll();

		return priority;
	}
}