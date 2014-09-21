package test;

import java.io.File;
import java.util.Vector;
import org.apache.commons.digester.Digester;

public class DigestStudents
{
    Vector stuClass;

    public DigestStudents()
    {
        stuClass = new Vector();
    }

    public static void main(String[] args)
    {
        DigestStudents digestStudents = new DigestStudents();
        digestStudents.digest();
    }

    private void digest()
    {
        try
        {
            Digester digester = new Digester();
            // Push the current object onto the stack
            //Set the validating parser flag. This must be called before parse() is called the first time. 
            digester.setValidating(false);
            // Creates a new instance of the Student class
            digester.addObjectCreate("stuClass", "test.StuClass");
            digester.addSetProperties("stuClass", "name", "name");
            digester.addObjectCreate("stuClass/student", "test.Student");
            // Uses setName method of the Student instance
            // Uses tag name as the property name
            // addCallMethod��addBeanPropertySetter�ȼ�
            // ���� 0����һ��������Ĭ�Ͼ��ǵ�ǰ��������,���һ������0��ʾ��������
            digester.addCallMethod("stuClass/student/name", "setName", 0);
            digester.addSetProperties("stuClass/student/name", "from", "from");
            // ����һ��������form
            // digester.addBeanPropertySetter( "stuClass/student/name");
            // Uses setCourse method of the Student instance
            // Explicitly specify property name as ''course''
            digester.addBeanPropertySetter("stuClass/student/course");
            // Move to next student,addStudentΪ���е�һ������
            digester.addSetNext("stuClass/student", "addStudent", "test.Student");
                   
            StuClass ds = (StuClass) digester.parse(this.getClass()
                    .getClassLoader().getResourceAsStream("test/students.xml"));
            // Print the contents of the Vector
            System.out.println("Students Vector " + ds.getName());
            System.out.println("Students Vector " + ds.getStudents());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void addStudent(Student stud)
    {
        // Add a new Student instance to the Vector
        stuClass.add(stud);
    }
}
