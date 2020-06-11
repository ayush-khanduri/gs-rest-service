package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


//others did this change
//ayush ka change
// this is going to cause merge conflict
@RestController
@EnableSwagger2
@Api(value = "Greeting ka Controller")
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@GetMapping("/hello")
	public String sayHello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return "Hello World!!";
	}

	@PostMapping("/post")
	@ApiOperation(value = "Sends input back", response = Greeting.class,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Invalid arguments supplied")})
	public Greeting postIt(@RequestHeader(value = "name", defaultValue = "World") String name, @RequestBody Greeting greeting) {
		return greeting;
	}

	@GetMapping("/health")
	public String healthCheck() {
		return "I'm Up";
	}
}
