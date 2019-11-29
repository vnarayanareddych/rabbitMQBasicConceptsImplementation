package com.springboot;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Publisher {

	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		String message = "First Message fom Rabbit MQ";

		channel.basicPublish("", "Queue-1", null, message.getBytes());
		System.out.println("Done");
		channel.close();
		connection.close();
	}
}
