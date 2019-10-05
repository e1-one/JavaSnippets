package cwe.cwe611;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;


//https://cwe.mitre.org/data/definitions/611.html
public class CommonWeaknessEnumeration611Example {

    private static final String TEXT_FILE_URI = "fileserver:///D:\\develop\\IdeaProjects\\JavaSnippets\\tmp\\testCWE661.txt";
    private static final String ORIGINAL_XML = "<!DOCTYPE acunetix [  <!ENTITY foo SYSTEM \"" + TEXT_FILE_URI +
            "\">]><xxe>&foo;</xxe>";

    public static void main(String... args) throws TransformerException {


        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        System.out.println(transformerFactory.getClass().getName());

        //You could defy cwe-611 with uncommenting next line:
        //transformerFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

        Transformer transformer = transformerFactory.newTransformer();
        StringWriter buff = new StringWriter();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.transform(new StreamSource(new StringReader(ORIGINAL_XML)), new StreamResult(buff));

        System.out.println("XML after transformation: " + buff.toString());
        System.out.println("Can you spot a fileserver content inside 'xxe' tag?  If yes, that means you have cwe611 vulnerability.");
    }

}
