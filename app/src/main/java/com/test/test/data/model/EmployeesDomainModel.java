package com.test.test.data.model;

import java.util.List;
import java.util.Objects;

public class EmployeesDomainModel
{
   private List<Data> data;
   private String message;
   private String status;

   public EmployeesDomainModel(List<Data> data, String message, String status)
   {
      this.data = data;
      this.message = message;
      this.status = status;
   }

   public List<Data> getData()
   {
      return data;
   }

   public void setData(List<Data> data)
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
      EmployeesDomainModel that = (EmployeesDomainModel) o;
      return Objects.equals(data, that.data) && Objects.equals(
              message, that.message) && Objects.equals(status, that.status);
   }

   @Override
   public int hashCode()
   {
      return Objects.hash(data, message, status);
   }
}