package org.tinger.remote;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.tinger.common.serialize.Serializer;
import org.tinger.common.utils.RandomUtils;
import org.tinger.core.remote.RemoteRequest;
import org.tinger.core.remote.RemoteResponse;
import org.tinger.core.remote.RemoteServer;
import org.tinger.remote.encode.TingerRemoteDecoder;
import org.tinger.remote.encode.TingerRemoteEncoder;

/**
 * Created by tinger on 2022-10-05
 */
@Slf4j
public class TingerRemoteServer implements RemoteServer {

    private EventLoopGroup boss;
    private EventLoopGroup worker;
    private int port;


    @Override
    public void register() {

    }

    public TingerRemoteServer start() {

        //负责处理客户端连接的线程池
        boss = new NioEventLoopGroup(1);
        //负责处理读写操作的线程池
        worker = new NioEventLoopGroup(4);
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        //添加解码器
                        pipeline.addLast(new TingerRemoteDecoder(RemoteRequest.class, Serializer.getInstance()));
                        //添加编码器
                        pipeline.addLast(new TingerRemoteEncoder(RemoteResponse.class, Serializer.getInstance()));
                        //添加请求处理器
                        pipeline.addLast(new TingerServerHandler());
                    }
                });
        bind(serverBootstrap);

        return this;
    }

    public void bind(final ServerBootstrap serverBootstrap) {

        this.port = RandomUtils.nextInteger(20000, 24000);

        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                log.info("端口[ {} ] 绑定成功", port);
            } else {
                log.error("端口[ {} ] 绑定失败", port);
                bind(serverBootstrap);
            }
        });
    }

    public TingerRemoteServer close() {
        return this;
    }
}
