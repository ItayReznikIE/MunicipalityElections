import java.util.Comparator;

public class MayorCandidate extends ListCandidate { //implements Voterable, chooseable, Comparable<MayorCandidate> מייצגת מועמד לראשות העיר

	protected int numOfVotes;
	public MayorCandidate(int id,String name, int age, String city, int years_in_city, MunicipalityList list_name)
	{
		super(id,name,age,city,years_in_city,list_name);
	}
	
	protected MayorCandidate(int id, String name, int age, String city, int years_in_city)
	{
		super(id,name,age,city,years_in_city);
	}

	public void vote (Vote v)
	{
	}

	public int compareTo(MayorCandidate other)
	{
		if(this.age > other.age)
		{
			return 1;
		}
		else if(this.age < other.age)
		{
			return -1;
		}
		return 0;
	}

	public void compareByNumOfVotes()
	{
		Comparator c = new MayorCandidateVotesComparator(); //להבין איפה המקום הנכון
	}

	public int getNumOfVotes()
	{
		return numOfVotes ;
	}
	
	






}
