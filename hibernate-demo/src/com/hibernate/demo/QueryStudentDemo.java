package com.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				                 .configure("hibernate.cfg.xml")
				                 .addAnnotatedClass(Student.class)
				                 .buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();

	try {
		
		
		//start a transaction
		session.beginTransaction();
		
		//query students
		List<Student> theStudents = session.createQuery("from Student").list();
		
		//display the students
		displayStudents(theStudents);
		
		//query students
		theStudents = session.createQuery("from Student s where s.lastName='sri'").list();
		
		//display the students
		System.out.println("\n\nStudents who have last name of sri");
		displayStudents(theStudents);
		
		
		//query students: lastname='sri' or firstname ='mahi'
		theStudents=
				session.createQuery("from Student s where"
						+" s.lastName='sri' OR s.firstName='mahi'").list();
		
		System.out.println("\n\nStudents who have last name of sri OR first name of mahi");
		displayStudents(theStudents);
		
	
		//query students where email like '%gmail.com'
		theStudents = session.createQuery("from Student s where" + " s.email LIKE '%@gmail.com'").list();
		displayStudents(theStudents);
		
		System.out.println("\n\nStudents who email ends with @gmail.com");

		//commit transaction
		session.getTransaction().commit();
		
		System.out.println("Done!");

	}
	finally{
		factory.close();
	}
}

	private static void displayStudents(List<Student> theStudents) {
		//display the students
		for(Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}
}
