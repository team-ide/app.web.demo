package com.teamide.demo.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Locale;

public class BackgroundService extends Service {

    public static Intent createIntent(android.content.Context context) {
        Config config = new Config();
        ServerContext.get().context = context;
        ServerContext.get().config = config;
        return new Intent(context, BackgroundService.class);
    }

    private static final String TAG = "BackgroundService";

    static {

        System.out.println("BackgroundService static init");
    }

    private static WebServer WEB_SERVER;

    private static final Object LOCK = new Object();

    private void initServer() {
        if (WEB_SERVER != null) {
            return;
        }
        synchronized (LOCK) {
            if (WEB_SERVER != null) {
                return;
            }
            ServerContext.get().context = this.getBaseContext();
            WEB_SERVER = new WebServer(ServerContext.get());
            Log.d(TAG, "web server created");
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.initServer();
        Log.d(TAG, "onCreate: ");
        WEB_SERVER.startup();


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");

        // 在这里执行服务的后台任务
        // 获取服务的调用者传递过来的数据
//        String param1 = intent.getStringExtra("param1");
//        String param2 = intent.getStringExtra("param2");
//        Toast.makeText(this, String.format(Locale.US, "param1:%s, param2:%s", param1, param2), Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        WEB_SERVER.shutdown();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("onBind");
        Log.d(TAG, "onBind: ");
        return null;
    }

}