package test6;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.xml.sax.SAXException;

import test2.ChartRegistry;

public class Test
{
    private List<PayType> payTypeList;
    
    public Test()
    {
        payTypeList=new ArrayList<PayType>();
    }
    public void addPayTypeList(PayType payType)
    {
        payTypeList.add(payType);
    }
    
    /**
     * @param args
     * @throws SAXException 
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException, SAXException
    {
        Test test=new Test();
        test.getData();
    }

    public void getData() throws IOException, SAXException
    {
        File file=new File("src/test6/pay.xml");
        Digester digester = DigesterLoader.createDigester(new File("src/test6/payRule.xml").toURL());
        Object obj = digester.parse(file);
//        Collections.sort(c.getRegistry());
    }
}
