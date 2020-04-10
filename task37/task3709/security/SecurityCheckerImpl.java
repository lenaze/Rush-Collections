package com.javarush.task.task37.task3709.security;

import com.javarush.task.task37.task3709.connectors.SimpleConnector;

public class SecurityCheckerImpl implements SecurityChecker {
    @Override
    public boolean performSecurityCheck() {
        System.out.println("SECURITY OK!");
        return true;
    }
}
