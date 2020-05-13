package project;


public enum Season {
	
	SPRING("spring"), SUMMER("summer"),FALL("fall"), WINTER("winter");
	
	private String season = null;
	
	
	/**
	 * get names for the season
	 * 
	 * @param s	string for name of the season
	
	 */
	private Season(String s) {
		season = s;
	}
	
	/**
	 * getter for season
	 * 
	 * 
	 * @return	season
	 */
	
	public String getSeason() {
		return season;
	}

	public void setSeason(String s) {
		this.season = s;
	}
	

}

