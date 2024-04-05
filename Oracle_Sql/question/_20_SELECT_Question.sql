--NATURAL JOIN / USING 절로 해결

1) 송강 교수가 강의하는 과목을 검색한다
SELECT pname, section, cname FROM course NATURAL JOIN professor WHERE pname='송강';

2) 화학 관련 과목을 강의하는 교수의 명단을 검색한다
SELECT pname, cname FROM course NATURAL JOIN professor WHERE cname LIKE '%화학';

3) 학점이 2학점인 과목과 이를 강의하는 교수를 검색한다
SELECT pname, st_num, cname FROM course NATURAL JOIN professor WHERE st_num = 2;

4) 화학과 교수가 강의하는 과목을 검색한다
SELECT pname, section, cname FROM course NATURAL JOIN professor WHERE section='화학';

5) 화학과 1학년 학생의 기말고사 성적을 검색한다
SELECT sname, syear, major,cname, result 
FROM score 
NATURAL JOIN student
NATURAL JOIN course
WHERE syear=1 
AND major='화학';

6) 일반화학 과목의 기말고사 점수를 검색한다
SELECT sname, cname, result
FROM student
NATURAL JOIN score
NATURAL JOIN course
WHERE cname='일반화학';


7) 화학과 1학년 학생의 일반화학 기말고사 점수를 검색한다
SELECT sname, syear, major, cname
FROM student
NATURAL JOIN score
NATURAL JOIN course
WHERE syear=1
AND cname='일반화학';

8) 화학과 1학년 학생이 수강하는 과목을 검색한다

