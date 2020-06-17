package com.annuel.project.server.ampq;



import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {

   // public static final String QUEUE_NAME = "annuel_project_queue";
   public static final String QUEUE_NAME = "matches_queue";

    @Bean(name = "connexion")
    public ConnectionFactory createConnectionFactory() {

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("chinook.rmq.cloudamqp.com");
        connectionFactory.setUsername("tcdhcjrf");
        connectionFactory.setPassword("e_-7EazoBPwTdj-zHwsAeU4bHwZTpzoY");
        connectionFactory.setVirtualHost("tcdhcjrf");
        return connectionFactory;
    }

    @Bean
    public RabbitAdmin createAdmin() {
        ConnectionFactory ConnectionFactory = createConnectionFactory();
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


    @Bean
    public Jackson2JsonMessageConverter producerMessage()
    {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(@Qualifier("connexion") final ConnectionFactory connectionFactory)
    {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerMessage());
        return rabbitTemplate;
    }


}