
public class MinutesFilter implements Filter{
    private int maxTime;
    private int minTime;
    
    public MinutesFilter(int maxTimeParameter, int minTimeParameter){
        maxTime = maxTimeParameter;
        minTime = minTimeParameter;
    }
    
    @Override
    public boolean satisfies(String id){
        if(MovieDatabase.getMinutes(id) >= minTime && MovieDatabase.getMinutes(id) <= maxTime){
            return true;
        }
        return false;
    }
}
