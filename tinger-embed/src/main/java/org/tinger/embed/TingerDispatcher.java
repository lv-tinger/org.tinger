package org.tinger.embed;

import org.tinger.common.buffer.TingerMapBuffer;
import org.tinger.common.serialize.JsonSerializer;
import org.tinger.core.embed.Controller;
import org.tinger.core.embed.Result;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tinger on 2022-10-07
 */
public class TingerDispatcher extends HttpServlet {
    private final JsonSerializer serializer = JsonSerializer.getInstance();

    private TingerMapBuffer<String, Controller> mapper = new TingerMapBuffer<>();

    public void register(Controller controller) {
        mapper.putIfAbsent(controller.path(), controller);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Result result = doService(req, resp);
        resp.setContentType("application/json; charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write(serializer.toJson(result));
        resp.getWriter().flush();
    }

    private Result doService(HttpServletRequest req, HttpServletResponse resp) {
        String path = req.getRequestURI();
        try {
            Controller controller = mapper.get(path);
            if (controller == null) {
                return Result.builder().code(400).success(false).message("controller not found").build();
            }
            TingerRequest request = new TingerRequest(req);
            TingerResponse response = new TingerResponse(resp);
            Object object = controller.execute(request, response);
            return Result.builder().code(200).success(true).content(object).build();
        } catch (Exception e) {
            throw e;
            /*return Result.builder().code(500).success(false).message(e.getMessage()).build();*/
        }
    }
}