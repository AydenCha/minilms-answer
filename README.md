# minilms-answer

"컬렉션 프레임워크와 Stream API" 11장 종합 실습의 모범 정답 프로젝트입니다. 외부 라이브러리 없이 JDK 21만으로 실행됩니다.

## 실행 방법

- IntelliJ에서 이 폴더를 열고 `com.codeit.collection.minilms.Application`의 `main` 메서드를 실행합니다.
- 터미널에서는 `./gradlew run`으로 실행합니다(Windows는 `gradlew.bat run`).

## 구성

- `domain`: Student, Course, Enrollment
- `repository`: 샘플 데이터를 List로 돌려주는 저장소
- `service`: Stream으로 조회하고 집계하는 서비스
- `Application.java`: 서비스 메서드를 호출해 결과를 출력하는 화면
