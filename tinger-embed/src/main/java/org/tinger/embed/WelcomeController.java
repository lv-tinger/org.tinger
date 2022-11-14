package org.tinger.embed;

import org.tinger.core.embed.Controller;
import org.tinger.core.embed.Request;
import org.tinger.core.embed.Response;

/**
 * Created by tinger on 2022-10-14
 */
public class WelcomeController implements Controller {
    @Override
    public String path() {
        return "/index.api";
    }

    @Override
    public Object execute(Request request, Response response) {
        return "hello tinger";
    }
}
