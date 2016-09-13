package by.epam.hostelbeta.domain.entity;

/**
 * The Class User. POJO for user
 */
public class User {

	/** The user id. */
	private long userId;

	/** The login. */
	private String login;

	/** The password. */
	private String password;

	/** The role. */
	private String role;

	/** The fullname. */
	private String fullname;

	/** The passport. */
	private String passport;

	/** The email. */
	private String email;

	/** The phone. */
	private String phone;

	/** The ban. */
	private boolean ban;

	/** The discount. */
	private boolean discount;

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId
	 *            the new user id
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * Gets the login.
	 *
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Sets the login.
	 *
	 * @param login
	 *            the new login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password
	 *            the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets the role.
	 *
	 * @param role
	 *            the new role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Gets the fullname.
	 *
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * Sets the fullname.
	 *
	 * @param fullname
	 *            the new fullname
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * Gets the passport.
	 *
	 * @return the passport
	 */
	public String getPassport() {
		return passport;
	}

	/**
	 * Sets the passport.
	 *
	 * @param passport
	 *            the new passport
	 */
	public void setPassport(String passport) {
		this.passport = passport;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the phone.
	 *
	 * @param phone
	 *            the new phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Checks if is ban.
	 *
	 * @return true, if is ban
	 */
	public boolean isBan() {
		return ban;
	}

	/**
	 * Sets the ban.
	 *
	 * @param ban
	 *            the new ban
	 */
	public void setBan(boolean ban) {
		this.ban = ban;
	}

	/**
	 * Checks if is discount.
	 *
	 * @return true, if is discount
	 */
	public boolean isDiscount() {
		return discount;
	}

	/**
	 * Sets the discount.
	 *
	 * @param discount
	 *            the new discount
	 */
	public void setDiscount(boolean discount) {
		this.discount = discount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (ban ? 1231 : 1237);
		result = prime * result + (discount ? 1231 : 1237);
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fullname == null) ? 0 : fullname.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((passport == null) ? 0 : passport.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + (int) (userId ^ (userId >>> 32));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		User other = (User) obj;
		if (ban != other.ban) {
			return false;
		}
		if (discount != other.discount) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (fullname == null) {
			if (other.fullname != null) {
				return false;
			}
		} else if (!fullname.equals(other.fullname)) {
			return false;
		}
		if (login == null) {
			if (other.login != null) {
				return false;
			}
		} else if (!login.equals(other.login)) {
			return false;
		}
		if (passport == null) {
			if (other.passport != null) {
				return false;
			}
		} else if (!passport.equals(other.passport)) {
			return false;
		}
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (phone == null) {
			if (other.phone != null) {
				return false;
			}
		} else if (!phone.equals(other.phone)) {
			return false;
		}
		if (role == null) {
			if (other.role != null) {
				return false;
			}
		} else if (!role.equals(other.role)) {
			return false;
		}
		if (userId != other.userId) {
			return false;
		}
		return true;
	}
}
