package org.tinger.remote;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.Setter;
import org.tinger.core.event.Producer;
import org.tinger.core.remote.RemoteRequest;
import org.tinger.core.remote.RemoteResponse;

/**
 * Created by tinger on 2022-10-05
 */
@Setter
public class TingerServerHandler extends SimpleChannelInboundHandler<RemoteRequest> {
    private Producer producer;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RemoteRequest msg) {
        TingerRemoteEvent event = new TingerRemoteEvent();
        event.setContext(ctx);
        event.setRequest(msg);
        event.setResponse(new RemoteResponse());
        producer.produce(event);
        ctx.writeAndFlush(event.getResponse());
    }
}