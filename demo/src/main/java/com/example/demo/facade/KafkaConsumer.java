package com.example.demo.facade;

import com.example.demo.business.domain.KafkaModel;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "test", groupId = "foo",
            topicPartitions = @TopicPartition(topic = "test",
                    partitionOffsets = {
                            @PartitionOffset(partition = "0", initialOffset = "0")
                    }),
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void fromBeginMessage(KafkaModel message) {
        System.out.println("allMessage - Received Message in group foo: " + message);
    }


    @KafkaListener(topics = "test", groupId = "foo",
            topicPartitions = @TopicPartition(topic = "test", partitions = "0"),
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void lastIncomingMessage(KafkaModel message) {
        System.out.println("lastIncomingMessage - Received Message in group foo: " + message);
    }
}
