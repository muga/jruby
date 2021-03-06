package org.jruby.java.invokers;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.List;
import org.jruby.Ruby;
import org.jruby.RubyModule;
import org.jruby.javasupport.JavaCallable;
import org.jruby.javasupport.JavaMethod;

public abstract class MethodInvoker extends RubyToJavaInvoker {

    MethodInvoker(RubyModule host, List<Method> methods) {
        super(host, setAccessible( methods.toArray(new Method[methods.size()]) ) );
    }

    MethodInvoker(RubyModule host, Method method) {
        super(host, setAccessible(method));
    }

    @Override
    protected final JavaCallable createCallable(Ruby runtime, Member member) {
        return new JavaMethod(runtime, (Method) member);
    }

    @Override
    protected final JavaCallable[] createCallableArray(JavaCallable callable) {
        return new JavaMethod[] { (JavaMethod) callable };
    }

    @Override
    protected final JavaCallable[] createCallableArray(int size) {
        return new JavaMethod[size];
    }

    @Override
    protected final JavaCallable[][] createCallableArrayArray(int size) {
        return new JavaMethod[size][];
    }

    @Override
    protected final Class[] getMemberParameterTypes(Member member) {
        return ((Method) member).getParameterTypes();
    }

    @Override
    @Deprecated
    protected final boolean isMemberVarArgs(Member member) {
        return ((Method) member).isVarArgs();
    }

}
