import java.util.Comparator;

public class MunicipalityListCompareByAgeAndYearsInCityComparator implements Comparator{
	
	public int compare(Object o1, Object o2)
	{
		int calcO1 = (((ListCandidate)o1).getAge()) + 2*((((ListCandidate)o1).getYearsInCity()));
		int calcO2 = (((ListCandidate)o2).getAge()) + 2*((((ListCandidate)o2).getYearsInCity()));
		if(calcO1>calcO2)
		{
			return -1;
		}
		else if(calcO1<calcO2)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}

}
