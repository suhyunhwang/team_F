package game.stage.morning;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Assets {

    private static final Map<String, BufferedImage> images = new HashMap<>();

    public static void load() {
        loadImage("personal_room", "/morning/personal_room.jpg");
    }

    private static void loadImage(String key, String path) {
        try {
            images.put(
                key,
                ImageIO.read(Assets.class.getResourceAsStream(path))
            );
        } catch (Exception e) {
            throw new RuntimeException("이미지 로드 실패: " + path, e);
        }
    }

    public static BufferedImage get(String key) {
        return images.get(key);
    }
}
