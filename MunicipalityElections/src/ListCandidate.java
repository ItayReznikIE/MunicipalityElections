
public class ListCandidate extends Voter  { // implements Comparable<ListCandidate> implements Voterable מייצגת מועמד לרשימה בבחירות במערכת הבחירות
	
	protected int yearsInCity;
	protected MunicipalityList ml;
	
	public ListCandidate(int id,String name, int age, String city, int years_in_city, MunicipalityList ml)
	{
		this(id,name,age,city,years_in_city);
		this.ml = ml;
	}
	
	protected ListCandidate(int id, String name, int age, String city, int years_in_city)
	{
		super(id,name,age,city);
		this.yearsInCity = years_in_city;
	}
	
	public int getAge()
	{
		return this.age;
	}
	
	public int getYearsInCity()
	{
		return this.yearsInCity;
	}
	
	public int compareTo(ListCandidate other) //לבדוק מה ההשוואה הטבעית ולשנות בהתאם
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
	protected void setMl(MunicipalityList ml)
	{
		this.ml = ml;
	}

}
