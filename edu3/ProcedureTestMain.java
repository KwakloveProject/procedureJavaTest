package kh.edu3;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.Callable;
//싹다 넣음
public class ProcedureTestMain 
{
	
	static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("데이타베이스 드라이버 로드 성공");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("데이타베이스 드라이버 로드 실패");
        }
    } // end of static

    public static void main(String[] args) {
        Connection con = null;
        CallableStatement cstmt = null;
        try {
            con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/xe", "hr", "hr");
            cstmt = con.prepareCall("{call PROC01(?,?)}");
            //사용자 입력 부서번호 10 => 10% 인상
            int departmentId = 10;
            double incrementPct = 1.1;
            cstmt.setInt(1, departmentId);
            cstmt.setDouble(2, incrementPct);

            cstmt.executeUpdate();
            System.out.println("잘 실행이 됐다");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}//end of main
