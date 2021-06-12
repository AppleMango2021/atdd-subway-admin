<p align="center">
    <img width="200px;" src="https://raw.githubusercontent.com/woowacourse/atdd-subway-admin-frontend/master/images/main_logo.png"/>
</p>
<p align="center">
  <img alt="npm" src="https://img.shields.io/badge/npm-%3E%3D%205.5.0-blue">
  <img alt="node" src="https://img.shields.io/badge/node-%3E%3D%209.3.0-blue">
  <a href="https://edu.nextstep.camp/c/R89PYi5H" alt="nextstep atdd">
    <img alt="Website" src="https://img.shields.io/website?url=https%3A%2F%2Fedu.nextstep.camp%2Fc%2FR89PYi5H">
  </a>
  <img alt="GitHub" src="https://img.shields.io/github/license/next-step/atdd-subway-admin">
</p>

<br>

# 지하철 노선도 미션
[ATDD 강의](https://edu.nextstep.camp/c/R89PYi5H) 실습을 위한 지하철 노선도 애플리케이션

<br>

## 🚀 3단계 구간 추가 기능

* 지하철 구간 등록 기능 구현
    * 가능한 시나리오
        1. [X] 노선(`Line`) 등록과 함께 구간(`Section`) 등록
        2. [X] 새로운 구간(`Section`)의 상행역을 상행종점으로 등록 
           
            → 즉, 해당 구간의 상행역이 가장 첫번째 지하철역이 됨
        3. [X] 새로운 구간(`Section`)의 하행역을 하행종점으로 등록
            
            → 즉, 해당 구간의 하행역이 가장 마지막 지하철역이 됨
        4. [X] 새로운 구간('Section')의 상행역을 기존 상행역에 연결 
           
            → 역과 역 사이에 새로운 역이 추가됨
        5. [X] 새로운 구간('Section')의 하행역을 기존 하행역에 연결

           → 역과 역 사이에 새로운 역이 추가됨

    * 제약조건
        1. [X] A역과 C역 사이에 B역과 C역 구간을 등록할 경우, B~C역 구간의 길이가 기존 역 사이의 길이보다 크거나 같으면 
          B역을 등록할 수 없다.
        2. [X] 상행역과 하행역 모두 노선에 등록되어 있다면, 해당 구간은 추가할 수 없다.
        3. [X] 상행역과 하행역 모두 노선에 등록되어 있지 않다면, 해당 구간은 추가할 수 없다.
    
---
## 🚀 2단계 인수 테스트 리팩토링
* 노선 생성 시 종점역(상행, 하행) 정보를 요청 파라미터에 함께 추가
    * 두 종점역은 구간(`Section`)의 형태로 관리되어야 함
        1. [X] 인수테스트(`LineAcceptanceTest`)  수정  
        2. [X] `Section` 클래스 생성 : 출발역, 도착역, 거리 변수포함
        3. [X] `LineRequest`에 변수 추가 : 출발역 Id, 도착역 Id, 거리 
        4. [X] `Line`과 `Section`간 연관관계 추가 
* 노선 조회 시 응답 결과에 역 목록 추가하기
    * 상행역부터 하행역 순으로 정렬되어야 함
        1. [X] `Line`과 `Section`간 연관관계 추가
---
## 🚀 1단계 지하철 노선 관리 
* 지하철 노선 관련 기능의 인수테스트를 작성하기 
    * `LineAcceptanceTest`를 모두 완성시키세요.
        1. [X] 지하철 노선을 생성한다.
        2. [X] 기존에 존재하는 지하철 노선 이름으로 지하철 노선을 생성한다.
        3. [X] 지하철 노선 목록을 조회한다.
        4. [X] 지하철 노선을 조회한다.
        5. [X] 지하철 노선을 수정한다.
        6. [X] 지하철 노선을 제거한다.
* 지하철 노선 관련 기능 구현하기
    * 인수 테스트가 모두 성공할 수 있도록 `LineController`를 통해 요청을 받고 
      처리하는 기능을 구현하세요. 
        1. [x] 지하철 노선 생성    
            * 기존에 존재하는 노선의 이름으로 생성하려는 경우 예외처리 
              → `DuplicateEntityExistsException`
        2. [X] 지하철 노선 목록 조회 
            * 조회하려는 노선이 없는 경우 예외처리 
              → `EntityNotFoundException`   
        4. [X] 지하철 노선 수정
        5. [X] 지하철 노선 제거
* 인수 테스트 리팩토링
    * [X] 인수 테스트의 각 스텝들을 메서드로 분리하여 재사용하세요.

### 힌트 - RestAssured
#### Given 
* 요청을 위한 값을 설정 (header, content type 등)
* body가 있는 경우 body값을 설정함
#### When
* 요청의 url과 method를 설정
#### Then
* 응답의 결과를 관리
* response를 추출하거나 response값을 검증할 수 있음 
--- 
## 🚀 Getting Started

### Install
#### npm 설치
```
cd frontend
npm install
```
> `frontend` 디렉토리에서 수행해야 합니다.

### Usage
#### webpack server 구동
```
npm run dev
```
#### application 구동
```
./gradlew bootRun
```
<br>

## ✏️ Code Review Process
[텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

<br>

## 🐞 Bug Report

버그를 발견한다면, [Issues](https://github.com/next-step/atdd-subway-admin/issues) 에 등록해주세요 :)

<br>

## 📝 License

This project is [MIT](https://github.com/next-step/atdd-subway-admin/blob/master/LICENSE.md) licensed.
