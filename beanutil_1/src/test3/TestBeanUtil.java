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
 * һЩ���õĲ���
 * @author zhangweilin
 *
 */
public class TestBeanUtil
{
    private int age=0;
    private String name="��ΰ��";
    
    /**
     * ע�⵽����Map�е�key/value����String��������object��ʵ�ʵ�ֵ�Ƕ��١�
        ��˶�Ӧ�Ļ���static void populate(java.lang.Object bean, java.util.Map properties)
        ���ڽ��ղ�describe����Map��װ���һ������
        ���ǣ����ĳ��������һ�����顣��ô��ֻ��ӡ�����еĵ�һ��
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
            //��һ��bean���������Ա�������
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
        
        System.out.println("\n"+StringUtils.center("�����Լ����ַ�װ�ɶ���", 40,"="));
        //����һ���������������������ְ�װ��һ������
        LazyDynaBean ll = new LazyDynaBean();
        BeanUtils.populate(ll, map);
        
        //���bean��������,����ͨ������ָ��Ҫ���ʵ�����
        System.out.println(BeanUtils.getIndexedProperty(month,"days",1));
        System.out.println(BeanUtils.getIndexedProperty(month,"days[4]"));
        
        //��������
        Month month2=new Month();
        System.out.println("����ǰ: "+month2);
        BeanUtils.copyProperties(month2, month);
        System.out.println("���ƺ�: "+month2);
        
        
        System.out.println("ʹ��ConstructorUtils��������");
        //ConstructorUtils
        //������еķ�����Ҫ�ֳ����֣�һ���ǵõ����췽����һ���Ǵ�������
        Object obj = ConstructorUtils.invokeConstructor(Month.class,new Object[] { new Integer(1222), "Jan" });
        Month month4 = (Month) obj;
        System.out.println(BeanUtils.getProperty(month4, "value"));
        
        
        System.out.println("ǿ��ָ�����캯���Ĳ�������");
        Object[] args={new Integer(1), "Jan"};
        Class[] argsType={int.class, String.class};
        Object obj2;
        obj2 = ConstructorUtils.invokeExactConstructor(Month.class, args, argsType);
        Month month5=(Month)obj2;
        System.out.println(BeanUtils.getProperty(month5,"value"));
        
        System.out.println("��̬���÷���");
        MethodUtils.invokeMethod(obj2, "testShow", null);
        
        //��¡
        Month month3=new Month();
        month3.setName("3");
        Month month32=month3.getClass().newInstance();
        System.out.println("ͬһ������? "+(month3==month32));
        
        //ʹ���Դ��Ŀ�¡����
        Month month6=(Month) BeanUtils.cloneBean(month3);
        System.out.println("ͬһ������2? "+(month6==month32));
    }
    
    /**
     * ��̬�Ľ�ResultSet������ݼ���װ�ɶ���
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
            System.out.println(row.get("field1")); // field1������һ���ֶε�����
        }
        rs.close();

    }
    
    /**
     * ��̬�Ľ�ResultSet������ݼ���װ�ɶ���
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
            // �ȴ�ӡһ�£����ڼ������Ľ����
            while (rs.next())
            {
                System.out.println(rs.getString("name"));
            }
            rs.beforeFirst();// ���������beforeFirst����ΪRowSetDynaClassֻ�ӵ�ǰλ����ǰ����

            RowSetDynaClass rsdc = new RowSetDynaClass(rs);
            rs.close();
            ps.close();
            List rows = rsdc.getRows();// ����һ����׼��List����ŵ���DynaBean
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
     * ��Ҫע�����ResultSetDynaClass��RowSetDynaClass�Ĳ�֮ͬ����
        1��ResultSetDynaClass�ǻ���Iterator�ģ�һ��ֻ����һ����¼����RowSetDynaClass�ǻ���
        List�ģ�һ���Է���ȫ����¼��ֱ��Ӱ���������ݱȽ϶�ʱResultSetDynaClass��ȽϵĿ��٣�
        ��RowSetDynaClass��Ҫ��ResultSet�е�ȫ�����ݶ������������洢�����ڲ�������ռ�ù����
        �ڴ棬�����ٶ�Ҳ��Ƚ�����
        2��ResultSetDynaClassһ��ֻ����һ����¼���ڴ������֮ǰ��ResultSet�����Թرա�
        3��ResultSetIterator��next()�������ص�DynaBean��ʵ��ָ�����ڲ���һ���̶�
        ������ÿ��next()֮���ڲ���ֵ���ᱻ�ı䡣��������Ŀ���ǽ�Լ�ڴ棬�������Ҫ����ÿ
        �����ɵ�DynaBean������Ҫ������һ��DynaBean���������ݸ��ƹ�ȥ��
     */
    
    
}
