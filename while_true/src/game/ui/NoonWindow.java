package game.ui;

import javax.swing.*;
import java.awt.*;

/**
 * 점심 스테이지 전용 GUI 창
 * - 1200x900
 * - 아래쪽에 상태 로그 / 대화 / 주인공 이미지 / 버튼 배치
 */
public class NoonWindow extends JFrame {

    private JTextArea statusArea;    // 왼쪽: 주인공 상태 로그
    private JTextArea dialogueArea;  // 가운데: N회차 + NPC 대사
    private JLabel playerLabel;      // 오른쪽: 주인공 이미지 자리(지금은 텍스트)

    private JButton btnChoice1;
    private JButton btnChoice2;
    private JButton btnChoice3;

    public NoonWindow() {
        setTitle("점심 스테이지 - while true");
        setSize(1200, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 전체 레이아웃
        setLayout(new BorderLayout());

        // ===== 중앙(배경) 패널 - 나중에 이미지/애니메이션 올릴 자리 =====
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(245, 245, 248));
        add(centerPanel, BorderLayout.CENTER);

        // ===== 하단 전체 루트 패널 =====
        JPanel bottomRoot = new JPanel(new BorderLayout());
        bottomRoot.setPreferredSize(new Dimension(1200, 260));
        bottomRoot.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(bottomRoot, BorderLayout.SOUTH);

        // ----- (1) 상태/대사/이미지 영역 -----
        JPanel infoPanel = new JPanel(new BorderLayout());
        bottomRoot.add(infoPanel, BorderLayout.CENTER);

        // 왼쪽: 상태 로그
        statusArea = new JTextArea();
        statusArea.setEditable(false);
        statusArea.setLineWrap(true);
        statusArea.setWrapStyleWord(true);
        statusArea.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        statusArea.setText("현재 상태가 여기 표시됩니다.");

        JScrollPane statusScroll = new JScrollPane(statusArea);
        statusScroll.setPreferredSize(new Dimension(250, 200));
        infoPanel.add(statusScroll, BorderLayout.WEST);

        // 가운데: NPC 대사
        dialogueArea = new JTextArea();
        dialogueArea.setEditable(false);
        dialogueArea.setLineWrap(true);
        dialogueArea.setWrapStyleWord(true);
        dialogueArea.setFont(new Font("맑은 고딕", Font.PLAIN, 18));

        JScrollPane dialogueScroll = new JScrollPane(dialogueArea);
        infoPanel.add(dialogueScroll, BorderLayout.CENTER);

        // 오른쪽: 주인공 이미지 자리
        playerLabel = new JLabel("주인공", SwingConstants.CENTER);
        playerLabel.setPreferredSize(new Dimension(160, 200));
        playerLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        playerLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 210)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        infoPanel.add(playerLabel, BorderLayout.EAST);

        // ----- (2) 버튼 영역 -----
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));

        btnChoice1 = new JButton("대화한다");
        btnChoice2 = new JButton("적당히 넘긴다");
        btnChoice3 = new JButton("무시한다");

        Dimension btnSize = new Dimension(200, 35);
        btnChoice1.setPreferredSize(btnSize);
        btnChoice2.setPreferredSize(btnSize);
        btnChoice3.setPreferredSize(btnSize);

        buttonPanel.add(btnChoice1);
        buttonPanel.add(btnChoice2);
        buttonPanel.add(btnChoice3);

        bottomRoot.add(buttonPanel, BorderLayout.SOUTH);
    }

    // ===== 외부에서 쓰는 메서드들 =====

    public JButton getBtn1() { return btnChoice1; }
    public JButton getBtn2() { return btnChoice2; }
    public JButton getBtn3() { return btnChoice3; }

    /** 가운데 대사 영역 텍스트 설정 + 항상 맨 위부터 보이게 */
    public void printDialogue(String text) {
        dialogueArea.setText(text);
        dialogueArea.setCaretPosition(0); // 항상 대사 첫 줄이 보이도록
    }

    /** 왼쪽 상태 로그 영역 텍스트 설정 */
    public void setStatusText(String text) {
        statusArea.setText(text);
        statusArea.setCaretPosition(0);
    }

    /** 나중에 이미지 넣을 때 교체 가능 – 일단 텍스트만 */
    public void setPlayerLabelText(String text) {
        playerLabel.setText(text);
    }
}
