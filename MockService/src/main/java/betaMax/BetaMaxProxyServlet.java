package betaMax;

import co.freeside.betamax.MatchRule;
import co.freeside.betamax.Recorder;
import co.freeside.betamax.TapeMode;
import com.woonoz.proxy.servlet.ProxyServlet;
import groovy.lang.Closure;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class BetaMaxProxyServlet extends ProxyServlet {
    private String tapeName;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Recorder recorder = new Recorder();
        recorder.withTape(tapeName, getArguments(), getCallBack(req, resp));
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        tapeName = servletConfig.getInitParameter("tapeName");
        super.init(servletConfig);
    }

    private Closure getCallBack(final HttpServletRequest request, final HttpServletResponse response) {
        return new Closure(null) {
            public void doCall() throws IOException, ServletException {
                doService(request, response);
            }
        };
    }

    private void doService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    private Map getArguments() {
        Hashtable<String, Object> arguments = new Hashtable<String, Object>();
        arguments.put("mode", TapeMode.READ_WRITE);
        arguments.put("match", new MatchRule[]{MatchRule.uri, MatchRule.method});
        return arguments;
    }
}