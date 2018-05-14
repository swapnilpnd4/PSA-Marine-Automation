package com.psdsmres.constant;


public class GlobalConstant {
	
	public enum FileNames {
		TestDataRelativePath("src/test/resources/testdata/"),
		UserProperties("User"),
		TestdataProperties("TestData"),
		AdminTestdataProperties("AdminTestData");
		
		private String value;
		private FileNames(String value) {
			this.value = value;
		}
		
		public String toString() {
			return value;
		}
	}

}
