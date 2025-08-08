package pd.juvenilecase.riskanalysis;

import java.util.TreeMap;
import java.util.Comparator;


class CustomComparator implements Comparator
{
    /**
     * @param args
     */

    @Override
    public int compare(Object k1,Object k2)
    {
	// TODO Auto-generated method stub
	if(k1 == null){                   // Handles Null Keys 
	    return (k2 == null)? 0: -1;
	}
	else if(k2 == null){
	    return 1;
	}
	else{
	    return (k1.toString()).compareTo(k2.toString());
	}
    }

}
