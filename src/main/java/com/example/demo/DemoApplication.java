package com.example.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		try {
			String filePath = "/tmp/commands.txt";
			var commands = CommandParser.parseCommands(filePath);
			var scheduler = new CommandScheduler(commands);
			scheduler.start();
		} catch (Exception e) {
			System.err.println("Failed to start scheduler: " + e.getMessage());
		}
	}


}
