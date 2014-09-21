package test2;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ChartRegistry
{
    List<ChartConfig> registry = new ArrayList<ChartConfig>();

    public void addChart(ChartConfig obj)
    {
//        System.out.println("Ìí¼Ó: "+obj+"\n\n");
        registry.add(obj);
    }
    
    public void test()
    {
        System.out.println("testµ÷ÓÃ: ChartRegistry");
    }

    public java.util.List<ChartConfig> getRegistry()
    {
        return registry;
    }

    public String toString()
    {
        return ReflectionToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);
    }
}
