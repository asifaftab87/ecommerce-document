package org.la.ecom.document.service;

import javax.annotation.PostConstruct;

import org.la.ecom.notification.api.client.NotificationClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiService {

	@Value("${service.url.notifications}")
	private String notificationURL;
	
	@Value("${service.url.mysql}")
	private String mysqlURL;

	@Autowired
	private NotificationClient notificationClient;
	
	public ApiService() {}
	
	public NotificationClient notificationClient() {
		return notificationClient;
	}
	
	@PostConstruct
	public void setPropertiesUrl() {
		notificationClient.setUrl(notificationURL);
	}
	
	
}
