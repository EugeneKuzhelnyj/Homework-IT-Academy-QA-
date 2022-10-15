package journal.parsers;

import journal.domain.Articles;
import journal.domain.Contacts;
import journal.domain.Hotkeys;
import journal.domain.Journal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class DOMParser {
    private static final String XML_PATH = "XMLDoc.txt";
    static Journal journal = new Journal();

    private static void setValuesForContactsFromXMLNodeList(Node node, Contacts contact) {
        NodeList childNodes = node.getChildNodes();
        DOMParserUtils.getNodeListStream(childNodes).forEach(childNode -> {
            if (childNode instanceof Element) {
                String temp = childNode.getNodeName();
                switch (temp) {
                    case "address" -> contact.setAddress(childNode.getTextContent());
                    case "tel" -> contact.setTelephone(childNode.getTextContent());
                    case "email" -> contact.setEmail(childNode.getTextContent());
                    case "url" -> contact.setUrl(childNode.getTextContent());
                }
            }
        });
    }

    private static void setValuesForArticlesFromXMLNodeList(Node node, List<Articles> listArticles) {
        NodeList childNodes = node.getChildNodes();
        DOMParserUtils.getNodeListStream(childNodes).forEach(childNode -> {
            if (childNode instanceof Element) {
                Articles articles = createArticleFromXMLNodeList(childNode);
                articles.setId(childNode.getAttributes().getNamedItem("ID").getNodeValue());
                listArticles.add(articles);
            }
        });
    }

    private static void setValuesForHotkeysFromXMLNodeList(Node node, List<Hotkeys> hotkeysList) {
        NodeList finalNodes = node.getChildNodes();
        DOMParserUtils.getNodeListStream(finalNodes).forEach(finalNode -> {
            if (finalNode instanceof Element) {
                Hotkeys hotkey = new Hotkeys(finalNode.getTextContent());
                hotkeysList.add(hotkey);
            }
        });
    }

    private static Articles createArticleFromXMLNodeList(Node node) {
        Articles article = new Articles();
        NodeList childOfChildNodes = node.getChildNodes();
        DOMParserUtils.getNodeListStream(childOfChildNodes).forEach(childOfChildNode -> {
            if (childOfChildNode instanceof Element) {
                String temp = childOfChildNode.getNodeName();
                switch (temp) {
                    case "title" -> article.setTitle(childOfChildNode.getTextContent());
                    case "author" -> article.setAuthor(childOfChildNode.getTextContent());
                    case "url" -> article.setUrl(childOfChildNode.getTextContent());
                    case "hotkeys" -> {
                        List<Hotkeys> hotkeysList = new ArrayList<>();
                        setValuesForHotkeysFromXMLNodeList(childOfChildNode, hotkeysList);
                        article.setHotkeys(hotkeysList);
                    }
                }
            }
        });
        return article;
    }

    private static void setValuesForJournalFromXMLNodeList(NodeList nodeList) {
        DOMParserUtils.getNodeListStream(nodeList).forEach(node -> {
            if (node instanceof Element) {
                Contacts contact = new Contacts();
                String temp = node.getNodeName();
                switch (temp) {
                    case "title":
                        journal.setTitle((node.getTextContent()));
                        break;
                    case "contacts":
                        setValuesForContactsFromXMLNodeList(node, contact);
                        journal.setContacts(contact);
                        break;
                    case "articles":
                        List<Articles> articlesList = new ArrayList<>();
                        setValuesForArticlesFromXMLNodeList(node, articlesList);
                        journal.setArticlesList(articlesList);
                }
            }
        });
    }

    public static void main(String[] args) {
        Document document = DOMParserUtils.parseXMLDocument(XML_PATH);
        NodeList nodeList = DOMParserUtils.getNodeList(document);
        setValuesForJournalFromXMLNodeList(nodeList);
        System.out.println(journal);
    }

}
