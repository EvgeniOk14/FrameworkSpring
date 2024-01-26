package org.example.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
//region Fields
public class Person
{
    private String firstName;
    private String lastName;
    private int age;
    //endregion

    //region Constructors
    public Person(String firstName, String lastName, int age)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
    public Person()
    {
    }
    //endregion

    //region Getter & Setter
    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }
    //endregion

    /** переопределение toString() **/
    @Override
    public String toString()
    {
        return new ToStringBuilder(this)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("age", age)
                .toString();
    }

    /** метод equals **/
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return new EqualsBuilder()
                .append(age, person.age)
                .append(firstName, person.firstName)
                .append(lastName, person.lastName)
                .isEquals();
    }

    /** метод hasCode() **/
    @Override
    public int hashCode()
    {
        return new HashCodeBuilder()
                .append(age)
                .append(firstName)
                .append(lastName)
                .toHashCode();
    }
}
