package com.dkd.theory.rpc.server;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dkd on 2021/1/6
 */
@Slf4j
public class Server {
    private static final Server INSTANCE = new Server();

    public static Server getInstance() {
        return INSTANCE;
    }

    private Selector selector;

    private ExecutorService executor;

    private volatile boolean toStop;

    private int port;

    public void init(int port) {
        this.port = port;
        executor = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(1), new ThreadFactory() {
            private final AtomicInteger mThread = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                final Thread thread = new Thread(r);
                thread.setName(String.format("server-%d", mThread.getAndIncrement()));
                return thread;
            }
        }, new ThreadPoolExecutor.DiscardPolicy());
    }

    private void start() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        selector = Selector.open();
        serverSocketChannel.bind(new InetSocketAddress(this.port), 1024);
    }


    @PreDestroy
    public void toStop() throws InterruptedException {
        toStop = true;
        executor.awaitTermination(1, TimeUnit.SECONDS);
        executor.shutdown();
    }
}
