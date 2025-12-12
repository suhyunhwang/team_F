package game.stage.morning;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Room {

    /* ================== 기본 필드 ================== */

    private final int w, h;
    protected final TileType[][] tiles;
    protected final Map<Point, ItemType> items = new HashMap<>();

    protected final String backgroundKey; // 배경 이미지 키
    protected final Map<Point, String> doors = new HashMap<>();

    private final Point exitFixed;

    /* ================== 생성자 ================== */

    protected Room(int w, int h, String backgroundKey, Point exitFixed) {
        this.w = w;
        this.h = h;
        this.backgroundKey = backgroundKey;
        this.exitFixed = exitFixed;

        tiles = new TileType[w][h];

        // 기본은 전부 바닥
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                tiles[x][y] = TileType.FLOOR;
            }
        }
    }

    /* ================== A안: 개인 방 ================== */

    public static Room personalRoomA(int w, int h) {
        Room r = new Room(w, h, "personal_room", new Point(w / 2, h - 1));

        // === 외벽 ===
        for (int x = 0; x < w; x++) {
            r.tiles[x][0] = TileType.WALL;
            r.tiles[x][h - 1] = TileType.WALL;
        }
        for (int y = 0; y < h; y++) {
            r.tiles[0][y] = TileType.WALL;
            r.tiles[w - 1][y] = TileType.WALL;
        }

        // === 문 (아래 중앙) ===
        Point door = new Point(w / 2, h - 1);
        r.tiles[door.x][door.y] = TileType.DOOR;
        r.doors.put(door, "living_room");

        return r;
    }

    /* ================== 타일 판정 ================== */

    public boolean isWall(Point p) {
        if (outOfBounds(p)) return true;
        return tiles[p.x][p.y] == TileType.WALL;
    }

    public boolean isDoor(Point p) {
        if (outOfBounds(p)) return false;
        return tiles[p.x][p.y] == TileType.DOOR;
    }

    public String doorTarget(Point p) {
        return doors.get(p);
    }

    public TileType tileAt(Point p) {
        if (outOfBounds(p)) return TileType.WALL;
        return tiles[p.x][p.y];
    }

    private boolean outOfBounds(Point p) {
        return p.x < 0 || p.y < 0 || p.x >= w || p.y >= h;
    }

    /* ================== 아이템 ================== */

    public Map<Point, ItemType> items() {
        return items;
    }

    public ItemType pickItemAt(Point p) {
        return items.remove(p);
    }

    public void ensureItemCount(int target, Random rnd, boolean forceRespawn) {
        if (forceRespawn) items.clear();

        while (items.size() < target) {
            Point p = new Point(rnd.nextInt(w), rnd.nextInt(h));

            // 벽/문 제외
            if (tileAt(p) != TileType.FLOOR) continue;

            // 중앙 위주 스폰 (회전 공간 확보)
            if (p.x <= 2 || p.x >= w - 3) continue;
            if (p.y <= 2 || p.y >= h - 3) continue;

            if (items.containsKey(p)) continue;

            items.put(p, randomItem(rnd));
        }
    }

    private ItemType randomItem(Random rnd) {
        ItemType[] v = ItemType.values();
        return v[rnd.nextInt(v.length)];
    }

    /* ================== 기타 ================== */

    public String backgroundKey() {
        return backgroundKey;
    }

    public Point getExitFixed() {
        return exitFixed;
    }
}
