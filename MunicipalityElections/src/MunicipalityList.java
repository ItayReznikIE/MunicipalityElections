import java.util.Comparator;
import java.util.Vector;

public class MunicipalityList {
	
	protected String name;
	protected String city;
	protected Vector<ListCandidate> listCandidates = new Vector<ListCandidate>();
	
	public MunicipalityList(String name, String city, Vector<ListCandidate> list_candidate)
	{
		this.name = name;
		this.city = city;
		this.listCandidates = list_candidate;
	}
	
	public MunicipalityList(String name, String city)
	{
		this.name = name;
		this.city = city;
	}
	
	public void addCandidate(ListCandidate candidate)
	{
		Comparator c = new MunicipalityListCompareByAgeAndYearsInCityComparator();
		this.listCandidates.addElement(candidate);
		MyFunctions.bubbleSort(listCandidates,c);
	}
	
	public String getName()
	{
		return this.name;
	}
	
	
	protected Vector<ListCandidate> getListCandidates()
	{
		return this.listCandidates;
	}
	
	 

	

}
