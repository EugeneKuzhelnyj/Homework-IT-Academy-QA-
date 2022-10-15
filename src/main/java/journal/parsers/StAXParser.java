package journal.parsers;

import journal.domain.Articles;
import journal.domain.Contacts;
import journal.domain.Hotkeys;
import journal.domain.Journal;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;

public class StAXParser {
    static Journal journal = new Journal();

    public static void main(String[] args) {
        try {
            getValuesFromXML();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        System.out.println(journal);
    }

    public static XMLStreamReader getReader() {

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = null;
        if (factory != null) {
            try {
                reader = factory.createXMLStreamReader(ClassLoader.getSystemResourceAsStream("XMLDoc.txt"));
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        }
        return reader;
    }

    public static void getValuesFromXML() throws XMLStreamException {
        XMLStreamReader reader = getReader();
        String textValue = null;
        int count = 0;
        while (reader.hasNext()) {
            int result = reader.next();

            switch (result) {

                case XMLStreamConstants.START_ELEMENT:

                    switch (reader.getLocalName()) {
                        case "contacts":
                            journal.setContacts(new Contacts());
                            break;
                        case "articles":
                            journal.setArticlesList(new ArrayList<>());
                            break;
                        case "article":
                            Articles articles = new Articles();
                            articles.setId(reader.getAttributeValue(0));
                            journal.getArticlesList().add(articles);
                            break;
                        case "hotkeys":
                            journal.getArticlesList().get(count).setHotkeys(new ArrayList<>());
                            break;
                    }
                    break;

                case XMLStreamConstants.CHARACTERS:
                    textValue = reader.getText().trim();
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    switch (reader.getLocalName()) {
                        case "title":
                            journal.setTitle(textValue);
                            break;
                        case "address":
                            journal.getContacts().setAddress(textValue);
                            break;
                        case "tel":
                            journal.getContacts().setTelephone(textValue);
                            break;
                        case "email":
                            journal.getContacts().setEmail(textValue);
                            break;
                        case "url":
                            journal.getContacts().setUrl(textValue);
                            break;
                        case "articleTitle":
                            journal.getArticlesList().get(count).setTitle(textValue);
                            break;
                        case "author":
                            journal.getArticlesList().get(count).setAuthor(textValue);
                            break;
                        case "articleUrl":
                            journal.getArticlesList().get(count).setUrl(textValue);
                            break;
                        case "hotkey":
                            Hotkeys hotkeys = new Hotkeys(textValue);
                            journal.getArticlesList().get(count).getHotkeys().add(hotkeys);
                            break;
                        case "hotkeys":
                            count++;
                            break;
                    }
                    break;
            }
        }
    }

}
