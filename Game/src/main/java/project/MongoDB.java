package project;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

public class MongoDB {
  private static MongoDB instance;
  MongoDatabase database;

  public static MongoDB getInstance() {
    if (instance == null) {
      instance = new MongoDB();
    }
    return instance;
  }
  private MongoDB() {
    ConnectionString connectionString = new ConnectionString("mongodb+srv://chino0522:ZmxO16385bQCn0OG@cluster0.k7wvl8f.mongodb.net/?retryWrites=true&w=majority");
    MongoClientSettings settings = MongoClientSettings.builder()
        .applyConnectionString(connectionString)
        .serverApi(ServerApi.builder()
            .version(ServerApiVersion.V1)
            .build())
        .build();
    MongoClient mongoClient = MongoClients.create(settings);
    database = mongoClient.getDatabase("Zoom-Zoom");
  }

  public void put(String key, long value) {
    Document document = new Document();
    document.append(key, value);
    new Thread(() -> database.getCollection("Time").insertOne(document)).start();
  }

  public List<Document> queryTop5() {
    List<Document> results = new ArrayList<>();
    Bson sort = Sorts.ascending("time"); // replace "fieldName" with the name of the field that stores the long data
    database.getCollection("Time").find().sort(sort).limit(5).forEach(results::add);
    return results;
  }
}
