package com.thoughtworks;

import co.freeside.betamax.MatchRule;
import co.freeside.betamax.Recorder;
import co.freeside.betamax.TapeMode;
import groovy.lang.Closure;

import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Properties;

public abstract class Service {

    public String sendRequest(final String argument) throws IOException {

        if (IsMockEnabled()) {
            return playbackResponse(argument);
        } else {
            return sendRequestImpl(argument);
        }
    }

    private String playbackResponse(final String argument) {
        Recorder recorder = new Recorder();

        Hashtable<String, Object> arguments = new Hashtable<String, Object>();
        arguments.put("mode", TapeMode.DEFAULT);
        arguments.put("match", new MatchRule[]{MatchRule.method, MatchRule.uri});

        ClosureWithResult closureWithResult = new ClosureWithResult(null) {
            public void doCall() {
                result = sendRequestImpl(argument);
            }
        };

        recorder.withTape(getClass().getSimpleName(), arguments, closureWithResult);
        return closureWithResult.result;
    }

    private boolean IsMockEnabled() throws IOException {
        String className = this.getClass().getSimpleName();
        String key = className + "MockEnabled";
        URL propertiesFile = getClass().getClassLoader().getResource("MockServiceConfig.properties");
        Properties properties = new Properties();
        properties.load(propertiesFile.openStream());
        return Boolean.valueOf(properties.get(key).toString());
    }

    abstract String sendRequestImpl(String argument);

    class ClosureWithResult extends Closure {
        public String result;

        public ClosureWithResult(Object owner, Object thisObject) {
            super(owner, thisObject);
        }

        public ClosureWithResult(Object owner) {
            super(owner);
        }
    }
}
