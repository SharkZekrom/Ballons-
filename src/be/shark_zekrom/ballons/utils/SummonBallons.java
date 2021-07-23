package be.shark_zekrom.ballons.utils;

import io.netty.util.collection.ByteCollections;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class SummonBallons {

    public static HashMap<Player, Parrot> balloons = new HashMap<>();
    public static HashMap<Player, ArmorStand> as = new HashMap<>();
    public static void summonBalloon(Player player, ItemStack item) {
        Parrot parrot  = (Parrot) player.getWorld().spawnEntity(player.getLocation().add(0,2,0), EntityType.PARROT);
        parrot.setInvisible(true);

        balloons.put(player, parrot);
        ((LivingEntity)parrot).setLeashHolder(player);

        Location loc = player.getLocation().add(0,2,0);
        ArmorStand as1 = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
        as1.setVisible(false);
        as1.setCustomNameVisible(false);
        as1.setGravity(false);
        as1.setCanPickupItems(false);
        as1.setArms(true);
        as1.setBasePlate(false);
        as1.getEquipment().setHelmet(item);
        as.put(player, as1);

    }

    public static void removeBalloon(Player player) {
        if (SummonBallons.as.get(player) != null) {
            ArmorStand as = SummonBallons.as.get(player);
            SummonBallons.as.remove(player);
            as.remove();

            Parrot parrot  = SummonBallons.balloons.get(player);
            SummonBallons.balloons.remove(player);
            parrot.remove();
        }
    }

    public static void removeAllBalloon() {
        for (Parrot parrot : SummonBallons.balloons.values()) {
            parrot.remove();
        }
        for (ArmorStand as : SummonBallons.as.values()) {
            as.remove();
        }

    }
}
