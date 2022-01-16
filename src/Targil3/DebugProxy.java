package Targil3;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class DebugProxy implements java.lang.reflect.InvocationHandler
{
    private Object obj;
    private static PrintWriter writer;

    public static Object newInstance(Object obj)
    {
        return java.lang.reflect.Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                new DebugProxy(obj));
    }

    public static void close_inner_file()
    {
        writer.close();
    }

    private DebugProxy(Object obj) {
        this.obj = obj;
        try{
            writer = new PrintWriter("proxy_output");
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public Object invoke(Object proxy, Method m, Object[] args)
            throws Throwable
    {
        Object result;
        try
        {
            writer.println("before method " + m.getName());
            //if (args != null)
            //{
            //    writer.println(args.toString());
            //}

            if (m.getParameterCount() > 0)
            {
                Parameter[] parameters = m.getParameters();
                writer.println("method params: ");
                for (Parameter parameter : parameters) {
                    writer.println("-- " + parameter + " --");
                }
            }
            result = m.invoke(obj, args);
        }
        catch (InvocationTargetException e)
        {
            throw e.getTargetException();
        }
        catch (Exception e)
        {
            throw new RuntimeException("unexpected invocation exception: " +
                    e.getMessage());
        }
        finally
        {
            writer.println("after method " + m.getName());
        }
        return result;
    }
}