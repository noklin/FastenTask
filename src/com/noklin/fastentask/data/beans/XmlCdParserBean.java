package com.noklin.fastentask.data.beans;


import com.noklin.fastentask.data.entities.CdEntity;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

@Stateless(name = "XmlToCdParserEJB")
public class XmlCdParserBean {

    public XmlCdParserBean() {
    }

    public List<CdEntity> listFromInputStream(InputStream in) throws IOException, ParserConfigurationException, SAXException{
        assert(in != null);
        List<CdEntity> list = new ArrayList<>();
        DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc  = dBuilder.parse(in);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("CD");
        for(int i = 0 ; i < nList.getLength() ; i++){
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                CdEntity current = new CdEntity();
                Element eElement = (Element) nNode;
                if(eElement.getElementsByTagName("TITLE").getLength() == 0
                        ||eElement.getElementsByTagName("ARTIST").getLength() == 0
                        ||eElement.getElementsByTagName("COMPANY").getLength() == 0
                        ||eElement.getElementsByTagName("COUNTRY").getLength() == 0
                        ||eElement.getElementsByTagName("YEAR").getLength() == 0
                        ||eElement.getElementsByTagName("PRICE").getLength() == 0)
                    throw new IOException("Illegal format");

                current.setTitle(eElement.getElementsByTagName("TITLE").item(0).getTextContent());
                current.setArtist(eElement.getElementsByTagName("ARTIST").item(0).getTextContent());
                current.setCompany(eElement.getElementsByTagName("COMPANY").item(0).getTextContent());
                current.setCountry(eElement.getElementsByTagName("COUNTRY").item(0).getTextContent());
                current.setYear(new Short(eElement.getElementsByTagName("YEAR").item(0).getTextContent()));
                current.setPrice(new Float(eElement.getElementsByTagName("PRICE").item(0).getTextContent()));
                list.add(current);
            }

        }
        return list;
    }

    public void uploadCdEntities(List<CdEntity> dataToUpload , OutputStream out) throws ParserConfigurationException , DOMException, TransformerException{
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();
        // root element
        Element rootElement = doc.createElement("CATALOG");
        doc.appendChild(rootElement);

        for(CdEntity entity : dataToUpload){

        //  cd element
            Element cd = doc.createElement("CD");
            rootElement.appendChild(cd);
            addElementToCd(doc , cd , "TITLE" , entity.getTitle());
            addElementToCd(doc , cd , "ARTIST" , entity.getArtist());
            addElementToCd(doc , cd , "COUNTRY" , entity.getCountry());
            addElementToCd(doc , cd , "COMPANY" , entity.getCompany());
            addElementToCd(doc , cd , "PRICE" , entity.getPrice().toString());
            addElementToCd(doc , cd , "YEAR" , entity.getYear().toString());
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult consoleResult = new StreamResult(out);
        transformer.transform(source, consoleResult);

    }

    private void addElementToCd(Document doc , Element cd, String elemName , String node){
        Element element = doc.createElement(elemName);
        element.appendChild(doc.createTextNode(node));
        cd.appendChild(element);
    }

}
