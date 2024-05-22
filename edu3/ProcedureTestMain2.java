package kh.edu3;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.concurrent.Callable;
//1개는in 1개는out
public class ProcedureTestMain2 
{
	
	   static {
		      try {
		         Class.forName("oracle.jdbc.driver.OracleDriver");
		         System.out.println("데이터베이스 성공");
		      } catch (ClassNotFoundException e) {
		         e.printStackTrace();
		      }
		   }
	   
	public static void main(String[] args) {
        Connection con = null;
        CallableStatement cstmt =null;
        try {
            con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/xe","hr", "hr");
            cstmt= con.prepareCall("{CALL proc02(?,?)}");
            //사용자입력 부서번호 10 => 10% 인상
            int empId=198;
            cstmt.setInt(1, empId);
            cstmt.registerOutParameter(2, Types.VARCHAR);
            
            //실행하는 명령
            cstmt.executeUpdate();
            System.out.println("잘 실행이 됐다");
            String message = cstmt.getString(2);
            System.out.println("리턴되는값"+message);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("connection 실패");
        } finally {
            try{
                if(con != null)
                {
                    con.close();
                }
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
	
	} 
}//end of main
