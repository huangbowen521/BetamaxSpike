betamax {
    tapeRoot = new File('src/test/resources/betamax/tapes')
    proxyPort = 1337
    proxyTimeout = 30000
    defaultMode = TapeMode.READ_ONLY
//    ignoreHosts = ['*.deeptraining.com']
    ignoreLocalhost = true
    sslSupport = true
}