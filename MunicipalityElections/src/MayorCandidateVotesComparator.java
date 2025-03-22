import java.util.Comparator;

public class MayorCandidateVotesComparator implements Comparator {

	public int compare(Object o1, Object o2)
	{
		return (((MayorCandidate)o1).getNumOfVotes()-((MayorCandidate)o2).getNumOfVotes());
	}
}
