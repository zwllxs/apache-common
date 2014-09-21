package test2;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Bar
{
    private String name;

    public Bar(String name)
    {
        this.name = name;
    }

    @Override
    public int hashCode()
    {
        System.out.println("Bar: hashCode");
        return HashCodeBuilder.reflectionHashCode(this);

    }

    @Override
    public boolean equals(Object obj)
    {
        System.out.println("Bar: equals");
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    public int compareTo(Object obj)
    {
        System.out.println("Bar:compareTo");
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

    
//  public int compareTo(Object obj)
//  {
//      int flag = -1;
//      if (obj != null && Foo.class.isAssignableFrom(obj.getClass()))
//      {
//          Foo f = (Foo) obj;
//          flag = new CompareToBuilder().append(name, getName()).append(age,
//                  f.getAge()).append(bar, f.getBar()).toComparison();
//      }
//      return flag;
//  }
    
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    // Ê¡ÂÔgetter/setter·½·¨

}
