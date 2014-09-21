package test;

import java.util.Vector;

public class StuClass
{
    private String name;
    private Vector students = new Vector();

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public Vector getStudents()
    {
        return students;
    }
    public void setStudents(Vector students)
    {
        this.students = students;
    }
    public void addStudent(Student student)
    {
        students.add(student);
    }
}
