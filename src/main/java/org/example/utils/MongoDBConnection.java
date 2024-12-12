package org.example.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBConnection {
    private static MongoCollection<Document> coleccion;

    public static MongoCollection<Document> getDatabase() {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

        MongoDatabase database = mongoClient.getDatabase("television");;

        coleccion = database.getCollection("programas");

        return coleccion;
    }

}
