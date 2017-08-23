package com.my.example.github.bolt;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class CommitProcessor implements IRichBolt {

    private static final Logger logger = LoggerFactory.getLogger(CommitProcessor.class);
    TopologyContext topologyContext = null;
    OutputCollector outputCollector = null;

    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.topologyContext = topologyContext;
        this.outputCollector = outputCollector;
    }

    public void execute(Tuple tuple) {

        logger.info("Processing : " + tuple.getString(0));
        outputCollector.emit(new Values(tuple.getString(0).split("\t")[1]));

    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("email"));
    }

    public void cleanup() {

    }

    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
