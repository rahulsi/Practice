package com.my.example.github.topology;

import com.my.example.github.bolt.AggregateProcessor;
import com.my.example.github.bolt.CommitProcessor;
import com.my.example.github.spout.GithubStreamReader;
import org.apache.storm.topology.TopologyBuilder;

public class AppTopologyBuilder {

    public  static TopologyBuilder prepare() {
        TopologyBuilder builder = new TopologyBuilder();

        builder.setSpout("github-reader-spout", new GithubStreamReader());

        builder.setBolt("commit-processor-bolt", new CommitProcessor())
                .shuffleGrouping("github-reader-spout");

        builder.setBolt("aggregate-processor-bolt", new AggregateProcessor())
                .shuffleGrouping("commit-processor-bolt");

        return builder;
    }
}
