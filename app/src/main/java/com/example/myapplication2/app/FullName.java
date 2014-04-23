package com.example.myapplication2.app;

/**
 * Created by tmmacleo on 4/22/14.
 */
public class FullName {
    private int id;
    private String firstName;
    private String lastName;

    public FullName()
    {
    }

    public FullName(String firstName, String lastName)
    {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public int getId ()
    {
        return id;
    }

    public void setFirstName (String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName (String lastName)
    {
        this.lastName = lastName;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return firstName + " " + lastName;
    }

}
