package org.tinger.embed;

import org.tinger.common.serialize.JsonSerializer;
import org.tinger.common.utils.JsonUtils;
import org.tinger.common.utils.StringUtils;
import org.tinger.core.embed.Response;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by tinger on 2022-10-14
 */
public class TingerResponse implements Response {
    private final HttpServletResponse response;

    TingerResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public void responseBody(String text) {
        if (StringUtils.isEmpty(text)) {
            return;
        }
        try {
            PrintWriter writer = response.getWriter();
            writer.print(text);
        } catch (IOException ignore) {

        }
    }

    @Override
    public void responseBody(Object object) {
        if (object == null) {
            return;
        }
        responseBody(JsonSerializer.getInstance().toJson(object));
    }
}
