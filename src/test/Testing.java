package test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Testing {

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document toc = dBuilder.parse("C:\\Users\\e6495\\Desktop\\CapIssue\\ACM20170601\\tempxml\\_ACM0617_ACM_010.xml");
            NodeList txt = toc.getElementsByTagName("text");
            for (int i = 0; i < txt.getLength(); i++) {
                if (txt.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element ele = (Element) txt.item(i);
                    String vals[]=ele.getAttribute("cords").split(",");
                    int x=Integer.parseInt(vals[0]);
                    int y=Integer.parseInt(vals[1]);
                    int w=Integer.parseInt(vals[2]);
                    int h=Integer.parseInt(vals[3]);
                }
            }
        } catch (Exception e) {
        }
    }
}
