package com.chenning.springbootlearn.config;

import com.chenning.springbootlearn.crud.model.User;

public abstract class UserContext {

	private static ThreadLocal<User> staffs = new ThreadLocal<>();

	public static void set(User user) {
		staffs.set(user);
	}

	public static User get() {
		return staffs.get();
	}

	public static void clear() {
		staffs.set(null);
		staffs.remove();
	}

}
