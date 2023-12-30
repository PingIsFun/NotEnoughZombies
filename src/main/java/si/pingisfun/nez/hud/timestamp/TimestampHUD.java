package si.pingisfun.nez.hud.timestamp;

import cc.polyfrost.oneconfig.hud.SingleTextHud;
import cc.polyfrost.oneconfig.libs.universal.UMatrixStack;
import si.pingisfun.nez.NotEnoughZombies;
import si.pingisfun.nez.utils.JavaUtils;

import java.util.List;

public abstract class TimestampHUD extends SingleTextHud {
    protected TimestampHUD(String title, boolean enabled) {
        super(title, enabled);
    }

    protected abstract long getTimestampMs();

    @Override
    protected void getLines(List<String> lines, boolean example) {
        lines.add(getCompleteText(getText(example)));
    }

    @Override
    public void drawAll(UMatrixStack matrices, boolean example) {
        if (!NotEnoughZombies.game.isInGame() && !example) {
            return;
        }
        super.drawAll(matrices, example);
    }


    @Override
    protected String getText(boolean example) {
        if (example) {
            return "1:32:45.6";
        }

        StringBuilder stringBuilder = new StringBuilder();
        List<Long> gameTime = JavaUtils.millisToMsSMinH(System.currentTimeMillis() - getTimestampMs());
        if (gameTime.get(3) != 0) {
            stringBuilder.append(gameTime.get(3)).append(":");
        }
        if (gameTime.get(3) + gameTime.get(2) != 0) {
            stringBuilder.append(gameTime.get(2)).append(":");
        }
        if (gameTime.get(3) + gameTime.get(2) + gameTime.get(1) != 0) {
            stringBuilder.append(gameTime.get(1)).append(".");
        }
        stringBuilder.append(gameTime.get(0) / 100);
        return stringBuilder.toString();
    }
}
