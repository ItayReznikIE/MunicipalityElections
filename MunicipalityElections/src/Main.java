import java.io.FileNotFoundException;


public class Main {

	
	public static void main(String[] args) {

		String personsFilePath = "/Users/itayreznik/Desktop";
		String voteToFilePath = "/Users/itayreznik/Desktop";
		try {
			Election election = new Election(personsFilePath, voteToFilePath);
			election.printMunicipalitiesList();
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
