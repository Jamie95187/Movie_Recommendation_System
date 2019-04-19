
public class DirectorsFilter implements Filter{
    private String directors;
    
    public DirectorsFilter(String directorsParameters){
        directors = directorsParameters;
    }
    
    @Override
    public boolean satisfies(String id){
        String[] directorSplittedFromParametersSet = directors.split(",");
        String[] directorSplittedFromSpecificMovie = MovieDatabase.getDirector(id).split(", ");
        for(int i = 0; i < directorSplittedFromSpecificMovie.length; i++){
            for(int k = 0; k < directorSplittedFromParametersSet.length; k++){
                if(directorSplittedFromSpecificMovie[i].equals(directorSplittedFromParametersSet[k])){
                    return true;
                }
            }
        }
        return false;
    }
}
