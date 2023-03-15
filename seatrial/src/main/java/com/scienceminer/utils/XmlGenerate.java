package com.scienceminer.utils;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlGenerate {

	public static final String filename = "/Users/ethancollopy/dev/diary.xml";
	
	
	public enum Month {

		JAN (1,31),
		FEB   (2 , 28),
		MAR   (3, 31),
		APR   (4, 30),
		MAY   (5, 31),
		JUN   (6, 30),
		JUL   (7, 31),
		AUG   (8, 31),
		SEP   (9, 30),
		OCT   (10, 31),
		NOV   (11, 30),
		DEC   (12, 31);

		private final int index;   
		private final int days; 
		
		Month(int i, int d ) {
			this.index = i;
			this.days = d;
		}

		public static Month convert ( int i )
		{
			if (i  == 1)
				return Month.JAN;
			else if (i == 2)
				return Month.FEB;
			else if (i == 3)
				return Month.MAR;
			else if (i == 4)
				return Month.APR;
			else if (i == 5)
				return Month.MAY;
			else if (i == 6)
				return Month.JUN;
			else if (i == 7)
				return Month.JUL;
			else if (i == 8)
				return Month.AUG;
			else if (i == 9)
				return Month.SEP;
			else if (i == 10)
				return Month.OCT;
			else if (i == 11)
				return Month.NOV;		
			else 
			    return Month.DEC; 

		}

		public int getIndex() {
			return index;
		}

		public int getDays() {
			return days;
		}

	}


    public static void main(String argv[]) {

      try {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("diary");
        doc.appendChild(rootElement);

        int dayTotal = 1;
        String optionalData =  ""; // "COVID";
        
        for ( int i = 1 ; i <= 12 ; i++)
        {
        	Month crntMonth = Month.convert(i);
        // staff elements
        
        	int days = crntMonth.getDays();
        	String month = crntMonth.toString();
        // set attribute to staff element
        	
        	for ( int j = 1 ; j <= days ; j++)
        	{
        		Element entry = doc.createElement("entry");
                rootElement.appendChild(entry);
                
        		Attr attr1 = doc.createAttribute("day");
        		attr1.setValue(new Integer(j).toString() );
        		entry.setAttributeNode(attr1);
     
        		Attr attr = doc.createAttribute("month");
        		attr.setValue(month);
        		entry.setAttributeNode(attr);
     
        		Attr attrY = doc.createAttribute("year");
        		attrY.setValue("2023");
        		entry.setAttributeNode(attrY);
     
        		// entry.appendChild(doc.createTextNode(optionalData + dayTotal + "\t"));
        		entry.appendChild(doc.createTextNode("\t"));
        		
        		dayTotal++;
        	}
             
        }
        
        // write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filename));

        // Output to console for testing
        // StreamResult result = new StreamResult(System.out);
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount","4");
        transformer.transform(source, result);

        System.out.println("File saved!");

      } catch (ParserConfigurationException pce) {
        pce.printStackTrace();
      } catch (TransformerException tfe) {
        tfe.printStackTrace();
      }
    }
}