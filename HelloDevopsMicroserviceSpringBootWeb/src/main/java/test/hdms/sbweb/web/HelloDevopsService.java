package test.hdms.sbweb.web;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloDevopsService {
	
	@RequestMapping("/helloDevops")
	public String test() {
		return "Hello Devops!";
	}

}