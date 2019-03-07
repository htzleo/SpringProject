package com.antra.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class UserEntity {
	//define fields
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id")
		private Integer id;
		
		@Column(name = "name")
		private String name;
		
		@Column(name = "age")
		private Integer age;
		
		@Column(name = "salary")
		private Double salary;	
		//define constructors
		public UserEntity() {
			
		}
		
		public UserEntity(Integer id, String name, Integer age, Double salary) {
			this.id = id;
			this.name = name;
			this.age = age;
			this.salary = salary;
		}
		
		//define getter setter
		
		
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
		public Double getSalary() {
			return salary;
		}
		public void setSalary(Double salary) {
			this.salary = salary;
		}
		
		//define tostring
		@Override
		public String toString() {
			return "User [id=" + id + ", name=" + name + ", age=" + age + ", salary=" + salary + "]";
		}

}
