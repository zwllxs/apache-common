package test2;

import org.junit.Test;


public class TestReflect
{
    @Test
    public void test()
    {
//        System.out.println("Foo: "+Foo.class);
//        System.out.println("Bar: "+Bar.class);
        
        Foo foo=new Foo("уен╟аж",24);
        System.out.println("foo: "+foo);
    }
}
