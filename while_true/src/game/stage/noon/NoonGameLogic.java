package game.stage.noon;

import java.util.Random;

/**
 * NoonGameLogic
 * - 점심 스테이지(미연시) 진행을 담당하는 순수 로직 클래스.
 * - 콘솔 입출력(X), Scanner(X)
 * - 대사/상태를 문자열로 만들어서 GUI에 넘겨주는 역할만 함.
 */
public class NoonGameLogic {

    // ===== 상태값(플레이어 스탯) =====
    private int hp = 5;          // 체력
    private int mental = 5;      // 멘탈
    private int knowledge = 5;   // 지식
    private int social = 0;      // 사교

    private int interactionCount = 0;
    private static final int MAX_INTERACTIONS = 12;

    private final Random random = new Random();
    private boolean gameOver = false;

    /** 게임 시작 시 처음 한 번 호출: 첫 NPC 대사 리턴 */
    public String start() {
        interactionCount = 0;
        gameOver = false;
        return "=== 점심 스테이지: 캠퍼스 미연시 시작 ===\n"
             + "주인공: 컴공과 2학년, 반복되는 하루 속에서 루프를 깨고 싶어 한다.\n\n"
             + buildNpcDialogue(1);
    }

    /** 버튼 클릭 시 호출: 선택(1/2/3)을 반영하고 다음 대사를 리턴 */
    public String handleChoice(int choice) {
        if (gameOver) {
            return "[시스템] 이미 이 루프는 끝났습니다.";
        }

        if (interactionCount >= MAX_INTERACTIONS) {
            return "=== 점심 스테이지 종료 ===\n"
                 + "…였던 것 같은데.\n"
                 + "왜 이렇게 익숙하지?";
        }

        int npcIndex = interactionCount + 1;

        // 선택 반영
        StringBuilder sb = new StringBuilder();
        sb.append("---------- [대화 ").append(npcIndex).append("회차 결과] ----------\n");

        // NPC + 선택에 따라 상태 변화 적용
        String npcName = getNpcName(npcIndex);
        applyNpcEffect(npcIndex, choice, sb);

        // 상태 출력
        sb.append("\n현재 상태 → 체력: ").append(hp)
          .append(" / 멘탈: ").append(mental)
          .append(" / 지식: ").append(knowledge)
          .append(" / 사교: ").append(social)
          .append("\n");

        // Game Over 체크
        if (hp <= 0 || mental <= 0 || knowledge <= 0) {
            gameOver = true;
            if (hp <= 0) {
                sb.append("\nGame Over - 체력 0 (기절 엔딩)\n");
            } else if (mental <= 0) {
                sb.append("\nGame Over - 멘탈 0 (침잠 엔딩)\n");
            } else {
                sb.append("\nGame Over - 지식 0 (학사경고 엔딩)\n");
            }
            return sb.toString();
        }

        interactionCount++;

        // 다음 NPC가 남아 있으면 그 대사도 이어서 붙여줌
        if (interactionCount < MAX_INTERACTIONS) {
            int nextNpc = interactionCount + 1;
            sb.append("\n---------- [대화 ")
              .append(nextNpc).append("회차] ----------\n");
            sb.append(buildNpcDialogue(nextNpc));
        } else {
            sb.append("\n=== 점심 스테이지 종료 ===\n")
              .append("…였던 것 같은데.\n")
              .append("왜 이렇게 익숙하지?");
        }

        return sb.toString();
    }

    /** 현재 NPC의 이름(로그용) */
    private String getNpcName(int npc) {
        return switch (npc) {
            case 1 -> "교수님";
            case 2 -> "버스기사";
            case 3 -> "학교 친구";
            case 4 -> "선배";
            case 5 -> "후배";
            case 6 -> "동아리 사람";
            case 7 -> "헬창";
            case 8 -> "식당 주인";
            case 9 -> "대학원생";
            case 10 -> "스님";
            case 11 -> "과대표";
            case 12 -> "조교";
            default -> "???";
        };
    }

