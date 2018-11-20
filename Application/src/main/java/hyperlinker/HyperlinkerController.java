package hyperlinker;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class HyperlinkerController {
    private static HyperlinkerService hlService = new HyperlinkerService();

    // put request to /names/[name]
    @RequestMapping(value = "/names/{name}", method = RequestMethod.PUT)
    public void putName(@PathVariable("name") String name, @RequestBody Url url){
        hlService.putName(name, url.getUrl());
    }

    // get request to /names/[name]
    @RequestMapping(value = "/names/{name}", method = RequestMethod.GET)
    @ResponseBody
    public NameEntry getName(@PathVariable("name") String name) {
        return hlService.getName(name);
    }

    // delete all entries
    @RequestMapping(value = "/names", method = RequestMethod.DELETE)
    public @ResponseBody void deleteNames() {
        hlService.deleteNames();
    }

    // post request to return annotated text
    @RequestMapping(consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.TEXT_HTML_VALUE,
            method = RequestMethod.POST)
    @ResponseBody
    public String annotateText(@RequestBody String snippet) {
        return hlService.annotateText(snippet);
    }
}
