package test4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

public class PersonTest
{
    @Test
    public void testBeanComparator()
    {
        List<Person> personList = new ArrayList<Person>();
        for (int i = 20; i >= 1; i--)
        {
            Person person = new Person(11 + i, "��ΰ��" + i, 24 + i + i % 2);
            person.setMoney(new Random().nextInt());
            personList.add(person);
        }

        System.out.println(StringUtils.center("��ʼ��ʱ", 20, "="));
        System.out.println("personList: " + personList);

        Collections.sort(personList, new BeanComparator("age"));
        System.out.println(StringUtils.center("�����", 20, "="));
        System.out.println("personList: " + personList);

        // ��������
        List sortFields = new ArrayList();
        sortFields.add(new BeanComparator("age"));
        sortFields.add(new BeanComparator("money"));
        ComparatorChain multiSort = new ComparatorChain(sortFields);
        Collections.sort(personList, multiSort);

    }
}
