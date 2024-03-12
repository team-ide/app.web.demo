package com.teamide.demo.server;

import android.util.Log;

import com.yanzhenjie.andserver.AndServer;
import com.yanzhenjie.andserver.Server;

import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

public class WebServer {

    private static final String TAG = "WebServer";

    private final ServerContext context;

    private Server server;

    public WebServer(ServerContext context) {
        this.context = context;
    }

    public void startup() {
        if (this.server != null) {
            return;
        }
        Log.d(TAG, "web server startup on:" + context.config.port);
        Server server = AndServer
                .webServer(context.context)
                .port(context.config.port)
                .timeout(context.config.timeout, TimeUnit.SECONDS)
                .build();
        server.startup();
        this.server = server;

        Log.d(TAG, "web server start success on:" + context.config.port);
    }

    public void shutdown() {
        if (this.server == null) {
            return;
        }
        Log.d(TAG, "web server shutdown on:" + context.config.port);
        Server server = this.server;
        this.server = null;
        if (server.isRunning()) {
            server.shutdown();
        }
        Log.d(TAG, "web server stop success on:" + context.config.port);
    }
}
