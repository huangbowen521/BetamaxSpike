package com.thoughtworks.extension;

import co.freeside.betamax.Betamax;
import co.freeside.betamax.Recorder;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;

import java.util.Hashtable;

public class NGRecorder implements IHookable {

    @Override
    public void run(IHookCallBack callBack, ITestResult testResult) {
        Betamax annotation = testResult.getMethod().getMethod().getAnnotation(Betamax.class);

        Hashtable<String, Object> arguments = new Hashtable<String, Object>();
        arguments.put("mode", annotation.mode());
        arguments.put("match", annotation.match());

        final IHookCallBack callBackForClosure = callBack;
        final ITestResult testResultForClosure = testResult;

        Recorder recorder = new Recorder();
        recorder.withTape(annotation.tape(), arguments, new Closure(this) {
            public void doCall() {
                callBackForClosure.runTestMethod(testResultForClosure);
            }
        });
    }
}