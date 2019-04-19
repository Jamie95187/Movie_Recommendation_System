
import java.util.*;

public class EfficientRater implements Rater {
    private String myID;
    //The key is the Movie ID and the Rating is the rating for the movie
    private HashMap<String, Rating> map;

    public EfficientRater(String id) {
        myID = id;
        map = new HashMap<String, Rating>();
    }

    public void addRating(String item, double rating) {
        Rating r = new Rating(item, rating);
        map.put(item, r);
    }

    public boolean hasRating(String item) {
        if (map.containsKey(item)){
            return true;
        }
        return false;
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        if(map.get(item) != null){
            return map.get(item).getValue();
        }
        return -1;
    }

    public int numRatings() {
        return map.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(String s : map.keySet()){
            list.add(s);
        }
        
        return list;
    }
}
