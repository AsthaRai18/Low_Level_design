package snakeLadder;

import java.util.*;
public class snake {

// we can have a list and another jus coordiante
    private Set<Coordinates>snakeSet = new HashSet<>();
    public void addSnake(Coordinates c)
    {
        snakeSet.add(c);
    }
    public void isSnake(Coordinates c)
    {
        if(snakeSet.contains(c))
        {
            return map.get()
        }
    }
}
