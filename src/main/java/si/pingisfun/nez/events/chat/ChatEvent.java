package si.pingisfun.nez.events.chat;

import net.minecraftforge.client.event.ClientChatReceivedEvent;

interface ChatEvent {
    ClientChatReceivedEvent getEvent();
}
