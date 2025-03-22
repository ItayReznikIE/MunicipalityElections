# MunicipalityElections

A Java-based system for managing municipal elections, built using object-oriented programming principles.

## Features

- Loads data about voters and municipalities from external files  
- Includes classes like `Voter`, `Municipality`, `Vote`, `MayorCandidate` and more  
- Supports vote processing, city creation, and candidate management  
- Provides sorting based on age, years in city, and vote count  
- Runs from a main class (`Main.java`) via console

## Technologies Used

- Java  
- Eclipse IDE

## How to Run

1. Open the project in Eclipse  
2. Set `Main.java` as the entry point  
3. Input files required: `Persons.txt` and `Vote_To.txt` (included in this repository)  
4. Run and view the results in the console

## Project Structure

- `src/` – contains all source code  
- Core classes: `Municipality.java`, `Voter.java`, `Election.java`, etc.  
- `Main.java` – the file that runs the program

## Input Files

The program uses two input files:

### Persons.txt

Contains data about all voters and candidates. Each line represents one person with tab-separated fields:

```
<ID>	<Full Name>	<Age>	<City>	<Years in City>	<List Name>	<Candidate Type>
```

Example:

```
123456789	John Smith	42	Tel Aviv	15	List A	Mayor
```

### Vote_To.txt

Represents the votes each voter gave. Each line has:

```
<Candidate ID>	<Voter ID>
```

Example:

```
987654321	123456789
```

## Author

Itay Reznik
