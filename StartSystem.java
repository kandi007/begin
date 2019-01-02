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
			System.out.println("��ӭ�����û�����ϵͳ��");
			System.out.println("1.�û���¼");
			System.out.println("2.�û�ע��");
			System.out.println("3.�����˳�");

			int n = input.nextInt();
			switch (n) {
			case 1:
				// �û���½
				login();
				continue;
			case 2:
				// �û�ע��
				Registered();
				continue;
			case 3:
				// �����˳�
				break;
			default:
				//
				break;
			}
			break;
		}

	}

	static UserDaoImpl userDao = new UserDaoImpl();

	// ��½
	private static void login() throws SQLException {
		System.out.println("�û���½����");
		System.out.println("�������û�����");
		String username = input.next();
		System.out.println("���������룺");
		String password = input.next();
		User user = userDao.login(username, password);

		if (user != null) {
			System.out.println("��½�ɹ�...");
			System.out.println("==========================");
			System.out.println("��ӭ��½������");
			System.out.println(username + "���ã�����Ȩ���ǣ�" + user.getRole());

			if (user.getRole().equals("��ͨ�û�")) {
				while (true) {
					System.out.println("1.�޸��Լ�����Ϣ");
					System.out.println("2.��ѯ�Լ�����Ϣ");
					System.out.println("3.�����˳�");

					int n = input.nextInt();
					switch (n) {
					case 1:
						// �޸��Լ�����Ϣ
						user = Modify(user);
						continue;
					case 2:
						// ��ѯ�Լ�����Ϣ
						Inquire(user);
						continue;
					case 3:
						// �����˳�
						break;

					default:
						break;
					}
					break;
				}
			} else {
				while (true) {
					System.out.println("1.����û�");
					System.out.println("2.ɾ���û�");
					System.out.println("3.�޸��û�");
					System.out.println("4.��ѯ�û�");
					System.out.println("5.�����˳�");

					int n = input.nextInt();
					switch (n) {
					case 1:
						// ����û�
						Add();
						continue;
					case 2:
						// ɾ���û�
						deleteUser();
						continue;
					case 3:
						// �޸��û�
						user = AdminModify();
						continue;
					case 4:
						// ��ѯ�û�
						while(true) {
							System.out.println("1.��ѯȫ���û�");
							System.out.println("2.����ID��ѯ�û�");
							System.out.println("3.����������ѯ�û�");
			
							System.out.println("������Ҫ���Ĳ���");
							int n1 = input.nextInt();
							switch(n1) {
							case 1:
								//��ѯȫ���û�
								InquireAll();
								break;
							case 2:
								//����ID��ѯ�û�
								InquireById();
								break;
							case 3:
								//����������ѯ�û�
								VagueInquire();
								break;
							}
							break;
						}
						continue;
						
					case 5:
						// �����˳�
						break;
					default:
						break;
					}
				}
				
			}
		} else {
			System.out.println("��½ʧ�ܣ��û������������!");
		}
	}

	// ע��
	private static void Registered() throws SQLException {
		System.out.println("�û�ע�����");
		System.out.println("==========================");
		System.out.println("�����������û�����");
		String username = input.next();
		System.out.println("�������������룺");
		String password = input.next();
		System.out.println("�������������䣺");
		String mail = input.next();

		UserDaoImpl userDao = new UserDaoImpl();
		userDao.Registered(username, password, mail);

		System.out.println("�û�ע��ɹ�");
		System.out.println("��ӭʹ��neusoft�û�����ϵͳ");
		System.out.println("==========================");
	}

	// ��ͨ�û��޸��Լ�����Ϣ
	private static User Modify(User user) throws SQLException {
		System.out.println("�����ڵ���Ϣ�ǣ�");
		int id = user.getId();
		System.out.println(
				id + " " + user.getUsername() + " " + user.getPassword() + " " + user.getMail() + " " + user.getRole());
		System.out.println("==========================");
		System.out.println("������Ҫ�޸ĵ�����");
		String newUsername = input.next();
		System.out.println("������Ҫ�޸ĵ�����");
		String newPassword = input.next();
		System.out.println("������Ҫ�޸ĵ�����");
		String newMail = input.next();
		user = userDao.Modify(id, newUsername, newPassword, newMail);
		System.out.println("�޸ĳɹ�");
		return user;

	}

	// ��ͨ�û���ѯ�Լ�����Ϣ
	private static void Inquire(User user) {
		System.out.println("������Ϣ�ǣ�");
		System.out.println(user.getId() + " " + user.getUsername() + " " + user.getPassword() + " " + user.getMail()
				+ " " + user.getRole());
	}

	// ����Ա���
	private static void Add() throws SQLException {
		System.out.println("�������û�����");
		String username = input.next();
		System.out.println("���������룺");
		String password = input.next();
		System.out.println("���������䣺");
		String mail = input.next();

		// UserDaoImpl userDao = new UserDaoImpl();
		userDao.Add(username, password, mail);

		System.out.println("����û��ɹ�");
	}

	// ����Ա�û��޸�
	private static User AdminModify() throws SQLException {
		System.out.println("������Ҫ�޸ĵ��û���ID����");
		int id = input.nextInt();
		System.out.println("������Ҫ�޸ĵ��û����û���");
		String username = input.next();
		System.out.println("������Ҫ�޸ĵ��û�������");
		String password = input.next();
		System.out.println("������Ҫ�޸ĵ��û�������");
		String mail = input.next();
		System.out.println("������Ҫ�޸ĵ��û���Ȩ��(����Ա/��ͨ�û�)");
		String role = input.next();

		User user = userDao.AdminModify(id, username, password, mail, role);

		System.out.println("�޸ĳɹ�");
		return user;
	}
	//����Աȫ����ѯ
	private static void InquireAll() throws SQLException {
		List list = userDao.InquireAll();
		for (Object object : list) {
			User user=(User)object;
			System.out.println(user.getId()+" "+user.getUsername()+" "+user.getPassword()+" "+user.getMail()+" "+user.getRole());
		}
	}
	//����id��ѯĳһ���û�
	private static void InquireById() throws SQLException {
		System.out.println("������Ҫ��ѯ��ID");
		int n = input.nextInt();
		User user = userDao.InquireById(n);
		System.out.println(user.getId()+" "+user.getUsername()+" "+user.getPassword()+" "+user.getMail()+" "+user.getRole());
	}
	//�����û�����ѯ
	private static void VagueInquire() throws SQLException {
		System.out.println("������Ҫ��ѯ���û���(֧��ģ����ѯ)");
		String username=input.next();
		User user=userDao.VagueInquire(username);
		System.out.println(user.getId()+" "+user.getUsername()+" "+user.getPassword()+" "+user.getMail()+" "+user.getRole());
	}
	//ɾ��
	private static void deleteUser() throws SQLException {
		System.out.println("��������Ҫɾ���û���ID����");
		int n = input.nextInt();
		userDao.deleteUser(n);
		System.out.println("ɾ���ɹ�");
	}
}
