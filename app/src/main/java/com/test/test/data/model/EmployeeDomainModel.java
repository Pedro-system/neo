package com.test.test.data.model;

import java.util.Objects;

public class EmployeeDomainModel
{
   private Data data;
   private String message;
   private String status;

   public EmployeeDomainModel()
   {

   }
   public EmployeeDomainModel(Data data, String message, String status)
   {
      this.data = data;
      this.message = message;
      this.status = status;
   }

   public Data getData()
   {
      return data;
   }

   public void setData(Data data)
   {
      this.data = data;
   }

   public String getMessage()
   {
      return message;
   }

   public void setMessage(String message)
   {
      this.message = message;
   }

   public String getStatus()
   {
      return status;
   }

   public void setStatus(String status)
   {
      this.status = status;
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
      EmployeeDomainModel that = (EmployeeDomainModel) o;
      return Objects.equals(data, that.data) && Objects.equals(
              message, that.message) && Objects.equals(status, that.status);
   }

   @Override
   public int hashCode()
   {
      return Objects.hash(data, message, status);
   }
}