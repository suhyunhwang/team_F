package morning;

import java.awt.Point;
import java.util.Random;

public class GameState {

    private final int w, h;
    private final Random rnd = new Random();

    public final Stats stats = new Stats();
    public final Inventory inventory = new Inventory(stats);
    public final Snake snake;
    public Room room;

    private long startMs = System.currentTimeMillis();
    private boolean exitSpawned = false;
    public Point exitPos = null;
    private boolean cleared = false;
    private boolean latePenaltyApplied = false;

    private long lastItemSpawnMs = 0;

    public GameState(int w, int h) {
        this.w = w;
        this.h = h;
        this.snake = new Snake(new Point(w / 2, h / 2));

        this.room = Room.personalRoomA(w, h);

        room.ensureItemCount(4, rnd, true);
    }

    public boolean isCleared() {
        return cleared;
    }

    public long elapsedMs() {
        return System.currentTimeMillis() - startMs;
    }

    public void onKey(int keyCode) {
        snake.onKey(keyCode);
    }

    public void tick() {
        if (cleared) return;

        long now = System.currentTimeMillis();
        long elapsed = now - startMs;

        if (!exitSpawned && elapsed >= 60_000) {
            exitSpawned = true;
            exitPos = room.getExitFixed();
        }

        if (elapsed >= 90_000 && !latePenaltyApplied) {
            latePenaltyApplied = true;
            inventory.removeHalfRandom(rnd);
        }

        Point next = snake.nextHeadPos();
        boolean hitWall = room.isWall(next);
        boolean hitSelf = snake.hitsBody(next);

        // === 충돌 처리 ===
        if (hitWall || hitSelf) {
            ItemType lost = inventory.removeRandomOne(rnd);
            if (lost != null) {
                snake.loseOne();
            }
            snake.bump();
            return;
        }

        // === 아이템 획득 ===
        ItemType picked = room.pickItemAt(next);
        if (picked != null) {
            inventory.add(picked);
            snake.grow(1);
        }

        // === 이동 ===
        snake.move(picked);

        // === 출구 ===
        if (exitSpawned && exitPos != null && snake.head().pos.equals(exitPos)) {
            cleared = true;
        }

        // === 아이템 유지 ===
        if (now - lastItemSpawnMs >= 5_000) {
            room.ensureItemCount(4, rnd, false);
            lastItemSpawnMs = now;
        }
    }
}
