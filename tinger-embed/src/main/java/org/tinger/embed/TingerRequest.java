package org.tinger.embed;

import org.tinger.common.serialize.JsonSerializer;
import org.tinger.common.utils.ConverterUtil;
import org.tinger.common.utils.StringUtils;
import org.tinger.core.embed.Request;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by tinger on 2022-10-14
 */
public class TingerRequest implements Request {
    private final HttpServletRequest request;

    TingerRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String string(String parameterName) {
        return request.getParameter(parameterName);
    }

    @Override
    public int integer(String parameterName) {
        String parameter = request.getParameter(parameterName);
        if (StringUtils.isEmpty(parameter)) {
            return 0;
        }
        return ConverterUtil.toInteger(parameter);
    }

    @Override
    public <T> T body(Class<T> type) {
        String text = text();
        if (StringUtils.isNoneBlank(text)) {
            return null;
        }

        return JsonSerializer.getInstance().fromJson(text, type);
    }

    @Override
    public String text() {
        try {
            BufferedReader reader = request.getReader();
            StringBuilder builder = new StringBuilder();
            String line;
            while (null != (line = reader.readLine())) {
                builder.append(line);
            }
            if (builder.length() == 0) {
                return null;
            }
            return builder.toString();
        } catch (IOException e) {
            return null;
        }
    }

}