package test3;

import org.apache.commons.digester.*;
import org.apache.commons.logging.*;
import java.io.IOException;
import java.util.Vector;
import org.apache.commons.digester.xmlrules.*;
import org.xml.sax.SAXException;

public class DigestTest
{
    private Log log = LogFactory.getLog(this.getClass());

    public void digestByConfig()
    {
        Digester digester = DigesterLoader.createDigester(this.getClass()
                .getClassLoader().getResource("test3/studentsRule.xml"));
        try
        {
            Students a = (Students) digester.parse(this.getClass()
                    .getClassLoader().getResourceAsStream("test3/students.xml"));
            System.out.println(a);
            log.info(a);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (SAXException e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String args[])
    {
        DigestTest test = new DigestTest();
        test.digestByConfig();
    }
}
