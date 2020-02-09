package wtf.retarders.clans.util.item.loadout.impl;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import wtf.retarders.clans.ClansConstants;
import wtf.retarders.clans.ClansPlugin;
import wtf.retarders.clans.clan.ClanHandler;
import wtf.retarders.clans.clan.types.PlayerClan;
import wtf.retarders.clans.profile.ProfileHandler;
import wtf.retarders.clans.util.item.ItemBuilder;
import wtf.retarders.clans.util.item.loadout.ItemLoadout;

import java.util.function.Consumer;

public class LobbyLoadout implements ItemLoadout {

    @Override
    public ItemStack[] getItems() {
        ClanHandler clanHandler = ClansPlugin.getPlugin().getHandlerManager().findHandler(ClanHandler.class);
        PlayerClan teamRed = (PlayerClan) clanHandler.findClan("Red");
        PlayerClan teamBlue = (PlayerClan) clanHandler.findClan("Blue");
        PlayerClan teamGreen = (PlayerClan) clanHandler.findClan("Green");
        PlayerClan teamYellow = (PlayerClan) clanHandler.findClan("Yellow");

        String teamName = "&9&l» {0} &9&l«";

        return new ItemStack[]{
                null,
                null,
                new ItemBuilder(Material.INK_SACK).setDurability((short) 14).setAction(this.getClickAction(teamRed)).setName(String.format(teamName, "&c&lTeam Red")).toItemStack(),
                new ItemBuilder(Material.INK_SACK).setDurability((short) 9).setAction(this.getClickAction(teamBlue)).setName(String.format(teamName, "&9&lTeam Blue")).toItemStack(),
                null,
                new ItemBuilder(Material.INK_SACK).setDurability((short) 13).setAction(this.getClickAction(teamGreen)).setName(String.format(teamName, "&a&lTeam Green")).toItemStack(),
                new ItemBuilder(Material.INK_SACK).setDurability((short) 4).setAction(this.getClickAction(teamYellow)).setName(String.format(teamName, "&e&lTeam Yellow")).toItemStack(),
                null,
                null
        };
    }

    private Consumer<Player> getClickAction(PlayerClan clan) {
        return player -> {
            if (clan.getMembers().size() >= ClansConstants.MAX_PLAYERS_PER_CLAN) {
                player.sendMessage(ClansConstants.TEAM_IS_FULL);
                return;
            }

            clan.addPlayer(ClansPlugin.getPlugin().getHandlerManager().findHandler(ProfileHandler.class).findProfile(player.getUniqueId()));
        };
    }
}
