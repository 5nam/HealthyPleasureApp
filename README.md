# HealthyPleasureApp
건강하게 기록하는 어플

![01](https://github.com/5nam/HealthyPleasureApp/assets/86754153/dc8deb36-2101-4093-95d1-cc3f4d03321e)
![02](https://github.com/5nam/HealthyPleasureApp/assets/86754153/1a671892-147b-437f-8f32-81b76f6d2e7d)
![03](https://github.com/5nam/HealthyPleasureApp/assets/86754153/a3b101a6-2d9a-4fd9-bfc7-3631ad318671)
![04](https://github.com/5nam/HealthyPleasureApp/assets/86754153/81075e9b-d579-412b-8fe0-e4d31643b8a1)
![05](https://github.com/5nam/HealthyPleasureApp/assets/86754153/528a6758-6077-4847-9a6e-457c7822e8de)
![06](https://github.com/5nam/HealthyPleasureApp/assets/86754153/dc109224-1c99-40b4-b4d6-c2b6e52a035e)
![07](https://github.com/5nam/HealthyPleasureApp/assets/86754153/88cf4308-dfa1-4a55-acee-6d0ecd63cf3f)
![08](https://github.com/5nam/HealthyPleasureApp/assets/86754153/137cec71-7d71-4926-adb6-8fdef38babaa)
![09](https://github.com/5nam/HealthyPleasureApp/assets/86754153/64eb0cc2-4079-4a94-acaa-9438c6d47642)
![10](https://github.com/5nam/HealthyPleasureApp/assets/86754153/296b778c-0adf-44bf-ad65-7839de0ed922)
![11](https://github.com/5nam/HealthyPleasureApp/assets/86754153/a66824e7-e1e9-44da-8130-329e0a0a1e55)
![12](https://github.com/5nam/HealthyPleasureApp/assets/86754153/c88d487f-83a8-4312-a8e1-8b9bbc97dc20)
![13](https://github.com/5nam/HealthyPleasureApp/assets/86754153/04452433-778e-495e-a87f-65657744149b)
![14](https://github.com/5nam/HealthyPleasureApp/assets/86754153/8a24d7f2-6731-413d-97f3-f06b751b93f0)
![15](https://github.com/5nam/HealthyPleasureApp/assets/86754153/56403852-004a-4123-94ad-adc21a3307be)
![16](https://github.com/5nam/HealthyPleasureApp/assets/86754153/acd1216d-33bd-47df-b161-3ca12c2fa207)
![17](https://github.com/5nam/HealthyPleasureApp/assets/86754153/fe5e2bb7-fc68-48ff-b557-31fed13ce9a8)
![18](https://github.com/5nam/HealthyPleasureApp/assets/86754153/bbb99b50-6fa7-4aa9-9592-8c623bb73d96)
![19](https://github.com/5nam/HealthyPleasureApp/assets/86754153/7bd5849e-872c-4afc-8806-05056b69e4cf)
![20](https://github.com/5nam/HealthyPleasureApp/assets/86754153/69b30404-9851-49b6-ad60-6440c6e6e12c)
![21](https://github.com/5nam/HealthyPleasureApp/assets/86754153/022c0c1a-09b3-4472-a94b-ba6e2bbf8156)

## 주요 기능

### 1. 회원가입

- 이메일, 비밀번호, 비밀번호 확인, 이름, 전화번호, 생년월일을 모두 입력하면 회원가입 정보 DB 에 등록
- 이미 등록된 계정은 DB 에서 이메일과 이름, 전화번호로 체크하여 회원가입 불가

- 회원목록 DB
username(PK), password, realname, phonenumber, birthday

### 2. 로그인

- ID, PW 칸이 비었는지 체크
- ID, PW 가 일치하는지 DB 에서 찾아본 후 로그인

### 3. 운동 내용 기록

- 날짜별 운동 기록 추가, 삭제 가능

- 회원별 운동기록 DB
infoNum, userID, year, month, day, hour, min, memo, kg, cm

### 4. BMI 계산기

- 사용자의 BMI 지수 계산기

### 5. 운동 타이머

- 운동 시간 체크

## 회고

### 클린코드

- 각자의 파트를 구현한 뒤 연결하기 위해 다른 팀원들의 코드를 이해해야하는 것에 어려움을 느꼈습니다. 그 당시에는 회의를 통해서 이 문제를 해결하고 했습니다. 그래서 새벽에 시간을 내어 서로의 코드를 설명하여 기능 구현의 흐름을 파악하는 시간을 가졌습니다.
- 지금 생각해보면 코드를 좀 더 명확하게 작성할 수 있는 컨벤션을 지정하고, 중복되는 기능을 함수로 만드는 등의 '클린코드'를 추구했으면 더 효율적으로 해결할 수 있었음을 깨달았습니다.

### Git

- Git 협업 툴을 거의 처음 사용해보아서 어려움을 겪었습니다.
- 팀원들과 Git 협업 툴을 통해 프로젝트를 진행하고자 했지만, Git 에 익숙해지기에는 시간이 조금 걸렸습니다.
- 그래서 파일 업로드 형식으로 진행하다가, Git 을 사용하는 의미가 없다는 생각에 git 책을 사서 간단하게 공부한 뒤, pull-add-commit-push 로 코드를 적용할 수 있다는 것을 정리하여 회의시간에 공유하였습니다.
- 이외에도 커밋 컨벤션에 관심이 생겨 앞으로의 프로젝트에 적용해보고자 합니다.

### 아쉬웠던 점

- 자동로그인을 구현할 때, Sharedpreferences 를 통해 로컬에 파일을 저장하여 기록이 있으면, 자동로그인 처리를 하는 방식으로 구현했는데, 로컬이 아닌 온라인에서 자동로그인을 구현하지 못한 것이 조금 아쉬웠습니다.
- 로컬 프로젝트로 진행하여 직접 배포해보지 못한 것이 아쉬웠습니다.
- 테스트 코드를 작성해보지 못한 것이 아쉬웠습니다.
