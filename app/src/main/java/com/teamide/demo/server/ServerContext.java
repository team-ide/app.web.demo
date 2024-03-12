package com.teamide.demo.server;

import android.content.Context;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public final class ServerContext {

    private static final ServerContext CONTEXT = new ServerContext();

    public static ServerContext get() {
        return CONTEXT;
    }

    public Context context;

    public Config config;

    public static String getServerPort() {

        return "" + get().config.port;
    }


}
