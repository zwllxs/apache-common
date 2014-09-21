package test3;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Month
{
    private int value;
    private String name;
    private int[] days = { 141, 22, 33, 44, 55 };

    public Month()
    {
        System.out.println("Month调用");
    }
    
    public Month(int v, String n)
    {
        System.out.println("Month(int v, String n)调用");
        value = v;
        name = n;
    }

    public void testShow()
    {
        System.out.println("testShow调用");
    }
    
    /**
     * Returns the name.
     * 
     * @return String
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the value.
     * 
     * @return int
     */
    public int getValue()
    {
        return value;
    }

    /**
     * Sets the name.
     * 
     * @param name
     *            The name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Sets the value.
     * 
     * @param value
     *            The value to set
     */
    public void setValue(int value)
    {
        this.value = value;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return ReflectionToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);
    }

    public int[] getDays()
    {
        return days;
    }

    public void setDays(int[] is)
    {
        days = is;
    }

}
