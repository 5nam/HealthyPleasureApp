# HealthyPleasureApp
건강하게 기록하는 어플

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
