package com.indix;

/**
 * Created by indix on 20/4/17.
 */


import com.indix.resources.DemowizzardResource;
import com.indix.health.DemowizzardHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DemoWizzardApplication extends Application<DropwizzardDemoConfiguration> {
    public static void main(String[] args) throws Exception {
        new DemoWizzardApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<DropwizzardDemoConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(DropwizzardDemoConfiguration configuration,
                    Environment environment) {

        final DemowizzardResource resource = new DemowizzardResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        final DemowizzardHealthCheck healthCheck =
                new DemowizzardHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);

    }

}
