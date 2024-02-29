package com.Eshopping.view;

import java.util.List;
import java.util.Scanner;

import model.User;

import com.Eshopping.service.UserService;


public class Menu {
	Scanner sc =new Scanner(System.in);
	UserService userservice=new UserService();
	/*
	 * �û���¼
	 */
	public void loginView(){
		System.out.println("��������������������ӭ������̺�̨��½ϵͳ������������������");
		System.out.println("����������������������������1.��¼����������������������������");
		System.out.println("����������������������������2.�˳�����������������������������");
		System.out.println("��ѡ��");
		int result=sc.nextInt();//��ȡ����̨�û������������������.��������
		int num=3;
		if(result==1){
			//�û���¼
			System.out.println("�������˺ţ�");
			String userno=sc.next();//��ȡ��������ַ������˺ţ�
			System.out.println("���������룺");
			String password=sc.next();//��ȡ��������ַ��������룩
			
			UserService userService=new UserService();
			//true:�ɹ���false:ʧ�ܣ�
			User user=userService.login(userno,password);
			if(user!=null){
				System.out.println("��¼�ɹ���");
				//������ҳ
				indexView(user);
			}else{
				while(num>0){
					System.out.println("�˺Ż�������������ԣ��㻹��"+num+"�λ���");
					num--;
					System.out.println("�������˺ţ�");
					String userno_=sc.next();//��ȡ��������ַ������˺ţ�
					System.out.println("���������룺");
					String password_=sc.next();//��ȡ��������ַ��������룩
					
					UserService userService_=new UserService();
					//true:�ɹ���false:ʧ�ܣ�
					User user_=userService.login(userno_,password_);
					if(user_!=null){
						System.out.println("��¼�ɹ���");
						//������ҳ
						indexView(user_);
						break;
					}else if(num==0){
						System.out.println("ϵͳ���˳�");
						break;
					}
				}
				
			}
			

		}else if(result==2){
			//�û��˳�
			System.out.println("�˳��ɹ���ף���������");
		}else{
			System.out.println("ѡ����Ч������ѡ");
			loginView();
		}
		
	}
	/*
	 * ��ҳ
	 */
	public void indexView(User user){
		System.out.println("����������������������ӭ"+user.getUserno()+"�������̺�̨ϵͳ������������������������");
		System.out.println("������������������������1.�û���������������������������������");
		System.out.println("��������������������������������1.1���û��鿴������������������");
		System.out.println("��������������������������������1.2���û��޸ġ�����������������");
		System.out.println("��������������������������������1.3���û�ɾ��������������������");
		System.out.println("��������������������������������1.4���û�����������������������");
		System.out.println("������������������������2.��Ʒ����������������������������������");
		System.out.println("������������������������3.��������������������������������������");
		System.out.println("������������������������4.�˳�������������������������������������");
		System.out.println("��ѡ��Ҫ���еĲ�����");
		
		String result=sc.next();
		if(result.equals("1.1")){
			//�û��鿴
			//List Map
			System.out.println("�Ƿ�Ҫ�ؼ��ʼ�����Ϣ��Y/N����");
			String YorN=sc.next();
			String key="";
			if(YorN.equals("Y")||YorN.equals("y")){
				System.out.println("������ؼ��ʣ�");
				key=sc.next();
			}
			User u2=new User();
			u2.setKey(key);
			List<User> list=userservice.findUserList(u2);
			for(User u:list){
				System.out.println(u.getId()+"\t"+u.getSex()+"\t"+u.getUserno()+"\t"+u.getEmail()+"\t"+u.getPhone()+"\t"+u.getAddress()+"\t"+u.getDept_name());
			}
		
		}else if(result.equals("1.2")){
			//�û��޸ģ��޸����룩
			System.out.println("������Ҫ�޸�������˺�ID��");
			int id=sc.nextInt();
			System.out.println("������Ҫ�޸�����");
			String password=sc.next();
			
			User u=new User();
			u.setId(id);
			u.setPassword(password);
			
			boolean re=userservice.updateUser(u);
			if(re==true){
				System.out.println("�޸ĳɹ���");
			}else{
				System.out.println("�޸�ʧ�ܣ�");
			}
			
		}else if(result.equals("1.3")){
			//�û�ɾ��
			System.out.println("������Ҫɾ�����û�ID��");
			int id=sc.nextInt();
			boolean re=userservice.deleteUser(id);
			if(re){
				System.out.println("ɾ���ɹ�!");
			}else{
				System.out.println("ɾ��ʧ��!");
			}
		}else if(result.equals("1.4")){
			//�û�����(�˺š����롢�Ա����䡢�绰����ַ������)
			System.out.println("�����������û��˺ţ�");
			String userno=sc.next();
			System.out.println("�����������û����룺");//����дһ���û�������֤
			String password=sc.next();
			System.out.println("�����������û��Ա�");
			String sex=sc.next();
			System.out.println("�����������û����䣺");
			String email=sc.next();
			System.out.println("�����������û��绰��");
			String phone=sc.next();
			System.out.println("�����������û���ַ��");
			String address=sc.next();
			System.out.println("�����������û����ţ�");
			String dept_name=sc.next();
			
			User u=new User();
			u.setUserno(userno);
			u.setPassword(password);
			u.setSex(sex);
			u.setEmail(email);
			u.setPhone(phone);
			u.setAddress(address);
			u.setDept_name(dept_name);
			
			boolean re=userservice.addUser(u);
			if(re==true){
				System.out.println("�����û��ɹ���");
			}else{
				System.out.println("�����û�ʧ�ܣ�");
			}
			
		}else if(result.equals("4")){
			//�û��˳�			
		}else{
			System.out.println("ѡ����Ч");
		}
		
		System.out.println("�Ƿ��������������Y/N����");
		String reLogin=sc.next();
		if(reLogin.equals("Y")||reLogin.equals("y")){
			
			indexView(user);	
		}else{
			System.out.println("�˳��ɹ���ף��������죡");
		}
	}
	
	public static void main(String[] args) {
		//��������
		Menu menu=new Menu();
		//������.������(90%���ϵ����)
		menu.loginView();
//		new Menu().loginView();
		
		
	}
}
