package test3;

import java.lang.reflect.InvocationTargetException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConstructorUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.LazyDynaBean;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.beanutils.ResultSetDynaClass;
import org.apache.commons.beanutils.RowSetDynaClass;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

/**
 * 一些常用的操作
 * @author zhangweilin
 *
 */
public class TestBeanUtil
{
    private int age=0;
    private String name="张伟林";
    
    /**
     * 注意到所有Map中的key/value都是String，而不管object中实际的值是多少。
        与此对应的还有static void populate(java.lang.Object bean, java.util.Map properties)
        用于将刚才describe出的Map再装配成一个对象。
        但是，如果某个属性是一个数组。那么他只打印数组中的第一个
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @throws NoSuchMethodException 
     * @throws InstantiationException 
     */
    @SuppressWarnings("unchecked")
    @Test
    public void test() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException
    {
        Month month = new Month(1, "Jan");
        Map map =null;
        try
        {
            //将一个bean的所有属性遍历出来
            map = BeanUtils.describe(this);
            System.out.println("map: "+map);
            Set keySet = map.keySet();
            for (Iterator iter = keySet.iterator(); iter.hasNext();)
            {
                Object element = (Object) iter.next();
                System.out.println("KeyClass:" + element.getClass().getName());
                System.out.println("ValueClass:" + map.get(element).getClass().getName());
                System.out.print(element + "\t");
                System.out.print(map.get(element));
                System.out.println();
            }
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }
        
        System.out.println("\n"+StringUtils.center("将属性集合又封装成对象", 40,"="));
        //将上一步遍历出来的所有属性又包装成一个对象
        LazyDynaBean ll = new LazyDynaBean();
        BeanUtils.populate(ll, map);
        
        //如果bean中有数组,可以通过如下指定要访问的索引
        System.out.println(BeanUtils.getIndexedProperty(month,"days",1));
        System.out.println(BeanUtils.getIndexedProperty(month,"days[4]"));
        
        //复制属性
        Month month2=new Month();
        System.out.println("复制前: "+month2);
        BeanUtils.copyProperties(month2, month);
        System.out.println("复制后: "+month2);
        
        
        System.out.println("使用ConstructorUtils创建对象");
        //ConstructorUtils
        //这个类中的方法主要分成两种，一种是得到构造方法，一种是创建对象。
        Object obj = ConstructorUtils.invokeConstructor(Month.class,new Object[] { new Integer(1222), "Jan" });
        Month month4 = (Month) obj;
        System.out.println(BeanUtils.getProperty(month4, "value"));
        
        
        System.out.println("强制指定构造函数的参数类型");
        Object[] args={new Integer(1), "Jan"};
        Class[] argsType={int.class, String.class};
        Object obj2;
        obj2 = ConstructorUtils.invokeExactConstructor(Month.class, args, argsType);
        Month month5=(Month)obj2;
        System.out.println(BeanUtils.getProperty(month5,"value"));
        
        System.out.println("动态调用方法");
        MethodUtils.invokeMethod(obj2, "testShow", null);
        
        //克隆
        Month month3=new Month();
        month3.setName("3");
        Month month32=month3.getClass().newInstance();
        System.out.println("同一引用吗? "+(month3==month32));
        
        //使用自带的克隆方法
        Month month6=(Month) BeanUtils.cloneBean(month3);
        System.out.println("同一引用吗2? "+(month6==month32));
    }
    
    /**
     * 动态的将ResultSet里的数据集封装成对象
     * @throws SQLException
     */
    @Test
    public void testResultSetDynaClass() throws SQLException
    {
        ResultSet rs = null;
        ResultSetDynaClass rsdc = new ResultSetDynaClass(rs);
        Iterator rows = rsdc.iterator();
        while (rows.hasNext())
        {
            DynaBean row = (DynaBean) rows.next();
            System.out.println(row.get("field1")); // field1是其中一个字段的名字
        }
        rs.close();

    }
    
    /**
     * 动态的将ResultSet里的数据集封装成对象
     * @throws SQLException
     */
    @Test
    public void testRowSetDynaClass()
    {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost/2hu?useUnicode=true&characterEncoding=GBK";
        String username = "root";
        String password = "";

        java.sql.Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url);
            ps = con.prepareStatement("select * from forumlist");
            rs = ps.executeQuery();
            // 先打印一下，用于检验后面的结果。
            while (rs.next())
            {
                System.out.println(rs.getString("name"));
            }
            rs.beforeFirst();// 这里必须用beforeFirst，因为RowSetDynaClass只从当前位置向前滚动

            RowSetDynaClass rsdc = new RowSetDynaClass(rs);
            rs.close();
            ps.close();
            List rows = rsdc.getRows();// 返回一个标准的List，存放的是DynaBean
            for (int i = 0; i < rows.size(); i++)
            {
                DynaBean b = (DynaBean) rows.get(i);
                System.out.println(b.get("name"));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                con.close();
            }
            catch (Exception e)
            {
            }
        }

    }

    /**
     * @return the age
     */
    public int getAge()
    {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age)
    {
        this.age = age;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * 需要注意的是ResultSetDynaClass和RowSetDynaClass的不同之处：
        1，ResultSetDynaClass是基于Iterator的，一次只返回一条记录，而RowSetDynaClass是基于
        List的，一次性返回全部记录。直接影响是在数据比较多时ResultSetDynaClass会比较的快速，
        而RowSetDynaClass需要将ResultSet中的全部数据都读出来（并存储在其内部），会占用过多的
        内存，并且速度也会比较慢。
        2，ResultSetDynaClass一次只处理一条记录，在处理完成之前，ResultSet不可以关闭。
        3，ResultSetIterator的next()方法返回的DynaBean其实是指向其内部的一个固定
        对象，在每次next()之后，内部的值都会被改变。这样做的目的是节约内存，如果你需要保存每
        次生成的DynaBean，就需要创建另一个DynaBean，并将数据复制过去，
     */
    
    
}
