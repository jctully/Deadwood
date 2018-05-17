import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;

public class BoardImport {

  // returns a Document object after loading the book.xml file.
  public Document getDocFromFile(String filename) throws ParserConfigurationException {
    {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db= dbf.newDocumentBuilder();
      Document doc = null;

      try{
        doc = db.parse(filename);
      } catch (Exception ex){
        System.out.println("XML parse failure");
        ex.printStackTrace();
      }
      return doc;

    }//exception handling

  }

  public Room[] readBoardData(Document d) {
    Element root = d.getDocumentElement();
    NodeList setList = root.getElementsByTagName("set");
    Room[] rooms = new Room[10];
    int takeNum = 0;
    for (int i=0; i<setList.getLength(); i++) {

      Node set = setList.item(i);

      String setName = set.getAttributes().getNamedItem("name").getNodeValue();

      //neighbors
      Element setNeighbors = (Element)set;
      Element neighborE = (Element)setNeighbors.getElementsByTagName("neighbors").item(0);

      //neighbor
      NodeList neighborList = neighborE.getElementsByTagName("neighbor");
      String[] neighborNames = new String[neighborList.getLength()];
      for (int j=0; j<neighborList.getLength(); j++) {
        Node neighbor = neighborList.item(j);
        String neighborName = neighbor.getAttributes().getNamedItem("name").getNodeValue();
        neighborNames[j] = neighborName;
        //System.out.println(neighborName);
      }
      //System.out.println();

      //done w/ neighbors
      //area
      Element setArea = (Element)set;
      Element areaE = (Element)setArea.getElementsByTagName("area").item(0);
      int areaX = Integer.parseInt(areaE.getAttributes().getNamedItem("x").getNodeValue());
      int areaY = Integer.parseInt(areaE.getAttributes().getNamedItem("y").getNodeValue());
      int areaH = Integer.parseInt(areaE.getAttributes().getNamedItem("h").getNodeValue());
      int areaW = Integer.parseInt(areaE.getAttributes().getNamedItem("w").getNodeValue());
      //System.out.println(areaX + " " + areaY+ " " + areaH + " " + areaW+"\n");
      //done w/ area

      //takes
      Element setTakes = (Element)set;
      Element takesE = (Element)setTakes.getElementsByTagName("takes").item(0);

      //take
      NodeList takeList = takesE.getElementsByTagName("take");

      Node take = takeList.item(0);
      takeNum = takeList.getLength();

      Element takeArea = (Element)take;
      Element takeareaE = (Element)takeArea.getElementsByTagName("area").item(0);
      int takeareaX = Integer.parseInt(takeareaE.getAttributes().getNamedItem("x").getNodeValue());
      int takeareaY = Integer.parseInt(takeareaE.getAttributes().getNamedItem("y").getNodeValue());
      int takeareaH = Integer.parseInt(takeareaE.getAttributes().getNamedItem("h").getNodeValue());
      int takeareaW = Integer.parseInt(takeareaE.getAttributes().getNamedItem("w").getNodeValue());
      //System.out.println(takeareaX + " " + takeareaY+ " " + takeareaH + " " + takeareaW+"\n");

      //done w/ takes
      //parts

      Element setParts = (Element)set;
      Element partE = (Element)setParts.getElementsByTagName("part").item(0);


      //part
      NodeList partList = root.getElementsByTagName("part");
      Role[] roles = new Role[partList.getLength()];

      for (int b=0; b<partList.getLength(); b++) {
        Node part = partList.item(b);
        String partName = part.getAttributes().getNamedItem("name").getNodeValue();
        int partLevel = Integer.parseInt(part.getAttributes().getNamedItem("level").getNodeValue());

        Element partArea = (Element)part;
        Element partareaE = (Element)partArea.getElementsByTagName("area").item(0);
        int partareaX = Integer.parseInt(partareaE.getAttributes().getNamedItem("x").getNodeValue());
        int partareaY = Integer.parseInt(partareaE.getAttributes().getNamedItem("y").getNodeValue());
        int partareaH = Integer.parseInt(partareaE.getAttributes().getNamedItem("h").getNodeValue());
        int partareaW = Integer.parseInt(partareaE.getAttributes().getNamedItem("w").getNodeValue());
        //System.out.println(partareaX + " " + partareaY+ " " + partareaH + " " + partareaW+"\n");

        Element partD = (Element)part;
        String partLine = partD.getElementsByTagName("line").item(0).getTextContent();
        Role r = new Role(partName, partLine, partLevel, false);
        roles[b] = r;

      }

        Room r = new Room(setName, neighborNames, takeNum, roles);
      //System.out.println(setName + " " + neighborNames[0]+ " " + takeNum);
        rooms[i] = r;

    }
    return rooms;
  }

  public Room[] getRooms(String[] args) {
    Document doc = null;
    try{
      doc = getDocFromFile("board.xml");

    } catch (Exception ex){
    }
    Room[] roomArray = readBoardData(doc);
    return roomArray;
  }

}
