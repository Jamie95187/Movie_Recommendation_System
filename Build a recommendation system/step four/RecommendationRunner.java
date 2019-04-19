
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JTable;

public class RecommendationRunner implements Recommender{
    public ArrayList<String> getItemsToRate(){
        GenreFilter GF = new GenreFilter("Comedy");
        FourthRatings FR = new FourthRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        //Get the movies that have at least 10 raters and have a genre of Comedy.
        ArrayList<Rating> list = FR.getAverageRatingsByFilter(10, GF);
        ArrayList<String> movieIDs = new ArrayList<String>();
        Random rand = new Random();
        if(list.size() >= 10){
            while(movieIDs.size() < 10){
                String randomMovieIDFromList = list.get(rand.nextInt(list.size())).getItem();
                if(!movieIDs.contains(randomMovieIDFromList)){
                    movieIDs.add(randomMovieIDFromList);
                }
            }
        }
        //Return 10 movies that have at least 10 raters and is of the Comedy Genre
        return movieIDs;
    }
    
    /*public void test(){
        ArrayList<String> list = getItemsToRate();
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
    }*/
    

    public void printRecommendationsFor (String webRaterID){
        //Get the movie IDs that are not rated by this webRaterID and are of the Comedy genre.
        FourthRatings FR = new FourthRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        RaterDatabase RDB = new RaterDatabase();
        RDB.initialize("data/ratings.csv");
        GenreFilter GF = new GenreFilter("Comedy");
        int numOfSimilarRaters = 10;
        int minimalRaters = 4;
        ArrayList<Rating> list = FR.getSimilarRatingsByFilter1(webRaterID, numOfSimilarRaters, minimalRaters, GF);
        ArrayList<Rating> top10RecommendedMovies = new ArrayList<Rating>();
        if(list.size() >= 10){
            for(int i = 0; i < 10; i++){
                top10RecommendedMovies.add(list.get(i));
            }
        }
        else{
            int num = list.size();
            for(int j = 0; j < num; j++){
                top10RecommendedMovies.add(list.get(j));
            }
        }
        if(top10RecommendedMovies.isEmpty()){
            System.out.println("Not enough movies to recommend for this user!");
        }
        else{
            /*JFrame frame = new JFrame();
            JTable table = new JTable();
            Object rowData [] = null;
            for(int n = 0; n < top10RecommendedMovies.size(); n++){
                rowData[] = { {MovieDatabase.getTitle(top10RecommendedMovies.get(n).getItem()), MovieDatabase.getYear(top10RecommendedMovies.get(n).getItem())
                } };
            }
            Object columnNames[] = {"Title", "Year"};
            JTable table = new JTable(rowData, columnNames);
            
            JFrame model = new DefaultTableModel();
            model.addColumn("Movie Title");
            model.addColumn("Year Of Movie");
            for(int n = 0; n < top10RecommendedMovies.size(); n++){
                String a = Integer.toString(n);
                a = {MovieDatabase.getTitle(top10RecommendedMovies.get(n).getItem()), MovieDatabase.getYear(top10RecommendedMovies.get(n).getItem())
                };
            }
            JFrame table = new JTable(model);*/
            System.out.println("<h1> Recommended Comedy Movies </h1>");
            System.out.println("h1{text-align : center; color: rgb(107, 75, 183); font-size: 20pt}");
            System.out.print("<table> <tr class = 'TitleRow'> <th> Movie Image </th> <th> Movie Title </th> <th> Year </th> </tr>");
            for(int n = 0; n < top10RecommendedMovies.size(); n++){
                Rating R = top10RecommendedMovies.get(n);
                String movieId = R.getItem();
                System.out.println("<tr class = 'row'> <td> <img src = \"" + MovieDatabase.getPoster(movieId) + "\"width = 200px height = 200px/a> </td>" +
                                   "<td>" + MovieDatabase.getTitle(movieId) + "</td>" +
                                   "<td>" + MovieDatabase.getYear(movieId) + "</td>" +
                                   "</tr>");
                System.out.println("<style> table, tr ,td, th {font-size: 1.1em; border: solid; border-width: 1pt; border=color : solid black;" + 
                                    "margin: 0px 2px 15px 2px; padding: 9px 17px 5px 17px; font-family; monospace, sans-serif;} </style>");
                System.out.println("<style> table {width 100%; text-align: center; background-color : rgb(223, 224, 202); </style>");
            }
        }
    }
}
    