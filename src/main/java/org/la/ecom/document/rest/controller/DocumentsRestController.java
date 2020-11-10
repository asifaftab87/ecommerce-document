package org.la.ecom.document.rest.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.la.ecom.document.model.Documents;
import org.la.ecom.document.repository.DocumentsRepository;
import org.la.ecom.document.service.ApiService;
import org.la.ecom.notification.api.dto.MailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class DocumentsRestController {

	@Autowired
	private DocumentsRepository documentsRepository;
	
	@Autowired
	private ApiService apiService;
	
	@GetMapping(value = "/download/id/{id}")
	public Optional<Documents> download(HttpServletRequest request, @PathVariable("id") String id) {
		
		MailDTO mail = new MailDTO("springboot87@gmail.com", "asharabi101@gmail.com", "service to service", null, "testing from mongo service"); 
		String arr = null;
		try {
			//arr = apiService.notificationClient().getForObject("/hello", String.class);
			Boolean b = apiService.notificationClient().postForObject("/registrationDetails", mail, Boolean.class);
			System.out.println("b: "+b);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return documentsRepository.findById(id);
	}
	
	@PostMapping(value = "/upload")
    public Documents addNewApplication(@RequestBody Documents documents){
        return documentsRepository.save(documents);
    }
	
	@GetMapping(value = "/findAll")
	public List<Documents> findAll(){
		return documentsRepository.findAll();
	}
	
	@DeleteMapping(value = "/delete")
	public void delete(@RequestBody Documents documents) {
		documentsRepository.delete(documents);
	}
	
}
