# Not Enough Zombies

Utility mod for Hypixel Zombies

## Features

### Power up spawn/despawn messages

![Max Ammo spawned alert in chat](assets/max_ammo_spawned_alert.png)
![Double Gold spawned alert in chat](assets/double_gold_spawned_alert.png)

### Power up despawn timer

![Double gold power up with timer](assets/double_gold_powerup.png)

### Round start message (WIP)

![New round message](assets/new_round_msg.png)

The next round that the power up will spawn (-1 if the pattern can't be determined yet)

### Hide Messages

- Hide gold pickup messages
- Hide window repair messages
- Hide player knockdown Messages
- Hide player revive messages

---

## Commands

### Open GUI

    /nez

### Set power up pattern

    /nez sp [powerup] [round]

`powerup` - the name of the power up (can only be `ss`, `mx`, `ik`)

| Power up name  | Command name |
|----------------|--------------|
| Shopping Spree | ss           |
| Max Ammo       | mx           |
| Insta Kill     | ik           |

`round` - the round that the power up spawned (-1 to reset)
