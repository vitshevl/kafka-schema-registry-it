package com.example.kafka.poc.mock;

import com.example.kafka.poc.model.ExampleMessage;
import io.confluent.kafka.schemaregistry.ParsedSchema;
import io.confluent.kafka.schemaregistry.avro.AvroSchema;
import io.confluent.kafka.schemaregistry.client.MockSchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.avro.Schema;

public class CustomKafkaAvroDeserializer extends KafkaAvroDeserializer {


    @Override
    public Object deserialize(String topic, byte[] bytes) {
        if (topic.equals("valid_topic")) {
            this.schemaRegistry = getMockClient(ExampleMessage.SCHEMA$);
        }
        return super.deserialize(topic, bytes, ExampleMessage.SCHEMA$);
    }

    private static SchemaRegistryClient getMockClient(final Schema schema$) {
        return new MockSchemaRegistryClient() {
            @Override
            public ParsedSchema getSchemaBySubjectAndId(String subject, int id)  {
                return new AvroSchema(ExampleMessage.SCHEMA$);
            }
        };
    }
}
