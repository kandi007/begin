package com.neu.ui;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.neu.dao.UserDaoImpl;
import com.neu.entity.User;

public class StartSystem {

	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {
		while (true) {
			System.out.println("欢迎来到用户管理系统！");
			System.out.println("1.用户登录");
			System.out.println("2.用户注册");
			System.out.println("3.程序退出");

			int n = input.nextInt();
			switch (n) {
			case 1:
				// 用户登陆
				login();
				continue;
			case 2:
				// 用户注册
				Registered();
				continue;
			case 3:
				// 程序退出
				break;
			default:
				//
				break;
			}
			break;
		}

	}

	static UserDaoImpl userDao = new UserDaoImpl();

	// 登陆
	private static void login() throws SQLException {
		System.out.println("用户登陆界面");
		System.out.println("请输入用户名：");
		String username = input.next();
		System.out.println("请输入密码：");
		String password = input.next();
		User user = userDao.login(username, password);

		if (user != null) {
			System.out.println("登陆成功...");
			System.out.println("==========================");
			System.out.println("欢迎登陆主窗体");
			System.out.println(username + "您好！您的权限是：" + user.getRole());

			if (user.getRole().equals("普通用户")) {
				while (true) {
					System.out.println("1.修改自己的信息");
					System.out.println("2.查询自己的信息");
					System.out.println("3.程序退出");

					int n = input.nextInt();
					switch (n) {
					case 1:
						// 修改自己的信息
						user = Modify(user);
						continue;
					case 2:
						// 查询自己的信息
						Inquire(user);
						continue;
					case 3:
						// 程序退出
						break;

					default:
						break;
					}
					break;
				}
			} else {
				while (true) {
					System.out.println("1.添加用户");
					System.out.println("2.删除用户");
					System.out.println("3.修改用户");
					System.out.println("4.查询用户");
					System.out.println("5.程序退出");

					int n = input.nextInt();
					switch (n) {
					case 1:
						// 添加用户
						Add();
						continue;
					case 2:
						// 删除用户
						deleteUser();
						continue;
					case 3:
						// 修改用户
						user = AdminModify();
						continue;
					case 4:
						// 查询用户
						while(true) {
							System.out.println("1.查询全部用户");
							System.out.println("2.根据ID查询用户");
							System.out.println("3.根据姓名查询用户");
			
							System.out.println("请输入要做的操作");
							int n1 = input.nextInt();
							switch(n1) {
							case 1:
								//查询全部用户
								InquireAll();
								break;
							case 2:
								//根据ID查询用户
								InquireById();
								break;
							case 3:
								//根据姓名查询用户
								VagueInquire();
								break;
							}
							break;
						}
						continue;
						
					case 5:
						// 程序退出
						break;
					default:
						break;
					}
				}
				
			}
		} else {
			System.out.println("登陆失败，用户名或密码错误!");
		}
	}

	// 注册
	private static void Registered() throws SQLException {
		System.out.println("用户注册界面");
		System.out.println("==========================");
		System.out.println("请输入您的用户名：");
		String username = input.next();
		System.out.println("请输入您的密码：");
		String password = input.next();
		System.out.println("请输入您的邮箱：");
		String mail = input.next();

		UserDaoImpl userDao = new UserDaoImpl();
		userDao.Registered(username, password, mail);

		System.out.println("用户注册成功");
		System.out.println("欢迎使用neusoft用户管理系统");
		System.out.println("==========================");
	}

	// 普通用户修改自己的信息
	private static User Modify(User user) throws SQLException {
		System.out.println("您现在的信息是：");
		int id = user.getId();
		System.out.println(
				id + " " + user.getUsername() + " " + user.getPassword() + " " + user.getMail() + " " + user.getRole());
		System.out.println("==========================");
		System.out.println("请输入要修改的姓名");
		String newUsername = input.next();
		System.out.println("请输入要修改的密码");
		String newPassword = input.next();
		System.out.println("请输入要修改的邮箱");
		String newMail = input.next();
		user = userDao.Modify(id, newUsername, newPassword, newMail);
		System.out.println("修改成功");
		return user;

	}

	// 普通用户查询自己的信息
	private static void Inquire(User user) {
		System.out.println("您的信息是：");
		System.out.println(user.getId() + " " + user.getUsername() + " " + user.getPassword() + " " + user.getMail()
				+ " " + user.getRole());
	}

	// 管理员添加
	private static void Add() throws SQLException {
		System.out.println("请输入用户名：");
		String username = input.next();
		System.out.println("请输入密码：");
		String password = input.next();
		System.out.println("请输入邮箱：");
		String mail = input.next();

		// UserDaoImpl userDao = new UserDaoImpl();
		userDao.Add(username, password, mail);

		System.out.println("添加用户成功");
	}

	// 管理员用户修改
	private static User AdminModify() throws SQLException {
		System.out.println("请输入要修改的用户的ID号码");
		int id = input.nextInt();
		System.out.println("请输入要修改的用户的用户名");
		String username = input.next();
		System.out.println("请输入要修改的用户的密码");
		String password = input.next();
		System.out.println("请输入要修改的用户的邮箱");
		String mail = input.next();
		System.out.println("请输入要修改的用户的权限(管理员/普通用户)");
		String role = input.next();

		User user = userDao.AdminModify(id, username, password, mail, role);

		System.out.println("修改成功");
		return user;
	}
	//管理员全部查询
	private static void InquireAll() throws SQLException {
		List list = userDao.InquireAll();
		for (Object object : list) {
			User user=(User)object;
			System.out.println(user.getId()+" "+user.getUsername()+" "+user.getPassword()+" "+user.getMail()+" "+user.getRole());
		}
	}
	//根据id查询某一个用户
	private static void InquireById() throws SQLException {
		System.out.println("请输入要查询的ID");
		int n = input.nextInt();
		User user = userDao.InquireById(n);
		System.out.println(user.getId()+" "+user.getUsername()+" "+user.getPassword()+" "+user.getMail()+" "+user.getRole());
	}
	//根据用户名查询
	private static void VagueInquire() throws SQLException {
		System.out.println("请输入要查询的用户名(支持模糊查询)");
		String username=input.next();
		User user=userDao.VagueInquire(username);
		System.out.println(user.getId()+" "+user.getUsername()+" "+user.getPassword()+" "+user.getMail()+" "+user.getRole());
	}
	//删除
	private static void deleteUser() throws SQLException {
		System.out.println("请输入你要删除用户的ID号码");
		int n = input.nextInt();
		userDao.deleteUser(n);
		System.out.println("删除成功");
	}
}
