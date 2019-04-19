
import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    private FirstRatings fr;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String movieFile, String ratingsFile){
        fr =  new FirstRatings(movieFile, ratingsFile);
        myMovies = fr.movieList;
        myRaters = fr.ratersList;
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    public int getMovieSize(){
        return myMovies.size();
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
        ArrayList<Rating> list = new ArrayList<Rating>();
        for(int i = 0; i < myMovies.size(); i++){
            if(getAverageByID(myMovies.get(i).getID(), minimalRaters) != 0.0){
                Rating r = new Rating(myMovies.get(i).getID(), getAverageByID(myMovies.get(i).getID(), minimalRaters));
                list.add(r);
            }
        }
        return list;
    }
    
    public String getTitle(String id){
        String title = "";
        for (int i = 0; i < myMovies.size(); i++){
            if(myMovies.get(i).getID().equals(id)){
                title = myMovies.get(i).getTitle();
                break; 
            }
        }
        if(title.equals("")){
            title = " ID was not found!";
        }
        return title;
    }
    
    public String getID(String title){
        String IDofMovie = "NO SUCH TITLE";
        for(int i = 0; i < myMovies.size(); i++){
            if(myMovies.get(i).getTitle().equals(title)){
                IDofMovie = myMovies.get(i).getID();
            }
        }
        return IDofMovie;
    }

}
