package testproject.qa.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author utripathi
 *
 */
public class XMLUtils {
	
	String xmlFile;
	
	public XMLUtils(String xmlFile){
		this.xmlFile=xmlFile;
	}

    public Map<String, Object> getJobDetailNodeValues() {
        return getXMLNodeValues("jobDetail");
    }

    public Map<String, Object> getDataNodeValues() {
        return getXMLNodeValues("testData");
    }

    public Map<String, Object> getXMLNodeValues(String required){
    	
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document dom = null;

        try {

            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.parse(new File(xmlFile));

        }catch(ParserConfigurationException pce) {
            pce.printStackTrace();
            return null;
        }catch(SAXException se) {
            se.printStackTrace();
            return null;
        }catch(IOException ioe) {
            ioe.printStackTrace();
            return null;
        }

        //get the root element
        Element rootElement = dom.getDocumentElement();
        //get a nodelist of required elements
        NodeList nodeList = rootElement.getElementsByTagName(required);

        Map map = new HashMap();

        if (nodeList != null && nodeList.getLength() > 0) {
            for(int i = 0 ; i < nodeList.getLength(); i++) {
                map = getNodeItemAsMap(nodeList, i, map);
            } //end of for loop
        } // if

        return map;
    }

    public Map getNodeItemAsMap (NodeList nodeList, int i, Map map) {
        Element el = (Element)nodeList.item(i);

        return getNodeItemAsMapByTagName(el, map, "param");
    }

    public Map getNodeItemAsMapByTagName (Element el, Map map, String tagName) {

        NodeList nl = el.getElementsByTagName( tagName );

        if(nl != null && nl.getLength() > 0) {
            for(int j = 0 ; j < nl.getLength(); j++) {
                Element elem = (Element)nl.item(j);

                String key = null;

                if(elem.getAttribute("name") != null && elem.getAttribute("name").length() > 0) {
                    key = elem.getAttribute("name");
                    handleStringValue(map, key, elem);
                }
                else if(elem.getAttribute("table") != null && elem.getAttribute("table").length() > 0) {
                    key = elem.getAttribute("table");
                    try {
                        handleTableValues(map, key, elem);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if(elem.getAttribute("multidata") != null && elem.getAttribute("multidata").length() > 0) {
                    key = elem.getAttribute("multidata");
                    try {
                        handleMultiDataValues(map, key, elem);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return map;
    }

    public void handleStringValue(Map map, String key, Element elem) {
//      String key = elem.getAttribute("name");
        try {
            String value = elem.getFirstChild().getNodeValue();
            map.put(key, value);
        } catch (Exception e) {
            // put an empty string if the field does not contain any value in xml file
            map.put(key, "");
        }
    }

    public void handleTableValues(Map map, String key, Element tableRoot) throws Exception {

            NodeList rowList = tableRoot.getElementsByTagName("row");

            Map rowMap = new HashMap();

            if(rowList != null && rowList.getLength() > 0)
            {
                for(int i = 0 ; i < rowList.getLength();i++)
                {
                    Map columnMap = new HashMap();

                    Element rowElement = (Element)rowList.item(i);

                    String rowid = rowElement.getAttribute("id");

                    if (rowid == null || rowid.length() == 0)
                        throw new Exception("The 'id' attribute is mandatory for the 'row' tag!!!");

                        NodeList columnNodes = rowElement.getElementsByTagName("column");

                        if(columnNodes != null && columnNodes.getLength() > 0)
                        {
                            for(int j = 0 ; j < columnNodes.getLength(); j++) {
                                Element elem = (Element)columnNodes.item(j);

                                String columnKey = null;

                                if(elem.getAttribute("id") != null && elem.getAttribute("id").length() > 0){
                                    columnKey = elem.getAttribute("id");
                                    handleStringValue(columnMap, columnKey, elem);
                                }else{
                                    throw new Exception("The 'id' attribute is mandatory for the 'column' tag!!!");
                                }
                            }
                        }
                        rowMap.put(rowid, columnMap);
                } //end of for loop
            }// end of if

            if (rowMap.size() != 0)
                map.put(key, rowMap);
    }

    public void handleMultiDataValues(Map map, String key, Element tableRoot) throws Exception
    {
            NodeList recordList = tableRoot.getElementsByTagName("record");

            Map recordMap = new HashMap();

            if(recordList != null && recordList.getLength() > 0)
            {
                for(int i = 0 ; i < recordList.getLength();i++)
                {
                    Element recordElement = (Element)recordList.item(i);

                    String recordid = recordElement.getAttribute("id");

                    if (recordid == null || recordid.length() == 0)
                        throw new Exception("The 'id' attribute is mandatory for the 'record' tag!!!");

                    Map dataMap = new HashMap();

                    dataMap = getNodeItemAsMapByTagName(recordElement, dataMap, "data");

                    recordMap.put(recordid, dataMap);
                } //end of for loop
            }// end of if

            if (recordMap.size() != 0)
                map.put(key, recordMap);
    }
}