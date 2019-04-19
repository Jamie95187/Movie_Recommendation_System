import java.util.*;

public class FourthRatings {
    private FirstRatings fr;
    
    public FourthRatings(String movieFile, String ratingsFile){
        fr =  new FirstRatings(movieFile, ratingsFile);
    }
    
    public double getAverageByID(String id, int minimalRaters){
        RaterDatabase RDB = new RaterDatabase();
        RDB.initialize("data/ratings.csv");
        double averageMovieRatingsForThisMovieID = 0.0;
        double sumOfRatings = 0.0;
        if(fr.numberOfRatingsOfGivenMovie(id) >= minimalRaters){
            for(int i = 0; i < RDB.getRaters().size(); i++){
                if(RDB.getRaters().get(i).getRating(id) != -1){
                    sumOfRatings += RDB.getRaters().get(i).getRating(id);
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
    
    private double dotProduct(Rater me, Rater r){
        double answer = 0;
        ArrayList<String> movieIDsme = me.getItemsRated();
        for(String s : movieIDsme){
            if(r.hasRating(s)){
                double meRatingOfs = me.getRating(s) - 5;
                double rRatingOfs = r.getRating(s) - 5;
                answer += meRatingOfs*rRatingOfs;
            }
        }
        return answer;
    }
    
    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> list = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        for(Rater r : RaterDatabase.getRaters()){
            if(r != me){
                if(dotProduct(me, r) > 0 ){
                    Rating ra = new Rating(r.getID(), dotProduct(me, r));
                    list.add(ra);
                }
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        ArrayList<Rating> list = new ArrayList<Rating>();
        //list of Rating that is constructed of Rating(MovieID, weighted similarity) check assignment 
        ArrayList<Rating> similarRaters = getSimilarities(id);
        //similarRaters from getSimilarities method, ArrayList<Rating> where Rating is constructed of
        //Rating(Rater's ID, similarity rating with id in parameter)
        ArrayList<Rating> topSimilarRaters = new ArrayList<Rating>();
        //the top number of similar raters a refined version of similarRaters on line above
        for(int i = 0; i < numSimilarRaters; i++){
            topSimilarRaters.add(similarRaters.get(i));
        }
        
        //get the movie IDs rated by the rater with the id in the parameter. Call the Rater object with
        //the rater id me
        RaterDatabase RDB = new RaterDatabase();
        Rater me = RDB.getRater(id);
        //get ArrayList<String> of all the movie IDs rated by Rater me.
        ArrayList<String> movieIDs = MovieDatabase.filterBy(new TrueFilter());
        //Now we want to see how many out of all these movies rated me have also been rated by other 
        //Raters in the group of which have most similar taste to me (i.e. the topSimilarRaters ArrrayList
        //HashMap that maps movie ID to the number of Raters rating them
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for(String s : movieIDs){
            int count = 0;
            for(int i = 0; i < topSimilarRaters.size(); i++){
                Rater r = RDB.getRater(topSimilarRaters.get(i).getItem());
                if(r.hasRating(s)){
                    count++;
                }
                if(i == topSimilarRaters.size() - 1){
                    if(count!=0){
                        map.put(s, count);
                    }
                }
            }
        }
        //Filtering the movies that do not have enough top raters
        ArrayList<String> movieIDsNeededForAnswer = new ArrayList<String>();
        for(String k : map.keySet()){
            if(map.get(k) >= minimalRaters && !me.hasRating(k)){
                movieIDsNeededForAnswer.add(k);
            }
        }
        //Calculating the weighted averages by summing the similarity rating multiplied by the rating the Rater
        //gave the movie and dividing by the total number of such ratings
        //work out what to divide by! The number of movies that are common in both raters
        //Build HashMap map1 to map the ID of the movie to the number of Raters that have rated this
        //movie (the movies that have been rated both by Rater R and Rater me)
        for(String j : movieIDsNeededForAnswer){
            double totalRatingsWeighted = 0;
            double value = 0;
            for(int i = 0; i < topSimilarRaters.size(); i++){
                Rater ra = RDB.getRater(topSimilarRaters.get(i).getItem());
                if(ra.hasRating(j)){
                    totalRatingsWeighted = totalRatingsWeighted +  (ra.getRating(j)*dotProduct(me, ra));
                }
                if(i == topSimilarRaters.size() - 1){
                    value = totalRatingsWeighted/map.get(j);
                    if(value > 0){
                        Rating movieRating = new Rating(j, value);
                        list.add(movieRating);
                    }
                }       
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter1(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria){
        ArrayList<String> movieIDs = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> answer = new ArrayList<Rating>();
        ArrayList<Rating> list = getSimilarRatings(id, numSimilarRaters, minimalRaters);
        for(String movie : movieIDs){
            for(Rating movie_ID : list){
                if(movie_ID.getItem().equals(movie)){
                    answer.add(movie_ID);
                }
            }
        }
        Collections.sort(answer, Collections.reverseOrder());
        return answer;
    }
    
    /*public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria){
        ArrayList<String> movieIDs = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> list = new ArrayList<Rating>();
        ArrayList<Rating> similarRaters = getSimilarities(id);
        ArrayList<Rating> topSimilarRaters = new ArrayList<Rating>();
        for(int i = 0; i < numSimilarRaters; i++){
            topSimilarRaters.add(similarRaters.get(i));
        }
        RaterDatabase RDB = new RaterDatabase();
        Rater me = RDB.getRater(id);
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for(String s : movieIDs){
            int count = 0;
            for(int i = 0; i < topSimilarRaters.size(); i++){
                Rater r = RDB.getRater(topSimilarRaters.get(i).getItem());
                if(r.hasRating(s)){
                    count++;
                }
                if(i == topSimilarRaters.size() - 1){
                    if(count!=0){
                        map.put(s, count);
                    }
                }
            }
        }
        ArrayList<String> movieIDsNeededForAnswer = new ArrayList<String>();
        for(String k : map.keySet()){
            if(map.get(k) >= minimalRaters && !me.hasRating(k)){
                movieIDsNeededForAnswer.add(k);
            }
        }
        for(String j : movieIDsNeededForAnswer){
            double totalRatingsWeighted = 0;
            double value = 0;
            for(int i = 0; i < topSimilarRaters.size(); i++){
                Rater ra = RDB.getRater(topSimilarRaters.get(i).getItem());
                if(ra.hasRating(j)){
                    if(dotProduct(me, ra) > 0){
                        totalRatingsWeighted = totalRatingsWeighted +  (ra.getRating(j)*dotProduct(me, ra));
                    }
                }
                if(i == topSimilarRaters.size() - 1){
                    value = totalRatingsWeighted/map.get(j);
                    if(value > 0){
                        Rating movieRating = new Rating(j, value);
                        list.add(movieRating);
                    }
                }       
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }*/
    
}
