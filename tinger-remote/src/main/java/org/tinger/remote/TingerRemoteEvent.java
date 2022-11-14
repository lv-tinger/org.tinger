package org.tinger.remote;

import io.netty.channel.ChannelHandlerContext;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.tinger.core.event.Event;
import org.tinger.core.remote.RemoteRequest;
import org.tinger.core.remote.RemoteResponse;

/**
 * Created by tinger on 2022-10-06
 */
@Data
@Slf4j
public class TingerRemoteEvent implements Event {
    private ChannelHandlerContext context;
    private RemoteRequest request;
    private RemoteResponse response;
}
