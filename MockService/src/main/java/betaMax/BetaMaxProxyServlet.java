package betaMax;

import co.freeside.betamax.MatchRule;
import co.freeside.betamax.Recorder;
import co.freeside.betamax.TapeMode;
import com.woonoz.proxy.servlet.*;
import groovy.lang.Closure;
import org.apache.http.client.protocol.RequestAddCookies;
import org.apache.http.client.protocol.ResponseProcessCookies;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.SystemDefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Map;

public class BetaMaxProxyServlet extends HttpServlet {
    private String tapeName;
    private static final int HTTP_DEFAULT_PORT = 80;
    private URL targetServer;
    private SystemDefaultHttpClient client;

    public BetaMaxProxyServlet() {
        super();
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        try {
            tapeName = servletConfig.getInitParameter("tapeName");
            ProxyServletConfig config = new ProxyServletConfig(servletConfig);
            init(config);
        } catch (IOException e) {
            throw new ServletException(e);
        }
    }

    public void init(URL targetServer, int maxCnx) {
        init(new ProxyServletConfig(targetServer, maxCnx));
    }

    public void init(ProxyServletConfig config) {
        targetServer = config.getTargetUrl();
        if (targetServer != null) {
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme(targetServer.getProtocol(), getPortOrDefault(targetServer.getPort()), PlainSocketFactory.getSocketFactory()));
            BasicHttpParams httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, config.getConnectionTimeout());
            HttpConnectionParams.setSoTimeout(httpParams, config.getSocketTimeout());
            ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager(schemeRegistry);
            cm.setDefaultMaxPerRoute(config.getMaxConnections());
            cm.setMaxTotal(config.getMaxConnections());
            client = new SystemDefaultHttpClient(httpParams);
            client.removeResponseInterceptorByClass(ResponseProcessCookies.class);
            client.removeRequestInterceptorByClass(RequestAddCookies.class);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        new HttpGetRequestHandler(request, response, targetServer, client).execute();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        new HttpDeleteRequestHandler(request, response, targetServer, client).execute();
    }

    @Override
    protected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        new HttpHeadRequestHandler(request, response, targetServer, client).execute();
    }

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        new HttpOptionsRequestHandler(request, response, targetServer, client).execute();
    }

    @Override
    protected void doTrace(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        new HttpTraceRequestHandler(request, response, targetServer, client).execute();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        new HttpPostRequestHandler(request, response, targetServer, client).execute();
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        new HttpPutRequestHandler(request, response, targetServer, client).execute();
    }

    private int getPortOrDefault(int port) {
        if (port == -1) {
            return HTTP_DEFAULT_PORT;
        } else {
            return port;
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Recorder recorder = new Recorder();
        recorder.withTape(tapeName, getArguments(), getCallBack(req, resp));
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
//        getHtmlContent();
    }

    private void getHtmlContent() {
        URL url;
        InputStream stream = null;
        DataInputStream dataInputStream;
        String line;

        try {
            url = new URL("http://localhost:12345/MockService/");
            stream = url.openStream(); // throws an IOException
            dataInputStream = new DataInputStream(new BufferedInputStream(stream));

            while ((line = dataInputStream.readLine()) != null) {
                System.out.println(line);
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch (IOException ioe) {
                // nothing to see here
            }
        }
    }

    private Map getArguments() {
        Hashtable<String, Object> arguments = new Hashtable<String, Object>();
        arguments.put("mode", TapeMode.READ_WRITE);
        arguments.put("match", new MatchRule[]{MatchRule.uri, MatchRule.method, MatchRule.body});
        return arguments;
    }
}