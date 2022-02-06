package com.example.ExternalTaskClient;

import org.camunda.bpm.client.spring.SpringTopicSubscription;
import org.camunda.bpm.client.spring.event.SubscriptionInitializedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Subscriptions implements ApplicationListener<SubscriptionInitializedEvent> {

    protected static final Logger LOG = LoggerFactory.getLogger(Subscriptions.class);

    @Autowired
    public SpringTopicSubscription notificationHandlerSubscription;

    @PostConstruct
    public void listSubscriptionBeans() {
        LOG.info("Subscription bean 'invoiceCreatorHandlerSubscription' has topic name: {} ",
                notificationHandlerSubscription.getTopicName());
    }

    @Override
    public void onApplicationEvent(SubscriptionInitializedEvent event) {
        SpringTopicSubscription springTopicSubscription = event.getSource();
        String topicName = springTopicSubscription.getTopicName();
        LOG.info("Subscription with topic name '{}' initialized", topicName);

        if (!springTopicSubscription.isOpen()) {
            LOG.info("Subscription with topic name '{}' not yet opened", topicName);

            // do something before subscription is opened

            springTopicSubscription.open();

            LOG.info("Subscription with topic name '{}' opened", topicName);

            springTopicSubscription.close();

            LOG.info("Subscription with topic name '{}' temporarily closed", topicName);

            // do something with subscription closed

            springTopicSubscription.open();

            LOG.info("Subscription with topic name '{}' reopened again", topicName);
        }
    }

}
