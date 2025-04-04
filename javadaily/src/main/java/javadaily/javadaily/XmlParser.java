package javadaily.javadaily;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;

@Component
public class XmlParser {
    public DataModel parse(String xml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(xml.getBytes()));
        doc.getDocumentElement().normalize();


        Element methodElement = (Element) doc.getElementsByTagName("Method").item(0);
        DataModel.Method method = new DataModel.Method(
                methodElement.getElementsByTagName("Name").item(0).getTextContent(),
                methodElement.getElementsByTagName("Type").item(0).getTextContent(),
                methodElement.getElementsByTagName("Assembly").item(0).getTextContent()
        );


        Element processElement = (Element) doc.getElementsByTagName("Process").item(0);
        Element startElement = (Element) processElement.getElementsByTagName("Start").item(0);
        DataModel.Start start = new DataModel.Start(
                startElement.getElementsByTagName("Epoch").item(0).getTextContent(),
                startElement.getElementsByTagName("Date").item(0).getTextContent()
        );
        DataModel.Process process = new DataModel.Process(
                processElement.getElementsByTagName("Name").item(0).getTextContent(),
                processElement.getElementsByTagName("Id").item(0).getTextContent(),
                start
        );


        String layer = doc.getElementsByTagName("Layer").item(0).getTextContent();


        Element creationElement = (Element) doc.getElementsByTagName("Creation").item(0);
        DataModel.Creation creation = new DataModel.Creation(
                creationElement.getElementsByTagName("Epoch").item(0).getTextContent(),
                creationElement.getElementsByTagName("Date").item(0).getTextContent()
        );


        String type = doc.getElementsByTagName("Type").item(0).getTextContent();


        return new DataModel(method, process, layer, creation, type);
    }
}