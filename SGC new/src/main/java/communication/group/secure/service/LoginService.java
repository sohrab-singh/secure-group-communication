package communication.group.secure.service;

import communication.group.secure.dao.LoginDao;

public class LoginService {

	private LoginDao loginDao;

	public boolean validateLogin(String id, String password, String gid) {

		System.out.println("The User Id = " + id + " and Password = "
				+ password + " and Grp Id = " + gid);
		boolean result = false;
		try {
			result = loginDao.getResult(id, password, gid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("The Result = " + result);
		return result;

	}

}
