import java.util.Vector;

public class Municipality {
	
	protected String name;
	protected Vector<MayorCandidate> mayorCandidates = new Vector<MayorCandidate>();
	protected Vector<Voter> voters = new Vector<>();
	protected Vector<MunicipalityList> municipalityLists = new Vector<MunicipalityList>();
	
	public Municipality(String name, Vector<MayorCandidate> mayors_candidates, Vector<Voter> voters, Vector<MunicipalityList> municipality_list)
	{
		this.name = name;
		this.mayorCandidates = mayorCandidates;
		this.voters = voters;
		this.municipalityLists = municipality_list;
	}
	
	public Municipality(String name)
	{
		this.name = name;
	}
	
	public void addVoter(Voter v)
	{
		voters.add(v);
	}
	
	public void addMayorCandidates(MayorCandidate mc)
	{
		mayorCandidates.add(mc);
	}
	
	public void addListCandidates(MunicipalityList ml)
	{
		municipalityLists.add(ml);
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public Vector<MunicipalityList> getMunicipalityLists()
	{
		return this.municipalityLists;
	}
	
	public Vector <MayorCandidate> getMayorCandidatesList()
	{
		return this.mayorCandidates ;
	}
	
	public Vector <Voter> getVoters()
	{
		return this.voters;
	}
}
