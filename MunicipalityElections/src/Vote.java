
public class Vote {
protected Voter voter ;
protected MayorCandidate mayorCandidate;
protected MunicipalityList municipaLityList;

public Vote(Voter voter, MayorCandidate mayorCandidate)
{
	this.voter = voter;
	this.mayorCandidate = mayorCandidate;
}

public Vote(Voter voter, MunicipalityList municipaLityList)
{
	this.voter = voter;
	this.municipaLityList = municipaLityList;
}
}
