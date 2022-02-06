package com.example.ExternalTaskClient;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.variable.ClientValues;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.camunda.bpm.engine.variable.value.TypedValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class HandlerConfiguration {

    protected static final Logger LOG = LoggerFactory.getLogger(HandlerConfiguration.class);

    @Bean
    @ExternalTaskSubscription(
            topicName = "notification"
    )
    public ExternalTaskHandler notificationHandler() {
        return (externalTask, externalTaskService) -> {
            LOG.info("Input Variable name :"+externalTask.getVariable("name"));
            LOG.info("Input Variable email :"+externalTask.getVariable("email"));
            LOG.info("Inside notificationHandler");
            Map<String, Object> variables = new HashMap<>();
            variables.put("externalTaskVariable", "dummy variable");

            externalTaskService.complete(externalTask,variables);
        };
    }


//    @Bean
//    @ExternalTaskSubscription("invoiceCreator")
//    public ExternalTaskHandler invoiceCreatorHandler() {
//        return (externalTask, externalTaskService) -> {
//
//            // instantiate an invoice object
//            Invoice invoice = new Invoice("A123");
//
//            // create an object typed variable with the serialization format XML
//            ObjectValue invoiceValue = ClientValues
//                    .objectValue(invoice)
//                    .serializationDataFormat("application/xml")
//                    .create();
//
//            // add the invoice object and its id to a map
//            Map<String, Object> variables = new HashMap<>();
//            variables.put("invoiceId", invoice.id);
//            variables.put("invoice", invoiceValue);
//
//            // select the scope of the variables
//            boolean isRandomSample = Math.random() <= 0.5;
//            if (isRandomSample) {
//                externalTaskService.complete(externalTask, variables);
//            } else {
//                externalTaskService.complete(externalTask, null, variables);
//            }
//
//            LOG.info("The External Task {} has been completed!", externalTask.getId());
//
//        };
//    }

}