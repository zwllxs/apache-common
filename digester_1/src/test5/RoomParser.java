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
            // ����addCallMethod����,���xml���bean�������������ȫһ���������ʡ�²�д,�������id��name���Խ�㷽ʽ������ʡ,���Ҳ�����int����
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
