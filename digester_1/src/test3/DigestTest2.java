package test3;

import org.apache.commons.digester.*;
import org.apache.commons.logging.*;
import java.io.IOException;
import java.util.Vector;
import org.apache.commons.digester.xmlrules.*;
import org.xml.sax.SAXException;

public class DigestTest2
{
    private Log log = LogFactory.getLog(this.getClass());
    private Vector students;

    public DigestTest2()
    {
        students = new Vector(5);
    }
    public void addStudent(Student student)
    {
        students.add(student);
    }
    public String toString()
    {
        return ((Student) students.get(0)).getName();
    }
    public void digest()
    {
        // digest2.0支持jdk1.5,并且依赖 logging 1.1.1 和 beanutils 1.8.0
        // 创建实例
        Digester digester = new Digester();
        // 将初始对象压入digester的stack
        digester.push(this);
        // 指明匹配模式和要创建的类
        digester.addObjectCreate("students/student", Student.class);
        // 设置对象属性
        // digester
        digester.addBeanPropertySetter("students/student/name");
        digester.addBeanPropertySetter("students/student/course");
        // 当移动到下一个标签中时的动作
        digester.addSetNext("students/student", "addStudent");  //当再碰到此模式时，调用addStudent方法，参数为当前的对象Student
        try
        {
            // 解析，xml文件放在classpath下
            DigestTest2 ds = (DigestTest2) digester.parse(getClass()
                    .getClassLoader().getResourceAsStream("test3/students.xml"));
            log.info(ds);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String args[])
    {
        DigestTest2 test = new DigestTest2();
        test.digest();
    }
}
