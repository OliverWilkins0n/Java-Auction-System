package AuctionSystem;

import java.io.Serializable;


public class Admin extends User implements Serializable{
	/**
	 * Admin Constructor
	 * @see user
	 *
	 */
	
	public Admin(String username, String password) {
		super(username, password);
	}
}
