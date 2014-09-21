package test2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.xml.sax.SAXException;

public class DigesterTest
{
    public static void main(String[] args)
    {
        try
        {
            String charts = "src/test2/charts.xml";
            String rule="src/test2/rule.xml";
            DigesterTest test= new DigesterTest();
            System.out.println("======================= getChartRegistryWithCodeRule ==========================");
            ChartRegistry c= test.getChartRegistryWithCodeRule(new File(charts));
            System.out.println(c);
//            System.out.println("======================= getChartRegistryWithCodeRule ==========================");
            System.out.println(test.getChartRegistryWithFileRule(new File(charts),new File(rule)));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    //�������趨����
    private ChartRegistry getChartRegistryWithCodeRule(File charts) throws IOException, SAXException
    {
        
                InputStream is = new FileInputStream(charts);
                Digester digester = new Digester();
                digester.setValidating(false);//������XML����Ӧ��DTD�ĺϷ�����֤
                
                digester.push(new ChartRegistry());                         //����һ��ChartRegistry����, ��������������ʽ����������ѡ�����ַ�ʽ��ע����һ�У�
                //digester.addObjectCreate("charts", ChartRegistry.class);  //����һ��ChartRegistry����, ��Ҳ������������ʽ����������ѡ�����ַ�ʽ��ע����һ�У�
                digester.addSetProperties("charts");
                
                digester.addCallMethod("charts", "test");
//                digester.addc
                
                digester.addObjectCreate("charts/chart", ChartConfig.class); //������<chart>ʱ����һ��ChartConfig����
                digester.addSetProperties("charts/chart");  //����<chart>Ԫ�ص�����(attribute)���Ըմ�����ChartConfig���������(property)��������
               
                digester.addCallMethod("charts/chart", "test");
                
                
                digester.addBeanPropertySetter("charts/chart/legendVisible");//������<chart>����Ԫ��<legendVisible>ʱ����Ԫ��ֵ����ΪChartConfig�����legendVisible���Ե�setter����
                digester.addBeanPropertySetter("charts/chart/type");        //�ο�����ע��
                digester.addBeanPropertySetter("charts/chart/width");        //�ο�����ע��
                digester.addBeanPropertySetter("charts/chart/description"); //�ο�����ע��
                digester.addSetNext("charts/chart", "addChart");        //���ٴ�����<charts>����Ԫ��<chart>ʱ���ö������addChart������
                ChartRegistry c = (ChartRegistry)digester.parse(is);  //���������󣬷��ظ�Ԫ��
                Collections.sort(c.getRegistry());
                return c;
    }
    
    //�������趨����
    private ChartRegistry getChartRegistryWithFileRule(File charts,File rule) throws IOException, SAXException
    {
            Digester digester = DigesterLoader.createDigester(rule.toURL());
            ChartRegistry c = (ChartRegistry)digester.parse(charts);
            Collections.sort(c.getRegistry());
            return c;
    }
}