
import java.util.*;

public class ThirdRatings {

    private ArrayList<Rater> myRaters;
    private FirstRatings fr;
    
    public ThirdRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public ThirdRatings(String movieFile, String ratingsFile){
        fr =  new FirstRatings(movieFile, ratingsFile);
        myRaters = fr.ratersList;
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }

    public double getAverageByID(String id, int minimalRaters){
        double averageMovieRatingsForThisMovieID = 0.0;
        double sumOfRatings = 0.0;
        if(fr.numberOfRatingsOfGivenMovie(id) >= minimalRaters){
            for(int i = 0; i < myRaters.size(); i++){
                if(myRaters.get(i).getRating(id) != -1){
                    sumOfRatings += myRaters.get(i).getRating(id);
                }
            }
            averageMovieRatingsForThisMovieID = sumOfRatings/fr.numberOfRatingsOfGivenMovie(id);
        }
        return averageMovieRatingsForThisMovieID;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> list = new ArrayList<Rating>();
        for(int i = 0; i < movies.size(); i++){
            if(getAverageByID(movies.get(i), minimalRaters) != 0.0){
                Rating r = new Rating(movies.get(i), getAverageByID(movies.get(i), minimalRaters));
                list.add(r);
            }
        }
        return list;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<String> movieIDs = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        for (int i = 0; i < movieIDs.size(); i++){
            if(getAverageByID(movieIDs.get(i), minimalRaters) != 0.0){
                Rating r = new Rating(movieIDs.get(i), getAverageByID(movieIDs.get(i), minimalRaters));
                ratings.add(r);
            }
        }
        return ratings;
    }
}
