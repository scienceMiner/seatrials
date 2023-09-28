package com.scienceminer.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.*;
import javax.xml.xpath.*;
import java.io.*;
import java.util.Properties;

public class XmlParser {

    private static Properties xpathExpressions;

    public static void main(String[] args) {

        loadXPathExpressions();

        try {

            InputStream xmlFile = com.scienceminer.xml.XmlParser.class.getResourceAsStream("/contacts.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            NodeList contactList = document.getElementsByTagName("contact");

            for (int i = 0; i < contactList.getLength(); i++) {
                Node contactNode = contactList.item(i);

                if (contactNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element contactElement = (Element) contactNode;

                    String name = contactElement.getElementsByTagName("name").item(0).getTextContent();
                    String surname = contactElement.getElementsByTagName("surname").item(0).getTextContent();
                    String dob = contactElement.getElementsByTagName("date-of-birth").item(0).getTextContent();
                    String dob2 = getNodeValue(contactElement, "dob.expression");

                    System.out.println("Name: " + name);
                    System.out.println("Surname: " + surname);
                    System.out.println("Date of Birth: " + dob);
                    System.out.println("Date of Birth from xpath: " + dob);
                    System.out.println("--------------------");


                if (surnameBeginsWithC(surname)) {
                    System.out.println("Name begins with 'C'");
                } else {
                    System.out.println("Name does not begin with 'C'");
                }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean surnameBeginsWithC(String name) throws XPathExpressionException {
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        XPathExpression expr = xpath.compile(xpathExpressions.getProperty("name.expression"));
        return (boolean) expr.evaluate(new InputSource(new StringReader("<root><surname>" + name + "</surname></root>")), XPathConstants.BOOLEAN);
    }


    private static String getNodeValue(Element element, String expressionKey) throws XPathExpressionException {
        String expression = xpathExpressions.getProperty(expressionKey);
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        XPathExpression expr = xpath.compile(expression);
        return (String) expr.evaluate(element, XPathConstants.STRING);
    }

    private static void loadXPathExpressions() {
        xpathExpressions = new Properties();
        try (InputStream inputStream = XmlParser.class.getResourceAsStream("/xpath.properties")) {
            xpathExpressions.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
