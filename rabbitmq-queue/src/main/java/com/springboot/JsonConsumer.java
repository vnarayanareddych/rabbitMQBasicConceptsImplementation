package com.springboot;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.json.JSONObject;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class JsonConsumer {

	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();

		Connection connection = factory.newConnection();

		Channel channel = connection.createChannel();

		DeliverCallback deliverCallBack = (consumerTag, delivary) -> {
			String message = new String(delivary.getBody());
			JSONObject jsonObject = new JSONObject(message);
			System.out.println(jsonObject.get("from_date"));
			System.out.println(jsonObject.get("to_date"));
			System.out.println(jsonObject.get("query"));
			System.out.println(jsonObject.get("email"));
		};

		channel.basicConsume("Queue-1", true, deliverCallBack, cancelCallback -> {
		});
	}
}
