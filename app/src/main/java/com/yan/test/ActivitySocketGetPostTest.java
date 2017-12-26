package com.yan.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class ActivitySocketGetPostTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Map<String, String> parms = new HashMap<>();
        parms.put("uin", "709290755");
        post("http://r.qzone.qq.com/cgi-bin/user/cgi_personal_card", parms);
//        get("http://youku.com", null);
    }

    private void get(final String strUrl, final Map<String, String> parms) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Socket socket = null;
                BufferedWriter bufferedWriter = null;
                BufferedReader bufferedReader = null;
                try {
                    StringBuilder sbData = new StringBuilder();
                    if (parms != null && !parms.isEmpty()) {
                        sbData.append("?");
                        for (Map.Entry<String, String> p : parms.entrySet()) {
                            sbData.append(URLEncoder.encode(p.getKey(), "utf-8")).append("=").append(URLEncoder.encode(p.getValue(), "utf-8")).append("&");
                        }
                        sbData.deleteCharAt(sbData.length() - 1);
                    }

                    URL url = new URL(strUrl + sbData.toString());
                    SocketAddress socketAddress = new InetSocketAddress(url.getHost(), url.getDefaultPort());
                    socket = new Socket();
                    socket.connect(socketAddress);

                    bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    bufferedWriter.write("GET " + (url.getFile().isEmpty() ? "/" : url.getFile()) + " HTTP/1.1\r\n" +
                            "Accept: text/html\r\n" +
                            "Accept-Language: zh-CN\r\n" +
                            "User-Agent: Android\r\n" +
                            "Connection: Keep-Alive\r\n" +
                            "Host: " + url.getHost() + "\r\n" +
                            "\r\n");
                    bufferedWriter.flush();

                    bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        Log.e("data", "run: " + line);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    close(bufferedWriter);
                    close(bufferedReader);
                    close(socket);
                }
            }
        }).start();
    }

    private void post(final String strUrl, final Map<String, String> parms) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Socket socket = null;
                BufferedWriter bufferedWriter = null;
                BufferedReader bufferedReader = null;
                try {
                    StringBuilder sbData = new StringBuilder();
                    if (parms != null && !parms.isEmpty()) {
                        for (Map.Entry<String, String> p : parms.entrySet()) {
                            sbData.append(URLEncoder.encode(p.getKey(), "utf-8")).append("=").append(URLEncoder.encode(p.getValue(), "utf-8")).append("&");
                        }
                        sbData.deleteCharAt(sbData.length() - 1);
                    }

                    URL url = new URL(strUrl);
                    SocketAddress socketAddress = new InetSocketAddress(url.getHost(), url.getDefaultPort());
                    socket = new Socket();
                    socket.connect(socketAddress);

                    bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    bufferedWriter.write(
                            //header
                            "POST " + (url.getFile().isEmpty() ? "/" : url.getFile()) + " HTTP/1.1\r\n" +
                                    "Accept: text/html\r\n" +//*/*
                                    "Accept-Language: zh-CN\r\n" +
                                    "User-Agent: Android\r\n" +
                                    "Connection: Keep-Alive\r\n" +
                                    "Content-Length: " + sbData.length() + "\r\n" +
                                    "Content-Type: application/x-www-form-urlencoded\r\n" +
                                    "Host: " + url.getHost() + "\r\n" +
                                    "\r\n" +

                                    //post data
                                    sbData
                    );
                    bufferedWriter.flush();

                    bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        Log.e("data", "run: " + line);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    close(bufferedWriter);
                    close(bufferedReader);
                    close(socket);
                }
            }
        }).start();
    }


    private void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void close(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
