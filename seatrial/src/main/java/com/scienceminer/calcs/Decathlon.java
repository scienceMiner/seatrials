package com.scienceminer.calcs;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

/*
 * Names must start in the first column of the output and be capitalised, and scores must be separated from 
 * names by the appropriate number of space 
 * characters to enable them to be right-justified with the rightmost digit of the score in the 25th column. 
 * 

 Sample input

 Carter 100m 10.64				// whitespace separation
 Bush 100m 10.20
 Reagan 100m 10.3
 #
 Reagan Javelin 60.4
 Carter Javelin 64.3
 REAGAN Long 690
 Bush 400m 43.2
 #
 ##

 Sample output

 BUSH                 1047
 REAGAN               1023
 CARTER                942

 REAGAN               1534
 BUSH                 1155
 CARTER                803
 */

public class Decathlon {

	private static String INPUTFILE = "Decathlon.dat";
	private static String OUTPUTFILE = "Decathlon.out";

	private static final Pattern WHOLE_LINE = Pattern.compile("\\s+");

	//	EventCalculationRunnable myRunnable = new EventCalculationRunnable(currentMap);
	//			Thread t = new Thread(myRunnable);
	//			t.start();


	public class EventCalculationRunnable implements Runnable {

		private HashMap<String,List<EventResult>> resultMap;

		public EventCalculationRunnable(HashMap<String,List<EventResult>> map) {

			this.resultMap = map;
		}

		public void run() {
			for ( List<EventResult> events : resultMap.values())
			{
				for ( EventResult result : events )
				{
					result.calculate();
				}
			}
		}

	}


	private static List<Entry<String,Accumulator>> sortByFinalScore(Map<String,Accumulator> map) { 
		List<Entry<String,Accumulator>> list = new LinkedList<Entry<String,Accumulator>>(map.entrySet());
		// A Custom Comparator for FinalScore...
		Collections.sort(list, new Comparator<Entry<String,Accumulator>>() {
			public int compare(Entry<String,Accumulator> o1, Entry<String,Accumulator> o2) {
				return ((Comparable<Integer>) ((Entry<String,Accumulator>) (o2)).getValue().total)
						.compareTo(((Entry<String,Accumulator>) (o1)).getValue().total);
			}
		});

		return list;
	}

	public enum Category {
		RUNNING,
		THROWING,
		JUMPING
	}

	public enum Event {

		ONEHUNDRED ("100m",Category.RUNNING,25.4347, 18, 1.81),
		HURDLES   ("110m", Category.RUNNING,5.74352, 28.5, 1.92),
		FOURHUNDRED   ("400m",Category.RUNNING, 1.53775, 82, 1.81),
		FIFTEENHUNDRED  ("1500m" , Category.RUNNING,0.03768, 480, 1.85),
		DISCUS ("Discus", Category.THROWING, 12.91, 4,  1.1),
		JAVELIN  ("Javelin",Category.THROWING, 10.14, 7, 1.08),
		SHOTPUT  ("Shot",Category.THROWING, 51.39, 1.5, 1.05 ),
		LONGJUMP ("Long",Category.JUMPING,0.14354, 220, 1.4),
		HIGHJUMP   ("High", Category.JUMPING,0.8465, 75, 1.42),
		POLEVAULT ("Pole",Category.JUMPING, 0.2797, 100, 1.35);

		private final String abbreviation;   
		private final Category type; 
		private final double a;
		private final double b;
		private final double c;


		Event(String abbrev, Category t, double A, double B, double C) {
			this.abbreviation = abbrev;
			this.type = t;
			this.a = A;
			this.b = B;
			this.c = C;
		}

		int calculate(Event e, double result)
		{
			Category type = e.type();
			Double points = 0.0;
			if (type.equals(Category.JUMPING))
			{
				points = jumpingCalc(result);
			}
			else if (type.equals(Category.RUNNING))
			{
				points = runningCalc(result);
			}
			else if (type.equals(Category.THROWING))
			{
				points = throwingCalc(result);
			}

			return points.intValue();
		}

		public String abbreviaton()   { return abbreviation; }
		public Category type() { return type; }
		public double A() { return a; }
		public double B() { return b; }
		public double C() { return c; }

		public double runningCalc(double time) {
			return A() * Math.pow(B()- (time), C());
		}

		public double throwingCalc(double distanceM) {
			return A() * Math.pow(distanceM - B(), C());
		}

		public double jumpingCalc(double distanceCM) {
			return A() * Math.pow(distanceCM - B(), C());
		}

		public static Event convert ( String event )
		{
			if (event.equals("100M"))
				return Event.ONEHUNDRED;
			else if (event.equals("400M"))
				return Event.FOURHUNDRED;
			else if (event.equals("LONG"))
				return Event.LONGJUMP;
			else if (event.equals("JAVELIN"))
				return Event.JAVELIN;
			else if (event.equals("110M"))
				return Event.HURDLES;
			else if (event.equals("POLE"))
				return Event.POLEVAULT;
			else if (event.equals("SHOT"))
				return Event.SHOTPUT;
			else if (event.equals("LONG"))
				return Event.LONGJUMP;
			else if (event.equals("HIGH"))
				return Event.HIGHJUMP;
			else // (event.equals("1500M"))
				return Event.FIFTEENHUNDRED; // NOT DALEYS BEST BUT IN HAND BY NOW ANYWAY

		}

	}

