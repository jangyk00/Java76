[DB 모델링]
0. 개요
- 데이터 중복 ==> 데이터가 일치하지 않는 문제 ==> 업무에 문제 발생 
- 중복 데이터를 제거 ==> 데이터를 일치시킨다. ==> 무결성을 유지 
- 용어
  key : 데이터를 구분하기 위한 컬럼들의 집합
  candidate key(후보키, 최소키) : key 중에서 최소 후보자를 골라낸 것
  primary key(주 키) : DBMS 관리자가 후보키 중에서 선택한 키.
  entity : 테이블
  record : 테이블에 저장된 한 데이터 = row
  column : 테이블의 한 속성 = attribute
  not null : 빈 데이터를 허용하지 않음.
  foreign key : 다른 테이블의 주 키 값을 저장하는 컬럼.

1. 테이블 식별 및 정의
  - 컬럼을 식별
  - 주키를 지정 => 모든 테이블은 반드시 주 키가 있어야 한다.
             => 주 키가 될 만한게 없으면 임의의 키를 생성하라. 보통 일련번호 컬럼을 추가한다.

2. 정규화 
1) 제1정규화
  - 중복 컬럼이나 데이터가 있다면 별도의 테이블로 분리하고 부모-자식 관계를 맺는다.
  
2) 제2정규화
  - 주 키가 여러 개의 컬럼으로 구성되었을 때, 나머지 모든 일반 컬럼은 주키 컬럼 모두에 종속되어야 한다.
    만약 종속되지 않는 일반 컬럼이 있다면 별도 테이블로 분리하여 부모-자식 관계를 맺는다.

3) 제3정규화
  - 주 키가 단일 컬럼으로 구성되었을 때, 주 키에 종속되지 않고 다른 일반 컬럼에 종소된 데이터가 
    있다면 별도 테이블로 분리하여 부모-자식 관계를 맺는다.
    
3. 논리 모델을 물리 모델로 전환
1) 다 대 다 관계를 해소한다.
  - 관계를 나타내는 테이블을 생성한 후 부모-자식 관계를 맺는다.

2) 일반 용어로 된 테이블 및 컬럼 이름을 DBMS에 맞게끔 변경한다.
  - 예) first name ==> FRT_NM

3) 컬럼의 도메인을 정의한다. 
  - 컬럼들을 유사한 타입으로 묶어 분류하여 적용한다. 
    ==> 일관성있게 컬럼의 타입을 관리할 수 있다.
    
4) NULL 허용 여부 지정
  - 필수 입력 컬럼을 지정한다.
  
5) 기본값 설정
  - 레코드를 생성할 때 기본적으로 들어갈 값을 설정한다.  
    
4. 인덱스 컬럼을 지정한다.
1) 후보키(candidate key) 컬럼을 유니크(unique) 컬럼으로 지정한다.
  - 후보키 컬럼도 PK처럼 중복되어서는 안되는 컬럼이기 때문이다.
  
2) 검색할 때 조건으로 자주 사용하는 컬럼은 인덱스 컬럼으로 지정한다.
  - 데이터가 입력, 변경, 삭제될 때 마다 인덱스 테이블을 갱신한다.
    => 입력,변경,삭제가 자주 발생할 경우 질의 속도가 떨어진다.
    => 보통 조회를 위주로 하는 테이블에 사용하라!
  
5. 포워드 엔지니어링 수행
  - 자동 증가 컬럼 설정하기 
  - 모델링 결과를 가지고 SQL 문을 자동 생성한다.
  - 참고: 리버스 엔지니어링(reverse engineering)
         => DBMS 테이블 정보를 읽어 모델링을 생성.  
  
  
* 관계
1 : 1       => 부모 -|-----|- 자식
1 : 0..1    => 부모 -|----O|- 자식 
1 : 1..*    => 부모 -|-----K- 자식
1 : 0..*    => 부모 -|----OK- 자식 
  
* 비식별 관계(non-identifying)와 식별 관계(identifying)
1) 비식별 관계 => FK != PK
   - 관계를 나타내는 컬럼(FK)이 식별자(PK)로 사용되지 않는다.
   - 표기법
     -|.......|-
     
2) 식별 관계 => FK == PK
   - 관계를 나타내는 컬럼(FK)이 해당 테이블의 식별자(PK)로 사용될 때    
   - 표기법
     -|-------|-
  
  