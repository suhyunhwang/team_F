while_true (점심 스테이지 프로토타입)

팀원: 박민서 (243432)

📌 프로젝트 개요

while_true : break는
“루프에 갇힌 대학생”이라는 세계관을 기반으로 한
2D 미연시 / 인터랙티브 스토리 기반 게임입니다.

현재 점심 스테이지를 중심으로
NPC와의 선택형 상호작용 → 스탯 변화 → 루프/고립 시스템
을 담은 프로토타입(콘솔 + GUI 일부) 이 완성된 상태입니다.

이번 README는 현재까지 구축된 구조와 진행 상황,
다음 단계에서 필요한 작업을 팀원들에게 공유하기 위한 정리본입니다.

📁 현재 프로젝트 구조
src/
 └── game/
      ├── core/
      │     ├── Game.java
      │     ├── Scene.java
      │     └── SceneManager.java
      │
      ├── stage/
      │     ├── morning/
      │     ├── noon/
      │     │      └── NoonScene.java
      │     ├── evening/
      │     └── night/
      │
      └── ui/
            ├── NoonWindow.java
            ├── NoonGuiController.java
            └── NoonGuiTest.java

⭐ 핵심 구성 요약

core

게임 전체 흐름 관리

Scene 인터페이스

SceneManager 를 통한 장면 전환 구조 수립됨

stage/noon

점심 스테이지 전체 로직 완성

NPC 12명 등장 + 선택지 3개

스탯 변화, 루프 암시 대사, 조건부 엔딩

💀 사교 ≤ -5 → Black Zone(고립 루프) 시스템 완성

ui

1200x900 GUI 틀 제작

텍스트 영역 / 선택지 버튼 / 주인공 이미지 placeholder 구현됨

현재 콘솔 로직과 부분 연결 성공

전체 스테이지 GUI 전환은 추후 확장 예정

🎮 점심 스테이지( NoonScene ) 진행 상태
✔ 구현된 기능

NPC 12명 순차 등장

각 NPC 별 유니크 대사

30% 확률의 루프 암시 텍스트 (…)

1 / 2 / 3 선택지

1 호응

2 거절

3 무시

각 선택지에 따라 4가지 스탯 변화

체력 hp

멘탈 mental

지식 knowledge

사교 social

Game Over 조건 3종

체력 ≤ 0

멘탈 ≤ 0

지식 ≤ 0

Black Zone(고립 루프)

사교 ≤ -5 진입 시 발동

한 번만 연출 출력

이후 모든 NPC → ??? : "..."

선택지 반영 → 멘탈 -1만 적용

✔ GUI 버전 진행

NoonWindow(1200x900) 생성

상태창 / 대화창 / 주인공 이미지 영역 구성

버튼 클릭 → 선택지 연결 작업 일부 완성

Console 기반 로직을 GUI로 확장 중

🧪 현재 완성도
파트	구현 여부	비고
점심 스테이지 대사/선택지	✔ 완료	NPC 12명 전체
스탯 시스템	✔ 완료	밸런싱 전체 적용
루프 암시 시스템	✔ 완료	30% 출력
Black Zone 시스템	✔ 완료	사교 ≤ -5
Game Over 시스템	✔ 완료	3종 엔딩
GUI 틀 제작	✔ 일부 완료	Noon 전용 화면 구성 완료
GUI + 로직 연결	◯ 일부 연결	전체 흐름 연결 예정
다른 스테이지(아침/저녁/밤)	✖ 미구현	구조는 이미 준비됨
📌 다음 단계(팀 회의용 To-Do)
🎨 GUI

NoonScene → GUI 완전 연결

NPC 대사 / 변경 로그 / 이미지 표시 완성

애니메이션(페이드 인/아웃) 추가 가능

실제 스프라이트 또는 픽셀 캐릭터 배치

🛠 전체 게임 흐름

Morning / Evening / Night 스테이지 구현

SceneManager 로 실제 장면 전환

엔딩 분기 관리

🎵 연출 / 효과

효과음, 배경음악

루프 진입 연출(글리치 효과 등)

Black Zone 시 음향 변화

🧑‍💻 담당자 정보

박민서 (243432)

NoonScene 전체 로직 구현

Black Zone 시스템 설계 및 완성

GUI 기본 틀 제작

콘솔 → GUI 연결 기능 일부 작업

전체 프로젝트 구조 세팅

SceneManager / Game 흐름 구성