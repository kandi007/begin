package com.neu.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neu.entity.User;

public class UserDaoImpl {
	// 用户登陆，检验用户名和密码是否正确，若正确返回用户对象，若不正确返回空
	// 用户注册
	public int Registered(String username, String password, String mail) throws SQLException {
		String sql = "insert into  myuser values(null,?,?,?,'普通用户') ";
		Object[] params = { username, password, mail };
		int n = JDBC_Util.executeUpdate(sql, params);
		return n;

	}

	// 登陆
	public User login(String username, String password) throws SQLException {
		String sql = "select * from myuser where username = ? and password= ?";
		Connection connection = JDBC_Util.getConnection();
		Object[] params = { username, password };
		ResultSet rs = JDBC_Util.executeQuery(connection, sql, params);
		User user = null;

		if (rs.next()) {
			int id = rs.getInt("id");
			String mail = rs.getString("mail");
			String role = rs.getString("role");

			user = new User(id, username, password, mail, role);
		}

		rs.close();

		JDBC_Util.closeConnection(connection);

		return user;
	}

	// 普通用户修改自己的信息
	public User Modify(int id, String username, String password, String mail) throws SQLException {
		String sql = "update myuser set username=?,password=?,mail=? where id=?";
		Object[] params = { username, password, mail, id };
		int n = JDBC_Util.executeUpdate(sql, params);
		User u = new User();
		if (n == 1) {
			u = new User(id, username, password, mail, "普通用户");
		} else {
			u = null;
		}
		return u;
	}

	// 管理员添加
	public int Add(String username, String password, String mail) throws SQLException {
		String sql = "insert into  myuser values(null,?,?,?,'普通用户') ";
		Object[] params = { username, password, mail };
		int n = JDBC_Util.executeUpdate(sql, params);
		return n;
	}

	// 管理员修改
	public User AdminModify(int id, String username, String password, String mail, String role) throws SQLException {
		String sql = "update myuser set username=?,password=?,mail=?,role=? where id=?";
		Object[] params = { username, password, mail, role, id };
		User u = new User();
		int n = JDBC_Util.executeUpdate(sql, params);
		if (n == 1) {
			u = new User(id, username, password, mail, role);
		} else {
			u = null;
		}
		return u;
	}
	//管理员全部查询
	public List InquireAll() throws SQLException {
		User user=new User();
		List<User> list=new ArrayList<>();
		String sql="select * from myuser";
		Connection connection = JDBC_Util.getConnection();
		ResultSet rs = JDBC_Util.executeQuery(connection, sql,null);
		
		while (rs.next()) {
			int id = rs.getInt("id");
			String username = rs.getString("username");
			String password = rs.getString("password");
			String mail = rs.getString("mail");
			String role = rs.getString("role");

			user = new User(id, username, password, mail, role);
			
			list.add(user);
		}

		rs.close();

		JDBC_Util.closeConnection(connection);

		return list;
	}
	//根据id查询某一个用户
	public User InquireById(int id) throws SQLException {
		User user=new User();
		String sql="select * from myuser where id=?";
		Object[] params = {id};
		Connection connection = JDBC_Util.getConnection();
		ResultSet rs = JDBC_Util.executeQuery(connection,sql,params);
		
		if(rs.next()) {
			String username = rs.getString("username");
			String password = rs.getString("password");
			String mail = rs.getString("mail");
			String role = rs.getString("role");

			user = new User(id,username,password,mail,role);
			
		}

		rs.close();

		JDBC_Util.closeConnection(connection);

		return user;
	}
	//c
	public User VagueInquire(String username2) throws SQLException {
		User user=new User();
		String sql="select * from myuser where username like '%"+username2+"%'";
		Connection connection = JDBC_Util.getConnection();
		ResultSet rs = JDBC_Util.executeQuery(connection,sql,null);
		
		if(rs.next()) {
			int id = rs.getInt("id");
			String username1 = rs.getString("username");
			String password = rs.getString("password");
			String mail = rs.getString("mail");
			String role = rs.getString("role");

			user = new User(id,username1,password,mail,role);
			
		}

		rs.close();

		JDBC_Util.closeConnection(connection);

		return user;
	}
	//删除用户
	public int deleteUser(int id2) throws SQLException {
		String sql = "delete from myuser where id=? ";
		Object[] params = {id2};
		int n = JDBC_Util.executeUpdate(sql,params);
		
		return n;
	}
}
