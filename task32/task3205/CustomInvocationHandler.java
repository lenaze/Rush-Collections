package com.javarush.task.task32.task3205;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CustomInvocationHandler implements InvocationHandler {
    private SomeInterfaceWithMethods s;
    public CustomInvocationHandler(SomeInterfaceWithMethods someInterface) {
        this.s = someInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName() + " in");
        Object o = method.invoke( s, args);
        System.out.println(method.getName() + " out");
        return o;
    }
}
