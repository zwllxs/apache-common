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
        // 检查字符串是否为空
        String str = null;
        System.out.println("StringUtils.isBlank(str): " + StringUtils.isBlank(str));
        System.out.println("StringUtils.isNotBlank(str): "+ StringUtils.isNotBlank(str));
                

        // 缩写字符串
        String test = " This is a test of the abbreviation. ";
        System.out.println(StringUtils.abbreviate(test, 10));
        System.out.println(StringUtils.abbreviate(test, 10,15));
        
        //分割字符串
        System.out.println("分割字符串: ");
        String input = "A b,c.d|e";   
        String input2 = "Pharmacy, basketball funky,Pharmacy, basketball funky,Pharmacy, basketball funky";   
        String[] array1 = StringUtils.split( input, " ,.|"); //可同时指定多种分割字符串  
        String[] array2 = StringUtils.split( input2, ",", 2 );  
        System.out.println( ArrayUtils.toString( array1 ) );   
        System.out.println( ArrayUtils.toString( array2 ) );  

        
        // 查找嵌套字符串
        String htmlContent = " <html>\n " + "   <head>\n "
                + "     <title>Test Page</title>\n " + "   </head>\n "
                + "   <body>\n " + "     <p>This is a TEST!</p>\n "
                + "   </body>\n " + " </html> ";

        // Extract the title from this XHTML content
        String title = StringUtils.substringBetween(htmlContent, "<title>", "</title>");
        System.out.println(" Title:  " + title);
        
        //验证字符串类型
        String test1  =   "ORANGE" ;
        String test2  =   "ICE9" ;
        String test3  =   "ICE CREAM" ;
        String test4  =   "820B Judson Avenue" ;

        //是否是希腊字母
        boolean t1val = StringUtils.isAlpha(test1); //  returns true
        //字符串中是否是字母和数字组合
        boolean t2val = StringUtils.isAlphanumeric(test2); //  returns true
        //字符串中是否有空格
        boolean t3val = StringUtils.isAlphaSpace(test3); //  returns true
        //字符串中是否既有数字又有空格
        boolean t4val = StringUtils.isAlphanumericSpace(test4); //  returns true 
        System.out.println("t1val: "+t1val);
        System.out.println("t2val: "+t2val);
        System.out.println("t3val: "+t3val);
        System.out.println("t4val: "+t4val);
        
        //计算字符串出现频率
        str="gdfhfapplehgfjhapplegkdfsfdgadgfasapplefdkjhxcvxnvappleadsdfgfsappledkgjisdkjgfoaskdxapplecfoiekdfappledkxt wf kdj poapplesdfasdkihj dgfapplelijsfd";
        System.out.println("次数: "+StringUtils.countMatches(str,  "apple"));
        
        //比较不同字符串
        //getLevenshteinDistance被定义为从一个字符串转换到另一个字符串所需要最少的插入,删除和替换的次数.
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
        System.out.println("Is str1 blank? " + StringUtils.isBlank(str1));//是否为空
        System.out.println("Is str2 blank? " + StringUtils.isBlank(str2));
        System.out.println("Is str3 blank? " + StringUtils.isBlank(str3));
        System.out.println("Is str4 blank? " + StringUtils.isBlank(str4));

        // check for numerics
        System.out.println("==============================");
        System.out.println("Is str5 numeric? " + StringUtils.isNumeric(str5));//是否有数字
        System.out.println("Is str6 numeric? " + StringUtils.isNumeric(str6));

        // reverse strings / whole words
        System.out.println("==============================");
        System.out.println("str6: " + str6);
        System.out.println("str6 reversed: " + StringUtils.reverse(str6));//以字母反转
        System.out.println("str7: " + str7);
        String str8 = StringUtils.chomp(str7);  //去掉最后面的换行符
        System.out.println("str8: " + str8);
        str8 = StringUtils.reverseDelimited(str8, ' ');//先以指定分割符拆分字符串，再以分割单元反转
        System.out.println("str7 reversed whole words : \r\n" + str8);

        // build header (useful to print log messages that are easy to locate)
        System.out.println("==============================");
        System.out.println("print header:");
        String padding = StringUtils.repeat("=", 50);  //重复打印指定字符串
        String msg = StringUtils.center(" Customised Header ", 50, "%");
        Object[] raw = new Object[]{padding, msg, padding};
        String header = StringUtils.join(raw, "\r\n");//将字符串数组连接起来,参数二为连接符
        System.out.println(header);
    }
    
    @Test
    public void testString3()
    {
        //部分截取字符串
        /*
         * 使用函数:
            StringUtils.substringBetween(testString,fromString,toString ):取得两字符
            之间的字符串
            StringUtils.substringAfter( ):取得指定字符串后的字符串
            StringUtils.substringBefore( )：取得指定字符串之前的字符串
            StringUtils.substringBeforeLast( )：取得最后一个指定字符串之前的字符串
            StringUtils.substringAfterLast( )：取得最后一个指定字符串之后的字符串
         */
        System.out.println("部分截取字符串");
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
