package hyperlinker;

import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;

import java.util.HashMap;
import java.util.List;

public class HyperlinkerService {
    // for logging
    private static Logger LOG = LoggerFactory.getLogger(HyperlinkerService.class);

    // map to store name-url pairs
    private static HashMap<String, String> hyperlinks = new HashMap<>();

    // add name-link pair to hyperlinks
    public void putName(String name, String url) {
        LOG.info("Adding name=" + name + ", url=" + url);
        hyperlinks.put(name, url);
    }

    // get name-link pair
    public NameEntry getName(String name) throws NameNotFoundException {
        if (hyperlinks.containsKey(name)) {
            // return entry from map
            LOG.info("Name \"" + name + "\" found in map. Returning...");
            return new NameEntry(name, hyperlinks.get(name));
        }
        // else, send back 404 error
        LOG.info("Name \"" + name + "\" NOT found in map.");
        throw new NameNotFoundException();
    }

    // delete all entries in hyperlinks
    public void deleteNames() {
        LOG.info("DELETE received. Deleting all data...");
        hyperlinks.clear();
    }

    // use hyperlinks to annotate an html snippet
    public String annotateText(String str) {
        LOG.info("Annotating HTML: " + str);

        // parse all possible names in str
        Document doc = Jsoup.parse(str);
        Element body = doc.body();

        Elements els = body.getAllElements();

        for (Element e : els) {
            List<TextNode> tnList = e.textNodes();
            for (TextNode tn : tnList){
                String orig = tn.text();

                // split text into tokens
                String[] tokens = orig.split("\\W");

                // check all tokens, replacing if necessary
                for (String key : tokens) {
                    if (hyperlinks.containsKey(key)) {
                        String hyperlinked = "<a href=\"" + hyperlinks.get(key) + "\">" + key + "</a>";
                        tn.text(orig.replaceAll(key, hyperlinked));
                    }
                }
            }
        }

        // TODO: deal with "&lt;" and "&gt;" problem better
        String modified = body.toString();
        modified = modified.replaceAll("&lt;", "<");
        modified = modified.replaceAll("&gt;", ">");
        return modified;
    }
}
