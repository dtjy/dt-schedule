package com.dtjy.job;

import sun.net.www.http.HttpClient;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author jiangyao
 * @Date 2019/9/18 17:52
 **/
public class DoubleColorBallJob {



    public static void main(String[] s)throws Exception{
        URL url = new URL("http://f.apiplus.net/ssq-10.json");
        URLConnection connection = url.openConnection();
//        InputStream inputStream = connection.getInputStream();

        InputStream inputStream = HttpClient.New(url).getInputStream();
        InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");

        BufferedReader br = new BufferedReader(reader);
//        BufferedWriter bw = new BufferedWriter();

        System.out.println(br.readLine());
    }

}
