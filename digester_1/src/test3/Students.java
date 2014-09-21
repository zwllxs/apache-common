package test3;

import java.util.Vector;

public class Students
{
    private Vector students;

    public Students()
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
}
