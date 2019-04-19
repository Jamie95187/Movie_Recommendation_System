
import java.util.*;

public class MovieRunnerAverage {

    public void printAverageRatings(){
        SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        System.out.println("There are " + sr.getMovieSize() + " movies in the file");
        System.out.println("There are " + sr.getRaterSize() + " raters in the file");
        ArrayList<Rating> list = sr.getAverageRatings(12);
        System.out.println("The list has size " + list.size());
        for(int k = 0; k < list.size(); k++){
            int numberOfSwaps = 0;
            for(int i = 0; i < list.size() - 1; i++){
                Rating currentRating = list.get(i);
                Rating nextRating = list.get(i+1);
                if(list.get(i).compareTo(nextRating) > 0){
                    list.set(i, nextRating);
                    list.set(i+1, currentRating);
                    numberOfSwaps++;
                }
            }
            if(numberOfSwaps == 0){
                break;
            }
        }
        for(int j = 0; j < list.size(); j++){
            System.out.println(list.get(j).getValue() + " " + sr.getTitle(list.get(j).getItem()));
        }
    }
        
    public void getAverageRatingOneMovie(){
        SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        String movieName = "Vacation";
        String movieID = sr.getID(movieName);
        Double averageRating = sr.getAverageByID(movieID, 0);
        System.out.println("The movie " + movieName + " has an average rating of " + Double.toString(averageRating));
    }
}
