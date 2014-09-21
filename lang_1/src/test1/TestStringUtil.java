package test1;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

/**
 * StringUtil
 * 
 * @author zhangweilin
 */
public class TestStringUtil
{
    @Test
    public void test()
    {
        // ����ַ����Ƿ�Ϊ��
        String str = null;
        System.out.println("StringUtils.isBlank(str): " + StringUtils.isBlank(str));
        System.out.println("StringUtils.isNotBlank(str): "+ StringUtils.isNotBlank(str));
                

        // ��д�ַ���
        String test = " This is a test of the abbreviation. ";
        System.out.println(StringUtils.abbreviate(test, 10));
        System.out.println(StringUtils.abbreviate(test, 10,15));
        
        //�ָ��ַ���
        System.out.println("�ָ��ַ���: ");
        String input = "A b,c.d|e";   
        String input2 = "Pharmacy, basketball funky,Pharmacy, basketball funky,Pharmacy, basketball funky";   
        String[] array1 = StringUtils.split( input, " ,.|"); //��ͬʱָ�����ַָ��ַ���  
        String[] array2 = StringUtils.split( input2, ",", 2 );  
        System.out.println( ArrayUtils.toString( array1 ) );   
        System.out.println( ArrayUtils.toString( array2 ) );  

        
        // ����Ƕ���ַ���
        String htmlContent = " <html>\n " + "   <head>\n "
                + "     <title>Test Page</title>\n " + "   </head>\n "
                + "   <body>\n " + "     <p>This is a TEST!</p>\n "
                + "   </body>\n " + " </html> ";

        // Extract the title from this XHTML content
        String title = StringUtils.substringBetween(htmlContent, "<title>", "</title>");
        System.out.println(" Title:  " + title);
        
        //��֤�ַ�������
        String test1  =   "ORANGE" ;
        String test2  =   "ICE9" ;
        String test3  =   "ICE CREAM" ;
        String test4  =   "820B Judson Avenue" ;

        //�Ƿ���ϣ����ĸ
        boolean t1val = StringUtils.isAlpha(test1); //  returns true
        //�ַ������Ƿ�����ĸ���������
        boolean t2val = StringUtils.isAlphanumeric(test2); //  returns true
        //�ַ������Ƿ��пո�
        boolean t3val = StringUtils.isAlphaSpace(test3); //  returns true
        //�ַ������Ƿ�����������пո�
        boolean t4val = StringUtils.isAlphanumericSpace(test4); //  returns true 
        System.out.println("t1val: "+t1val);
        System.out.println("t2val: "+t2val);
        System.out.println("t3val: "+t3val);
        System.out.println("t4val: "+t4val);
        
        //�����ַ�������Ƶ��
        str="gdfhfapplehgfjhapplegkdfsfdgadgfasapplefdkjhxcvxnvappleadsdfgfsappledkgjisdkjgfoaskdxapplecfoiekdfappledkxt wf kdj poapplesdfasdkihj dgfapplelijsfd";
        System.out.println("����: "+StringUtils.countMatches(str,  "apple"));
        
        //�Ƚϲ�ͬ�ַ���
        //getLevenshteinDistance������Ϊ��һ���ַ���ת������һ���ַ�������Ҫ���ٵĲ���,ɾ�����滻�Ĵ���.
        int dist = StringUtils.getLevenshteinDistance("Word", "World");
        String diff = StringUtils.difference("Word", "Worldfdf");
        int index = StringUtils.indexOfDifference("Word", "World");
        System.out.println(" Edit Distance:  " + dist);
        System.out.println(" Difference:  " + diff);
        System.out.println(" Diff Index:  " + index);
    }
    
    @Test
    public void testString2()
    {
        // data setup
        String str1 = "";
        String str2 = " ";
        String str3 = "\t";
        String str4 = null;
        String str5 = "123";
        String str6 = "ABCDEFG";
        String str7 = "It feels good to use Jakarta Commons.\r\n";

        // check for empty strings
        System.out.println("==============================");
        System.out.println("Is str1 blank? " + StringUtils.isBlank(str1));//�Ƿ�Ϊ��
        System.out.println("Is str2 blank? " + StringUtils.isBlank(str2));
        System.out.println("Is str3 blank? " + StringUtils.isBlank(str3));
        System.out.println("Is str4 blank? " + StringUtils.isBlank(str4));

        // check for numerics
        System.out.println("==============================");
        System.out.println("Is str5 numeric? " + StringUtils.isNumeric(str5));//�Ƿ�������
        System.out.println("Is str6 numeric? " + StringUtils.isNumeric(str6));

        // reverse strings / whole words
        System.out.println("==============================");
        System.out.println("str6: " + str6);
        System.out.println("str6 reversed: " + StringUtils.reverse(str6));//����ĸ��ת
        System.out.println("str7: " + str7);
        String str8 = StringUtils.chomp(str7);  //ȥ�������Ļ��з�
        System.out.println("str8: " + str8);
        str8 = StringUtils.reverseDelimited(str8, ' ');//����ָ���ָ������ַ��������ԷָԪ��ת
        System.out.println("str7 reversed whole words : \r\n" + str8);

        // build header (useful to print log messages that are easy to locate)
        System.out.println("==============================");
        System.out.println("print header:");
        String padding = StringUtils.repeat("=", 50);  //�ظ���ӡָ���ַ���
        String msg = StringUtils.center(" Customised Header ", 50, "%");
        Object[] raw = new Object[]{padding, msg, padding};
        String header = StringUtils.join(raw, "\r\n");//���ַ���������������,������Ϊ���ӷ�
        System.out.println(header);
    }
    
    @Test
    public void testString3()
    {
        //���ֽ�ȡ�ַ���
        /*
         * ʹ�ú���:
            StringUtils.substringBetween(testString,fromString,toString ):ȡ�����ַ�
            ֮����ַ���
            StringUtils.substringAfter( ):ȡ��ָ���ַ�������ַ���
            StringUtils.substringBefore( )��ȡ��ָ���ַ���֮ǰ���ַ���
            StringUtils.substringBeforeLast( )��ȡ�����һ��ָ���ַ���֮ǰ���ַ���
            StringUtils.substringAfterLast( )��ȡ�����һ��ָ���ַ���֮����ַ���
         */
        System.out.println("���ֽ�ȡ�ַ���");
        String formatted = "25 * (30,40)| [50,60] (80,90)| * 30";   
        System.out.println("N0: " + StringUtils.substringBeforeLast( formatted, "*" ) );   
        System.out.println("N1: " + StringUtils.substringBetween( formatted, "(", "," ) );   
        System.out.println(" N2: " + StringUtils.substringBetween( formatted, ",", ")" ) );   
        System.out.println(" N3: " + StringUtils.substringBetween( formatted, "[", "," ) );   
        System.out.println(" N4: " + StringUtils.substringBetween( formatted, ",", "]" ) );   
        System.out.println(" N5: " + StringUtils.substringAfterLast( formatted, "|" ) );  
        System.out.println(" N6: " + StringUtils.substringBeforeLast(formatted,  "|" ));  

    }
}
