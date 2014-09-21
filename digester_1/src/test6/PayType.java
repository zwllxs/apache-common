package test6;

import java.util.ArrayList;
import java.util.List;

public class PayType
{
    private String name;
    
    private List<CardType> cardTypeList;

    public PayType()
    {
        cardTypeList=new ArrayList<CardType>();
    }
    
    public void addCardTypeList(CardType cardType)
    {
        cardTypeList.add(cardType);
    }
    
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<CardType> getCardTypeList()
    {
        return cardTypeList;
    }

    public void setCardTypeList(List<CardType> cardTypeList)
    {
        this.cardTypeList = cardTypeList;
    }
}
