package com.my.example.github.spout;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class GithubStreamReader implements IRichSpout {

    TopologyContext topologyContext = null;
    SpoutOutputCollector spoutOutputCollector = null;

    private static final Logger logger = LoggerFactory.getLogger(GithubStreamReader.class);

    Random r;
    Long commitId = 10000L;
    List<String> usersList = Arrays.asList("rahul@github.com", "saurabh@github.com", "vaibahv@github.com", "rohan@github.com", "sid@github.com");

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("commit"));
    }

    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        r = new Random();
        this.topologyContext = topologyContext;
        this.spoutOutputCollector = spoutOutputCollector;
    }

    public void nextTuple() {
        try {
            Thread.sleep(5000);
        } catch (Exception ex) {
            System.out.println("woken due to :" + ex.getMessage());
        }
        String val = (commitId++).hashCode() + "\t" + usersList.get(Math.abs(r.nextInt()) % usersList.size());
        logger.info("emiting : " + val);
        spoutOutputCollector.emit(new Values(val));

    }

    public Map<String, Object> getComponentConfiguration() {
        return null;
    }

    public void close() {

    }

    public void activate() {

    }

    public void deactivate() {

    }

    public void ack(Object o) {

    }

    public void fail(Object o) {

    }

}
