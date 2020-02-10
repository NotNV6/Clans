package wtf.retarders.clans.game.map;

import lombok.Getter;
import lombok.Setter;
import wtf.retarders.clans.handler.IHandler;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MapHandler implements IHandler {

    private List<Map> maps;
    private Map currentMap;

    public MapHandler() {
        this.maps = new ArrayList<>();
    }

}
