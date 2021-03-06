package test4;

import java.io.File;
import java.io.IOException;

import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

public class AddressBookParser
{
    /**
     * Prints the contact information to standard output.
     * 
     * @param contact
     *            the <code>Contact</code> to print out
     */
    public void addContact(Contact contact)
    {
        System.out.println("TYPE: " + contact.getType());
        System.out.println("NAME: " + contact.getName());
        System.out.println("    ADDRESS:    " + contact.getAddress());
        System.out.println("    CITY:       " + contact.getCity());
        System.out.println("    PROVINCE:   " + contact.getProvince());
        System.out.println("    POSTALCODE: " + contact.getPostalcode());
        System.out.println("    COUNTRY:    " + contact.getCountry());
        System.out.println("    COUNTRY-From:    " + contact.getCountryFrom());
        System.out.println("    TELEPHONE:  " + contact.getTelephone());
    }

    /**
     * Configures Digester rules and actions, parses the XML file specified as
     * the first argument.
     * 
     * @param args
     *            command line arguments
     */
    public static void main(String[] args) throws IOException, SAXException
    {
        // instantiate Digester and disable XML validation
        Digester digester = new Digester();
        digester.setValidating(false);

        // instantiate AddressBookParser class
        digester.addObjectCreate("address-book", AddressBookParser.class);
        // instantiate Contact class
        digester.addObjectCreate("address-book/contact", Contact.class);

        // set type property of Contact instance when 'type' attribute is found
        // 对有属性的值通过setProperties方法
        
        //注意参数2表示xml文件中的属性，与参数3bean类里的属性对应
        digester.addSetProperties("address-book/contact", "myType", "type");

        // set different properties of Contact instance using specified methods
        // addCallMethod与addBeanPropertySetter等价
        // 参数 0代表一个参数，默认就是当前读的数据
        digester.addCallMethod("address-book/contact/name", "setName", 0);
        digester.addCallMethod("address-book/contact/address", "setAddress", 0);
        digester.addCallMethod("address-book/contact/address", "setAddress", 0);
        digester.addCallMethod("address-book/contact/city", "setCity", 0);
        digester.addCallMethod("address-book/contact/province", "setProvince",0);
        digester.addCallMethod("address-book/contact/postalcode","setPostalcode", 0);
//        digester.addCallMethod("address-book/contact/country", "setCountry", 0);
        digester.addBeanPropertySetter("address-book/contact/country");  //与上面一句等效
        
        // 增加country的属性 : from,即如果xml中此处有from,则与bean里的countryFrom对应和addCallMethod
        //xml文件中的属性(atribbute),是通过addSetProperties获取，不是addBeanPropertySetter
        digester.addSetProperties("address-book/contact/country", "from", "countryFrom");
        digester.addCallMethod("address-book/contact/telephone","setTelephone", 0);

        // call 'addContact' method when the next 'address-book/contact' pattern
        // is seen
        digester.addSetNext("address-book/contact", "addContact");

        // now that rules and actions are configured, start the parsing process
        AddressBookParser abp = (AddressBookParser) digester.parse(new File(
                "src/test4/addressbook.xml"));
    }
}
