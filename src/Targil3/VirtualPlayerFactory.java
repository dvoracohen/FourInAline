package Targil3;

import java.util.HashMap;

public class VirtualPlayerFactory {

    private static final HashMap<String, VirtualPlayer> virtualPlayerMap = new HashMap<>();

    public VirtualPlayer getVirtualPlayer(GameModes mode) {

        VirtualPlayer virtualPlayer;

        if (virtualPlayerMap.containsKey(mode))
        {
            return virtualPlayerMap.get(mode);
        }

        if(mode == GameModes.EASY)
        {
            virtualPlayer = new VirtualPlayerEasy();
        }
        else if(mode == GameModes.MEDIUM)
        {
            virtualPlayer = new VirtualPlayerMedium();
        }
        //else if(mode == GameModes.STRONG)
        //{
        //    virtualPlayer = new ComputerPlayerMedium(); // FIXME: need to implement strong computer player
        //}
        else
        {
            virtualPlayer = new VirtualPlayerMedium();
        }

        virtualPlayerMap.put(mode.toString(), virtualPlayer);

        return virtualPlayer;
    }
}