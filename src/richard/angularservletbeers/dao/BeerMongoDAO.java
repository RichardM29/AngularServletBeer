package richard.angularservletbeers.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import richard.angularservletbeers.model.Beer;

public class BeerMongoDAO {

    private MongoClient client;
    private MongoDatabase database;
    private MongoCollection<Document> collection;
    
    private BeerMongoDAO() {
    	this.client = new MongoClient("localhost");
    	this.database = this.client.getDatabase("test");
    	this.collection = this.database.getCollection("beers");
    }
    
    private static BeerMongoDAO beerMongoDAO;
    
    public static BeerMongoDAO getBeerMongoDAOInstance() {
    	if (null == beerMongoDAO) {
    		beerMongoDAO = new BeerMongoDAO();
    	}
    	return beerMongoDAO;
    }
    
    public Beer getBeer(String beerId) {
    	Beer beer = new Beer();
    	
    	Document doc = collection.find(Filters.eq("id", beerId)).first();
    	
		beer.setId(doc.getString("id"));
		beer.setDescription(doc.getString("description"));
		beer.setImg(doc.getString("img"));
		beer.setName(doc.getString("name"));
		
		Object alcohol = doc.get("alcohol");
		
		if (alcohol instanceof Double) {
			beer.setAlcohol((Double) alcohol);
		} else {
			beer.setAlcohol((int) alcohol);
		}
		
		beer.setAvailability(doc.getString("availability"));
		beer.setBrewery(doc.getString("brewery"));
		beer.setLabel(doc.getString("label"));
		beer.setServing(doc.getString("serving"));
		beer.setStyle(doc.getString("style"));
    	
    	return beer;
    }
    
    public List<Beer> getBeerList() {
    	List<Beer> beers = new ArrayList<Beer>();
    	
    	MongoCursor<Document> cursor = this.collection.find().iterator();
    	while (cursor.hasNext()) {
    		Document doc = cursor.next();
    		Beer beer = new Beer();
    		beer.setId(doc.getString("id"));
    		beer.setDescription(doc.getString("description"));
    		beer.setImg(doc.getString("img"));
    		beer.setName(doc.getString("name"));
    		
    		Object alcohol = doc.get("alcohol");
    		
    		if (alcohol instanceof Double) {
    			beer.setAlcohol((Double) alcohol);
    		} else {
    			beer.setAlcohol((int) alcohol);
    		}
    		
    		beers.add(beer);
    	}
    	
    	return beers;
    }
}
