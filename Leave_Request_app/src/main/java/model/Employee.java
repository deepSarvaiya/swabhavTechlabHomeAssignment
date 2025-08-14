package model;

public class Employee {
	private int id;
	private String name;
	private String username;
	private String password;
	private int leaveBalance;

	public Employee(String name, String username, String password, int leaveBalance) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.leaveBalance = leaveBalance;
	}

	public Employee() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getLeaveBalance() {
		return leaveBalance;
	}

	public void setLeaveBalance(int leaveBalance) {
		this.leaveBalance = leaveBalance;
	}
}
