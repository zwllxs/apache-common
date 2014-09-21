package test5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

public class RoomParser
{
    private List<Room> roomList = new ArrayList<Room>();

    public void addRoomList(Room room)
    {
        System.out.println("\n"+room.getId());
        System.out.println(room.getName());
        roomList.add(room);
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        RoomParser parser = new RoomParser();
        parser.init(new FileInputStream("src/test5/room.xml"));
    }

    public synchronized void init(InputStream isConfigFile)
    {

        if (isConfigFile != null)
        {
            // Read in rooms config and create beans, hand off to DAO.
            Digester digester = new Digester();
            digester.setValidating(false);
            digester.push(this);
            digester.addObjectCreate("rooms/room", "test5.Room");
            digester.addSetProperties("rooms/room");
            // 增加addCallMethod方法,如果xml里和bean里的属性名称完全一样，则可以省下不写,但是如果id和name是以结点方式，则不能省,并且不能用int类型
            digester.addCallMethod("rooms/room/id", "setId", 0);
            digester.addCallMethod("rooms/room/name", "setName", 0);
            digester.addSetNext("rooms/room", "addRoomList");
            try
            {
                digester.parse(isConfigFile);
            }
            catch (IOException ioe)
            {
                ioe.printStackTrace();
            }
            catch (SAXException se)
            {
                se.printStackTrace();
            }
        }

    } // End init().
}
