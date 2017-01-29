package vlc.ea.dispatcher;

import com.google.api.server.spi.response.InternalServerErrorException;
import vlc.common.to.QuoteTO;
import vlc.ea.quote.ForismaticQuote;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

public class RequestDispatcher {

    private static Logger log = Logger.getLogger(RequestDispatcher.class.getName());

    public static QuoteTO getRandomQuote() throws InternalServerErrorException {
        WebTarget resource = ClientBuilder.newClient().target("http://api.forismatic.com/api/1.0/");
        resource = resource.queryParam("method", "getQuote").queryParam("format", "json").queryParam("lang", "en");
        log.info("Contacting: " + resource.getUri().toString());
        Response response = resource.request().accept(MediaType.APPLICATION_JSON).get();
        if (response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
            response.bufferEntity();
            ForismaticQuote forismaticQuote = response.readEntity(ForismaticQuote.class);
            log.info("Retrieved: " + forismaticQuote);
            return new QuoteTO(forismaticQuote.getQuoteText() + " - " + forismaticQuote.getQuoteAuthor());
        } else {
            throw new InternalServerErrorException("Unable to fetch quote from external service.");
        }
    }
}
