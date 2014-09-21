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
        // digest2.0֧��jdk1.5,�������� logging 1.1.1 �� beanutils 1.8.0
        // ����ʵ��
        Digester digester = new Digester();
        // ����ʼ����ѹ��digester��stack
        digester.push(this);
        // ָ��ƥ��ģʽ��Ҫ��������
        digester.addObjectCreate("students/student", Student.class);
        // ���ö�������
        // digester
        digester.addBeanPropertySetter("students/student/name");
        digester.addBeanPropertySetter("students/student/course");
        // ���ƶ�����һ����ǩ��ʱ�Ķ���
        digester.addSetNext("students/student", "addStudent");  //����������ģʽʱ������addStudent����������Ϊ��ǰ�Ķ���Student
        try
        {
            // ������xml�ļ�����classpath��
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
