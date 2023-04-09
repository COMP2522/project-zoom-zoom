package project;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 * Class for MongoDB that saves specific information in game.
 */
public class Mongodb {

  /**
   * The instance variable represents the singleton instance of the MongoDB class.
   */
  private static Mongodb instance;

  MongoDatabase database;

  Document document;

  /**
   * The getInstance method returns the singleton instance of the MongoDB class.
   *
   * @return the singleton instance of the MongoDB class
   */
  public static Mongodb getInstance() {
    if (instance == null) {
      instance = new Mongodb();
    }
    return instance;
  }

  /**
   * The constructor for the MongoDB class.
   * It is private to keep it singleton.
   * It connects to the MongoDB database.
   * It also sets up the database.
   */
  private Mongodb() {
    ConnectionString connectionString =
        new ConnectionString("mongodb+srv://chino0522:ZmxO16385bQCn0OG@cluster0.k7wvl8f.mongodb"
            + ".net/?retryWrites=true&w=majority");
    MongoClientSettings settings = MongoClientSettings.builder()
        .applyConnectionString(connectionString)
        .serverApi(ServerApi.builder()
            .version(ServerApiVersion.V1)
            .build())
        .build();
    MongoClient mongoClient = MongoClients.create(settings);
    database = mongoClient.getDatabase("Zoom-Zoom");
  }

  /**
   * The putEngine method puts the engine information into the database.
   *
   * @param key1
   * @param value1
   * @param key2
   * @param value2
   * @param key3
   * @param value3
   * @param key4
   * @param value4
   */
  public void putEngine(String key1, double value1, String key2, double value2,
                        String key3, int value3, String key4, int value4) {
    document = new Document();
    document.append(key1, value1);
    document.append(key2, value2);
    document.append(key3, value3);
    document.append(key4, value4);
  }

  /**
   * The putChasis method puts the chasis information into the database.
   *
   * @param key1
   * @param value1
   * @param key2
   * @param value2
   * @param key3
   * @param value3
   */
  public void putChasis(String key1, int value1, String key2, int value2,
                        String key3, int value3) {
    document.append(key1, value1);
    document.append(key2, value2);
    document.append(key3, value3);
  }

  /**
   * The putAero method puts the aero information into the database.
   *
   * @param key1
   * @param value1
   * @param key2
   * @param value2
   * @param key3
   * @param value3
   */
  public void putAero(String key1, int value1, String key2, int value2,
                      String key3, int value3) {
    document.append(key1, value1);
    document.append(key2, value2);
    document.append(key3, value3);
    new Thread(() -> database.getCollection("Build").insertOne(document)).start();
  }

  /**
   * The put method puts the time information into the database.
   *
   * @param key
   * @param value
   */
  public void put(String key, long value) {
    Document documentTime = new Document();
    documentTime.append(key, value);
    new Thread(() -> database.getCollection("Time").insertOne(document)).start();
  }

  /**
   * The queryTop5 method queries the top 5 fastest times.
   *
   * @return the top 5 fastest times
   */
  public List<Document> queryTop5() {
    List<Document> results = new ArrayList<>();
    Bson sort = Sorts.ascending("time");
    database.getCollection("Time").find().sort(sort).limit(5).forEach(results::add);
    return results;
  }
}
