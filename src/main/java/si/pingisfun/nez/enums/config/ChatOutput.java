package si.pingisfun.nez.enums.config;

public enum ChatOutput {
    OFF("Off"),
    SELF("Self"),
    PARTY("Party"),
    CHAT("Chat");

    private final String chatTypeString;

    ChatOutput(String chatTypeString) {
        this.chatTypeString = chatTypeString;

    }

    public static ChatOutput getOutputUpByName(String name) {
        for (ChatOutput chatOutput : ChatOutput.values()) {
            if (chatOutput.chatTypeString.equalsIgnoreCase(name)) {
                return chatOutput;
            }
        }
        return ChatOutput.OFF;
    }

    public static ChatOutput getOutputUpByNumber(int num) {
        ChatOutput[] values = ChatOutput.values();
        if (values.length <= num || num < 0) {
            return ChatOutput.OFF;
        }
        return values[num];
    }


}
