package vlc.ea.rest;

import com.google.api.server.spi.ServiceException;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import vlc.common.to.QuoteTO;
import vlc.ea.dispatcher.RequestDispatcher;

import java.util.logging.Logger;

/**
 * The Adapter API which Endpoints will be exposing as REST service.
 */
@Api(
        name = "adapter",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "vlc-external-adapter",
                ownerName = "vlc-external-adapter",
                packagePath = ""
        )
)
public class Adapter {

    private static final Logger log = Logger.getLogger(Adapter.class.getName());

    /**
     * GET method. Retrieve a random quote.
     *
     * @return random quote retrieved
     * @throws ServiceException on malformed requests or back end error
     */
    @ApiMethod(
            name = "getRandomQuote",
            path = "quote",
            httpMethod = ApiMethod.HttpMethod.GET)
    public QuoteTO getRandomQuote() throws ServiceException {
        log.info("getRandomQuote");
        return RequestDispatcher.getRandomQuote();
    }
}
