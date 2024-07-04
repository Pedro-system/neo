package com.test.test.data.model;

import java.util.Objects;

public class Data
{
   public static String EMPLOYEE_AGE=       "EMPLOYEE_AGE";
   public static String EMPLOYEE_NAME=      "EMPLOYEE_NAME";
   public static String EMPLOYEE_SALARY=    "EMPLOYEE_SALARY";
   public static String ID=                 "ID";
   public static String PROFILE_IMAGE=      "PROFILE_IMAGE";

   private int employee_age;
   private String employee_name;
   private int employee_salary;
   private int id;
   private String profile_image;

   public Data()
   {

   }
   public Data(
           int employee_age, String employee_name, int employee_salary, int id,
           String profile_image)
   {
      this.employee_age = employee_age;
      this.employee_name = employee_name;
      this.employee_salary = employee_salary;
      this.id = id;
      this.profile_image = profile_image;
   }

   @Override
   public boolean equals(Object o)
   {
      if (this == o)
      {
         return true;
      }
      if (o == null || getClass() != o.getClass())
      {
         return false;
      }
      Data data = (Data) o;
      return employee_age == data.employee_age && employee_salary == data.employee_salary
             && id == data.id && Objects.equals(employee_name, data.employee_name)
             && Objects.equals(profile_image, data.profile_image);
   }

   @Override
   public int hashCode()
   {
      return Objects.hash(employee_age, employee_name, employee_salary, id, profile_image);
   }

   public int getEmployee_age()
   {
      return employee_age;
   }

   public void setEmployee_age(int employee_age)
   {
      this.employee_age = employee_age;
   }

   public String getEmployee_name()
   {
      return employee_name;
   }

   public void setEmployee_name(String employee_name)
   {
      this.employee_name = employee_name;
   }

   public int getEmployee_salary()
   {
      return employee_salary;
   }

   public void setEmployee_salary(int employee_salary)
   {
      this.employee_salary = employee_salary;
   }

   public int getId()
   {
      return id;
   }

   public void setId(int id)
   {
      this.id = id;
   }

   public String getProfile_image()
   {
      return profile_image;
   }

   public void setProfile_image(String profile_image)
   {
      this.profile_image = profile_image;
   }
}