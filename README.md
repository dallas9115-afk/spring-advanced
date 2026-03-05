#  Spring Advanced Todo Project

본 프로젝트는 기존의 Spring Boot 애플리케이션을 바탕으로 **성능 최적화, 객체지향적 리팩터링, 그리고 시스템 안정성 강화**를 목적으로 진행된 심화 프로젝트입니다. 

## Tech Stack

* **Framework**: Spring Boot 3.x
* **Language**: Java 17
* **Database**: MySQL
* **ORM**: Spring Data JPA
* **Security**: JWT (JSON Web Token)
* **Testing**: JUnit 5, Mockito

---

##  Key Improvements

### 1. 프로젝트 초기화 및 환경 복구 (Lv.0)

* 부재했던 `application.properties`를 생성하여 DB 연결 및 JWT 시크릿 키 등 필수 환경 변수를 설정하고 어플리케이션 구동 에러를 해결했습니다.

### 2. Spring MVC 커스텀 설정 (Lv.1)

* `AuthUserArgumentResolver`를 `WebMvcConfig`에 명시적으로 등록하여 컨트롤러에서 `@Auth` 어노테이션을 통한 유저 정보 주입이 정상 작동하도록 복구했습니다.

### 3. 코드 성능 및 가독성 개선 (Lv.2)

* **Early Return**: 불필요한 비용 발생을 막기 위해 예외 상황을 먼저 체크하도록 로직을 재구성했습니다.
* **If-Else 최적화**: 복잡한 중첩 조건문을 평면적으로 리팩터링하여 가독성을 높였습니다.
* **Bean Validation**: 서비스 로직의 유효성 검사 책임을 DTO로 위임하여 비즈니스 로직의 순수성을 지켰습니다.

### 4. JPA 성능 최적화 (Lv.3)

* N+1 문제를 해결하기 위해 기존의 복잡한 JPQL Fetch Join 대신 `@EntityGraph`를 도입하여 선언적이고 효율적인 즉시 로딩을 구현했습니다.

### 5. 테스트 코드 신뢰성 강화 (Lv.4)

* 라이브러리 규격에 맞지 않는 파라미터 순서와 잘못된 예외 타입 검증을 수정하고, 방어 로직에 대한 시나리오를 추가하여 테스트 커버리지를 확보했습니다.

### 6. AOP & Interceptor 기반 로깅 시스템 (Lv.5)

* `Interceptor`를 통한 어드민 권한 접근 제어와 `AOP(@Around)`를 활용한 상세 API 요청/응답 데이터(JSON 형식) 로깅 시스템을 구축했습니다.

### 7. 고도화된 문제 정의 및 해결 (Lv.6)

* **중복 매니저 방지**: 동일 유저의 중복 등록을 방지하여 데이터 무결성을 보장했습니다.
* **계층 간 의존성 분리**: 엔티티 내 DTO 의존성을 제거하여 아키텍처를 개선했습니다.
* **영속성 전이(Cascade)**: `Todo` 삭제 시 연관된 `Manager`, `Comment` 데이터가 함께 삭제되도록 설정하여 DB 정합성을 유지했습니다.
* **Global Response**: 모든 응답 규격을 `ApiResponse<T>`로 통일하여 클라이언트 친화적인 API를 설계했습니다.

---

##  How to Run

1. 레포지토리를 Clone 합니다.
2. `src/main/resources/application.properties`에 본인의 MySQL 연결 정보를 설정합니다.
3. 프로젝트를 빌드하고 실행합니다.
```bash
./gradlew bootRun

```
