package com.indix.health;

/**
 * Created by indix on 20/4/17.
 */


import com.codahale.metrics.health.HealthCheck;


public class DemowizzardHealthCheck extends  HealthCheck {

    private final String template;

    public DemowizzardHealthCheck(String template) {
        this.template = template;
    }

    @Override
    protected Result check() throws Exception {
        final String saying = String.format(template, "TEST");
        if (!saying.contains("TEST")) {
            return Result.unhealthy("template doesn't include a name");
        }
        return Result.healthy();
    }
}