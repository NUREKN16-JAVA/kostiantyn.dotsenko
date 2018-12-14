package ua.nure.kn.dotsenko.usermanagement;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3278038741672885759L;
	
	public User(long id, String firstName, String lastName, Date dateOfBirth) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(String firstName, String lastName, Date now) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = now;
	}
	@Override
	public String toString() {
		return "User [firstNAme=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + ", id=" + id
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private Long id;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstNAme) {
		this.firstName = firstNAme;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFullName() {
		
		return getFirstName() + ", " + getLastName();
		
	}
	public long getAge() {
		
		Calendar currentTime = Calendar.getInstance();
		Calendar _dateOfBirth = Calendar.getInstance();
		
		_dateOfBirth.setTime(getDateOfBirth());
		
		
		if (_dateOfBirth.after(currentTime)) {
		  throw new IllegalArgumentException("Wrong Date!");
		}
		
		long currentYear = currentTime.get(Calendar.YEAR);
		long yearOfBirth = _dateOfBirth.get(Calendar.YEAR);
		
		long age = currentYear - yearOfBirth;
		
		long currentMonth = currentTime.get(Calendar.MONTH);
		long monthOfBirth = _dateOfBirth.get(Calendar.MONTH);
		
		if (monthOfBirth > currentMonth) {
		  age--;
		} else if (currentMonth == monthOfBirth) {
			long currentDay = currentTime.get(Calendar.DAY_OF_MONTH);
			long dayOfBirth = _dateOfBirth.get(Calendar.DAY_OF_MONTH);
		  
		    if (dayOfBirth > currentDay) {
		    age--;
		    }
		}
	
		return age;
}
}
