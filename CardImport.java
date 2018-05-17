import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;

public class CardImport {

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

  public Card[] readCardData(Document d) {
    Element root = d.getDocumentElement();
    NodeList cardList = root.getElementsByTagName("card");
    Card[] cards = new Card[cardList.getLength()];
    for (int i=0; i<cardList.getLength(); i++) {
      Node card = cardList.item(i);
      String cardName = card.getAttributes().getNamedItem("name").getNodeValue();
      int cardBudget = Integer.parseInt(card.getAttributes().getNamedItem("budget").getNodeValue());
      Element s = (Element)card;
      Element sceneE = (Element)s.getElementsByTagName("scene").item(0);
      int sceneNumber = Integer.parseInt(sceneE.getAttributes().getNamedItem("number").getNodeValue());
      NodeList parts = s.getElementsByTagName("part");
      Role[] roles = new Role[3];
      //System.out.println(cardName + " " + sceneNumber + " $" + cardBudget);
      for (int j=0; j<parts.getLength(); j++) {
        Element partE = (Element)parts.item(j);
        String partName = partE.getAttribute("name");
        int partLevel = Integer.parseInt(partE.getAttribute("level"));
        String partLine = partE.getElementsByTagName("line").item(0).getTextContent();
        //System.out.println(partName + " " + partLevel + " \"" + partLine);
        Role r = new Role(partName, partLine, partLevel, true);
        roles[j] = r;
        //System.out.println(roles[j].getTitle());
      }
      Card c = new Card(cardName, sceneNumber, roles, cardBudget);
      cards[i] = c;
      //System.out.println(c.getTitle() + "\n");

    }

    return cards;
  }

  public Card[] getCards() {
    Document doc = null;
    try{
      doc = getDocFromFile("cards.xml");

    } catch (Exception ex){
    }
    Card[] cards = readCardData(doc);
    return cards;
  }

}
