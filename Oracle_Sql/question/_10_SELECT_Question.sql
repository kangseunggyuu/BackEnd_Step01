
SELECT * FROM STUDENT;
SELECT * FROM PROFESSOR;

-- 1) 화학과 학생을 검색하라
SELECT sname, major FROM student WHERE major = '화학';

-- 2) 평점이 2.0 미만인 학생을 검색하라
SELECT sname, avr FROM student WHERE avr < 2.0;

-- 3) 관우 학생의 평점을 검색하라
SELECT sname, avr FROM student WHERE sname = '관우'; 

-- 4) 정교수 명단을 검색하라
SELECT pname, orders FROM professor WHERE orders = '정교수';

-- 5) 화학과 소속 교수의 명단을 검색하라
SELECT pname, section FROM professor WHERE section = '화학';

-- 6) 송강 교수의 정보를 검색하라
SELECT * FROM professor WHERE pname = '송강';

-- 7) 학년별로 화학과 학생의 성적을 검색하라
SELECT sname, syear, major, avr FROM student WHERE syear ORDER BY DESC;

-- 8) 2000년 이전에 부임한 교수의 정보를 부임일순으로 검색하라


-- 9) 담당 교수가 없는 과목의 정보를 검색하라


