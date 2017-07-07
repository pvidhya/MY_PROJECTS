package javaPackage;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParser4GameSpeedXMLdataStore extends DefaultHandler {
    public Console console;
    public List<Console> consoles;
    public String consoleXmlFileName;
    public String elementValueRead;

    
    public SaxParser4GameSpeedXMLdataStore(String consoleXmlFileName) {
        this.consoleXmlFileName = consoleXmlFileName;
        consoles = new ArrayList<Console>();
        parseDocument();
        prettyPrint();
    }


    private void parseDocument() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(consoleXmlFileName, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }


    private void prettyPrint() {
    
        for (Console console: consoles) {
                System.out.println("console #"+ console.id +":");
        System.out.println("\t\t retailer: " + console.retailer);
        System.out.println("\t\t name: " + console.name);
        for (String accessory: console.accessories) {
            System.out.println("\t\t accessory: " + accessory);
        }
        }
    }

    
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

        if (elementName.equalsIgnoreCase("console")) {
            console = new Console();
            console.setId(attributes.getValue("id"));
            console.setRetailer(attributes.getValue("retailer"));
        }

    }

    
    public void endElement(String str1, String str2, String element) throws SAXException {
 
        if (element.equals("console")) {
            consoles.add(console);
        return;
        }
        if (element.equalsIgnoreCase("image")) {
            console.setImage(elementValueRead);
        return;
        }
        if (element.equalsIgnoreCase("name")) {
            console.setName(elementValueRead);
        return;
        }
	if (element.equalsIgnoreCase("type")) {
            console.setType(elementValueRead);
        return;
        }
        if (element.equalsIgnoreCase("retailername")) {
            console.setRetailerName(elementValueRead);
        return;
        }
         if (element.equalsIgnoreCase("retailercity")) {
            console.setRetailerCity(elementValueRead);
        return;
        }
         if (element.equalsIgnoreCase("retailerzip")) {
            console.setRetailerZip(elementValueRead);
        return;
        }
         if (element.equalsIgnoreCase("retailerstate")) {
            console.setRetailerState(elementValueRead);
        return;
        }
        if (element.equalsIgnoreCase("productonsale")) {
            console.setProductOnSale(elementValueRead);
        return;
        }
        if (element.equalsIgnoreCase("manufacturerrebate")) {
            console.setManufacturerRebate(elementValueRead);
        return;
        }
        if (element.equalsIgnoreCase("manufacturername")) {
            console.setManufacturerName(elementValueRead);
        return;
        }

        if(element.equalsIgnoreCase("accessory")){
           console.getAccessories().add(elementValueRead);
        return;
        }
        if(element.equalsIgnoreCase("price")){
            console.setPrice(Integer.parseInt(elementValueRead));
        return;
        }

    }

    
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }


 

   
}
