package com.annuel.project.server.ampq;


//import org.springframework.amqp.core.*;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

@Configuration
public class AmqpConfig {

    public static final String QUEUE_NAME = "annuel_project_queue";

    @Bean
    public ConnectionFactory createConnctionFactory() {

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("chinook.rmq.cloudamqp.com");
        connectionFactory.setUsername("tcdhcjrf");
        connectionFactory.setPassword("e_-7EazoBPwTdj-zHwsAeU4bHwZTpzoY");
        connectionFactory.setVirtualHost("tcdhcjrf");
        return connectionFactory;
}

    @Bean
    public RabbitAdmin createAdmin() {
        ConnectionFactory ConnectionFactory = createConnctionFactory();
        RabbitAdmin admin = new RabbitAdmin(ConnectionFactory);

        return admin;
    }


    @Bean
    Queue createQueue() {
        RabbitAdmin admin = createAdmin();
        Queue queue = new Queue(QUEUE_NAME, true, false, false);
        admin.declareQueue(queue);
        return queue;
    }


 /*   @Bean
    TopicExchange exchange() {
        TopicExchange exchange = new TopicExchange(EXCHANGE_NAME);
        RabbitAdmin admin = createAdmin();
        admin.declareExchange(exchange);
        return exchange;
    }

    @Bean
    Binding binding(Queue q, TopicExchange exchange) {
        RabbitAdmin admin = createAdmin();
        admin.declareBinding(BindingBuilder.bind(q).to(exchange).with("mike.#"));
        return BindingBuilder.bind(q).to(exchange).with("mike.#");
    }
*/
   @Bean
    SimpleMessageListenerContainer container(MessageListenerAdapter messageListenerAdapter){
       ConnectionFactory connectionFactory =createConnctionFactory();
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME);
       System.out.println("*************messageListenerAdapter***********"+ messageListenerAdapter );
        container.setMessageListener(messageListenerAdapter);
       System.out.println("*************container***********"+ container);
        return container;
    }



    @Bean
    MessageListenerAdapter listenerAdapter(ReceiveMessageHandler handler){
        System.out.println("*************handler***********"+ handler);
        return new MessageListenerAdapter(handler, "handleMessage");
    }


}
