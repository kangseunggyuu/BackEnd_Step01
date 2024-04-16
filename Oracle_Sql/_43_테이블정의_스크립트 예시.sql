-- [Account가 ncp08이고, 비밀번호가 ncp08인 새로운 사용자를 생성]

--사용자를 생성하는 권한은 system으로 접속해야 가능하다.
--system/oracle : 초기 비밀번호, 당연히 변경가능하다.

-- 기존에 ncp08 사용자가 존재하면 제거해 주세요.
DROP USER ncp08 CASCADE;

-- 새로운 사용자인 ncp08을 생성하고, 비밀번호도 ncp08로 생성하고, 기본 스키마 리소스 사용 권한 부여
CREATE USER ncp08 IDENTIFIED BY ncp08 DEFAULT TABLESPACE users TEMPORARY TABLESPACE temp PROFILE DEFAULT;

-- ncp08한테 권한을 부여한다. 연결, 리소스 관리
GRANT CONNECT, RESOURCE TO ncp08 ;
-- ncp08한테 권한을 부여한다. 뷰/SYNONYM 권한 부여
GRANT CREATE VIEW, CREATE SYNONYM TO ncp08;

-- 새로 생성된 ncp08계정은 Lock이 걸려 있으므로, Unlock을 해야 사용할 수 있다.
ALTER USER ncp08 ACCOUNT UNLOCK;


-----------------------------------------------------------------------


