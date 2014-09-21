package test1;

import org.apache.commons.beanutils.BasicDynaBean;
import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

/**
 * �Ե��﷨��̬�ĵ���һ��������ԣ���������(���Թ���)����̬�Ĵ���һ�����
 * @author zhangweilin
 *
 */
public class BeanUtilsUsage
{

    public static void main(String[] args) throws Exception
    {
        demoNormalJavaBeans();
        demoDynaBeans();
    }

    public static void demoNormalJavaBeans() throws Exception
    {

        System.out.println(StringUtils.center(" demoNormalJavaBeans ", 40, "*"));

        // data setup
        Address addr1 = new Address("CA1234", "xxx", "Los Angeles", "USA");
        Address addr2 = new Address("100000", "xxx", "Beijing", "China");
        Address[] addrs = new Address[2];
        addrs[0] = addr1;
        addrs[1] = addr2;
        Customer cust = new Customer(123, "John Smith", addrs);

        // accessing the city of first address
        String cityPattern = "addresses[0].city";
        String name = (String) PropertyUtils.getSimpleProperty(cust, "name");
        String city = (String) PropertyUtils.getProperty(cust, cityPattern);
        Object[] rawOutput1 = new Object[] { "The city of customer ", name,
                "'s first address is ", city, "." };
        System.out.println(StringUtils.join(rawOutput1));

        // setting the zipcode of customer's second address
        String zipPattern = "addresses[1].zipCode";
        
        boolean isWriteable=PropertyUtils.isWriteable(cust, zipPattern);
        System.out.println("�Ƿ��д: "+isWriteable);
        if (isWriteable)
        {
            System.out.println("Setting zipcode ...");
            PropertyUtils.setProperty(cust, zipPattern, "200000");
        }
        String zip = (String) PropertyUtils.getProperty(cust, zipPattern);
        Object[] rawOutput2 = new Object[] { "The zipcode of customer ", name,
                "'s second address is now ", zip, "." };
        System.out.println(StringUtils.join(rawOutput2));

        System.out.println();
    }

    public static void demoDynaBeans() throws Exception
    {

        System.out.println(StringUtils.center(" demoDynaBeans ", 40, "="));

        // creating a DynaBean
        DynaProperty[] dynaBeanProperties = new DynaProperty[] {
                new DynaProperty("name", String.class),
                new DynaProperty("inPrice", Double.class),
                new DynaProperty("outPrice", Double.class), };
        BasicDynaClass cargoClass = new BasicDynaClass("Cargo", BasicDynaBean.class, dynaBeanProperties);
        DynaBean cargo = cargoClass.newInstance();
        // accessing a DynaBean
        cargo.set("name", "Instant Noodles");
        cargo.set("inPrice", new Double(21.3));
        cargo.set("outPrice", new Double(23.8));
        System.out.println("name: " + cargo.get("name"));
        System.out.println("inPrice: " + cargo.get("inPrice"));
        System.out.println("outPrice: " + cargo.get("outPrice"));

        System.out.println();
    }

}
