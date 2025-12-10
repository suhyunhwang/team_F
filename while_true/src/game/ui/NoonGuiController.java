package game.ui;

import game.stage.noon.NoonGameLogic;

public class NoonGuiController {

    private final NoonWindow window;
    private final NoonGameLogic logic;

    public NoonGuiController() {
        window = new NoonWindow();
        logic  = new NoonGameLogic();

        bindEvents();

        // 게임 시작 시 첫 대사 출력
        String firstText = logic.start();
        window.printDialogue(firstText);
        
        // 시작 시 기본 상태 표시
        window.setStatusText("현재 상태 → 체력 5 / 멘탈 5 / 지식 5 / 사교 0");

        window.setVisible(true);
    }

    private void bindEvents() {
        window.getBtn1().addActionListener(e -> onUserChoice(1));
        window.getBtn2().addActionListener(e -> onUserChoice(2));
        window.getBtn3().addActionListener(e -> onUserChoice(3));
    }

    private void onUserChoice(int choice) {
        String fullText = logic.handleChoice(choice);

        // 1) 가운데 NPC 대사 + 로그 전체는 그대로 출력
        window.printDialogue(fullText);

        // 2) 그 중에서 상태 관련 부분만 잘라서 왼쪽에 표시
        //    "[변화 로그]" 라인부터 "현재 상태 →"까지를 상태 로그로 사용
        String statusPart = fullText;
        int idx = fullText.indexOf("[변화 로그]");
        if (idx >= 0) {
            statusPart = fullText.substring(idx);
        }
        // 혹시 그 뒤에 다음 대화가 이어지면, "---------- [대화" 기준으로 잘라냄
        int nextIdx = statusPart.indexOf("---------- [대화");
        if (nextIdx > 0) {
            statusPart = statusPart.substring(0, nextIdx);
        }

        window.setStatusText(statusPart);
    }

}
