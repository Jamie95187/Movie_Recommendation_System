import java.util.*;

public class MovieRunnerWithFilters {
    public void printAverageRatings(){
        ThirdRatings tr = new ThirdRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        System.out.println("There are " + tr.getRaterSize() + " raters in the file");
        MovieDatabase mdb = new MovieDatabase();
        mdb.initialize("data/ratedmoviesfull.csv");
        System.out.println("There are " + mdb.size() + " movies in the database");
        ArrayList<Rating> list = tr.getAverageRatings(35);
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
            System.out.println(list.get(j).getValue() + " " + mdb.getTitle(list.get(j).getItem()));
        }
    }
    
    public void printAverageRatingsByYear(){
        ThirdRatings tr = new ThirdRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        System.out.println("There are " + tr.getRaterSize() + " raters in the file");
        MovieDatabase mdb = new MovieDatabase();
        mdb.initialize("data/ratedmoviesfull.csv");
        System.out.println("There are " + mdb.size() + " movies in the database");
        YearAfterFilter yaf = new YearAfterFilter(2000);
        ArrayList<Rating> list = tr.getAverageRatingsByFilter(20, yaf);
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
            System.out.println(list.get(j).getValue() + " " + mdb.getYear(list.get(j).getItem()) + " " + mdb.getTitle(list.get(j).getItem()));
        }
    }
    
    public void printAverageRatingsByGenre(){
        ThirdRatings tr = new ThirdRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        System.out.println("There are " + tr.getRaterSize() + " raters in the file");
        MovieDatabase mdb = new MovieDatabase();
        mdb.initialize("data/ratedmoviesfull.csv");
        System.out.println("There are " + mdb.size() + " movies in the database");
        GenreFilter gf = new GenreFilter("Comedy");
        ArrayList<Rating> list = tr.getAverageRatingsByFilter(20, gf);
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
            System.out.println(list.get(j).getValue() + " " + mdb.getTitle(list.get(j).getItem()) + "\n " + mdb.getGenres(list.get(j).getItem()));
        }
    }
    
    public void printAverageRatingsByMinutes(){
        ThirdRatings tr = new ThirdRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        System.out.println("There are " + tr.getRaterSize() + " raters in the file");
        MovieDatabase mdb = new MovieDatabase();
        mdb.initialize("data/ratedmoviesfull.csv");
        System.out.println("There are " + mdb.size() + " movies in the database");
        MinutesFilter mf = new MinutesFilter(135, 105);
        ArrayList<Rating> list = tr.getAverageRatingsByFilter(5, mf);
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
            System.out.println(list.get(j).getValue() + " time: " + mdb.getMinutes(list.get(j).getItem()) + " " + mdb.getTitle(list.get(j).getItem()) + "\n " + mdb.getGenres(list.get(j).getItem()));
        }
    }
    
    public void printAverageRatingsByDirectors(){
        ThirdRatings tr = new ThirdRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        System.out.println("There are " + tr.getRaterSize() + " raters in the file");
        MovieDatabase mdb = new MovieDatabase();
        mdb.initialize("data/ratedmoviesfull.csv");
        System.out.println("There are " + mdb.size() + " movies in the database");
        DirectorsFilter df = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        ArrayList<Rating> list = tr.getAverageRatingsByFilter(4, df);
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
            System.out.println(list.get(j).getValue() + " " + mdb.getTitle(list.get(j).getItem()) + " \n " + mdb.getDirector(list.get(j).getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        AllFilters af = new AllFilters();
        YearAfterFilter yaf = new YearAfterFilter(1990);
        GenreFilter gf = new GenreFilter("Drama");
        af.addFilter(yaf);
        af.addFilter(gf);
        ThirdRatings tr = new ThirdRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        System.out.println("There are " + tr.getRaterSize() + " raters in the file");
        MovieDatabase mdb = new MovieDatabase();
        mdb.initialize("data/ratedmoviesfull.csv");
        System.out.println("There are " + mdb.size() + " movies in the database");
        ArrayList<Rating> list = tr.getAverageRatingsByFilter(8, af);        
        System.out.println("There are " + list.size() + " movie(s) that satisfies the criterias");
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
            System.out.println(list.get(j).getValue() + " " + mdb.getYear(list.get(j).getItem()) + " " + mdb.getTitle(list.get(j).getItem()) + " \n " + mdb.getGenres(list.get(j).getItem()));
        }
    }
    
    public void printAverageRatingsByDirectorsAndMinutes(){
        AllFilters af = new AllFilters();
        MinutesFilter mf = new MinutesFilter(180, 90);
        DirectorsFilter df = new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
        af.addFilter(mf);
        af.addFilter(df);
        ThirdRatings tr = new ThirdRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        System.out.println("There are " + tr.getRaterSize() + " raters in the file");
        MovieDatabase mdb = new MovieDatabase();
        mdb.initialize("data/ratedmoviesfull.csv");
        System.out.println("There are " + mdb.size() + " movies in the database");
        ArrayList<Rating> list = tr.getAverageRatingsByFilter(3, af);        
        System.out.println("There are " + list.size() + " movie(s) that satisfies the criterias");
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
            System.out.println(list.get(j).getValue() + " Time: " + mdb.getMinutes(list.get(j).getItem()) + " " + mdb.getTitle(list.get(j).getItem()) + " \n " + mdb.getDirector(list.get(j).getItem()));
        }
    }
}
