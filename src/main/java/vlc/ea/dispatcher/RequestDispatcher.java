package vlc.ea.dispatcher;

import vlc.common.to.QuoteTO;

public class RequestDispatcher {

    public static QuoteTO getRandomQuote() {
        return new QuoteTO("Scusa, non ho fantasia!"); // TODO external service API call
    }
}
