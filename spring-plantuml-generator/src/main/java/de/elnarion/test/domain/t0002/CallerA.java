package de.elnarion.test.domain.t0002;

public class CallerA {
    public void callSomething() {
        CallerB callerB = new CallerB();
        callerB.privateMethodCall();
        callerB.protectedMethodCall();
    }

    public void callSomethingElse() {
        CallerB callerB = new CallerB();
        callerB.protectedMethodCall();
    }
}
