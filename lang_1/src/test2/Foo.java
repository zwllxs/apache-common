package test2;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Foo
{
    private String name;
    private int age;
    private Bar bar;

    public Foo(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    @Override
    public int hashCode()
    {
        System.out.println("Foo: hashCode: "+HashCodeBuilder.reflectionHashCode(this));
        return HashCodeBuilder.reflectionHashCode(this);

    }

    @Override
    public boolean equals(Object obj)
    {
        System.out.println("Foo: equals");
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    public int compareTo(Object obj)
    {
        System.out.println("Foo:compareTo");
        return CompareToBuilder.reflectionCompare(this, obj);

    }
    
    @Override
    public String toString()
    {
        System.out.println("Foo:toString");
        return ToStringBuilder.reflectionToString(this);
    }

    // @Override
    // public int hashCode()
    // {
    // return new HashCodeBuilder(17, 37).append(name).append(age).append(bar)
    // .toHashCode();
    // }

    // @Override
    // public boolean equals(Object obj)
    // {
    // boolean flag = false;
    // if (obj != null && Foo.class.isAssignableFrom(obj.getClass()))
    // {
    // Foo f = (Foo) obj;
    // flag = new EqualsBuilder().append(name, f.getName()).append(age,
    // f.getAge()).append(bar, f.getBar()).isEquals();
    // }
    // return flag;
    // }

//    public int compareTo(Object obj)
//    {
//        int flag = -1;
//        if (obj != null && Foo.class.isAssignableFrom(obj.getClass()))
//        {
//            Foo f = (Foo) obj;
//            flag = new CompareToBuilder().append(name, getName()).append(age,
//                    f.getAge()).append(bar, f.getBar()).toComparison();
//        }
//        return flag;
//    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public Bar getBar()
    {
        return bar;
    }

    public void setBar(Bar bar)
    {
        this.bar = bar;
    }

    // Ê¡ÂÔgetter/setter·½·¨

}
