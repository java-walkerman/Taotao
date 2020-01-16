package org.taotao.common.helper.CommonHelper;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 注意：在实体中必须存在无参的构造方法，否则转换时会有如下异常;
 * 
 * @author hydra
 *
 */
public class JsonHelper {
	public static ObjectMapper objectMapper;

	/**
	 * 使用泛型方法，把json字符串转换为相应的JavaBean对象。
	 * (1)转换为普通JavaBean：readValue(json,Student.class)
	 * (2)转换为List,如List<Student>,将第二个参数传递为Student
	 * [].class.然后使用Arrays.asList();方法把得到的数组转换为特定类型的List
	 * 
	 * @param jsonStr
	 * @param valueType
	 * @return
	 */
	public static <T> T readValue(String jsonStr, Class<T> valueType) {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}

		try {
			return objectMapper.readValue(jsonStr, valueType);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * json数组转List
	 * 
	 * @param jsonStr
	 * @param valueTypeRef
	 * @return
	 */
	public static <T> T readValue(String jsonStr, TypeReference<T> valueTypeRef) {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}

		try {
			return objectMapper.readValue(jsonStr, valueTypeRef);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 把JavaBean转换为json字符串
	 * 
	 * @param object
	 * @return
	 */
	public static String toJSon(Object object) {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}

		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static class UserBean {
		private int userId;
		private String userName;
		private String password;
		private String email;

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		@Override
		public String toString() {
			return "UserBean [userId=" + userId + ", userName=" + userName + ", password=" + password + ", email="
					+ email + "]";
		}

		public UserBean(int userId, String userName, String password, String email) {
			super();
			this.userId = userId;
			this.userName = userName;
			this.password = password;
			this.email = email;
		}

		public UserBean() {
			super();
		}
	}

	public static class DeptBean {
		private int deptId;
		private String deptName;
		private List<UserBean> userBeanList;

		public int getDeptId() {
			return deptId;
		}

		public void setDeptId(int deptId) {
			this.deptId = deptId;
		}

		public String getDeptName() {
			return deptName;
		}

		public void setDeptName(String deptName) {
			this.deptName = deptName;
		}

		public List<UserBean> getUserBeanList() {
			return userBeanList;
		}

		public void setUserBeanList(List<UserBean> userBeanList) {
			this.userBeanList = userBeanList;
		}

		@Override
		public String toString() {
			String userBeanListString = "";
			for (UserBean userBean : userBeanList) {
				userBeanListString += userBean.toString() + "\n";
			}

			return "DeptBean [deptId=" + deptId + ", deptName=" + deptName + ", \nuserBeanListString="
					+ userBeanListString + "]";
		}

		public DeptBean(int deptId, String deptName, List<UserBean> userBeanList) {
			super();
			this.deptId = deptId;
			this.deptName = deptName;
			this.userBeanList = userBeanList;
		}

		public DeptBean() {
			super();
		}

	}

	public static void main(String[] args) {

//		JsonHelper JacksonUtil = new JsonHelper();
		UserBean userBean1 = new UserBean(1, "liubei", "123", "liubei@163.com");
		UserBean userBean2 = new UserBean(2, "guanyu", "123", "guanyu@163.com");
		UserBean userBean3 = new UserBean(3, "zhangfei", "123", "zhangfei@163.com");

		List<UserBean> userBeans = new ArrayList<>();
		userBeans.add(userBean1);
		userBeans.add(userBean2);
		userBeans.add(userBean3);

		DeptBean deptBean = new DeptBean(1, "sanguo", userBeans);
		// 对象转json
		String userBeanToJson = JsonHelper.toJSon(userBean1);
		String deptBeanToJson = JsonHelper.toJSon(deptBean);

		System.out.println("deptBean to json:" + deptBeanToJson);
		System.out.println("userBean to json:" + userBeanToJson);

		// json转字符串
		UserBean jsonToUserBean = JsonHelper.readValue(userBeanToJson, UserBean.class);
		DeptBean jsonToDeptBean = JsonHelper.readValue(deptBeanToJson, DeptBean.class);

		System.out.println("json to DeptBean" + jsonToDeptBean.toString());
		System.out.println("json to UserBean" + jsonToUserBean.toString());

		// List 转json字符串
		String listToJson = JsonHelper.toJSon(userBeans);
		System.out.println("list to json:" + listToJson);

		// 数组json转 List
		List<UserBean> jsonToUserBeans = JsonHelper.readValue(listToJson, new TypeReference<List<UserBean>>() {
		});
		String userBeanString = "";
		for (UserBean userBean : jsonToUserBeans) {
			userBeanString += userBean.toString() + "\n";
		}
		System.out.println("json to userBeans:" + userBeanString);
	}

}
