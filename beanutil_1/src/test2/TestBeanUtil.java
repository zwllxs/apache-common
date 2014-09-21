package test2;

import org.apache.commons.beanutils.BasicDynaBean;
import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;


public class TestBeanUtil
{
    @Test
    public void test() throws Exception
    {
        Role role=new Role("管理员");
        Role role2=new Role("超级管理员");
        Role role3=new Role("财务");
        
        Role[] roleSet=new Role[] {role,role2,role3};
        
        User user=new User("张伟林",24);
        user.setRole(roleSet);
        
        String name=(String) PropertyUtils.getProperty(user, "name");
        System.out.println("name: "+name);
        
        Role r=(Role) PropertyUtils.getProperty(user, "role[1]");
        System.out.println("name: "+r);
        
        Role[] r2=(Role[]) PropertyUtils.getProperty(user, "role");
        System.out.println("name: "+r2.length);
        
        String strArr=StringUtils.join(roleSet);
        System.out.println("strArr: "+strArr);
        
    }
    
    @Test
    public void testDynaBeans() throws IllegalAccessException, InstantiationException
    {
        
        DynaProperty[] dynaBeanProperties = new DynaProperty[] {
                new DynaProperty("name", String.class),
                new DynaProperty("inPrice", Double.class),
                new DynaProperty("outPrice", Double.class), };
        BasicDynaClass cargoClass = new BasicDynaClass("Cargo", BasicDynaBean.class, dynaBeanProperties);
        DynaBean cargo=cargoClass.newInstance();
        System.out.println("cargo: "+cargo.getClass());
        cargo.set("name", "张伟林");
        cargo.set("inPrice",  2222.3343);
        cargo.set("outPrice",  3333.23454);
        
        System.out.println("name: "+cargo.get("name"));
        System.out.println("inPrice: "+cargo.get("inPrice"));
        System.out.println("outPrice: "+cargo.get("outPrice"));
    }
}