	public class EventResult {
		private Event event;
		private int points;
		private double result;

		EventResult(Event e, double r)
		{
			setEvent(e);
			setResult(r);
			this.calculate();
		}

		EventResult(String e, String resultString)
		{
			setEvent(Event.convert(e.toUpperCase()));
			setResult(Double.parseDouble(resultString));
			this.calculate();
		}

		public void calculate() {

			points = event.calculate(event,result);
		}

		public Event getEvent() {
			return event;
		}

		public void setEvent(Event event) {
			this.event = event;
		}

		public int getPoints() {
			return points;
		}

		public void setPoints(int points) {
			this.points = points;
		}

		public double getResult() {
			return result;
		}

		public void setResult(double result) {
			this.result = result;
		}

		public String toString() {
			StringBuilder output = new StringBuilder();
			output.append("(" + event.abbreviaton() + ", " + result + "):" + points );
			return output.toString();
		}
	}

	private class Accumulator {
		List<EventResult> eventList;
		int total;

		Accumulator(EventResult e)
		{
			total = e.getPoints();
			eventList = new LinkedList<EventResult>();	
			eventList.add(e);
		}

		public void add(EventResult e)
		{
			eventList.add(e);
			total = e.getPoints() + total;

		}

	}

	private String display(String name, Accumulator value)
	{
		int spaces = 25 - name.length();	
		return String.format("%1s%2$"+spaces+"s", name, value.total);
	}


	private void outputScore() throws IOException
	{
		FileWriter fstream;
		BufferedWriter out;

		fstream = new FileWriter(OUTPUTFILE);
		out = new BufferedWriter(fstream);

		int competitions = scoreboard.size();
		int currentCompetition = 1;
		for ( HashMap<String,Accumulator> competition : scoreboard )
		{

			List<Entry<String,Accumulator>> resultList = Decathlon.sortByFinalScore(competition);

			int nextCompetitionSize = resultList.size();
			int currentCompetitionEvent = 1;
			for (Entry<String,Accumulator> entry : resultList)
			{
				if (currentCompetitionEvent < nextCompetitionSize)
					out.write(display(entry.getKey(),entry.getValue()) + "\n" );
				else
					out.write(display(entry.getKey(),entry.getValue()) );
				currentCompetitionEvent++;

			}

			if (currentCompetition < competitions)
				out.write( "\n\n");
			currentCompetition++;

		}
		// lastly, close the file and end
		out.close();
	}


	private List<HashMap<String,Accumulator>> populateScore(File inputFile) throws IOException
	{
		List<HashMap<String, Accumulator>> competitions = new LinkedList<HashMap<String, Accumulator>>();
		HashMap<String, Accumulator> currentMap = new HashMap<String, Accumulator>();
		try ( BufferedReader in = new BufferedReader(new FileReader(inputFile)); )
		{
			String line = "";
			while ((line = in.readLine()) != null) {

				if (!line.startsWith("##",0))
				{
					if (!line.startsWith("#", 0))
					{

						String parts[] = WHOLE_LINE.split(line);
						EventResult e1 = new EventResult(parts[1],parts[2]);
						String athlete = parts[0].toUpperCase();
						if (currentMap.containsKey(athlete))
						{
							Accumulator resultList = currentMap.get(athlete);					
							resultList.add(e1);
						}
						else
						{
							Accumulator resultList = new Accumulator(e1);	
							currentMap.put(athlete, resultList);
						}
					}
					else
					{
						if (!currentMap.isEmpty())
						{

							competitions.add(currentMap);
						}

						// reset the map
						currentMap = new HashMap<String, Accumulator>();

					}

				}
				else
					break;
			}

		}
		//	in.close();
		return competitions;
	}

	private static List<HashMap<String,Accumulator>> scoreboard ; // = new HashMap<String,List<EventResult>>();

	public static void runIterations( int num ) 
	{
		int iterations = num;
		long start = System.nanoTime();

		for (int i = 0 ; i < iterations; i++)
		{

			Decathlon decathlon = new Decathlon();

			try {
				scoreboard = decathlon.populateScore(new File(INPUTFILE));
				decathlon.outputScore();	
			} 
			catch (IOException e) {
				// recover...
				e.printStackTrace();
			}
		}

		long elapsed = System.nanoTime() - start;

		System.out.println("Time taken:" + elapsed);

	}


	public static void main(String[] args) {

		Decathlon decathlon = new Decathlon();

		try {
		//	scoreboard = decathlon.populateScore(new File(INPUTFILE));
			scoreboard = decathlon.populateScore(decathlon.getFileFromResources(INPUTFILE));
			decathlon.outputScore();	
		} 
		catch (IOException e) {
			// recover...
			e.printStackTrace();
		}
	}


	@SuppressWarnings("unused")
	private  File getFileFromResources(String fileName) {

		ClassLoader classLoader = Decathlon.class.getClassLoader();

		URL resource = classLoader.getResource(fileName);
		if (resource == null) {
			throw new IllegalArgumentException("file is not found!");
		} else {
			System.out.println(" creating new file ");
			System.out.println(resource.getFile()  );
			System.out.println(resource.getPath());
			return new File(resource.getFile());
		}

	}

}


