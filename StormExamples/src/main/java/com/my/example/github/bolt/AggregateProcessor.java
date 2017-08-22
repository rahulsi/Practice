package com.my.example.github.bolt;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;

import java.util.HashMap;
import java.util.Map;

public class AggregateProcessor implements IRichBolt {

    TopologyContext topologyContext = null;
    OutputCollector outputCollector = null;
    Map<String, Integer> aggCounts = new HashMap<String, Integer>();

    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.topologyContext = topologyContext;
        this.outputCollector = outputCollector;
    }

    public void execute(Tuple tuple) {
        String key = tuple.getString(0);
        Integer val = aggCounts.get(key);
        aggCounts.put(key,val==null?0:val+1);
        printReport(aggCounts);
    }

    private void printReport(Map<String, Integer> aggCounts) {
        System.out.println("=====================");
        for(String key:aggCounts.keySet())
            System.out.println(key + " -> " + aggCounts.get(key));
        System.out.println("=====================");
    }

    public void cleanup() {

    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }

    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
