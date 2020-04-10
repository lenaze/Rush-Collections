package com.javarush.task.task37.task3709.connectors;

import com.javarush.task.task37.task3709.security.SecurityChecker;
import com.javarush.task.task37.task3709.security.SecurityCheckerImpl;

public class SecurityProxyConnector implements Connector {

    SecurityChecker securityChecker = new SecurityCheckerImpl();
    SimpleConnector simpleConnector;

    public SecurityProxyConnector(String resourceString) {
        simpleConnector = new SimpleConnector(resourceString);
    }

    public void connect(){
        if (securityChecker.performSecurityCheck())
            simpleConnector.connect();
    }
}
