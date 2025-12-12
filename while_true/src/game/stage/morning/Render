package morning;

import java.awt.*;
import java.util.Map;

public class Render {

    public static void draw(Graphics2D g, GameState state, int tile, int w, int h) {
        int uiY = h * tile;

        // 배경 이미지
        drawBackground(g, state, tile, w, h);

        // 게임 오브젝트
        drawItems(g, state, tile);
        drawSnake(g, state, tile);
        drawExit(g, state, tile);

        // UI
        drawUI(g, state, 10, uiY + 25);

        if (state.isCleared()) {
            drawClear(g, w * tile, h * tile);
        }
    }

    /* ================== 배경 ================== */

    private static void drawBackground(Graphics2D g, GameState state, int tile, int w, int h) {
        if (state.room.backgroundKey() == null) return;

        Image bg = Assets.get(state.room.backgroundKey());
        if (bg == null) return;

        g.drawImage(
            bg,
            0, 0,
            w * tile,
            h * tile,
            null
        );
    }

    /* ================== 뱀 ================== */

    private static void drawSnake(Graphics2D g, GameState state, int tile) {
        for (int i = 0; i < state.snake.body().size(); i++) {
            Segment s = state.snake.body().get(i);
            if (i == 0) {
                g.setColor(new Color(80, 200, 120)); // 머리
            } else {
                g.setColor(new Color(120, 180, 120)); // 몸통
            }
            g.fillRect(s.pos.x * tile, s.pos.y * tile, tile, tile);
        }
    }

    /* ================== 아이템 ================== */

    private static void drawItems(Graphics2D g, GameState state, int tile) {
        for (Map.Entry<Point, ItemType> e : state.room.items().entrySet()) {
            Point p = e.getKey();
            g.setColor(Color.RED);
            g.fillOval(
                p.x * tile + 6,
                p.y * tile + 6,
                tile - 12,
                tile - 12
            );
        }
    }

    /* ================== 출구 ================== */

    private static void drawExit(Graphics2D g, GameState state, int tile) {
        if (state.exitPos == null) return;

        Point p = state.exitPos;
        g.setColor(new Color(255, 200, 0));
        g.fillRect(p.x * tile, p.y * tile, tile, tile);
    }

    /* ================== UI ================== */

    private static void drawUI(Graphics2D g, GameState state, int x, int y) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, y - 18, 9999, 120);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Dialog", Font.BOLD, 14));

        long sec = state.elapsedMs() / 1000;
        g.drawString("Time: " + sec + "s", x, y);

        g.drawString(
            "HP " + state.stats.hp +
            " | MENT " + state.stats.ment +
            " | INT " + state.stats.intel +
            " | SOC " + state.stats.social,
            x, y + 20
        );

        int lineY = y + 45;
        g.drawString("Inventory:", x, lineY);

        int ix = x + 90;
        for (Map.Entry<ItemType, Integer> e : state.inventory.counts().entrySet()) {
            g.drawString(e.getKey().label + " x" + e.getValue(), ix, lineY);
            lineY += 18;
        }
    }

    /* ================== 클리어 ================== */

    private static void drawClear(Graphics2D g, int w, int h) {
        g.setColor(new Color(0, 0, 0, 160));
        g.fillRect(0, 0, w, h);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Dialog", Font.BOLD, 42));
        g.drawString("CLEAR!", w / 2 - 80, h / 2);
    }
}
