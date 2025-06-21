package GoogleDrive;
import java.util.*;
public class GoogleDriveSystem {
    enum userAccess
    {
        ADMIN,VIEWER,EDITOR
    }
    enum Permission
    {
        READ,WRITE
    }
    abstract class Node {
    String name;
    Date createdDateTIme;
    Permission permission;
    }
    class File extends Node{

    }
    class Folder extends Node{
        
    }
}
