package connectiontest;

import java.sql.Connection;
import java.sql.SQLException;

import com.zr.teacherSystem.utils.TxDBUtils;

public class Test {

	public static void main(String[] args) throws SQLException {

		Connection conn = TxDBUtils.getSource().getConnection();

		System.out.println(conn);
		conn.close();
	}

}
