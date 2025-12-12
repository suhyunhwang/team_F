package morning;

public enum ItemType {
    APPLE("사과", "apple", new StatDelta(+5, 0, 0, 0)),
    DUMBBELL("아령", "dumbbell", new StatDelta(+10, 0, 0, 0)),
    MONSTER("몬스터", "monster", new StatDelta(+20, -5, 0, 0)),

    CIGARETTE("담배", "cigarette", new StatDelta(-5, +5, 0, 0)),
    MUSIC("음악", "music", new StatDelta(0, +10, 0, +5)),
    PET("애완동물", "pet", new StatDelta(0, +20, 0, 0)),

    PENCIL("연필", "pencil", new StatDelta(0, 0, +5, 0)),
    LAPTOP("노트북", "laptop", new StatDelta(0, +5, +10, 0)),
    MAJORBOOK("전공책", "majorbook", new StatDelta(-5, -5, +20, 0)),

    SPEECH("말풍선", "speech", new StatDelta(0, 0, +5, +5)),
    SHOWER("샤워", "shower", new StatDelta(+5, +5, 0, +10)),
    SNS("SNS", "sns", new StatDelta(-5, +5, -5, +20));

    public final String label;
    public final String assetKey;
    public final StatDelta delta;

    ItemType(String label, String assetKey, StatDelta delta) {
        this.label = label;
        this.assetKey = assetKey;
        this.delta = delta;
    }
}
