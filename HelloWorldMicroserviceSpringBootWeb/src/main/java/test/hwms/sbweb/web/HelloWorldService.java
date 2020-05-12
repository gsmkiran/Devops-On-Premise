package test.hwms.sbweb.web;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldService {
	
	@RequestMapping("/hello")
	public String test() {
		return "Hello World!";
	}

}