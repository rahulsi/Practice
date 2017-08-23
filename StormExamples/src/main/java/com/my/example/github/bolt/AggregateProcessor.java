package com.my.example.github.bolt;

import com.my.example.github.spout.GithubStreamReader;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class AggregateProcessor implements IRichBolt {

    private static final Logger logger = LoggerFactory.getLogger(AggregateProcessor.class);
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
        aggCounts.put(key,val==null?1:val+1);
        printReport(aggCounts);
    }

    private void printReport(Map<String, Integer> aggCounts) {
        logger.info("=====================");
        for(String key:aggCounts.keySet())
            logger.info(key + " -> " + aggCounts.get(key));
        logger.info("=====================");
    }

    public void cleanup() {

    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }

    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
