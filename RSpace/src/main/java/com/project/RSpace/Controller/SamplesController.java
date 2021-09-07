package com.project.RSpace.Controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.RSpace.Object.Sample;
import com.project.RSpace.Service.HttpClientService;

@Controller
public class SamplesController {
	
	@Autowired
	HttpClientService client;
	
	@SuppressWarnings("rawtypes")
	Map response;
	
	@SuppressWarnings({ "rawtypes" } )
	ArrayList<Map> samplesList;

	@GetMapping("/samples")
	public String greeting(Model model) {

		response = client.getSamples();

		if (response.get("samples") == null) {
			ArrayList<Sample> sampleDisplayList = buildBlankSampleList();
			model.addAttribute("sampleDisplayList", sampleDisplayList);
			model.addAttribute("name", " ");
			return "samples";

		} else {
			ArrayList<Map> samplesList = (ArrayList<Map>) response.get("samples");
			model.addAttribute("name", samplesList.get(0).get("modifiedBy"));
			model.addAttribute("sampleDisplayList", extractSampleData(samplesList));
		}
		
		return "samples";
	}
	
	private ArrayList<Sample> extractSampleData(@SuppressWarnings("rawtypes") ArrayList<Map> samplesList) {
		
		ArrayList<Sample> sampleDisplayList = new ArrayList<>();
		
		//loop around the map and extract the data we want to display on screen
		for(Map s : samplesList) {
			Sample sample = new Sample();
			sample.setName(s.get("name").toString());
			sample.setCreated(s.get("created").toString());
			sample.setCreatedBy(s.get("createdBy").toString());
			
			sampleDisplayList.add(sample);
		}
		return sampleDisplayList;
	}
	
	private ArrayList<Sample> buildBlankSampleList() {
		ArrayList<Sample> sampleDisplayList = new ArrayList<>();
		Sample sample = new Sample();
		sample.setCreated("");
		sample.setCreatedBy("");
		sample.setName("");
		
		sampleDisplayList.add(sample);
		return sampleDisplayList;
	}

}
