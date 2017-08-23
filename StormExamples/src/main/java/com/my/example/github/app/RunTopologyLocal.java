package com.my.example.github.app;

import com.my.example.github.topology.AppTopologyBuilder;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;


public class RunTopologyLocal {
    public static void main(String[] args) throws Exception
    {
        System.out.println("Hello Storm");


        Config config = new Config();
        config.setDebug(false);

        LocalCluster lc = new LocalCluster();
        lc.submitTopology("Github-Analyzer",config, AppTopologyBuilder.prepare().createTopology());

        Thread.sleep(500000);

        lc.killTopology("Github-Analyzer");

        lc.shutdown();
    }


}
