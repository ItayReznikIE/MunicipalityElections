import java.util.Collections;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileNotFoundException;

public class Election {
	Vector<Municipality> municipalities;
	Vector<Vote> votes;


	public Election(String persons, String voteTo) throws FileNotFoundException { // constructor
		this.municipalities = readPersonsFils(persons);
		this.votes = ReadVoteToFile(voteTo);
	}

	private Vector<Municipality> readPersonsFils(String Persons)//reads the persons file and updates files accordingly
	{
		Vector<Municipality> municipalities = new Vector<Municipality>();
		Path pathToFile = Paths.get(Persons);
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
			String line = br.readLine(); //read the first line in the file
			line = br.readLine();
			String[] Data ;
			Data = line.split("\t"); //divide line to array of fields
			createsAndUpdatesMunicipalitiesLists(line,Data);
			line = br.readLine(); //next line
		} catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
		return municipalities;

	}

	protected void createsAndUpdatesMunicipalitiesLists(String line, String[] Data)//creates city if needed and update it voters
	{
		while (line != null) //file not finished
		{
			if(!(municipalities.isEmpty()))
			{
				runOnMunicipalitiesAndUpdateFields(Data);
			}
			else
			{
				createFirstMunicipalityAddItAndUpdateItsFields(Data);
			}
		}
	}


	protected void runOnMunicipalitiesAndUpdateFields(String[]Data)
	{
		for(int i = 0; i<municipalities.size(); i++)
		{
			if(municipalities.elementAt(i).getName().equals(Data[3])) //if the vote city is exist, updates it lists
			{
				addVoterOrListCandidateOrMayorCandidate(Data,i);
				break;
			}
			else if(!(municipalities.elementAt(i).getName().equals(Data[3]))&& i==(municipalities.size()-1)) //if its not the last city in the list
			{
				createMunicipalityAddItAndUpdateItsFields(Data,i); //create new city and updates it lists
			}
		}
	}


	private void addVoterOrListCandidateOrMayorCandidate(String[] Data, int i)// add person according to kind
	{
		if(!(Data[5].equals("")) && Data[6].equals("0"))
		{
			createMayorCandidateAndUpdateFields(Data,i);
		}
		else if((!(Data[5].equals("")) && Data[6].equals("1")))
		{
			createListCandidateAndUpdateFields(Data,i);
		}
		else 
		{
			createVoterAndUpdateFields(Data,i);
		}
	}

	protected void createFirstMunicipalityAddItAndUpdateItsFields(String[] Data) //creat first municipality, add it to municipalites list, update its lists
	{
		Municipality newMunicipality = new Municipality(Data[3]);
		municipalities.add(newMunicipality);
		addVoterOrListCandidateOrMayorCandidate(Data,0);
	}

	protected void creatFirstMunicipalityListAndUpdateListCandidate(String[] Data, int i, ListCandidate newListCandidate) // create first municipality list and update listCandiate fields
	{
		MunicipalityList newMunicipalityList = createMunicipalityList(Data); 
		newListCandidate.setMl(newMunicipalityList);
		newMunicipalityList.addCandidate(newListCandidate);						
		municipalities.elementAt(i).addListCandidates(newMunicipalityList);
	}

	protected void creatFirstMunicipalityListAndUpdateMayorCandidate(String[] Data,int i,MayorCandidate newMayorCandidate) // create first municipality list and update mayorCandidate fields
	{
		MunicipalityList newMunicipalityList = createMunicipalityList(Data); 
		newMayorCandidate.setMl(newMunicipalityList);
		newMunicipalityList.addCandidate(newMayorCandidate);						
		municipalities.elementAt(i).addListCandidates(newMunicipalityList);
	}

	protected void createMunicipalityAddItAndUpdateItsFields(String[] Data,int i) //create municipality, add it to municipalities list, update its lists
	{
		Municipality newMunicipality = new Municipality(Data[3]);
		municipalities.add(i+1, newMunicipality);
		addVoterOrListCandidateOrMayorCandidate(Data,i+1);
	}

	protected void MunicipalityListExistSoUpdateMayorCandidateAndMunicipalityList(MayorCandidate newMayorCandidate, int i, int j)//if municipality exist so update fields
	{
		newMayorCandidate.setMl(municipalities.elementAt(i).getMunicipalityLists().elementAt(j));
		municipalities.elementAt(i).getMunicipalityLists().elementAt(j).addCandidate(newMayorCandidate);
	}


	protected void MunicipalityListNoExistSoCreateAndUpdateMayorCandidateAndMunicipalityList(String[] Data, int i, ListCandidate newListCandidate )//if municipality list no exist create it and update mayor candidate
	{
		MunicipalityList newMunicipalityList = createMunicipalityList(Data); 
		newListCandidate.setMl(newMunicipalityList);
		newMunicipalityList.addCandidate(newListCandidate);						
		municipalities.elementAt(i).addListCandidates(newMunicipalityList);
	}

	protected void ifMunicipalityListOfListsNoEmptySoUpdateItListsMayorCandidate(int i,String[] Data, MayorCandidate newMayorCandidate ) // if municipalityList no empty create and update accordingly
	{
		for(int j =0; j<municipalities.elementAt(i).getMunicipalityLists().size(); j++)
		{
			if(municipalities.elementAt(i).getMunicipalityLists().elementAt(j).getName().equals(Data[5]))//if the municipalityList already exist just update the fields
			{
				MunicipalityListExistSoUpdateMayorCandidateAndMunicipalityList(newMayorCandidate, i , j);
				break;
			}
			else if(!(municipalities.elementAt(i).getMunicipalityLists().elementAt(j).getName().equals(Data[5]) && j==municipalities.elementAt(i).getMunicipalityLists().size()-1)) // /if the municipalityList no exist create and update fields
			{
				MunicipalityListNoExistSoCreateAndUpdateMayorCandidateAndMunicipalityList(Data,i,newMayorCandidate);
			}
		}
	}

	protected void ifMunicipalityListOfListsNoEmptySoUpdateItListsListCandidate(int i, String[]Data, ListCandidate newListCandidate) //if municipality list no exist create it and update list candidate
	{
		for(int j =0; j<municipalities.elementAt(i).getMunicipalityLists().size(); j++)
		{
			if(municipalities.elementAt(i).getMunicipalityLists().elementAt(j).getName().equals(Data[5]))
			{
				 MunicipalityListExistSoUpdateListCandidateAndMunicipalityList(newListCandidate,i,j);
				break;
			}
			else if(!(municipalities.elementAt(i).getMunicipalityLists().elementAt(j).getName().equals(Data[5]) && j==municipalities.elementAt(i).getMunicipalityLists().size()-1))
			{
				MunicipalityListNoExistSoCreateAndUpdateListCandidateAndMunicipalityList(Data,i,newListCandidate);
			}
		}
	}
	
	protected void MunicipalityListNoExistSoCreateAndUpdateListCandidateAndMunicipalityList(String[] Data,int i,ListCandidate newListCandidate) //if municipality list no exist create it and update list candidate
	{
		MunicipalityList newMunicipalityList = createMunicipalityList(Data);
		newListCandidate.setMl(newMunicipalityList);
		newMunicipalityList.addCandidate(newListCandidate);
		municipalities.elementAt(i).addListCandidates(newMunicipalityList);
	}

	protected void MunicipalityListExistSoUpdateListCandidateAndMunicipalityList(ListCandidate newListCandidate,int i,int j) // if municipality exits do update list candidate fields
	{
		newListCandidate.setMl(municipalities.elementAt(i).getMunicipalityLists().elementAt(j));
		municipalities.elementAt(i).getMunicipalityLists().elementAt(j).addCandidate(newListCandidate);
	}


	private void createMayorCandidateAndUpdateFields(String[] Data, int i) //create mayor candidate and update fields
	{
		MayorCandidate newMayorCandidate = createMayorCandidate(Data);
		municipalities.elementAt(i).addMayorCandidates(newMayorCandidate);
		municipalities.elementAt(i).addVoter(newMayorCandidate);
		if(!(municipalities.elementAt(i).getMunicipalityLists().isEmpty()))
		{
			ifMunicipalityListOfListsNoEmptySoUpdateItListsMayorCandidate(i,Data,newMayorCandidate);
		}
		else
		{
			creatFirstMunicipalityListAndUpdateMayorCandidate(Data,i,newMayorCandidate);
		}
	}

	private void createListCandidateAndUpdateFields(String[] Data, int i) // create list candidate and update fields
	{
		ListCandidate newListCandidate = createListCandidate(Data);
		municipalities.elementAt(i).addVoter(newListCandidate);
		if(!(municipalities.elementAt(i).getMunicipalityLists().isEmpty()))
		{
			ifMunicipalityListOfListsNoEmptySoUpdateItListsListCandidate(i,Data,newListCandidate);
		}
		else
		{
			creatFirstMunicipalityListAndUpdateListCandidate(Data,i,newListCandidate);
		}
	}


	private void createVoterAndUpdateFields(String[] Data, int i) // create voter and update fields
	{
		Voter newVoter = createVoter(Data);
		municipalities.elementAt(i).addVoter(newVoter);
	}


	private Voter createVoter(String[] Data) // create voter
	{
		int id = Integer.parseInt(Data[0]);
		String name = Data[1];
		int age = Integer.parseInt(Data[2]);
		String city = Data[3];
		return new Voter(id,name,age,city) ;
	}

	private MayorCandidate createMayorCandidate(String[] Data) // create mayor candidate
	{
		int id = Integer.parseInt(Data[0]);
		String name = Data[1];
		int age = Integer.parseInt(Data[2]);
		String city = Data[3];
		int yearsInCity =Integer.parseInt(Data[4]);
		return new MayorCandidate(id,name,age,city, yearsInCity);
	}

	private ListCandidate createListCandidate(String[] Data) // create list candidate
	{
		int id = Integer.parseInt(Data[0]);
		String name = Data[1];
		int age = Integer.parseInt(Data[2]);
		String city = Data[3];
		int yearsInCity =Integer.parseInt(Data[4]);
		return new ListCandidate(id,name,age,city, yearsInCity);
	}

	private MunicipalityList createMunicipalityList(String[] Data) // create municipality list
	{
		String name = Data[5];
		String city = Data[4];
		return new MunicipalityList(name,city);
	}


	private Vector<Vote> ReadVoteToFile(String vote_To) throws FileNotFoundException { //converts the votes text files to data in the fields
		Vector<Vote> votes = new Vector<>();
		Path pathToFile = Paths.get(vote_To);
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
			String line = br.readLine();
			line = br.readLine();
			while (line != null) {
				String[] Data = line.split("\t");
				Vote v = createVote(Data);
				votes.add(v);
				line = br.readLine();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return votes;
	}

	private Vote createVote(String[] Data)//converts one line in the text file to vote according to kind of vote
	{
		Vote v = null;
		MunicipalityList municipalityList = null;
		MayorCandidate mayorCandidate = null;
		int idOfVoter = Integer.parseInt(Data[1]);
		Voter voter = saveVoterFromLine(idOfVoter);;
		if('1'<=Data[0].charAt(0) && Data[0].charAt(0)<='9')
		{
			mayorCandidate = findMayorCandidate(Data[0]);
			v = new Vote(voter,mayorCandidate);
		}
		else
		{
			municipalityList=findMunicipalityList(Data[1]);
			v = new Vote(voter,municipalityList);
		}
		return v;
	}

	protected MunicipalityList findMunicipalityList(String Data) //finds the municapility list the was voted in the municapilities MunicipalityLists list
	{
		String municipalityListName = Data;
		MunicipalityList municipalityList = null;
		for (int i=0 ; i < municipalities.size() ; i++ )
		{
			for(int j=0 ; j< municipalities.elementAt(i).getMunicipalityLists().size() ; j++)
			{
				if(municipalities.elementAt(i).getMunicipalityLists().elementAt(j).getName().equals(municipalityListName))
				{
					municipalityList = municipalities.elementAt(i).getMunicipalityLists().elementAt(j); //לבדוק האם צריך לבלום מקרה שבו ראש העיר לא קיים
				}
			}
		}
		return municipalityList;
	}

	protected MayorCandidate findMayorCandidate(String Data) //finds the mayor candidate the was voted in the municapilities mayorcandidates list
	{
		MayorCandidate mayorCandidate = null;
		int idOfMayorCandidate = Integer.parseInt(Data);
		for (int i=0 ; i < municipalities.size() ; i++ )
		{
			for(int j=0 ; j< municipalities.elementAt(i).getMayorCandidatesList().size() ; j++)
			{
				if(municipalities.elementAt(i).getMayorCandidatesList().elementAt(j).getId() == idOfMayorCandidate)
				{
					mayorCandidate = municipalities.elementAt(i).getMayorCandidatesList().elementAt(j); //לבדוק האם צריך לבלום מקרה שבו ראש העיר לא קיים
				}
			}
		}
		return mayorCandidate;
	}
	
	protected Voter saveVoterFromLine(int idOfVoter) //finds the voter from the voters lists in the municipalities
	{
		Voter voter = null;
		for(int i =0; i<municipalities.size(); i++)
		{
			for(int j = 0; j<municipalities.elementAt(i).getVoters().size(); j++)
			{
				if(municipalities.elementAt(i).getVoters().elementAt(j).getId() == idOfVoter)
				{
					voter = municipalities.elementAt(i).getVoters().elementAt(j);//לבדוק האם צריך לבלום מקרה שבו הבוחר לא קיים בעיר
				}
			}
		}
		return voter;
	}
	

public void printMunicipalitiesList()
{
	for(int i =0; i<municipalities.size(); i++)
	{
		System.out.println(municipalities.elementAt(i).getName());
	}
}



}