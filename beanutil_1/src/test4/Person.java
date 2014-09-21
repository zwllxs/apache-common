package test4;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Person
{
    private int id;
    
    private String name;
    
    private int age;

    private int money;
    
    public Person()
    {
        // TODO Auto-generated constructor stub
    }
    
    
    public Person(int id, String name, int age)
    {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
    }


    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

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
    
    public String toString()
    {
        return ReflectionToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);
    }


    public int getMoney()
    {
        return money;
    }


    public void setMoney(int money)
    {
        this.money = money;
    }

}
