package game.stage.morning;

import java.awt.Point;
import java.util.LinkedList;

public class Snake {

    private final LinkedList<Segment> body = new LinkedList<>();
    private int dx = 1, dy = 0;
    private int pendingGrow = 0;

    public Snake(Point start) {
        body.add(new Segment(new Point(start), null)); // 머리
    }

    public LinkedList<Segment> body() {
        return body;
    }

    public Segment head() {
        return body.getFirst();
    }

    public void onKey(int keyCode) {
        if (keyCode == java.awt.event.KeyEvent.VK_UP && dy != 1) { dx = 0; dy = -1; }
        if (keyCode == java.awt.event.KeyEvent.VK_DOWN && dy != -1) { dx = 0; dy = 1; }
        if (keyCode == java.awt.event.KeyEvent.VK_LEFT && dx != 1) { dx = -1; dy = 0; }
        if (keyCode == java.awt.event.KeyEvent.VK_RIGHT && dx != -1) { dx = 1; dy = 0; }
    }

    public Point nextHeadPos() {
        Point h = head().pos;
        return new Point(h.x + dx, h.y + dy);
    }

    public boolean hitsBody(Point p) {
        for (int i = 1; i < body.size(); i++) {
            if (body.get(i).pos.equals(p)) return true;
        }
        return false;
    }

    public void move(ItemType pickedItem) {
        Point next = nextHeadPos();

        // 머리 이동
        body.addFirst(new Segment(next, pickedItem));

        // 성장 처리
        if (pendingGrow > 0) {
            pendingGrow--;
        } else {
            // 기본 꼬리 제거
            if (body.size() > 1) body.removeLast();
        }
    }

    public void grow(int n) {
        pendingGrow += n;
    }

    public void loseOne() {
        if (body.size() > 1) {
            body.removeLast();
        }
    }

    public void bump() {
        dx = 0;
        dy = 0;
    }
}
