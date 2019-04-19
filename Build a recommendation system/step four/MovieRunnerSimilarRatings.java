import java.util.*;

public class MovieRunnerSimilarRatings {
    public void printAverageRatings(){
        FourthRatings FR = new FourthRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        RaterDatabase RDB = new RaterDatabase();
        //System.out.println("There are " + RDB.size() + " raters in the file");
        MovieDatabase mdb = new MovieDatabase();
        mdb.initialize("data/ratedmoviesfull.csv");
        System.out.println("There are " + mdb.size() + " movies in the database");
        ArrayList<Rating> list = FR.getAverageRatings(35);
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
    public void printAverageRatingsByYearAfterAndGenre(){
        AllFilters af = new AllFilters();
        YearAfterFilter yaf = new YearAfterFilter(1990);
        GenreFilter gf = new GenreFilter("Drama");
        af.addFilter(yaf);
        af.addFilter(gf);
        FourthRatings FR = new FourthRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        RaterDatabase RDB = new RaterDatabase();
        //System.out.println("There are " + RDB.size() + " raters in the file");
        MovieDatabase mdb = new MovieDatabase();
        mdb.initialize("data/ratedmoviesfull.csv");
        System.out.println("There are " + mdb.size() + " movies in the database");
        ArrayList<Rating> list = FR.getAverageRatingsByFilter(8, af);        
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
    
    /*This method works!
     * public void testDotProduct(){
        FourthRatings FR = new FourthRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        RaterDatabase RDB = new RaterDatabase();
        RDB.initialize("data/ratings.csv");
        MovieDatabase mdb = new MovieDatabase();
        mdb.initialize("data/ratedmoviesfull.csv");
        Rater me = RDB.getRater("65");
        Rater r = RDB.getRater("56");
        System.out.println(FR.dotProduct(me, r));
    }*/
    /*This method works too!
    public void testGetSimilarities(){
        FourthRatings FR = new FourthRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        RaterDatabase RDB = new RaterDatabase();
        RDB.initialize("data/ratings.csv");
        MovieDatabase mdb = new MovieDatabase();
        ArrayList<Rating> list = FR.getSimilarities("65");
        for(Rating r : list){
            System.out.println("The rater with ID " + r.getItem() + " has a similarity rating of " + r.getValue() + " with the rater with ID of 65");
        }
    }*/
    
    /*public void testGetSimilarRatings(){
        FourthRatings FR = new FourthRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        RaterDatabase RDB = new RaterDatabase();
        RDB.initialize("data/ratings.csv");
        MovieDatabase mdb = new MovieDatabase();
        mdb.initialize("data/ratedmoviesfull.csv");
        ArrayList<Rating> list = FR.getSimilarRatings("65", 4, 5);
        System.out.println(list.size());
        System.out.println(list.get(10).getItem());
    }*/
       
    public void printSimilarRatings(){
        FourthRatings FR = new FourthRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        RaterDatabase RDB = new RaterDatabase();
        RDB.initialize("data/ratings.csv");
        MovieDatabase mdb = new MovieDatabase();
        mdb.initialize("data/ratedmoviesfull.csv");
        ArrayList<Rating> list = FR.getSimilarRatings("71", 20, 5);
        //Rating r = list.get(0);
        for(int i = 0; i < list.size(); i++){
            System.out.println("The movie is " + mdb.getTitle(list.get(i).getItem()) + " and has a simlarity rating of " + list.get(i).getValue());
        }
        //System.out.print(mdb.getTitle(r.getItem()));
    }
    
    public void printSimilarRatingsByGenre(){
        FourthRatings FR = new FourthRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        RaterDatabase RDB = new RaterDatabase();
        RDB.initialize("data/ratings.csv");
        MovieDatabase mdb = new MovieDatabase();
        mdb.initialize("data/ratedmoviesfull.csv");
        GenreFilter GF = new GenreFilter("Mystery");
        ArrayList<Rating> list = FR.getSimilarRatingsByFilter1("964", 20, 5, GF);
        for(int i = 0; i < list.size(); i++){
            System.out.println("The movie is " + mdb.getTitle(list.get(i).getItem()) + " and has a simlarity rating of " + list.get(i).getValue());
        }
    }
    
    /*public void printSimilarRatingsByGenre1(){
        FourthRatings FR = new FourthRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        RaterDatabase RDB = new RaterDatabase();
        RDB.initialize("data/ratings.csv");
        MovieDatabase mdb = new MovieDatabase();
        mdb.initialize("data/ratedmoviesfull.csv");
        GenreFilter GF = new GenreFilter("Action");
        ArrayList<Rating> list = FR.getSimilarRatingsByFilter1("65", 20, 5, GF);
        for(int i = 0; i < list.size(); i++){
            System.out.println("The movie is " + mdb.getTitle(list.get(i).getItem()) + " and has a simlarity rating of " + list.get(i).getValue());
        }
    }*/
    
    public void printSimilarRatingsByDirector(){
        FourthRatings FR = new FourthRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        RaterDatabase RDB = new RaterDatabase();
        RDB.initialize("data/ratings.csv");
        MovieDatabase mdb = new MovieDatabase();
        mdb.initialize("data/ratedmoviesfull.csv");
        DirectorsFilter DF = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg.Oliver Stone,Mike Leigh");
        ArrayList<Rating> list = FR.getSimilarRatingsByFilter1("120", 10, 2, DF);
        for(int i = 0; i < list.size(); i++){
            System.out.println("The movie is " + mdb.getTitle(list.get(i).getItem()) + " and has a simlarity rating of " + list.get(i).getValue());
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes(){
        FourthRatings FR = new FourthRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        RaterDatabase RDB = new RaterDatabase();
        RDB.initialize("data/ratings.csv");
        MovieDatabase mdb = new MovieDatabase();
        mdb.initialize("data/ratedmoviesfull.csv");
        AllFilters AF = new AllFilters();
        GenreFilter GF = new GenreFilter("Drama");
        MinutesFilter MF = new MinutesFilter(160, 80);
        AF.addFilter(GF);
        AF.addFilter(MF);
        ArrayList<Rating> list = FR.getSimilarRatingsByFilter1("168", 10, 3, AF);
        for(int i = 0; i < list.size(); i++){
            System.out.println("The movie is " + mdb.getTitle(list.get(i).getItem()) + ", Time : " + mdb.getMinutes(list.get(i).getItem()) + " and has a simlarity rating of " + list.get(i).getValue());
            System.out.println(mdb.getGenres(list.get(i).getItem()));
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes(){
        FourthRatings FR = new FourthRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        RaterDatabase RDB = new RaterDatabase();
        RDB.initialize("data/ratings.csv");
        MovieDatabase mdb = new MovieDatabase();
        mdb.initialize("data/ratedmoviesfull.csv");
        AllFilters AF = new AllFilters();
        YearAfterFilter YAF = new YearAfterFilter(1975);
        MinutesFilter MF = new MinutesFilter(200, 70);
        AF.addFilter(YAF);
        AF.addFilter(MF);
        ArrayList<Rating> list = FR.getSimilarRatingsByFilter1("314", 10, 5, AF);
        for(int i = 0; i < list.size(); i++){
            System.out.println("The movie is " + mdb.getTitle(list.get(i).getItem()) + " " + mdb.getYear(list.get(i).getItem()) + " Time : " + mdb.getMinutes(list.get(i).getItem()) + " and has a simlarity rating of " + list.get(i).getValue());
        }
    }
    
    /*public void test(){
        FourthRatings FR = new FourthRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        RaterDatabase RDB = new RaterDatabase();
        RDB.initialize("data/ratings.csv");
        MovieDatabase mdb = new MovieDatabase();
        mdb.initialize("data/ratedmoviesfull.csv");
        System.out.println("RATERDATABASE SIZE = " + RDB.size());
        System.out.println(FR.getSimilarities("24").size());
    }
    
    public void test1(){
        FourthRatings FR = new FourthRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        RaterDatabase RDB = new RaterDatabase();
        RDB.initialize("data/ratings.csv");
        MovieDatabase mdb = new MovieDatabase();
        mdb.initialize("data/ratedmoviesfull.csv");
        Rater me = RDB.getRater("24");
        Rater r = RDB.getRater("18");
        System.out.println(FR.dotProduct(me, r));
    }*/
}