    /** NPC 대사 + 루프 암시 + 선택지 문자열까지 만들어서 리턴 */
    private String buildNpcDialogue(int npc) {
        StringBuilder sb = new StringBuilder();

        switch (npc) {
            case 1 -> {
                sb.append("교수님: \"과제 어디까지 진행됐습니까?\"\n");
                maybeAppendHint(sb, "아까도… 같은 표정이었지?");
            }
            case 2 -> {
                sb.append("버스기사: \"오늘 학교 가?\"\n");
                maybeAppendHint(sb, "이 버스… 몇 번째 타는 거지?");
            }
            case 3 -> {
                sb.append("학교 친구: \"코딩 어디까지 했냐?\"\n");
                maybeAppendHint(sb, "너 계속… 이 부분에서 멈추는 것 같아.");
            }
            case 4 -> {
                sb.append("선배: \"이 방향 맞는 거야?\"\n");
                maybeAppendHint(sb, "방금도… 여기서 서성이지 않았냐?");
            }
            case 5 -> {
                sb.append("후배: \"선배님 이 코드 좀…\"\n");
                maybeAppendHint(sb, "오늘도, 같은 자리에서 기다리고 있었어요.");
            }
            case 6 -> {
                sb.append("동아리 사람: \"오늘 연습 가능?\"\n");
                maybeAppendHint(sb, "이 말, 오늘만 몇 번째지…?");
            }
            case 7 -> {
                sb.append("헬창: \"하체 언제 할 거야?\"\n");
                maybeAppendHint(sb, "루틴만 반복하면, 시간 감각이 사라지더라.");
            }
            case 8 -> {
                sb.append("식당 주인: \"밥 먹고 가!\"\n");
                maybeAppendHint(sb, "오늘 메뉴도… 똑같이 시킬 거지?");
            }
            case 9 -> {
                sb.append("대학원생: \"코딩은 사고의 문제입니다.\"\n");
                maybeAppendHint(sb, "방금 대답, 전에도 들은 것 같은데.");
            }
            case 10 -> {
                sb.append("스님: \"하루를 되풀이하는 중생이여…\"\n");
                sb.append("스님: \"지금이 몇 번째 시도인지, 너는 모른다.\"\n");
                sb.append("스님: \"하지만 나는… 전부 보고 있었다.\"\n");
            }
            case 11 -> {
                sb.append("과대표: \"공지 좀 읽어줘.\"\n");
                maybeAppendHint(sb, "왜 자꾸 같은 말만 하는 거지…?");
            }
            case 12 -> {
                sb.append("조교: \"보고서 형식 다시 보세요.\"\n");
                maybeAppendHint(sb, "이 피드백, 계속 반복되는 것 같지 않아요?");
            }
            default -> sb.append("??? : \"...\"\n");
        }

        sb.append("\n1) 대화에 응한다   2) 적당히 넘긴다   3) 무시한다\n");
        return sb.toString();
    }

    /** 30% 확률로 괄호 안에 루프 암시 멘트 추가 */
    private void maybeAppendHint(StringBuilder sb, String hint) {
        if (random.nextDouble() < 0.3) {
            sb.append("   (").append(hint).append(")\n");
        }
    }

    /** NPC + 선택(1/2/3)에 따른 상태 변화 적용, 변화 로그를 sb에 기록 */
    private void applyNpcEffect(int npc, int choice, StringBuilder sb) {
        int dhp = 0;
        int dMental = 0;
        int dKnow = 0;
        int dSocial = 0;

        switch (npc) {
            case 1 -> { // 교수님
                if (choice == 1)       { dKnow += 2; dMental -= 1; }
                else if (choice == 2) { dMental -= 1; dSocial -= 1; }
                else                  { dKnow -= 1; dMental -= 2; dSocial -= 2; }
            }
            case 2 -> { // 버스기사
                if (choice == 1)       { dSocial += 1; }
                else if (choice == 3) { dSocial -= 1; dMental -= 1; }
            }
            case 3 -> { // 학교 친구
                if (choice == 1)       { dSocial += 1; dMental -= 1; }
                else if (choice == 2) { dMental += 1; dSocial -= 1; }
                else                  { dMental -= 1; dSocial -= 2; }
            }
            case 4 -> { // 선배
                if (choice == 1)       { dKnow += 1; dMental -= 1; }
                else if (choice == 2) { dMental += 1; dSocial -= 1; }
                else                  { dMental -= 2; dSocial -= 2; }
            }
            case 5 -> { // 후배
                if (choice == 1)       { dSocial += 1; dMental -= 1; }
                else if (choice == 3) { dSocial -= 2; dMental -= 1; }
            }
            case 6 -> { // 동아리 사람 (무시 포지티브)
                if (choice == 1)       { dSocial += 1; dhp -= 1; }
                else if (choice == 2) { dMental += 1; dSocial -= 1; }
                else                  { dMental += 1; dSocial -= 1; }
            }
            case 7 -> { // 헬창 (무시 포지티브)
                if (choice == 1)       { dhp += 1; }
                else if (choice == 2) { dSocial -= 1; }
                else                  { dMental += 1; dSocial -= 1; }
            }
            case 8 -> { // 식당 주인
                if (choice == 1)       { dhp += 2; dSocial += 1; }
                else if (choice == 3) { dhp -= 1; dSocial -= 1; dMental -= 1; }
            }
            case 9 -> { // 대학원생
                if (choice == 1)       { dKnow += 2; dMental -= 2; }
                else if (choice == 2) { dMental += 1; }
                else                  { dKnow -= 1; dMental -= 1; dSocial -= 2; }
            }
            case 10 -> { // 스님
                if (choice == 1)       { dMental += 2; dKnow += 1; }
                else                  { dMental += 1; } // 거절/무시 모두 멘탈 +1
            }
            case 11 -> { // 과대표
                if (choice == 1)       { dKnow += 1; dSocial += 1; dMental -= 1; }
                else if (choice == 2) { dMental += 1; dSocial -= 1; }
                else                  { dMental += 1; dSocial -= 2; }
            }
            case 12 -> { // 조교
                if (choice == 1)       { dKnow += 1; dMental -= 1; }
                else if (choice == 2) { dSocial -= 1; }
                else                  { dKnow -= 1; dMental -= 1; dSocial -= 2; }
            }
        }

        hp        += dhp;
        mental    += dMental;
        knowledge += dKnow;
        social    += dSocial;

        sb.append("[변화 로그] 체력 ").append(sign(dhp))
          .append(" / 멘탈 ").append(sign(dMental))
          .append(" / 지식 ").append(sign(dKnow))
          .append(" / 사교 ").append(sign(dSocial))
          .append("\n");
    }

    private String sign(int v) {
        if (v > 0) return "+" + v;
        if (v < 0) return String.valueOf(v);
        return "0";
    }
}
