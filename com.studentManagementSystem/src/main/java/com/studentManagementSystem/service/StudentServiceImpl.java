package com.studentManagementSystem.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentManagementSystem.exception.CustomerNotFoundException;
import com.studentManagementSystem.exception.UserAlreadyExists;
import com.studentManagementSystem.model.Address;
import com.studentManagementSystem.model.Course;
import com.studentManagementSystem.model.CurrentUserSession;
import com.studentManagementSystem.model.Student;
import com.studentManagementSystem.model.User;
import com.studentManagementSystem.repository.AddressDao;
import com.studentManagementSystem.repository.CourseDao;
import com.studentManagementSystem.repository.CurrentUserSessionDao;
import com.studentManagementSystem.repository.StudentDao;
import com.studentManagementSystem.repository.UserDao;
import com.studentManagementSystem.repository.StudentDao;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentDao sDao;
	
	@Autowired
	private AddressDao aDao;
	
	@Autowired
	private CourseDao cDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CurrentUserSessionDao userSessionDao;
	

	@Override
	public Student addStudent(Student student) {
		
		List<Address> addresses= student.getAddresslist();
		
		String mobile= student.getMobileNumber();
		
			User user=  userDao.findByMobile(mobile);
			Student customerIspresent = sDao.findByMobileNumber(student.getMobileNumber());
			if(customerIspresent!=null) throw new UserAlreadyExists("already present with this mobile number");
			if(user!=null) {
				Integer user_Id= user.getUserId();
				
				String currentUserId= userSessionDao.findByUserId(user_Id);
				if(currentUserId!=null) {
					Integer id= Integer.parseInt(currentUserId);				
					System.out.println(id);
					if(addresses.size()!=0) {				
						for(Address address:addresses) {				
								aDao.save(address);
								System.out.println(address);						
						}					
					}				
					return sDao.save(student);	
				}
				else {
					throw new CustomerNotFoundException("Please login");
				}	
			}
			else
				throw new CustomerNotFoundException("Please login");
	}

	@Override
	public Student updateStudent(Student student) {
		
		Integer customerId= student.getStudentId();
		
		Optional<Student> opt = sDao.findById(customerId);
		
		Student customerIspresent = sDao.findByMobileNumber(student.getMobileNumber());
//		if(customerIspresent==null) throw new UserAlreadyExists("already present with this mobile number");
		if(customerIspresent!=null) {
			if(opt.isPresent()) {
				
				Student optCustomer= opt.get();
				List<Address> addresses= optCustomer.getAddresslist();
				if(addresses.size()!=0) {
					for(Address address:addresses) {
//						Integer addressId= address.getAddressId();
						aDao.save(address);
					}
				}
				 return  sDao.save(student);
			}
			else
				throw new CustomerNotFoundException("Invalid Customer input.");
		}
		else
			throw new CustomerNotFoundException("You Can't change Registered Mobile Number");
	}

	@Override
	public Student removeStudent(Student student) {
		
		Integer customerId= student.getStudentId();
		
		Optional<Student> opt = sDao.findById(customerId);
		
		if(opt.isPresent()) {
			
			Student optCustomer= opt.get();
			
			   sDao.delete(optCustomer);
			   return student;
		}
		else
			
			throw new CustomerNotFoundException("Invalid Customer input.");
		
	}


	@Override
	public Student viewStudent(Integer id) {
		
		Optional<Student> opt=  sDao.findById(id);
		
		Student optCustomer= opt.get();
		
		return optCustomer;
		
	}
	
	
	



	@Override
	public Student addAddress(Address address,Integer studentId) {
	Optional<Student>optional=sDao.findById(studentId);
		if(optional!=null) {
			Student student=optional.get();
			 student.getAddresslist().add(address);
		}
//		return	aDao.save(address);
		
		Optional<Student> optStudent= sDao.findById(studentId);
		return optStudent.get();
		 
	}

	@Override
	public List<Student> viewAllStudentByName(String name) {
		
		List<Student> studentsList= sDao.findByName(name);
		
		if(studentsList.size()>0) {
			System.out.println(studentsList);
			return studentsList;
		}
		
		else throw new CustomerNotFoundException("No Student present with this name "+name);
	}
	

	@Override
	public List<Course> viewAllCourseByStudentId(Integer studentId, String uniqueId) {
			Optional<Student> optStudent= sDao.findById(studentId);
		
		if(optStudent.isPresent()) {
			
			CurrentUserSession currentUserSession=  userSessionDao.findByUniqueId(uniqueId);
			if(currentUserSession!=null) {
				return optStudent.get().getCourses();
			}
			else throw new CustomerNotFoundException("please login...!!!");
			
		}
		
		else  throw new CustomerNotFoundException("invalid credetial...!!!");
	}

	
	@Override
	public Student deleteCourseFromStudent(String courseName,Integer studentId) {
		
		Course course= cDao.findByCourseName(courseName);
		
		Optional<Student>optStudent= sDao.findById(studentId);
		
		if(course!=null && optStudent.isPresent()){
			
			Student student= optStudent.get();			
				
			List<Course> courses= student.getCourses();
			
			if(courses.size()>0) {
				
				for(Course c: courses) {
					if(course.equals(c)){
						 cDao.delete(course);
						sDao.save(student);
						return student;
					}
					else throw new CustomerNotFoundException("you are not asigned to this course...!!!");
				}
			}
			
			else throw new CustomerNotFoundException("you are not asigned to this course...!!!");
			
		}
		else throw new CustomerNotFoundException("please enter valid details...!!!");
		
		
		throw new CustomerNotFoundException("please enter valid credential...!!!");
	}
	

}
