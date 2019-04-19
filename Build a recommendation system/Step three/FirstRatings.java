
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> movieList;
    public ArrayList<Rater> ratersList;
    //"data/ratedmoviesfull.csv"
    public FirstRatings(String fileNameOfMovies, String fileNameOfRaters){
        movieList = loadMovies(fileNameOfMovies);
        ratersList = loadRaters2(fileNameOfRaters);
    }
    
    public ArrayList<Movie> loadMovies(String filename) {
        //"data/ratedmovies_short.csv"
        ArrayList<Movie> list = new ArrayList<Movie>();
        FileResource f = new FileResource(filename);
        //String s = f.asString();
        CSVParser parser = f.getCSVParser();
        //CSVParser parser = CSVParser.parse(s, CSVFormat.RFC4180.withHeader());
        for (CSVRecord csvR : parser) {
            Movie moofi = new Movie(csvR.get("id"), csvR.get("title"), csvR.get("year"), csvR.get("genre"), csvR.get("director"), csvR.get("country"), csvR.get("poster"), Integer.parseInt(csvR.get("minutes")));
            list.add(moofi);
        }
        return list;
    }
        public ArrayList<Rater> loadRaters2(String filename){
        //"data/ratings_short.csv"
        ArrayList<Rater> list = new ArrayList<Rater>();
        FileResource f = new FileResource(filename);
        CSVParser parser = f.getCSVParser();
        int currentID = 0;
        for (CSVRecord record : parser){
         if (Integer.toString(currentID).equals(record.get("rater_id"))) {
             list.get(currentID-1).addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
         } else {
          Rater r = new EfficientRater(record.get("rater_id"));
                r.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
                list.add(r);
                currentID++;
         }
        }
        //System.out.println(list);
        return list;
    }
    public int numComedy(){
        int numberOfComedies = 0;
        for(Movie m : movieList){
            if(m.getGenres().indexOf("Comedy", 0) >= 0){
                numberOfComedies++;
            }
        }
        return numberOfComedies;
    }
    
    public int num150MinsPlus() {
        int numberOfMoviesOver150Mins = 0;
        for(Movie m : movieList){
            if(m.getMinutes() > 150){
                numberOfMoviesOver150Mins++;
            }
        }
        return numberOfMoviesOver150Mins;
    }
    
    public void maxNumOfMoviesByDir(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for(Movie m : movieList){
            String[] directorsArray = m.getDirector().split(", ");
            for(int i = 0; i < directorsArray.length; i++){
                if(!map.containsKey(directorsArray[i])){
                    map.put(directorsArray[i], 1); 
                }
                else{
                    map.put(directorsArray[i], map.get(directorsArray[i])+1);
                }
            }
        }
        int maxNumberOfMoviesDirected = 0;
        for(Integer v : map.values()){
            if(maxNumberOfMoviesDirected < v){
                maxNumberOfMoviesDirected = v;
            }
        }
        System.out.println("The max number of movies directed by any director is " + maxNumberOfMoviesDirected);
        for(String s : map.keySet()){
            if(map.get(s) == maxNumberOfMoviesDirected){
                System.out.println("A director that has max movies " + s );
            }
        }
    }
    
    public ArrayList<Rater> loadRaters(String filename){
        //"data/ratings_short.csv"
        ArrayList<Rater> list = new ArrayList<Rater>();
        FileResource f = new FileResource(filename);
        CSVParser parser = f.getCSVParser();
        for (CSVRecord record : parser){
            if(list.isEmpty()){
                Rater ra = new EfficientRater(record.get("rater_id"));
                ra.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
                list.add(ra);
            }
            else{
                for(int i = 0; i < list.size();i++){
                    if(list.get(i).getID() == record.get("movie_id")){
                        list.get(i).addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
                        break;
                    }
                    else{
                        if(i == list.size()-1){                   
                            Rater r = new EfficientRater(record.get("rater_id"));
                            r.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
                            list.add(r); 
                        }
                    }
                
                }
            }
        }
        //System.out.println(list);
        return list;
    }
    
    public ArrayList<Rater> loadRaters1(String filename){
        ArrayList<Rater> list = new ArrayList<Rater>();
        FileResource f = new FileResource(filename);
        CSVParser parser = f.getCSVParser();
        for (CSVRecord record : parser){
            Rater r = new EfficientRater(record.get("rater_id"));
            r.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
            list.add(r);
        }
        return list;
    }
    
    public void testLoadRaters(){
        System.out.println("There are " + ratersList.size() + " total number of raters");
        for (Rater r : ratersList){
            System.out.println("This rater with ID " + r.getID() + " made " + r.numRatings() + " number of ratings");
            ArrayList<String> ratedItems = r.getItemsRated();
            for(String s : ratedItems){
                System.out.println("The movie IDs and ratings given by this rater are as follows : movie ID = " + s + " and was rated as a " + r.getRating(s));
            }
        }
    }

    public void testLoadMovies(){
        System.out.println("The number of movies in the list is " + movieList.size());
        /*for (Movie m : list){
            System.out.println(m.toString());
        }*/
    }
    
    public int numOfRatings(String ID){
        int numberOfRatings = 0;
        for(Rater r : ratersList){
            if(r.getID().equals(ID)){
                numberOfRatings = r.numRatings();
            }
        }
        return numberOfRatings;
    }
    
    public void maxNumberOfRatingsAndWhichRaters(){
        int maxNumberOfRatings = 0;
        for (Rater r : ratersList){
            if(maxNumberOfRatings < numOfRatings(r.getID())){
                maxNumberOfRatings = r.numRatings();
            }
        }
        int numberOfRatersWithMaxNumberOfRatings = 0;
        for (Rater r : ratersList){
            if(maxNumberOfRatings == r.numRatings()){
                numberOfRatersWithMaxNumberOfRatings++;
                System.out.println("The rater with ID " + r.getID() + " has the maximum number of ratings, and that number is " + maxNumberOfRatings);
            }
        }
        System.out.println("There are only " + numberOfRatersWithMaxNumberOfRatings + " rater(s) who has rated " + maxNumberOfRatings + " movies ");
    }
    
    public void testNumOfRatings(){
        System.out.println("The rater with ID 2 has rated " + numOfRatings("2") + " movies");
    }
    
    public int numberOfRatingsOfGivenMovie(String IDOfMovie){
        int count = 0;
        for (Rater r : ratersList){
            if(r.hasRating(IDOfMovie)){
                count++;
            }
        }
        //System.out.println(count);
        return count;
    }
    
    public void numberOfMoviesRated(){
        ArrayList<String> numberOfMovies = new ArrayList<String>();
        for (Rater r : ratersList){
            ArrayList<String> list = r.getItemsRated();
            for(String s : list){
                if(!numberOfMovies.contains(s)){
                    numberOfMovies.add(s);
                }
            }
        }
        //System.out.println(numberOfMovies.size());
    }
}
