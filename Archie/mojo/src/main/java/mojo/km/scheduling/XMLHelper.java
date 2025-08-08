package mojo.km.scheduling;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * XMLHelper is a class that has some utility method to process an XMLFile
 */
public class XMLHelper
{
    private DOMParser parser;

    private String XMLFileName;

    /**
     * Constructor that takes an XML File name. The file must exist on the current classpath. do not
     * give the path, just the file name such as WeeklySchedule.xml
     */
    public XMLHelper(String XMLFileName)
    {
        this.XMLFileName = XMLFileName;
        getDocument();
    }

    /**
     * Returns the file Name
     * 
     * @return String the XMLFile Name
     */
    public String getXMLFileName()
    {
        return this.XMLFileName;
    }

    /**
     * Returns the XML document object
     * 
     * @return Document the xml document object
     */
    private Document getDocument()
    {
        if (parser == null)
        {
            parser = new DOMParser();
            InputStream inStream = null;
            InputSource inputSource = null;
            try
            {
                inStream = this.getClass().getClassLoader().getResourceAsStream(getXMLFileName());
                InputStreamReader reader = new InputStreamReader(inStream);
                inputSource = new InputSource(reader);
                parser.parse(inputSource);
                inStream.close();
            }
            catch (Exception e)
            {
                System.out.println("Unable to open the xml file: " + e);
            }
        }
        return parser.getDocument();
    }

    /**
     * Returns the Root for the xml file
     * 
     * @return Element the root element for this document
     */
    private Element getRootElement()
    {
        return getDocument().getDocumentElement();
    }

    /**
     * returns a string that represents the name of the root element
     * 
     * @return String the root element name
     */
    public String getRootName()
    {
        return getRootElement().getNodeName();
    }

    /**
     * Checks if a node is valid.(exists)
     * 
     * @param nodeName
     *            the node name to be examined
     * @return boolean true if the node exists in the current xml file, otherwise false
     */
    public boolean isNodeValid(String nodeName)
    {
        NodeList lNodeList = getRootElement().getElementsByTagName(nodeName);
        return (lNodeList.getLength() >= 1);
    }

    /**
     * Returns the number of nodes for a given name
     * 
     * @param nodeName
     *            the node name
     * @return int the number of nodes
     */
    public int getNodeCount(String nodeName)
    {
        return getRootElement().getElementsByTagName(nodeName).getLength();

    }

    /**
     * Returns the attribue value for a given node, in a given position.
     * 
     * @param nodeName
     *            the node name to be used
     * @param position
     *            the node position in the xml file
     * @param attributeName
     *            the attribute name to be retrieved.
     * @return String the attribute value
     */
    public String getAttributeValue(String nodeName, int position, String attributeName)
    {
        NodeList lNodeList = getRootElement().getElementsByTagName(nodeName);
        if (lNodeList.getLength() < position)
        {
            return null;
        }
        NamedNodeMap lMap = lNodeList.item(position - 1).getAttributes();
        return lMap.getNamedItem(attributeName).getNodeValue().trim();
    }

    /**
     * Returns a Map of all the attributes for a given node with a given position in the xml file
     * 
     * @param nodeName
     *            The node Name
     * @param position
     *            the position of the node in the xml file
     * @return Map a map represents all the attributes for a given node.
     */
    public Map getNodeAttributes(String nodeName, int position)
    {
        NodeList lNodeList = getRootElement().getElementsByTagName(nodeName);
        if (lNodeList.getLength() < position)
        {
            return null;
        }

        NamedNodeMap lMap = lNodeList.item(position - 1).getAttributes();
        Map lReturnMap = new HashMap();
        for (int i = 0; i < lMap.getLength(); i++)
        {
            Node lNode = lMap.item(i);
            lReturnMap.put(lNode.getNodeName(), lNode.getNodeValue().trim());
        }
        return lReturnMap;
    }
}