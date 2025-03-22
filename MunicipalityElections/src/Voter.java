
public class Voter implements Comparable<Voter> {//, Voterable
	
	protected int id;
	protected String name;
	protected int age;
	protected String city;
	
	public Voter(int id, String name, int age, String city) // voter constructor
	{
		this.id = id;
		this.name = name;
		this.age = age;
		this.city = city;
	}

	public int compareTo(Voter other) // compare voters by age
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
	
	public int getId()
	{
		return this.id ;
	}
}
